package Exercises;

import Exercises.Homework1.Exercise1;
import Exercises.Homework1.Exercise2;
import Exercises.Homework1.Exercise3;
import Exercises.Homework1.Exercise4;
import Exercises.Homework2.Exercise5;
import Exercises.Homework2.Exercise6;
import Exercises.Homework2.Exercise7;

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
            default:
                throw new Exception("ЗАДАЧА ЕЩЕ НЕ РЕАЛИЗОВАНА");
        }
    }
}
