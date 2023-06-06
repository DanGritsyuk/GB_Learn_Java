package Exercises;

import Exercises.Homework1.Exercise1;
import Exercises.Homework1.Exercise2;
import Exercises.Homework1.Exercise3;
import Exercises.Homework1.Exercise4;

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
            default:
                throw new Exception("ЗАДАЧА ЕЩЕ НЕ РЕАЛИЗОВАНА");
        }
    }
}
