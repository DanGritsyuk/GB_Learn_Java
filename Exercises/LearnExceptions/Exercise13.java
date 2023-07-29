package Exercises.LearnExceptions;

import Exercises.Exercise;

public class Exercise13 extends Exercise {

    public Exercise13(String description) {
        super(description);
    }

    @Override
    public boolean solution() {
        float result;
        String str = cmdManager.inputText("Введите число: ");
        if (str.isEmpty())
            throw new NullPointerException("Введена пустая строка");

        try {
            result = Float.parseFloat(str);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Невозможно преобразовать строку в число типа float");
        }
        cmdManager.printText("Введеное число " + result);
        return false;
    }
}