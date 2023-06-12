package Decorators;

import Controllers.LoggerController;
import Exercises.Homework1.Exercise3;

public class LogDecorator extends Exercise3 {

    public LogDecorator(Exercise3 exercise) {
        super(exercise.GetDescription());
    }

    private LoggerController _logger = new LoggerController(Exercise3.class.getName(), false);

    @Override
    public boolean Solution() {
        Boolean result = super.Solution();
        String message = super.strResult;
        _logger.Log(message);
        return result;
    }

}