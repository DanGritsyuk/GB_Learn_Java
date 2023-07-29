package Interface;

import java.util.Scanner;

import Controllers.LoggerController;

public class ConsoleManager {

    private Scanner _cs;
    private StringBuilder _frame = new StringBuilder();
    private LoggerController _lc = null;
    private Boolean _onLog = false;

    public ConsoleManager() {
        setTextCode("");
    }

    public ConsoleManager(String charsetName) {
        setTextCode(charsetName);
    }

    public ConsoleManager(Boolean onLog) {
        this._onLog = onLog;
        setFullSettings("");
    }

    public ConsoleManager(String charsetName, Boolean onLog) {
        this._onLog = onLog;
        setFullSettings(charsetName);
    }

    public String getFrameText(Boolean toDelete) {
        var text = _frame.toString();
        if (toDelete) {
            _frame.delete(0, _frame.length());
            _lc.log("END LOG.");
            _lc.dispose();
        }
        return text;
    }

    public String inputText(String message) {
        System.out.print(message);
        var text = _cs.nextLine();
        consoleReading(message + text + "\n");
        return text;
    }

    public void printText() {
        printText("", "\n");
    }

    public void printText(String text) {
        printText(text, "\n");
    }

    public void printText(String text, String end) {
        consoleReading(text + end);
        System.out.print(text + end);
    }

    public <T> void printArray(T[] array) {
        var output = new StringBuilder();
        output.append("[ ");
        for (var item : array) {
            output.append(String.format("%s, ", item));
        }
        output.append("\b\b ]");
        var text = output.toString();
        consoleReading(text);
        System.out.print(text);
    }

    public int getKeyEvent() {
        return KeyEventManager.start();
    }

    public void consoleClear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        if (_onLog) {
            _lc.log("Clear console.");
        }
    }

    private void setFullSettings(String charsetName) {
        setTextCode(charsetName);
        _lc = _onLog ? new LoggerController(this.getClass().getName(), true) : null;
    }

    private void setTextCode(String charsetName) {
        if (charsetName.isEmpty()) {
            charsetName = "cp866";
        }
        _cs = new Scanner(System.in, charsetName);
    }

    private void consoleReading(String text) {
        if (_onLog) {
            _frame.append(text);
            _lc.log(text);
        }
    }

    public static void hideCursor(boolean isHidden) {
        if (isHidden) {
            System.out.print("\033[?25l");
        } else {
            System.out.print("\033[?25h");
        }
    }

    public static String compressString(String input) {
        StringBuilder result = new StringBuilder();
        int count = 1;
        char prevChar = input.charAt(0);
        for (int i = 1; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            if (currentChar == prevChar) {
                count++;
            } else {
                result.append(prevChar).append(count);
                prevChar = currentChar;
                count = 1;
            }
        }
        result.append(prevChar).append(count);
        return result.toString();
    }
}