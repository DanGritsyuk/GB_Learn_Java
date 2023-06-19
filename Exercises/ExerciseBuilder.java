package Exercises;

import Exercises.Homework1.Exercise1;
import Exercises.Homework1.Exercise2;
import Exercises.Homework1.Exercise3;
import Exercises.Homework1.Exercise4;
import Exercises.Homework2.Exercise5;
import Exercises.Homework2.Exercise6;
import Exercises.Homework2.Exercise7;
import Exercises.Homework2.Exercise8;
import Exercises.Homework3.Exercise9;
import Exercises.Homework4.Exercise10;
import Exercises.Homework5.Exercise11;

public class ExerciseBuilder {
    public static Exercise GetExerciseObject(ExerciseData exerciseData, int taskNum) throws Exception {
        int taskDataIndex = taskNum - 1;
        String taskText = exerciseData.GetTaskText(taskDataIndex);
        switch (taskNum) {
            case 1:
                return new Exercise1(taskText);
            case 2:
                return new Exercise2(taskText);
            case 3:
                return new Exercise3(taskText);
            case 4:
                return new Exercise4(taskText);
            case 5:
                return new Exercise5(taskText);
            case 6:
                return new Exercise6(taskText);
            case 7:
                return new Exercise7(taskText);
            case 8:
                return new Exercise8(taskText);
            case 9:
                return new Exercise9(taskText);
            case 10:
                return new Exercise10(taskText);
            case 11:
                return new Exercise11(taskText);
            default:
                String nameTask = taskText.length() < 51 ? taskText : taskText.substring(0, 47) + "...";
                throw new Exception("ЗАДАЧА <" + nameTask + "> ЕЩЕ НЕ РЕАЛИЗОВАНА");
        }
    }
}
