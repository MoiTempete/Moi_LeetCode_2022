package moi.leetcode2022.problems;

import moi.leetcode2022.utils.Logger;

import java.util.HashMap;
import java.util.Map;

/*
You are climbing a staircase. It takes n steps to reach the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

Example 1:

Input: n = 2
Output: 2
Explanation: There are two ways to climb to the top.
1. 1 step + 1 step
2. 2 steps
Example 2:

Input: n = 3
Output: 3
Explanation: There are three ways to climb to the top.
1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step

Constraints:

1 <= n <= 45
 */
public class P0070_ClimbingStairs {

    public static void main(String[] args) {
        test();
    }

    public static int climbStairs(int n) {
        /*
         * Easy dynamic programing problem
         * Let dp[i] = count of distinct ways to reach point [i] which distance is i
         * Cause each time we can either climb 1 or 2 steps, so dp[i] = dp[i-1] + dp[i-2]
         * And as we know dp[0]=0; dp[1]=1; dp[2]=2;
         */
        if (n <= 2) {
            return n;
        }
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        int i = 3;
        while (i <= n) {
            dp[i] = dp[i - 1] + dp[i - 2];
            i++;
        }

        return dp[n];
    }

    public static void test() {
        Map<Integer, Integer> cases = new HashMap<>();
        cases.put(1, 1);
        cases.put(2, 2);
        cases.put(3, 3);

        for (Map.Entry<Integer, Integer> entry : cases.entrySet()) {
            int input = entry.getKey();
            int expect = entry.getValue();
            Logger.i("input=" + input + ", except=" + expect);
            int output = climbStairs(input);
            if (output == expect) {
                Logger.i("case pass by output=" + output);
            } else {
                throw new AssertionError("case fail by:" + "\ninput=" + input + ", expect=" + expect + ", but output=" + output);
            }
        }
        Logger.i("All Pass");
    }
}
