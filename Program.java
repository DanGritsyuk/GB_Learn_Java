import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Program {
    public static void main(String[] args) throws IOException {
        int key = 0;
        if (args.length > 0) {
            key = 1; // tryparse
        }

        ConsoleManager.ConsoleClear();

        boolean startApp = true;
        while (startApp) {
            Map<String, List<String>> menuData = new HashMap<String, List<String>>();
            var nemuLines = new ArrayList<String>();
            nemuLines.add("Пункт 1");
            nemuLines.add("Пункт 2");
            nemuLines.add("Пункт 3");
            menuData.put("ЗАГОЛОВОК", nemuLines);

            key = MenuRender.StartRenderMenu(menuData, 0, 30, true, true, "", "");

            startApp = key == 0;
        }

        ConsoleManager.ConsoleClear();
        ConsoleManager.PrintText("Программа закрыта.");
    }
}