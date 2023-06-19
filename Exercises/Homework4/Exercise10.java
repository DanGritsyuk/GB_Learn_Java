package Exercises.Homework4;

import java.util.Deque;
import java.util.LinkedList;

import Exercises.Exercise;

public class Exercise10 extends Exercise {
    public Exercise10(String description) {
        super(description);
    }

    @Override
    public boolean Solution() {
        _cm.PrintText("Задача в разработке.\n");
        _cm.InputText("Нажмите Enter...\n");

        return true;
    }

    public Deque<Integer> multiply(Deque<Integer> num1, Deque<Integer> num2) {
        Deque<Integer> result = new LinkedList<>();
        int carry = 0;
        for (int i = 0; i < num1.size(); i++) {
            int digit1 = num1.removeFirst();
            int tempCarry = 0;
            Deque<Integer> tempResult = new LinkedList<>();
            for (int j = 0; j < num2.size(); j++) {
                int digit2 = num2.removeFirst();
                int product = digit1 * digit2 + carry;
                carry = product / 10;
                tempCarry = product % 10;
                tempResult.addLast(tempCarry);
            }
            if (carry > 0) {
                tempResult.addLast(carry);
                carry = 0;
            }
            for (int k = 0; k < i; k++) {
                tempResult.addFirst(0);
            }
            // result = sum(result, tempResult);
            num2.addAll(tempResult);
        }
        if (carry > 0) {
            result.addLast(carry);
        }
        // Collections.reverse(result.toArray());
        return result;
    }
}