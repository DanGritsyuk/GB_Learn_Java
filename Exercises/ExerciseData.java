package Exercises;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExerciseData {
    public ExerciseData() {
        descriptions = new HashMap<>();
        descriptions.put("ДОМАШНЕЕ ЗАДАНИЕ №1", Arrays.asList(
                "Задача 1. Напишите программу, которая принимает на вход цифру, обозначающую день недели, и выводит название этого дня недели.",
                "Задача 2. Напишите программу, которая принимает на вход координаты двух точек и находит расстояние между ними в 2D пространстве.",
                "Задача 3. Напишите программу, которая по заданному номеру четверти, показывает диапазон возможных координат точек в этой четверти (x и y).",
                "Задача 4. Напишите программу, которая на вход принимает число (N), а на выходе показывает все чётные числа от 1 до N."));
        descriptions.put("0: ЗАКРЫТЬ ПРОГРАММУ", Arrays.asList());
        linesCount = _TasksCount();
    }

    public Map<String, List<String>> descriptions;
    private int linesCount;

    public String GetTaskText(int index) {
        int count = 0;
        for (String keyWork : descriptions.keySet()) {
            int currentCount = descriptions.get(keyWork).size();
            if (count + currentCount <= index) {
                count += currentCount;
            } else {
                return descriptions.get(keyWork).get(index - count);
            }
        }
        return null;
    }

    private int _TasksCount() {
        int count = 0;
        for (List<String> tasks : descriptions.values()) {
            count += tasks.size();
        }
        return count;
    }

    public int getLinesCount() {
        return linesCount;
    }
}
