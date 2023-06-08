package Interface;

import java.util.Scanner;

public class ConsoleManager {

    public ConsoleManager() {
        this._cs = new Scanner(System.in, "cp866");
    }

    public ConsoleManager(Boolean onRead) {
        this._cs = new Scanner(System.in, "cp866");
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

    public int GetKeyEvent() {
        return KeyEventManager.Start();
    }

    private void ConsoleReading(String text) {
        if (_onRead) {
            _frame.append(text);
        }
    }
}