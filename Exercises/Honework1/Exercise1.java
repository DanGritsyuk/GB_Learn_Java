package Exercises.Honework1;

import Interface.ConsoleManager;
import Exercises.Exercise;

public class Exercise1 extends Exercise {
    public Exercise1(String description) {
        super(description);
    }

    @Override
    public boolean Solution() {
        ConsoleManager.PrintText("done");
        return false;
    }
}