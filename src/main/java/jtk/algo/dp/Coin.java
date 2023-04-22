package jtk.algo.dp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * You are given an integer array coins representing coins of different denominations and an integer amount
 * representing a total amount of money.
 * Return the fewest number of coins that you need to make up that amount.
 * If that amount of money cannot be made up by any combination of the coins, return -1.
 * You may assume that you have an infinite number of each kind of coin.
 *
 * Input: coins = [1,2,5], amount = 11
 * Output: 3
 * Explanation: 11 = 5 + 5 + 1
 */
public class Coin {
    private static final Logger log = LoggerFactory.getLogger(Coin.class);
    public static int coinChange(int[] coins, int amount) {
        if(amount <= 0) return 0;
        Arrays.sort(coins);
        if(coins[0] > amount) return -1;
        int[] dp = new int[amount+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i < amount+1 ; i++) {
            for (Integer coin : coins){
                if (coin <= i){
                    if(dp[i-coin] != Integer.MAX_VALUE)
                        dp[i] = Math.min(dp[i-coin] + 1, dp[i]);
                }
            }
        }
        return dp[amount] != Integer.MAX_VALUE ? dp[amount] : -1;
    }
    public static void main(String[] args) {
        int[] coins = {3,2,5};
        log.info("Coins needed {}",coinChange(coins, 11));

        coins = new int[]{2};
        log.info("Coins needed {}",coinChange(coins, 3));
    }

}
