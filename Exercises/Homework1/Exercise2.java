// Вывести все простые числа от 1 до 1000.
package Exercises.Homework1;

import Exercises.Exercise;

public class Exercise2 extends Exercise {
    public Exercise2(String description) {
        super(description);
    }

    @Override
    public boolean Solution() {
        for (int i = 2; i <= 1000; i++) {
            boolean isPrime = true;
            for (int j = 2; j * j <= i; j++) {
                if (i % j == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                cmdManager.PrintText(Integer.toString(i), " ");
            }
        }

        return false;
    }
}
