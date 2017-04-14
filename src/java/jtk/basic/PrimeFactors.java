package jtk.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Created by jubin on 29/1/17.
 */
public class PrimeFactors {

    private static Predicate<Integer> isMultipleOfTwo = integer -> integer % 2 == 0;

    public static List<Integer> primeFactorization(final Supplier<Integer> numberSupplier) {

        final List<Integer> factors = new ArrayList<>();

        int number = numberSupplier.get();
        /*
        You don't need to test whether the number is divisible by any even number other than 2 because,
        if it is divisible by any even number, it is divisible by 2.
        This means that you only need to check divisibility by 2 and then by odd numbers instead of by all possible factors.
        Doing so cuts the run time in half. O(log n)
         */

        while (isMultipleOfTwo.test(number)) {
            factors.add(2);
            number = number /2;
        }

        /*
        You only need to check for factors up to the square root of the number.
        If n = p × q, either p or q must be less than or equal to sqrt(n).
        (If both are greater than sqrt(n), their product is greater than n.) If you check possible factors up to sqrt(n),
         you will find the smaller factor, and when you divide n by that factor, you will find the other one.
         This reduces the run time to O(sqrt(n)).
         */
        int i = 3;
        int max_factor = (int) Math.sqrt(number);
        while (i <= max_factor){
                while (number%i ==0){
                factors.add(i);
                number=number/i;
                max_factor = (int) Math.sqrt(number);
            }
            i=i+2;//check next odd number
        }
        if(number>1)
            factors.add(number);
        return factors;
    }

    private static Integer calculateLCD(Supplier<List<Integer>> primeFactors1, Supplier<List<Integer>> primeFactors2) {
        System.out.println("primeFactors1 = " + primeFactors1.get());
        System.out.println("primeFactors2 = " + primeFactors2.get());
        primeFactors1.get().forEach(integer -> primeFactors2.get().remove(integer));
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.addAll(primeFactors1.get());
        arrayList.addAll(primeFactors2.get());
        return arrayList.stream().reduce((o, o2) -> o * o2).get();
    }

    public static void main(String[] args) {

        System.out.println("calculateLCD(primeFactorization(625),primeFactorization(16788)) = " +
                calculateLCD(() -> primeFactorization(() -> 625), () -> primeFactorization(() -> 16788)));


    }


}
