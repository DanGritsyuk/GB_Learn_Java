import Exercises.Exercise;
import Exercises.ExerciseBuilder;
import Exercises.ExerciseData;
import Interface.ConsoleManager;
import Interface.MenuRender;

public class Program {
    public static void main(String[] args) {
        final int CONST_MENU_COUNT = 30;
        int taskId = 1;

        var exData = new ExerciseData();
        var menu = new MenuRender(exData.descriptions, CONST_MENU_COUNT, true, true,
                "", "", "");

        boolean startApp = true;
        while (startApp) {
            ConsoleManager.ConsoleClear();
            taskId = menu.StartRenderMenu(taskId - 1);
            try {
                Exercise exercise = ExerciseBuilder.GetExerciseObject(exData, taskId);
                if (exercise != null) {
                    exercise.Start();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            startApp = taskId != 0;

        }

        ConsoleManager.ConsoleClear();
        var cm = new ConsoleManager(false);
        cm.PrintText("Программа закрыта.");
    }
}