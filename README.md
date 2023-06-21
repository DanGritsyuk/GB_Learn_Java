# ЗНАКОМСТВО С JAVA

В программе имеется интерфейс (еще в разработке) и реализует следующие задачи:

## Домашнее задание №1 

+ Вычислить n-ое треугольного число (сумма чисел от 1 до n), n! (произведение чисел от 1 до n).
+ Вывести все простые числа от 1 до 1000.
+ Реализовать простой калькулятор.
+ Задано уравнение вида q + w = e, q, w, e >= 0. Некоторые цифры могут быть заменены знаком вопроса, например, 2? + ?5 = 69. Требуется восстановить выражение до верного равенства. Знаки вопроса - одинаковые цифры.

## Домашнее задание №2

+ Дана строка sql-запроса "select * from students WHERE ". Сформируйте часть WHERE этого запроса, используя StringBuilder. Данные для фильтрации приведены ниже в виде json-строки.
Если значение null, то параметр не должен попадать в запрос.
String input_str = "{"name":"Ivanov", "country":"Russia", "city":"Moscow", "age":"null"}"
Ввод данных: {"name":"Ivanov", "country":"Russia", "city":"Moscow", "age":"null"}
вывод: select * from students WHERE name=Ivanov AND country=Russia AND city=Moscow

+ Реализуйте алгоритм сортировки пузырьком числового массива, результат после каждой итерации запишите в лог-файл.

### Дополнительные задания

+ Дана строка (сохранить в файл и читать из файла) [{"фамилия":"Иванов","оценка":"5","предмет":"Математика"},{"фамилия":"Петрова","оценка":"4","предмет":"Информатика"},{"фамилия":"Краснов","оценка":"5","предмет":"Физика"}]
Написать метод(ы), который распарсит json и, используя StringBuilder, создаст строки вида: Студент [фамилия] получил [оценка] по предмету [предмет].
Пример вывода:
Студент Иванов получил 5 по предмету Математика.
Студент Петрова получил 4 по предмету Информатика.
Студент Краснов получил 5 по предмету Физика.

+ К калькулятору из предыдущего ДЗ добавить логирование.
4+2=6
6-1=5

## Домашнее задание №3

+ Пусть дан произвольный список целых чисел.
1) Нужно удалить из него чётные числа
2) Найти минимальное значение
3) Найти максимальное значение
4) Найти среднее значение

## Домашнее задание №4

+ Даны два Deque, цифры в обратном порядке.

[3,2,1,-] - пример Deque
[5,4,3]- пример второго Deque
1) -123 * 345 = -42 435

Ответ всегда - связный список, в обычном порядке [-,4,2,4,3,5] - пример ответа

## Инструкция по использованию программы:
Чтобы открыть выполнение желаемой задачи нужно в главном меню стрелками выбрать нужный пункт и нажать ввод. **ВНИМАНИЕ:** для работы с консольным меню открывается небольшое окно, которое необходимо для распознования нажатой клавиши, для её работы оно должно быть в фокусе, самостоятельно закрывать его не рекомндуется. Если программу необходимо закрыть, нажмите клавишу Esc. При выборе пункта задания, откроется его интерфейс, после чего следуем инструкциям. Как только будет получен результат, программа запросит следующий шаг, где будет выбор из двух пунктов: "Выход в главное меню." откроется главное меню, "Начать заново." — выполнение задачи начнется сначала.