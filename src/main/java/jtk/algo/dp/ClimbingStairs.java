package jtk.algo.dp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * You are climbing a staircase. It takes n steps to reach the top.
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 * Input: n = 2
 * Output: 2
 * Explanation: There are two ways to climb to the top.
 * 1. 1 step + 1 step
 * 2. 2 steps
 * Input: n = 3
 *
 * Output: 3
 * Explanation: There are three ways to climb to the top.
 * 1. 1 step + 1 step + 1 step
 * 2. 1 step + 2 steps
 * 3. 2 steps + 1 step
 * https://www.youtube.com/watch?v=Y0lT9Fck7qI
 */
public class ClimbingStairs {
    private static final Logger log = LoggerFactory.getLogger(ClimbingStairs.class);
    public static int climbStairs(int n) {
        int prev1 = 1; // dp[i - 1]
        int prev2 = 1; // dp[i - 2]

        for (int i = 2; i <= n; ++i) {
            final int dp = prev1 + prev2;
            prev2 = prev1;
            prev1 = dp;
        }

        return prev1;

    }

    public static void main(String[] args) {
        int n = 3;
        log.info("Number of ways: {}",climbStairs(3) );
    }
}
