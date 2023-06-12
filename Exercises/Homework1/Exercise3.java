// Реализовать простой калькулятор.
package Exercises.Homework1;

import Exercises.Exercise;

public class Exercise3 extends Exercise {
    public Exercise3(String description) {
        super(description);
    }

    public String strResult = "";

    private Double _num1 = (double) 0;
    private Double _num2 = (double) 0;
    private char _operator = 0;

    @Override
    public boolean Solution() {
        GetDataFromConsole();
        GetResoultToString();
        _cm.PrintText(this.strResult);

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

    private void GetResoultToString() {
        try {
            Double result = Calculate(_num1, _num2, _operator);
            this.strResult = _num1 + " " + _operator + " " + _num2 + " = " + result;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void GetDataFromConsole() {
        _num1 = Double.parseDouble(_cm.InputText("Введите первое число: "));
        _operator = _cm.InputText("Введите оператор (+, -, *, /): ").charAt(0);
        _num2 = Double.parseDouble(_cm.InputText("Введите второе число: "));
    }
}
