// Реализовать простой калькулятор.
package Exercises.Homework1;

import Interface.ConsoleManager;

import java.util.Scanner;
import Exercises.Exercise;

public class Exercise3 extends Exercise {
    public Exercise3(String description) {
        super(description);
    }

    @Override
    public boolean Solution() {
        var sc = new Scanner(System.in, "cp866");
        double num1, num2, result;
        char operator;

        num1 = Double.parseDouble(ConsoleManager.InputText("Введите первое число: ", sc));
        operator = ConsoleManager.InputText("Введите оператор (+, -, *, /): ", sc).charAt(0);
        num2 = Double.parseDouble(ConsoleManager.InputText("Введите второе число: ", sc));

        try {
            result = Calculate(num1, num2, operator);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        ConsoleManager.PrintText(num1 + " " + operator + " " + num2 + " = " + result);
        return false;
    }

    public static Double Calculate(Double num1, Double num2, char operator) throws Exception {
        Double result;
        switch (operator) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                if (num2 == 0) {
                    throw new Exception("Ошибка: деление на ноль");
                }
                result = num1 / num2;
                break;
            default:
                throw new Exception("Ошибка: неверный оператор");
        }
        return result;
    }
}
