package jtk.basic;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by jubin on 29/1/17.
 */
public class RandomArrays {
    public static void main(String[] args) {
        Integer[] arrays = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        shuffle(arrays);
        System.out.println("arrays = " + Arrays.toString(arrays));

    }

    public static void shuffle(Integer[] arrays) {
        Random random = new Random(System.currentTimeMillis());
        //O(N)
        for (int i = 0; i < arrays.length ; i++) {
            int index = random.nextInt(15);
            int temp = arrays[index];
            arrays[index]=arrays[i];
            arrays[i]=temp;
        }
    }
}
