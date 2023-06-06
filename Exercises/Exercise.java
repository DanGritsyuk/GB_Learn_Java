package Exercises;

import java.util.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Interface.ConsoleManager;
import Interface.MenuRender;

public abstract class Exercise {
    public Exercise(String description) {
        this._description = description;
    }

    private String _description;
    private String _borderLine = "\n==========================================\n";

    public abstract boolean Solution() throws IOException;

    public void Start() {
        boolean done = false;
        while (!done) {
            DrawHeader();
            try {
                done = Solution();
            } catch (Exception ex) {
                DrawHeader();
                ConsoleManager.PrintText(ex.getMessage());
            }
            DrawFooter();
            if (done == false) {
                done = End();
            }
        }
    }

    public boolean End() {
        ConsoleManager.PrintText("\n\n");
        Map<String, List<String>> menuData = new HashMap<String, List<String>>();
        var nemuLines = new ArrayList<String>();
        menuData.put("0: ВЫХОД", nemuLines);
        var menu = new MenuRender(menuData);
        int answer = menu.StartRenderMenu();

        return answer == 0;
    }

    private void DrawHeader() {
        ConsoleManager.ConsoleClear();
        ConsoleManager.PrintText(this._description + this._borderLine);
    }

    private void DrawFooter() {
        ConsoleManager.PrintText(this._borderLine);
    }
}
