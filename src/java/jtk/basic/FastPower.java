package jtk.basic;

/**
 * Created by jubin on 29/1/17.
 */

import java.util.*;

/**
 * A^(2×M) = (A^M)^2
 * A^(M+N) = A^M × A^N
 */
public class FastPower {

    private static Map<Integer,Double> powerMap = new HashMap<>();

    //will not work for powers < 4
    public static void main(String[] args) {

        long startTime = System.nanoTime();
        System.out.println("Math.pow(29,199) = " + Math.pow(29, 199));
        long endTime = System.nanoTime();
        System.out.println("(endTime - startTime) = " + (endTime - startTime) / 10_000);

        startTime = System.nanoTime();
        System.out.println("calculatePower(29,199) = " + calculatePower(29.0, 199));
        endTime = System.nanoTime();
        System.out.println("(endTime - startTime) = " + (endTime - startTime) / 10_000);

    }

    private static double calculatePower(double base, double exp) {
        if (exp <= 4)
            throw new UnsupportedOperationException("Power cannot be less than or equal to 4");
        powerMap.clear();
        calculateForPow2(base,exp);
        return sumPowers(exp);
        //O(log(n)) + O(log(n)) = O(log(n))
    }


    // * A^(M+N) = A^M × A^N
    private static double sumPowers(double exp) {
        NavigableSet navigableSet = new TreeSet(powerMap.keySet());
        Iterator iter = navigableSet.descendingIterator();
        int exp1=0;
        double result=1.0;
        while (iter.hasNext()){//O(log(n)) because it iterates over the reduced set.
            int temp = (int) iter.next();
            if (exp1 + temp <= exp) {
                result *= powerMap.get(temp);
                exp1 += temp;

            }
        }
        return result;
    }

    //A^(2×M) = (A^M)^2
    // use above formula to find all powers in multiple of two.
    //powerMap will store the calculated powers in multiples of 2 for a base
    private static void calculateForPow2(double base, double exp){
        for (int i = 1; i < exp; i=i*2) { //log(n) -- because the numbers are reduced by half in each iteration
            powerMap.put(i,Math.pow(base,i));
        }
    }
}
