package Exercises;

import java.util.List;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import Interface.ConsoleManager;
import Interface.MenuRender;

public abstract class Exercise {
    public Exercise(String description) {
        this._description = description;
    }

    private String _description;
    private String _borderLine = "==========================================";
    protected ConsoleManager cmdManager = new ConsoleManager();

    public String GetDescription() {
        return _description;
    }

    public abstract boolean Solution();

    public void Start() {
        boolean done = false;
        while (!done) {
            DrawHeader();
            try {
                done = Solution();
            } catch (Exception ex) {
                DrawHeader();
                cmdManager.PrintText(ex.getMessage());
            }
            DrawFooter();
            if (!done) {
                done = End();
            }
        }
    }

    public boolean End() {
        cmdManager.PrintText("\n");
        Map<String, List<String>> menuData = new HashMap<String, List<String>>();
        menuData.put("Выберите следующий шаг:", Arrays.asList("Выход в главное меню.", "Начать заново."));
        var menu = new MenuRender(menuData, 0, true, false, "", "", "", "");

        int answer = menu.startRenderMenu(0);

        return answer == 0 || answer == 1;
    }

    protected void DrawHeader() {
        cmdManager.ConsoleClear();
        cmdManager.PrintText(this._description + "\n" + this._borderLine + "\n");
    }

    private void DrawFooter() {
        cmdManager.PrintText("\n\n" + this._borderLine);
    }
}
