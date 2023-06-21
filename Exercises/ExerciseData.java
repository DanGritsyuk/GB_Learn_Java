package Exercises;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ExerciseData {
    public ExerciseData() {
        descriptions = new LinkedHashMap<>();
        descriptions.put("ДОМАШНЯЯ РАБОТА 1", Arrays.asList(
                "Задание 1: Вычислить n-ое треугольного число (сумма чисел от 1 до n), n! (произведение чисел от 1 до n).",
                "Задание 2: Вывести все простые числа от 1 до 1000.",
                "Задание 3: Реализовать простой калькулятор.",
                "Задание 4: Задано уравнение вида q + w = e, q, w, e >= 0. Некоторые цифры могут быть заменены знаком вопроса, например, 2? + ?5 = 69. Требуется восстановить выражение до верного равенства. Знаки вопроса - одинаковые цифры."));
        descriptions.put("ДОМАШНЯЯ РАБОТА 2", Arrays.asList(
                "Задание 1: Дана строка sql-запроса \"select * from students WHERE \". Сформируйте часть WHERE этого запроса, используя StringBuilder. Данные для фильтрации приведены ниже в виде json-строки. Если значение null, то параметр не должен попадать в запрос.\nString input_str = \"{\"name\":\"Ivanov\", \"country\":\"Russia\", \"city\":\"Moscow\", \"age\":\"null\"}\" Ввод данных: {\"name\":\"Ivanov\", \"country\":\"Russia\", \"city\":\"Moscow\", \"age\":\"null\"} вывод: select * from students WHERE name=Ivanov AND country=Russia AND city=Moscow",
                "Задание 2: Реализуйте алгоритм сортировки пузырьком числового массива, результат после каждой итерации запишите в лог-файл.",
                "Задание 3: Дана строка (сохранить в файл и читать из файла)\n[{\"фамилия\":\"Иванов\",\"оценка\":\"5\",\"предмет\":\"Математика\"},{\"фамилия\": \"Петрова\",\"оценка\":\"4\",\"предмет\":\"Информатика\"},{\"фамилия\":\"Краснов\",\"оценка\":\"5\",\"предмет\":\"Физика\"}]\nНаписать метод(ы), который распарсит json и, используя StringBuilder, создаст строки вида: Студент [фамилия] получил [оценка] по предмету [предмет].\nПример вывода: Студент Иванов получил 5 по предмету Математика. Студент Петрова получил 4 по предмету Информатика. Студент Краснов получил 5 по предмету Физика.",
                "Задание 4: К калькулятору из предыдущего ДЗ добавить логирование."));
        descriptions.put("ДОМАШНЯЯ РАБОТА 3", Arrays.asList(
                "Задание 1: Пусть дан произвольный список целых чисел.\n1) Нужно удалить из него чётные числа\n2) Найти минимальное значение\n3) Найти максимальное значение\n4) Найти среднее значение"));
        descriptions.put("ДОМАШНЯЯ РАБОТА 4", Arrays.asList(
                "Задание 1: Даны два Deque, цифры в обратном порядке.\n[3,2,1,-] - пример Deque\n[5,4,3]- пример второго Deque\n1) -123 * 345 = -42 435\nОтвет всегда - связный список, в обычном порядке\n[-,4,2,4,3,5] - пример ответа"));

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
