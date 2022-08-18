package moi.leetcode2022.problems;

import moi.leetcode2022.utils.Logger;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
There is a robot on an m x n grid. The robot is initially located at the top-left corner (i.e., grid[0][0]). The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). The robot can only move either down or right at any point in time.

Given the two integers m and n, return the number of possible unique paths that the robot can take to reach the bottom-right corner.

The test cases are generated so that the answer will be less than or equal to 2 * 10^9.

Example 1:

Input: m = 3, n = 7
Output: 28
Example 2:

Input: m = 3, n = 2
Output: 3
Explanation: From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
1. Right -> Down -> Down
2. Down -> Down -> Right
3. Down -> Right -> Down

Constraints:

1 <= m, n <= 100
 */
public class P0062_UniquePaths {

    public static void main(String[] args) {
        test();
    }

    public static int uniquePaths(int m, int n) {
        if (m < 0 || n < 0) {
            return 0;
        }
        if (m == 1 || n == 1) {
            return 1;
        }
        int[][] dp = new int[m][n];
        /*
        Because the robot can only move either down or right at any point in time.
        So, if we set the number of possible unique paths that the robot can take to reach point[i][j] is dp[i][j]
        then we can know:
            dp[i][j] = dp[i][j-1] + dp[i-1][j]
        when i<0 || j < 0: dp[i][j]=0
        dp[][] should init by
        dp[0][0] = 0;
        dp[0][1] = 1;
        dp[1][0] = 1;
        (actually,  dp[n][0] and dp[0][n] is 1)
         */
        dp[0][0] = 0;
        dp[0][1] = 1;
        dp[1][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i + j < 2) {
                    Logger.i("dp[" + i + "][" + j + "]=" + dp[i][j]);
                    continue;
                }
                dp[i][j] = (i - 1 < 0 ? 0 : dp[i - 1][j]) + (j - 1 < 0 ? 0 : dp[i][j - 1]);
                Logger.i("dp[" + i + "][" + j + "]=" + dp[i][j]);
            }
        }

        return dp[m - 1][n - 1];
    }

    public static void test() {
        Map<int[], Integer> cases = new HashMap<>();
        cases.put(new int[]{3, 2}, 3);
        cases.put(new int[]{3, 7}, 28);

        for (Map.Entry<int[], Integer> entry : cases.entrySet()) {
            int[] input = entry.getKey();
            int expect = entry.getValue();
            Logger.i("input=" + Arrays.toString(input) + ", except=" + expect);
            int output = uniquePaths(input[0], input[1]);
            if (output == expect) {
                Logger.i("case pass by output=" + output);
            } else {
                throw new AssertionError("case fail by:" + "\ninput=" + Arrays.toString(input) + ", expect=" + expect + ", but output=" + output);
            }
        }
        Logger.i("All Pass");
    }
}
