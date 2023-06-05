package Exercises;

import Exercises.Honework1.Exercise1;

public class ExerciseBuilder {
    public static Exercise GetExerciseObject(ExerciseData exerciseData, int taskNum) throws Exception {
        int taskDataIndex = taskNum - 1;
        String taskText = exerciseData.GetTaskText(taskDataIndex);
        switch (taskNum) {
            case 1:
                return new Exercise1(taskText);
            default:
                throw new Exception("ЗАДАЧА ЕЩЕ НЕ РЕАЛИЗОВАНА");
        }
    }
}
