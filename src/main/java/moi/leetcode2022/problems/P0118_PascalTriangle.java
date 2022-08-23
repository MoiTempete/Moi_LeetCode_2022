package moi.leetcode2022.problems;

import moi.leetcode2022.utils.ArrayUtil;
import moi.leetcode2022.utils.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
Given an integer numRows, return the first numRows of Pascal's triangle.

In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:

Example 1:

Input: numRows = 5
Output: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
Example 2:

Input: numRows = 1
Output: [[1]]

Constraints:

1 <= numRows <= 30
 */
public class P0118_PascalTriangle {

    public static void main(String[] args) {
        test();
    }

    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> dp = new ArrayList<>();
        List<Integer> row = new ArrayList<>();
        List<Integer> lastRow;
        int value;
        for (int i = 0; i < numRows; i++) {
            lastRow = row;
            row = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if ((i == 1) || (j == 0 || j == i)) {
                    value = 1;
                } else if (lastRow.size() > 0) {
                    value = lastRow.get(j - 1) + lastRow.get(j);
                } else {
                    value =  Integer.MIN_VALUE;
                }
                row.add(value);
            }
            dp.add(row);
        }

        return dp;
    }

    public static void test() {
        Map<Integer, List<List<Integer>>> cases = new HashMap<>();
        cases.put(5, ArrayUtil.ofList2("[[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]"));

        for (Map.Entry<Integer, List<List<Integer>>> entry : cases.entrySet()) {
            int input = entry.getKey();
            List<List<Integer>> expect = entry.getValue();
            Logger.i("input=" + input + ", except=" + expect);
            List<List<Integer>> output = generate(input);
            if (output.equals(expect)) {
                Logger.i("case pass by output=" + output);
            } else {
                throw new AssertionError("case fail by:" + "\nexpect=" + expect + ", but output=" + output);
            }
        }
        Logger.i("All Pass");
    }
}
