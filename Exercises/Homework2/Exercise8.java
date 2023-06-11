package Exercises.Homework2;

import Exercises.Exercise;

public class Exercise8 extends Exercise {
    public Exercise8(String description) {
        super(description);
    }

    @Override
    public boolean Solution() {
        _cm.PrintText("Задача в разработке...\n");
        _cm.InputText("Нажмите Enter");
        return true;
    }
}
