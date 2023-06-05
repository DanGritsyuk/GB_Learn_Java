package Interface;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MenuRender {

    public MenuRender(Map<String, List<String>> menuData) {
        this._menuData = menuData;
        this._scanner = new Scanner(System.in, "cp866");
    }

    private Map<String, List<String>> _menuData;
    private Scanner _scanner;

    public int StartRenderMenu() {
        for (String key : this._menuData.keySet()) {
            ConsoleManager.PrintText(key);
            for (String taskText : this._menuData.get(key)) {
                ConsoleManager.PrintText("\n    ");
                ConsoleManager.PrintText(taskText);
            }
            ConsoleManager.PrintText("\n\n");
        }

        return Integer.parseInt(ConsoleManager.InputText("\n\nВведите номер: ", _scanner));
    }
}
