package Interface;

import java.util.Scanner;

public class ConsoleManager {

    public static void PrintText(String text) {
        System.out.print(text);
    }

    public static <T> void PrintArray(T[] array) {
        System.out.print("[ ");
        for (var item : array) {
            System.out.printf("%s, ", item);
        }
        System.out.println("\b\b ]");
    }

    public static String InputText(String message, Scanner cs) {
        System.out.print(message);
        var text = cs.nextLine();
        return text;
    }

    public static void ConsoleClear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}