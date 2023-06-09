package Interface;

import java.util.Scanner;

public class ConsoleManager {

    public ConsoleManager() {
        SetTextCode("");
    }

    public ConsoleManager(String charsetName) {
        SetTextCode(charsetName);
    }

    public ConsoleManager(Boolean onRead) {
        SetTextCode("");
        this._onRead = onRead;
    }

    public ConsoleManager(String charsetName, Boolean onRead) {
        SetTextCode(charsetName);
        this._onRead = onRead;
    }

    private Scanner _cs;
    private StringBuilder _frame = new StringBuilder();
    private Boolean _onRead = true;

    public String GetFrameText(Boolean toDelete) {
        var text = _frame.toString();
        if (toDelete) {
            _frame.delete(0, _frame.length());
        }
        return text;
    }

    public String InputText(String message) {
        System.out.print(message);
        var text = _cs.nextLine();
        ConsoleReading(message + text + "\n");
        return text;
    }

    public void PrintText() {
        PrintText("", "\n");
    }

    public void PrintText(String text) {
        PrintText(text, "\n");
    }

    public void PrintText(String text, String end) {
        ConsoleReading(text + end);
        System.out.print(text + end);
    }

    public <T> void PrintArray(T[] array) {
        var output = new StringBuilder();
        output.append("[ ");
        for (var item : array) {
            output.append(String.format("%s, ", item));
        }
        output.append("\b\b ]");
        var text = output.toString();
        ConsoleReading(text);
        System.out.print(text);
    }

    public int GetKeyEvent() {
        return KeyEventManager.Start();
    }

    public static void ConsoleClear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void HideCursor(boolean isHidden) {
        if (isHidden) {
            System.out.print("\033[?25l");
        } else {
            System.out.print("\033[?25h");
        }
    }

    private void SetTextCode(String charsetName) {
        if (charsetName.isEmpty()) {
            charsetName = "cp866";
        }
        _cs = new Scanner(System.in, charsetName);
    }

    private void ConsoleReading(String text) {
        if (_onRead) {
            _frame.append(text);
        }
    }
}