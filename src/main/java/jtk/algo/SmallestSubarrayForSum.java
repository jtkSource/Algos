package jtk.algo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Dynamic Sliding Window
 */
public class SmallestSubarrayForSum {
    public static final Logger log = LoggerFactory.getLogger(SmallestSubarrayForSum.class);

    private static int smallestSubArray(int targetSum, int[] arr) {
        int minWindowSize = Integer.MAX_VALUE;
        int windowStart = 0;
        int currentWindowSum = 0;
        for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
            currentWindowSum += arr[windowEnd];
            while (currentWindowSum >= targetSum){
                minWindowSize = Math.min(minWindowSize, windowEnd - windowStart + 1);
                currentWindowSum -= arr[windowStart];
                windowStart++;
            }
        }
        return minWindowSize;
    }

    public static void main(String[] args) {
        int[] input = new int[]{4,2,2,7,8,1,2,8,10};
        int targetSum = 25;
        log.info("smallestSubarray {}", smallestSubArray(targetSum, input));
    }

}
