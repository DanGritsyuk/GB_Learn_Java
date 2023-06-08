package Exercises;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExerciseData {
    public ExerciseData() {
        descriptions = new HashMap<>();
        descriptions.put("ДОМАШНЕЕ ЗАДАНИЕ №1", Arrays.asList(
                "1: Вычислить n-ое треугольного число (сумма чисел от 1 до n), n! (произведение чисел от 1 до n).",
                "2: Вывести все простые числа от 1 до 1000.",
                "3: Реализовать простой калькулятор.",
                "4: Задано уравнение вида q + w = e, q, w, e >= 0. Некоторые цифры могут быть заменены знаком вопроса, например, 2? + ?5 = 69. Требуется восстановить выражение до верного равенства. Знаки вопроса - одинаковые цифры."));
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
