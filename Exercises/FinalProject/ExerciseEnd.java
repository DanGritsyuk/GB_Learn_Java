package Exercises.FinalProject;

import Exercises.Exercise;

public class ExerciseEnd extends Exercise {
    public ExerciseEnd(String description) {
        super(description);
    }

    @Override
    public boolean Solution() {
        _cm.PrintText("Задача в разработке...");
        return false;
    }
}
