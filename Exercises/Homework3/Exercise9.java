package Exercises.Homework3;

import java.util.ArrayList;

import Exercises.Exercise;

public class Exercise9 extends Exercise {
    public Exercise9(String description) {
        super(description);
    }

    private ArrayList<Integer> _numbers;

    @Override
    public boolean solution() {
        fillArrayList();

        cmdManager.printText("Исходный список: " + _numbers, "\n\n");

        _numbers.removeIf(n -> n % 2 == 0);
        int min = _numbers.stream().min(Integer::compare).orElse(0);
        int max = _numbers.stream().max(Integer::compare).orElse(0);

        double average = _numbers.stream().mapToInt(Integer::intValue).average().orElse(0.0);

        cmdManager.printText("Список без чётных чисел: " + _numbers);
        cmdManager.printText("Минимальное значение: " + min);
        cmdManager.printText("Максимальное значение: " + max);
        cmdManager.printText("Среднее значение: " + average);
        return false;
    }

    private void fillArrayList() {
        int size = (int) (Math.random() * 11) + 5;
        _numbers = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            int randomNum = (int) (Math.random() * 100);
            _numbers.add(randomNum);
        }
    }
}