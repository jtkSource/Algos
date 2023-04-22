package jtk.algo.dp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed,
 * the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected
 * and it will automatically contact the police if two adjacent houses were broken into on the same night.
 *
 * Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.
 */
public class HouseRob {

    private static final Logger log = LoggerFactory.getLogger(HouseRob.class);

    private static Map<Integer, Integer> cache = new ConcurrentHashMap<>();
    public static int rob(int[] nums) {
        cache.clear();
        return maxLoot(nums, nums.length -1 );
    }
    static int maxLoot(int hval[], int n)
    {
        // base case
        if (n < 0) {
            return 0;
        }

        if (n == 0) {
            return hval[0];
        }

        // if current element is pick then previous cannot
        // be picked
        int pickmax;
        if (cache.get(n-2) == null) {
            pickmax = maxLoot(hval, n - 2);
            cache.put(n-2,pickmax);
        }else pickmax = cache.get(n-2);

        int pick = hval[n] + pickmax;

        // if current element is not picked then previous
        // element is picked

        int notPick;
        if (cache.get(n-1) == null) {
            notPick = maxLoot(hval, n - 1);
            cache.put(n-1,notPick);
        }else notPick = cache.get(n-1);

        // return max of picked and not picked
        return Math.max(pick, notPick);
    }

    public static void main(String[] args) {
        int[] nums = {2, 7, 9, 3, 1}; // 2+9+1
        log.info("houses {}", Arrays.toString(nums));
        log.info("Max Money: {}", rob(nums));


        nums = new int[]{1, 2, 3, 1}; // 1+3
        log.info("houses {}", Arrays.toString(nums));
        log.info("Max Money: {}", rob(nums));

        nums = new int[]{2,1,1,2};
        log.info("houses {}", Arrays.toString(nums));
        log.info("Max Money: {}", rob(nums));

        nums = new int[]{0};
        log.info("houses {}", Arrays.toString(nums));
        log.info("Max Money: {}", rob(nums));

        nums = new int[]{114,117,207,117,235,82,90,67,143,146,53,108,200,91,80,223,58,170,110,236,81,90,222,160,165,195,187,199,114,235,197,187,69,129,64,214,228,78,188,67,205,94,205,169,241,202,144,240};
        log.info("houses {}", Arrays.toString(nums));
        log.info("Max Money: {}", rob(nums ));

    }
}
