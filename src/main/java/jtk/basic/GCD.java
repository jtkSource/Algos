package jtk.basic;

/**
 * Created by jubin on 28/1/17.
 */

//The greatest common divisor (GCD) of two integers is the largest integer that evenly divides both of the numbers.
//O(Log N)
public class GCD {
    public static void main(String[] args) {
        System.out.println("Gcd(4851,3003) = " + Gcd(4851, 3003));
    }

    //Because the size of B decreases by a factor of at least 1/2 for every two iterations,
    // the algorithm runs in time at most O(log B).
    public static int Gcd(int a, int b){
        while (b!=0){
            //The modulus operator, which is written Mod in the pseudocode, means the remainder after division.
            // For example, 13 Mod 4 is 1 because 13 divided by 4 is 3 with a remainder of 1.
            int reminder = a%b;

            a=b;
            b=reminder;
        }
        return a;
    }
}
