import Exercises.Exercise;
import Exercises.ExerciseBuilder;
import Interface.ConsoleManager;
import Interface.MenuRender;

public class Program {
    public static void main(String[] args) {
        final int CONST_MENU_COUNT = 30;
        int taskId = 1;

        var cm = new ConsoleManager();

        var exBuilder = new ExerciseBuilder();
        var menu = new MenuRender(exBuilder.descriptions, CONST_MENU_COUNT, true, true,
                "", "", "", "");

        boolean startApp = true;
        while (startApp) {
            cm.consoleClear();
            taskId = menu.startRenderMenu(taskId - 1);
            menu.footerText = "";
            try {
                Exercise exercise = exBuilder.getExerciseObject(taskId);
                if (exercise != null) {
                    exercise.startup();
                }
            } catch (Exception e) {
                menu.footerText = e.getMessage();
            }

            startApp = taskId != 0;

        }

        cm.consoleClear();
        cm.printText("Программа закрыта.");
    }
}