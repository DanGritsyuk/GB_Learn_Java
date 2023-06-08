package Exercises.Honework1;

import Exercises.Exercise;

public class Exercise1 extends Exercise {
    public Exercise1(String description) {
        super(description);
    }

    @Override
    public boolean Solution() {
        _cm.PrintText("done");
        return false;
    }
}