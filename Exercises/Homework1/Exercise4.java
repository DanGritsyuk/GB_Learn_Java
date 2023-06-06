package Exercises.Homework1;

import Exercises.Exercise;
import Interface.ConsoleManager;

import java.util.Scanner;

public class Exercise4 extends Exercise {
    public Exercise4(String description) {
        super(description);
    }

    @Override
    public boolean Solution() {
        Scanner sc = new Scanner(System.in);
        String equation = ConsoleManager.InputText("Введите уравнение: ", sc).replace(" ", "");

        for (int i = 0; i < 10; i++) {
            String buffEquation = equation.replace("?", Integer.toString(i));
            Boolean isTrueEquality;
            try {
                isTrueEquality = CheckEquation(buffEquation);
            } catch (Exception e) {
                e.printStackTrace();
                isTrueEquality = false;
            }
            if (isTrueEquality) {
                ConsoleManager.PrintText(buffEquation);
                return false;
            }
        }

        ConsoleManager.PrintText("Решения нет!");
        return false;
    }

    private static Boolean CheckEquation(String equation) throws Exception {
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

        int answer = Exercise3.Calculate((double) a, (double) b, currOperator.charAt(0)).intValue();

        return answer == c;
    }
}
