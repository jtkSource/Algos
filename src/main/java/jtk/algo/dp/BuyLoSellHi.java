package jtk.algo.dp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

/**
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 *
 * You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future
 * to sell that stock.
 *
 * Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
 * Input: prices = [7,1,5,3,6,4]
 * Output: 5
 * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
 * Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.
 */
public class BuyLoSellHi {

    private static final Logger log = LoggerFactory.getLogger(BuyLoSellHi.class);
    public static int maxProfit(int[] prices) {
        return maxProfit(prices, prices.length);
    }
    static int maxProfit(int prices[], int n)
    {
        int buy = prices[0], max_profit = 0;
        for (int i = 1; i < n; i++) {

            // Checking for lower buy value
            if (buy > prices[i])
                buy = prices[i];

                // Checking for higher profit
            else if (prices[i] - buy > max_profit)
                max_profit = prices[i] - buy;
        }
        return max_profit;
    }

    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};
        log.info("Profit {}", maxProfit(prices));

        prices = new int[]{7, 6, 4, 3, 1};
        log.info("Profit {}", maxProfit(prices));
    }
}
