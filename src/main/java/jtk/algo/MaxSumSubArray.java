package jtk.algo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Fixed Sliding Window
 * Find the max sum subarray of a fixed size K
 * Example input:
 * [4,2,1,7,8,1,2,8,1,0]
 */
public class MaxSumSubArray {
    private static final Logger log = LoggerFactory.getLogger(MaxSumSubArray.class);
    public static int findMaxSumSubArray(int[] arr, int k){
        int maxValue = Integer.MIN_VALUE;
        int currentRunningSum = 0;
        for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
            currentRunningSum += arr[windowEnd];
            if(windowEnd >= (k-1)){
              maxValue = Math.max(maxValue, currentRunningSum);
              currentRunningSum -= arr[windowEnd-(k-1)] ;
            }
        }
        return maxValue;
    }
    public static void main(String[] args) {
        log.info("{}",
                findMaxSumSubArray(new int[]{4,2,1,7,8,1,2,8,1,0},3));
    }
}
