package Exercises.Homework2;

import Controllers.LoggerController;
import Exercises.Homework1.Exercise3;

public class CalcLog extends Exercise3 {

    private LoggerController _logger = new LoggerController(Exercise3.class.getName(), false);

    public CalcLog(Exercise3 exercise) {
        super(exercise.getDescription());
    }

    @Override
    public boolean solution() {
        Boolean result = super.solution();
        String message = super.strResult;
        _logger.log(message);
        _logger.dispose();
        return result;
    }

}