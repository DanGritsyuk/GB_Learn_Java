package Exercises;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ExerciseData {
    public ExerciseData() {
        descriptions = new TreeMap<>();
        descriptions.put("ДОМАШНЕЕ ЗАДАНИЕ 1", Arrays.asList(
                "1: Вычислить n-ое треугольного число (сумма чисел от 1 до n), n! (произведение чисел от 1 до n).",
                "2: Вывести все простые числа от 1 до 1000.",
                "3: Реализовать простой калькулятор.",
                "4: Задано уравнение вида q + w = e, q, w, e >= 0. Некоторые цифры могут быть заменены знаком вопроса, например, 2? + ?5 = 69. Требуется восстановить выражение до верного равенства. Знаки вопроса - одинаковые цифры."));
        descriptions.put("ДОМАШНЕЕ ЗАДАНИЕ 2", Arrays.asList(
                "1: Дана строка sql-запроса \"select * from students WHERE \". Сформируйте часть WHERE этого запроса, используя StringBuilder. Данные для фильтрации приведены ниже в виде json-строки. Если значение null, то параметр не должен попадать в запрос. String input_str = \"{\"name\":\"Ivanov\", \"country\":\"Russia\", \"city\":\"Moscow\", \"age\":\"null\"}\" Ввод данных: {\"name\":\"Ivanov\", \"country\":\"Russia\", \"city\":\"Moscow\", \"age\":\"null\"} вывод: select * from students WHERE name=Ivanov AND country=Russia AND city=Moscow",
                "2: Реализуйте алгоритм сортировки пузырьком числового массива, результат после каждой итерации запишите в лог-файл.",
                "3: Дана строка (сохранить в файл и читать из файла) [{\"фамилия\":\"Иванов\",\"оценка\":\"5\",\"предмет\":\"Математика\"},{\"фамилия\": \"Петрова\",\"оценка\":\"4\",\"предмет\":\"Информатика\"},{\"фамилия\":\"Краснов\",\"оценка\":\"5\",\"предмет\":\"Физика\"}] Написать метод(ы), который распарсит json и, используя StringBuilder, создаст строки вида: Студент [фамилия] получил [оценка] по предмету [предмет]. Пример вывода: Студент Иванов получил 5 по предмету Математика. Студент Петрова получил 4 по предмету Информатика. Студент Краснов получил 5 по предмету Физика.",
                "4: К калькулятору из предыдущего ДЗ добавить логирование."));
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
