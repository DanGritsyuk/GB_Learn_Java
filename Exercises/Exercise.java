package Exercises;

import java.util.List;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import Interface.ConsoleManager;
import Interface.MenuRender;

public abstract class Exercise {

    private String _description;
    private String _borderLine;
    protected ConsoleManager cmdManager;

    public Exercise(String description) {
        this._description = description;
        this._borderLine = "==========================================";
        this.cmdManager = new ConsoleManager();
    }

    public String getDescription() {
        return _description;
    }

    public abstract boolean solution();

    public void startup() {
        boolean done = false;
        while (!done) {
            drawHeader();
            try {
                done = solution();
            } catch (Exception ex) {
                drawHeader();
                cmdManager.printText(ex.getMessage());
            }
            drawFooter();
            if (!done) {
                done = endDialog();
            }
        }
    }

    private boolean endDialog() {
        cmdManager.printText("\n");
        Map<String, List<String>> menuData = new HashMap<String, List<String>>();
        menuData.put("Выберите следующий шаг:", Arrays.asList("Выход в главное меню.", "Начать заново."));
        var menu = new MenuRender(menuData, 0, true, false, "", "", "", "");

        int answer = menu.startRenderMenu(0);

        return answer == 0 || answer == 1;
    }

    protected void drawHeader() {
        cmdManager.consoleClear();
        cmdManager.printText(this._description + "\n" + this._borderLine + "\n");
    }

    private void drawFooter() {
        cmdManager.printText("\n\n" + this._borderLine);
    }
}
