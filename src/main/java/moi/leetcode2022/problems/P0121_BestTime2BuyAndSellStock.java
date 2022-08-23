package moi.leetcode2022.problems;

import moi.leetcode2022.utils.ArrayUtil;
import moi.leetcode2022.utils.Logger;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
You are given an array prices where prices[i] is the price of a given stock on the ith day.

You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.

Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.



Example 1:

Input: prices = [7,1,5,3,6,4]
Output: 5
Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.
Example 2:

Input: prices = [7,6,4,3,1]
Output: 0
Explanation: In this case, no transactions are done and the max profit = 0.


Constraints:

1 <= prices.length <= 10^5
0 <= prices[i] <= 10^4
 */
public class P0121_BestTime2BuyAndSellStock {

    public static void main(String[] args) {
        test();
    }

    public static int maxProfit(int[] prices) {
        // Use Kadane's algorithm like problem 0054 - maximum subarray
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int minSofar = prices[0];
        int maxSofar = prices[0];
        int maxProfit = 0;

        for (int i = 1; i < prices.length; i++) {
            maxProfit = Math.max(maxProfit, prices[i] - minSofar);
            maxSofar = Math.max(prices[i], maxSofar);
            minSofar = Math.min(minSofar, prices[i]);
        }

        return maxProfit;
    }

    public static void test() {
        Map<int[], Integer> cases = new HashMap<>();
        cases.put(ArrayUtil.of("[7,1,5,3,6,4]"), 5);
        cases.put(ArrayUtil.of("[7,6,4,3,1]"), 0);

        for (Map.Entry<int[], Integer> entry : cases.entrySet()) {
            int[] input = entry.getKey();
            int expect = entry.getValue();
            Logger.i("input=" + Arrays.toString(input) + ", except=" + expect);
            int output = maxProfit(input);
            if (output == expect) {
                Logger.i("case pass by output=" + output);
            } else {
                throw new AssertionError("case fail by:" + "\nexpect=" + expect + ", but output=" + output);
            }
        }
        Logger.i("All Pass");
    }
}
