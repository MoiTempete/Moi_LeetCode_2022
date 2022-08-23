package moi.leetcode2022.problems;

import moi.leetcode2022.utils.ArrayUtil;
import moi.leetcode2022.utils.Logger;

import java.util.*;
import java.util.stream.Collectors;

/*
Given an integer rowIndex, return the rowIndexth (0-indexed) row of the Pascal's triangle.

In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:

Example 1:

Input: rowIndex = 3
Output: [1,3,3,1]
Example 2:

Input: rowIndex = 0
Output: [1]
Example 3:

Input: rowIndex = 1
Output: [1,1]

Constraints:

0 <= rowIndex <= 33

Follow up: Could you optimize your algorithm to use only O(rowIndex) extra space?
 */
public class P0119_PascalTriangleII {

    public static void main(String[] args) {
        test();
    }

    public static List<Integer> getRow(int rowIndex) {
        if (rowIndex < 0) {
            return new ArrayList<>();
        }
        if (rowIndex == 0) {
            return List.of(1);
        }
        if (rowIndex == 1) {
            return List.of(1, 1);
        }
        int[][] dp = new int[rowIndex + 1][rowIndex + 1];
        for (int i = 0; i < rowIndex  + 1; i++) {
            for (int j = 0; j <= i; j++) {
                if ((j == 0 || j == i)) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1];
                }
            }
        }

        return Arrays.stream(dp[rowIndex]).boxed().collect(Collectors.toList());
    }

    public static void test() {
        Map<Integer, List<Integer>> cases = new HashMap<>();
        cases.put(4, ArrayUtil.ofList("[1,4,6,4,1]"));

        for (Map.Entry<Integer, List<Integer>> entry : cases.entrySet()) {
            int input = entry.getKey();
            List<Integer> expect = entry.getValue();
            Logger.i("input=" + input + ", except=" + expect);
            List<Integer> output = getRow(input);
            if (output.equals(expect)) {
                Logger.i("case pass by output=" + output);
            } else {
                throw new AssertionError("case fail by:" + "\nexpect=" + expect + ", but output=" + output);
            }
        }
        Logger.i("All Pass");
    }
}
