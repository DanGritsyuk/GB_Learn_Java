// Вычислить n-ое треугольного число (сумма чисел от 1 до n), n! (произведение чисел от 1 до n).
package Exercises.Homework1;

import Exercises.Exercise;

public class Exercise1 extends Exercise {
    public Exercise1(String description) {
        super(description);
    }

    @Override
    public boolean Solution() {
        String strN = "";
        strN = _cm.InputText("Введите число N: ");

        int n = Integer.parseInt(strN);
        int sumN = n * (n + 1) / 2;
        int factN = Factorial(n);

        String answer = "\n" + strN + "-ое треугольного число: " + Integer.toString(sumN);
        answer += ". Факториал: " + Integer.toString(factN);
        _cm.PrintText(answer);

        return false;
    }

    private static int Factorial(int n) {
        int factNum = 1;
        for (int i = 2; i <= n; i++) {
            factNum *= i;
        }
        return factNum;
    }
}