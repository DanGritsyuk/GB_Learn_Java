
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Scanner;

public class ConsoleManager {

    public static Point2D GetCursorCoordinate() throws IOException {
        System.out.print("\033[A\033[6n");
        try (Scanner scanner = new Scanner(System.in)) {
            String buff = "";
            boolean keepGoing = true;
            while (keepGoing) {
                buff += scanner.next();
                keepGoing = System.in.available() > 0;
            }

            String newBuff = DelWeedSymbols(buff.replace("[", ""));
            var x = newBuff.split(";")[1];
            var y = newBuff.split(";")[0];
            return new Point2D(
                    Integer.parseInt(x),
                    Integer.parseInt(y));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void SetCursorPosition(Point2D toPosition) throws IOException {
        Point2D currentPosition = GetCursorCoordinate();

        int yDistance = currentPosition.getY() - toPosition.getY();
        if (yDistance > 0) {
            System.out.print("\033[A".repeat(yDistance));
        }

        int xDistance = currentPosition.getX() - toPosition.getX();
        if (xDistance > 0) {
            System.out.print("\033[D".repeat(xDistance));
        }
    }

    public static String GetKeyEvent() throws IOException {
        int key = System.in.read();
        switch (key) {
            case '\r':
                return "enter";
            case 0:
                switch (System.in.read()) {
                    case 'H':
                        return "up";
                    case 'P':
                        return "down";
                    case 'K':
                        return "left";
                    case 'M':
                        return "right";
                }
            case 27:
                return "esc";
        }
        return "";
    }

    public static void HideCursor(boolean isHidden) throws IOException {
        if (isHidden) {
            System.out.print("\033[?25l");
        } else {
            System.out.print("\033[?25h");
        }
    }

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

    public static String InputText(String message) {
        System.out.print(message);
        var cs = new Scanner(System.in, "cp866");
        var text = cs.nextLine();
        cs.close();
        return text;
    }

    public static void ConsoleClear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static String DelWeedSymbols(String line) {
        int first = 0;
        if (line.charAt(0) < '1' || line.charAt(0) > '9') {
            first = 1;
        }
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == 'R') {
                return line.substring(first, i);
            }
        }
        return line;
    }

    private static void AutoPressEnter() {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        } catch (AWTException ex) {
            ex.printStackTrace();
        }
    }

}