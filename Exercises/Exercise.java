package Exercises;

import java.io.IOException;

import Interface.MenuRender;

public abstract class Exercise {
    public Exercise(String description) {
        this._description = description;
    }

    private String _description;

    public abstract boolean Solution();

    public void Start() {
        boolean done = false;
        while (!done) {
            DrawHeader();
            try {
                done = Solution();
            } catch (Exception ex) {
                DrawHeader();
                System.out.println(ex);
            }
            if (done == false) {
                done = End();
            }
        }
    }

    public boolean End() {

        return answer == 1;
    }

    private void DrawHeader() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (IOException | InterruptedException ex) {
            System.out.println(ex);
        }
        System.out.println(_description);
        System.out.println("==========================================");
    }
}
