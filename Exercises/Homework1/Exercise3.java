// Реализовать простой калькулятор.
package Exercises.Homework1;

import Exercises.Exercise;

public class Exercise3 extends Exercise {
    public Exercise3(String description) {
        super(description);
    }

    @Override
    public boolean Solution() {
        double num1, num2, result;
        char operator;

        num1 = Double.parseDouble(_cm.InputText("Введите первое число: "));
        operator = _cm.InputText("Введите оператор (+, -, *, /): ").charAt(0);
        num2 = Double.parseDouble(_cm.InputText("Введите второе число: "));

        try {
            result = Calculate(num1, num2, operator);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        _cm.PrintText(num1 + " " + operator + " " + num2 + " = " + result);
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
