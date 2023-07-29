package Exercises.Homework2;

import Exercises.Exercise;
import Exercises.Homework1.Exercise3;

public class Exercise8 extends Exercise {
    public Exercise8(String description) {
        super(description);
    }

    @Override
    public boolean solution() {
        Exercise3 calculator = new Exercise3("Простой калькулятор");
        Exercise3 calculatorWithLog = new CalcLog(calculator);
        return calculatorWithLog.solution();
    }
}
