package Exercises.Homework2;

import java.util.Arrays;

import Controllers.LoggerController;
import Controllers.RandomController;
import Exercises.Exercise;

public class Exercise6 extends Exercise {
    public Exercise6(String description) {
        super(description);
    }

    @Override
    public boolean solution() {
        var array = new int[10];
        RandomController.fillArray(array, 0, 10);
        LoggerController logger = new LoggerController(Exercise6.class.getName(), false);
        logger.log("Исходный массив: " + Arrays.toString(array));
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
            logger.log("Итерация " + (i + 1) + ": " + Arrays.toString(array));
        }
        logger.log("Результат: " + Arrays.toString(array));
        logger.dispose();
        return false;
    }
}
