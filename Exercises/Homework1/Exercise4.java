package Exercises.Homework1;

import Exercises.Exercise;

public class Exercise4 extends Exercise {
    public Exercise4(String description) {
        super(description);
    }

    @Override
    public boolean solution() {
        String equation = cmdManager.inputText("Введите уравнение: ").replace(" ", "");

        for (int i = 0; i < 10; i++) {
            String buffEquation = equation.replace("?", Integer.toString(i));
            Boolean isTrueEquality;
            try {
                isTrueEquality = checkEquation(buffEquation);
            } catch (Exception e) {
                e.printStackTrace();
                isTrueEquality = false;
            }
            if (isTrueEquality) {
                cmdManager.printText(buffEquation);
                return false;
            }
        }

        cmdManager.printText("Решения нет!");
        return false;
    }

    private static Boolean checkEquation(String equation) throws Exception {
        var operators = new String[] { "+", "-", "*", "/" };
        String currOperator = "";
        Boolean notFoundOperator = true;
        for (String operator : operators) {
            if (equation.contains(operator)) {
                currOperator = operator;
                notFoundOperator = false;
                break;
            }
        }
        if (notFoundOperator) {
            throw new Exception("Нет такого оператора");
        }

        var equationArr = equation.replace("=", "&").replace(currOperator, "&").split("&");
        int a = Integer.parseInt(equationArr[0]);
        int b = Integer.parseInt(equationArr[1]);
        int c = Integer.parseInt(equationArr[2]);

        int answer = Exercise3.calculate((double) a, (double) b, currOperator.charAt(0)).intValue();

        return answer == c;
    }
}
