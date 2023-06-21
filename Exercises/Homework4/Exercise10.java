package Exercises.Homework4;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

import Exercises.Exercise;

public class Exercise10 extends Exercise {
    public Exercise10(String description) {
        super(description);
    }

    @Override
    public boolean Solution() {
        Deque<Object> deque1 = new LinkedList<>(Arrays.asList(3, 2, 1, '-'));
        Deque<Object> deque2 = new LinkedList<>(Arrays.asList(5, 4, 3));

        _cm.PrintText("Первый: " + deque1);
        _cm.PrintText("Второй: " + deque2);

        Deque<Object> product = MultiplyDeque(deque1, deque2);
        _cm.PrintText("Произведение: " + product);

        Deque<Object> sum = SumDeque(deque1, deque2);
        _cm.PrintText("Сумма: " + sum);

        return false;
    }

    private static Deque<Object> MultiplyDeque(Deque<Object> deque1, Deque<Object> deque2) {
        boolean negative = false;
        if (deque1.peekLast().equals('-')) {
            negative = !negative;
            deque1.removeLast();
        }
        if (deque2.peekLast().equals('-')) {
            negative = !negative;
            deque2.removeLast();
        }

        int[] result = new int[deque1.size() + deque2.size()];
        int carry = 0;
        int i = 0;
        for (Object obj1 : deque1) {
            int digit1 = (int) obj1;
            int j = 0;
            for (Object obj2 : deque2) {
                int digit2 = (int) obj2;
                int product = digit1 * digit2 + carry + result[i + j];
                carry = product / 10;
                result[i + j] = product % 10;
                j++;
            }
            if (carry > 0) {
                result[i + j] += carry;
                carry = 0;
            }
            i++;
        }

        Deque<Object> product = new LinkedList<>();
        if (negative) {
            product.add('-');
        }
        boolean leadingZeros = true;
        for (int k = result.length - 1; k >= 0; k--) {
            if (result[k] != 0) {
                leadingZeros = false;
            }
            if (!leadingZeros) {
                product.add(result[k]);
            }
        }
        if (product.isEmpty()) {
            product.add(0);
        }
        return product;
    }

    private static Deque<Object> SumDeque(Deque<Object> deque1, Deque<Object> deque2) {
        boolean negative = false;
        if (deque1.peekLast().equals('-') && deque2.peekLast().equals('-')) {
            deque1.removeLast();
            deque2.removeLast();
            negative = true;
        } else if (deque1.peekLast().equals('-')) {
            deque1.removeLast();
            return Subtract(deque2, deque1);
        } else if (deque2.peekLast().equals('-')) {
            deque2.removeLast();
            return Subtract(deque1, deque2);
        }

        int[] result = new int[Math.max(deque1.size(), deque2.size()) + 1];
        int carry = 0;
        int i = 0;
        while (!deque1.isEmpty() || !deque2.isEmpty()) {
            int digit1 = deque1.isEmpty() ? 0 : (int) deque1.removeLast();
            int digit2 = deque2.isEmpty() ? 0 : (int) deque2.removeLast();
            int sum = digit1 + digit2 + carry;
            carry = sum / 10;
            result[i] = sum % 10;
            i++;
        }
        if (carry > 0) {
            result[i] += carry;
        }

        Deque<Object> sum = new LinkedList<>();
        if (negative) {
            sum.add('-');
        }
        boolean leadingZeros = true;
        for (int k = result.length - 1; k >= 0; k--) {
            if (result[k] != 0) {
                leadingZeros = false;
            }
            if (!leadingZeros) {
                sum.add(result[k]);
            }
        }
        if (sum.isEmpty()) {
            sum.add(0);
        }
        return sum;
    }

    private static Deque<Object> Subtract(Deque<Object> deque1, Deque<Object> deque2) {
        int[] result = new int[deque1.size()];
        int borrow = 0;
        int i = 0;
        while (!deque1.isEmpty() || !deque2.isEmpty()) {
            int digit1 = deque1.isEmpty() ? 0 : (int) deque1.removeLast();
            int digit2 = deque2.isEmpty() ? 0 : (int) deque2.removeLast();
            int difference = digit1 - digit2 - borrow;
            if (difference < 0) {
                difference += 10;
                borrow = 1;
            } else {
                borrow = 0;
            }
            result[i] = difference;
            i++;
        }

        Deque<Object> difference = new LinkedList<>();
        boolean leadingZeros = true;
        for (int k = result.length - 1; k >= 0; k--) {
            if (result[k] != 0) {
                leadingZeros = false;
            }
            if (!leadingZeros) {
                difference.add(result[k]);
            }
        }
        if (difference.isEmpty()) {
            difference.add(0);
        }
        return difference;
    }
}