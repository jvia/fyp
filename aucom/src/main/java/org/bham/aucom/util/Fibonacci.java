package org.bham.aucom.util;

import java.util.ArrayList;

public class Fibonacci {
    private static ArrayList<Integer> fibs;

    private static int fib(int i) {
        if (fibs == null) {
            fibs = new ArrayList<Integer>();
            fibs.add(0);
            fibs.add(1);
        }
        if (i == 0)
            return 0;
        if (i == 1)
            return 1;
        if (fibs.size() > i)
            return fibs.get(i);
        fibs.add(fib(i - 1) + fib(i - 2));
        return fibs.get(fibs.size() - 1);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 30; i++)
            System.out.println((15 + Fibonacci.fib(i)) % 255);
    }
}
