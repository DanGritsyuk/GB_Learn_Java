import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

public class MenuRender {

    public static int StartRenderMenu(Map<String, List<String>> menuData, int index, int consoleLines,
            boolean isEscActive, boolean showHelpControl, String prefix, String prafixMark) throws IOException {
        final int HEADER_LINE_COUNT = 2;
        int largestKey = GetLargestKeyTasks(menuData) + HEADER_LINE_COUNT + 1;
        consoleLines = largestKey > consoleLines ? largestKey : consoleLines;

        Function<Set<PageData>, PageData> getCheckCoordinates = (_pagesMap) -> {
            for (PageData page : _pagesMap) {
                if (index >= page.startLineIndex && index < page.startLineIndex + page.linesCount) {
                    page.currentLineIndex = index - page.startLineIndex;
                    return page;
                }
            }
            throw new RuntimeException("Page not found!");
        };

        Set<PageData> pagesMap = MenuRender.SplitDataToPages(menuData, consoleLines, HEADER_LINE_COUNT);
        PageData page = getCheckCoordinates.apply(pagesMap);
        int pageCount = pagesMap.size();

        Point2D cursorStartPosition = ConsoleManager.GetCursorCoordinate();
        DrawMenu(page, pageCount, cursorStartPosition, consoleLines,
                showHelpControl, prefix, prafixMark);
        while (true) {
            ConsoleManager.HideCursor(true);
            String key = ConsoleManager.GetKeyEvent();
            switch (key) {
                case "enter":
                    EndDraw(page, cursorStartPosition, prefix.length(), consoleLines);
                    return page.currentLineIndex + page.startLineIndex + 1;
                case "up":
                    if (page.currentLineIndex > 0) {
                        page.currentLineIndex -= 1;
                    }
                    break;
                case "down":
                    if (page.currentLineIndex < LineCount(page.pageData) - 1) {
                        page.currentLineIndex += 1;
                    }
                    break;
                case "left":
                    if (page.pageId > 1) {
                        page = GetNexPage(page, pagesMap, -1, prefix.length(), consoleLines, cursorStartPosition);
                    }
                    break;
                case "right":
                    if (page.pageId < pageCount) {
                        page = GetNexPage(page, pagesMap, 1, prefix.length(), consoleLines, cursorStartPosition);
                    }
                    break;
                case "esc":
                    if (isEscActive) {
                        EndDraw(page, cursorStartPosition, prefix.length(), consoleLines);
                        return 0;
                    }
                    break;
            }
            DrawMenu(page, pageCount, cursorStartPosition, consoleLines,
                    showHelpControl, prefix, prafixMark);
        }
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

    private static void DrawMenu(PageData page, int pagesCount, Point2D cursorStartPosition, int linesCount,
            boolean showHelpControl, String prefix, String prefixMark) throws IOException {
        ConsoleManager.SetCursorPosition(cursorStartPosition);
        int blockIdCount = 0;
        int largestLine = GetLargestLineLength(page.pageData);
        for (String key : page.pageData.keySet()) {
            if (blockIdCount > 0) {
                System.out.println();
            }
            System.out.println(key);
            List<String> pageData = page.pageData.get(key);
            for (int i = 0; i < pageData.size(); i++) {
                i += blockIdCount;
                boolean isSelected = i == page.currentLineIndex;
                if (isSelected) {
                    clearLineText(largestLine);
                }
                String prToConsole;
                if (prefixMark.equals("")) {
                    prToConsole = isSelected ? prefix : " ".repeat(prefix.length());
                    System.out.println(prToConsole + pageData.get(i));
                } else {
                    prToConsole = isSelected ? pageData.get(i).replace(prefixMark, prefix)
                            : pageData.get(i).replace(prefixMark, " ".repeat(prefixMark.length()));
                    System.out.println(prToConsole);
                }
            }
            blockIdCount += pageData.size();
        }
        if (showHelpControl) {
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
            System.out.print("\n".repeat(linesCount - page.linesCount - page.pageData.size() * 2) + indent
                    + strPageNumbers + "\n");
            String padding = "=".repeat(largestLine);
            String pagesSwitchInfo = pagesCount > 1 ? "← → - переключать страницы. " : "";
            System.out.println(padding + "\n↑ ↓ - перемещаться между строками. " + pagesSwitchInfo
                    + "Enter - выбрать задачу. Для выхода нажмите Esc.\n");
        }
        System.out.println();
    }

    private static void EndDraw(PageData page, Point2D cursorStartPosition, int prefixLength, int consoleLines)
            throws IOException {
        ConsoleManager.HideCursor(false);
        ConsoleManager.SetCursorPosition(cursorStartPosition);
        ClearConsoleText(GetLargestLineLength(page.pageData) + prefixLength, consoleLines);
        ConsoleManager.SetCursorPosition(cursorStartPosition);
    }

    private static int LineCount(Map<String, List<String>> menuData) {
        int count = 0;
        for (String key : menuData.keySet()) {
            count += menuData.get(key).size();
        }
        return count;
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

    private static int GetLargestKeyTasks(Map<String, List<String>> menuData) {
        int largestKeyTasks = 0;
        for (String key : menuData.keySet()) {
            int lengthKeyTasks = menuData.get(key).size();
            largestKeyTasks = lengthKeyTasks > largestKeyTasks ? lengthKeyTasks : largestKeyTasks;
        }
        return largestKeyTasks;
    }

    private static void ClearConsoleText(int charsCount, int linesCount) {
        for (int i = 0; i < linesCount; i++) {
            System.out.print(" ".repeat(charsCount));
        }
    }

    private static void clearLineText(int lineCount) throws IOException {
        Point2D pointCursor = ConsoleManager.GetCursorCoordinate();
        System.out.print(" ".repeat(lineCount));
        ConsoleManager.SetCursorPosition(pointCursor);
    }

    private static PageData GetNexPage(PageData page, Set<PageData> pagesMap,
            int step,
            int prefixLength,
            int consoleLines,
            Point2D cursorStartPosition) throws IOException {
        ConsoleManager.SetCursorPosition(cursorStartPosition);
        ClearConsoleText(GetLargestLineLength(page.pageData) + prefixLength,
                consoleLines);
        int currentIndex = page.currentLineIndex;
        final int pageId = page.pageId;
        page = pagesMap.stream().filter(p -> p.pageId == pageId + step).findFirst().get();
        page.currentLineIndex = currentIndex < page.linesCount ? currentIndex : page.linesCount - 1;
        return page;
    }
}
