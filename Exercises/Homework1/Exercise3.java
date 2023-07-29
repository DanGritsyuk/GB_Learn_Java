// Реализовать простой калькулятор.
package Exercises.Homework1;

import Exercises.Exercise;

public class Exercise3 extends Exercise {
    public String strResult = "";

    private Double _num1 = (double) 0;
    private Double _num2 = (double) 0;
    private char _operator = 0;

    public Exercise3(String description) {
        super(description);

        strResult = "";

        _num1 = (double) 0;
        _num2 = (double) 0;
        _operator = 0;
    }

    @Override
    public boolean solution() {
        getDataFromConsole();
        getResoultToString();
        cmdManager.printText(this.strResult);

        return false;
    }

    public static Double calculate(Double num1, Double num2, char operator) throws Exception {
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

    private void getResoultToString() {
        try {
            Double result = calculate(_num1, _num2, _operator);
            this.strResult = _num1 + " " + _operator + " " + _num2 + " = " + result;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getDataFromConsole() {
        _num1 = Double.parseDouble(cmdManager.inputText("Введите первое число: "));
        _operator = cmdManager.inputText("Введите оператор (+, -, *, /): ").charAt(0);
        _num2 = Double.parseDouble(cmdManager.inputText("Введите второе число: "));
    }
}
