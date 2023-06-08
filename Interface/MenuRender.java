package Interface;

import java.awt.event.KeyEvent;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MenuRender {

    public MenuRender(Map<String, List<String>> menuData, int consoleLines,
            boolean isEscActive, boolean showHelpControl, String frameText, String prefix, String prefixMark) {
        this._menuData = menuData;
        this._consoleLines = consoleLines;
        this._isEscActive = isEscActive;
        this._showHelpControl = showHelpControl;
        this._frameText = frameText;
        this._prefix = prefix == "" ? "> " : prefix;
        this._prefixMark = prefixMark == "" ? " ".repeat(prefix.length()) : prefixMark;
        this._pagesMap = SplitDataToPages(_menuData, _consoleLines, HEADER_LINE_COUNT);
    }

    private Map<String, List<String>> _menuData;
    private int _consoleLines;
    private boolean _isEscActive;
    private boolean _showHelpControl;
    private String _frameText;
    private String _prefix;
    private String _prefixMark;
    private ConsoleManager _consoleManager = new ConsoleManager(false);
    private Set<PageData> _pagesMap;
    private final int HEADER_LINE_COUNT = 2;

    public int StartRenderMenu(int index) {
        int largestKey = GetLargestKeyTasks();
        _consoleLines = largestKey > _consoleLines ? largestKey : _consoleLines;

        PageData page = null;
        try {
            page = GetCheckCoordinates(index);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int pageCount = _pagesMap.size();

        ConsoleManager.HideCursor(true);
        while (true) {
            DrawMenu(page, pageCount);
            int key = _consoleManager.GetKeyEvent();
            switch (key) {
                case KeyEvent.VK_ENTER:
                    ConsoleManager.HideCursor(false);
                    return page.currentLineIndex + page.startLineIndex + 1;
                case KeyEvent.VK_UP:
                    if (page.currentLineIndex > 0) {
                        page.currentLineIndex -= 1;
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (page.currentLineIndex < LineCount(page.pageData) - 1) {
                        page.currentLineIndex += 1;
                    }
                    break;
                case KeyEvent.VK_LEFT:
                    if (page.pageId > 1) {
                        page = GetNexPage(page, _pagesMap, -1, _prefix.length());
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (page.pageId < pageCount) {
                        page = GetNexPage(page, _pagesMap, 1, _prefix.length());
                    }
                    break;
                case KeyEvent.VK_ESCAPE:
                    if (_isEscActive) {
                        ConsoleManager.HideCursor(false);
                        return 0;
                    }
                    break;
            }
        }
    }

    private int GetLargestKeyTasks() {
        int largestKeyTasks = 0;
        for (String key : _menuData.keySet()) {
            int lengthKeyTasks = _menuData.get(key).size();
            largestKeyTasks = lengthKeyTasks > largestKeyTasks ? lengthKeyTasks : largestKeyTasks;
        }
        return largestKeyTasks + HEADER_LINE_COUNT + 1;
    }

    private PageData GetCheckCoordinates(int index) throws Exception {
        for (PageData page : _pagesMap) {
            if (index >= page.startLineIndex && index < page.startLineIndex + page.linesCount) {
                page.currentLineIndex = index - page.startLineIndex;
                return page;
            }
        }
        throw new Exception("Page not found!");
    }

    private void DrawMenu(PageData page, int pagesCount) {
        int blockIdCount = 0;
        int largestLine = GetLargestLineLength(page.pageData);
        ConsoleManager.ConsoleClear();
        _consoleManager.PrintText(_frameText);
        for (String key : page.pageData.keySet()) {
            if (blockIdCount > 0) {
                _consoleManager.PrintText();
            }
            _consoleManager.PrintText(key);
            List<String> pageData = page.pageData.get(key);
            for (int i = 0; i < pageData.size(); i++) {
                i += blockIdCount;
                boolean isSelected = i == page.currentLineIndex;
                String prToConsole;
                if (_prefixMark.equals("")) {
                    prToConsole = isSelected ? _prefix : " ".repeat(_prefix.length());
                    _consoleManager.PrintText(prToConsole + pageData.get(i));
                } else {
                    prToConsole = isSelected ? pageData.get(i).replace(_prefixMark, _prefix)
                            : pageData.get(i).replace(_prefixMark, " ".repeat(_prefixMark.length()));
                    _consoleManager.PrintText(prToConsole);
                }
            }
            blockIdCount += pageData.size();
        }
        if (_showHelpControl) {
            String strPageNumbers = "";
            if (pagesCount > 1) {
                strPageNumbers = "▪️".repeat(pagesCount);
                String strPageId = String.valueOf(page.pageId);
                for (int i = 0; i < strPageId.length(); i++) {
                    strPageNumbers = strPageNumbers.substring(0, page.pageId - 1 + i) + strPageId.charAt(i)
                            + strPageNumbers.substring(page.pageId + i);
                }
            }
            String indent = " ".repeat(50);
            if (pagesCount > 2) {
                if (page.pageId > 1) {
                    strPageNumbers = "← " + strPageNumbers;
                } else {
                    strPageNumbers = "  " + strPageNumbers;
                }
                if (page.pageId < pagesCount) {
                    strPageNumbers += " →";
                } else {
                    strPageNumbers += "  ";
                }
            }
            _consoleManager.PrintText("\n".repeat(_consoleLines - page.linesCount - page.pageData.size() * 2) + indent
                    + strPageNumbers);
            String padding = "=".repeat(largestLine);
            String pagesSwitchInfo = pagesCount > 1 ? "← → - переключать страницы. " : "";
            _consoleManager.PrintText(padding + "\n↑ ↓ - перемещаться между строками. " + pagesSwitchInfo
                    + "Enter - выбрать задачу. Для выхода нажмите Esc.\n");
        }
        _consoleManager.PrintText();
    }

    private static int GetLargestLineLength(Map<String, List<String>> menuData) {
        int largestLineLength = 0;
        for (String key : menuData.keySet()) {
            List<String> value = menuData.get(key);
            if (value.size() == 0) {
                continue;
            }
            int maxLength = Collections.max(value, Comparator.comparing(String::length)).length();
            if (largestLineLength < maxLength) {
                largestLineLength = maxLength;
            }
        }
        return largestLineLength;
    }

    private static int LineCount(Map<String, List<String>> menuData) {
        int count = 0;
        for (String key : menuData.keySet()) {
            count += menuData.get(key).size();
        }
        return count;
    }

    private static Set<PageData> SplitDataToPages(Map<String, List<String>> menuData, int linesPage,
            int headerLineCount) {
        int pageLineCount = 0;
        int pageFirstIndex = 0;
        int pageId = 1;
        Map<String, List<String>> pageData = new HashMap<>();
        Set<PageData> pagesMap = new HashSet<>();
        int i = 0;
        for (String key : menuData.keySet()) {
            int countLinesKeyTasks = menuData.get(key).size();
            if (countLinesKeyTasks + headerLineCount + pageLineCount >= linesPage) {
                PageData page = new PageData(pageId, pageFirstIndex, pageData);
                pagesMap.add(page);
                pageLineCount = 0;
                pageId += 1;
                pageFirstIndex += page.linesCount;
                pageData.clear();
            }
            pageLineCount += countLinesKeyTasks + 2;
            pageData.put(key, menuData.get(key));
            if (i == menuData.keySet().size() - 1) {
                pagesMap.add(new PageData(pageId, pageFirstIndex, pageData));
            }
            i++;
        }
        return pagesMap;
    }

    private static PageData GetNexPage(PageData page, Set<PageData> pagesMap,
            int step,
            int prefixLength) {
        int currentIndex = page.currentLineIndex;
        final int pageId = page.pageId;
        page = pagesMap.stream().filter(p -> p.pageId == pageId + step).findFirst().get();
        page.currentLineIndex = currentIndex < page.linesCount ? currentIndex : page.linesCount - 1;
        return page;
    }
}
