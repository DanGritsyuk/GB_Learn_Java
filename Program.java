import Exercises.Exercise;
import Exercises.ExerciseBuilder;
import Exercises.ExerciseData;
import Interface.ConsoleManager;
import Interface.MenuRender;

public class Program {
    public static void main(String[] args) {
        int taskId = 0;

        var exData = new ExerciseData();
        var menu = new MenuRender(exData.descriptions);

        boolean startApp = true;
        while (startApp) {
            ConsoleManager.ConsoleClear();
            try {
                taskId = menu.StartRenderMenu();
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
        ConsoleManager.PrintText("Программа закрыта.");
    }
}