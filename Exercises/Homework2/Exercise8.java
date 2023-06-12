package Exercises.Homework2;

import Exercises.Exercise;
import Decorators.LogDecorator;
import Exercises.Homework1.Exercise3;

public class Exercise8 extends Exercise {
    public Exercise8(String description) {
        super(description);
    }

    @Override
    public boolean Solution() {
        Exercise3 calculator = new Exercise3("Простой калькулятор");
        Exercise3 calculatorWithLog = new LogDecorator(calculator);
        return calculatorWithLog.Solution();
    }
}
