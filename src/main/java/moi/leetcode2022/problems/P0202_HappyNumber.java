package moi.leetcode2022.problems;

import moi.leetcode2022.utils.Logger;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
Write an algorithm to determine if a number n is happy.

A happy number is a number defined by the following process:

Starting with any positive integer, replace the number by the sum of the squares of its digits.
Repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1.
Those numbers for which this process ends in 1 are happy.
Return true, if n is a happy number, and false, if not.

Example 1:

Input: n = 19
Output: true,
Explanation:
12 + 92 = 82
82 + 22 = 68
62 + 82 = 100
12 + 02 + 02 = 1
Example 2:

Input: n = 2
Output: false,


Constraints:

1 <= n <= 2^31 - 1
 */
public class P0202_HappyNumber {

    public static void main(String[] args) {
        test();
    }
//
//    public static boolean isHappy(int n) {
//        boolean[] minSet = new boolean[]{true, false, false, false, false, false, true, false, false, true, false, false, true, false, false, false, false, false, true, false, false, false, true, false, false, false, false, true, false, false, true, true, false, false, false, false, false, false, false, false, false, false, false, true, false, false, false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, false, true, false, false, false, false, false, false, false, false, true, false, false, true, false, false, false, true, false, false, false, false, true, false, false, true, false, false, true, false, false, true, false, false, true, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, true, false, false, true, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false, false, false, true, false, true, false, true, true, false, false, false, false, false, false, false, false, false, true, false, false, false, false, true, false, false, false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, true, false, false, false, true, false, false, false, false, false, true, false, false, true, false, false, false, false};
//        int result = 0;
//        while (n > 243) {
//            result = sum(n);
//            n = result;
//        }
//        return minSet[result];
//    }
//
//    public static int sum(int num) {
//        int result = 0;
//        while (num != 0) {
//            result = result + (num % 10) * (num % 10);
//            num = num / 10;
//        }
//        return result;
//    }

    public static boolean isHappy(int n) {
        return helper(n, new HashSet<>());
    }

    private static boolean helper(int n, Set<Integer> seen) {
        if (n == 1) {
            return true;
        }

        int val = 0;
        while (n > 0) {
            int d = n % 10;
            n = n / 10;
            val += d * d;
        }
        if (seen.contains(val)) {
            return false;
        }
        seen.add(val);
        return helper(val, seen);
    }

    public static void test() {
        Map<Integer, Boolean> cases = new HashMap<>();
        cases.put(19, true);

        for (Map.Entry<Integer, Boolean> entry : cases.entrySet()) {
            int input = entry.getKey();
            boolean expect = entry.getValue();
            Logger.i("input=" + input + ", except=" + expect);
            boolean output = isHappy(input);
            if (output == expect) {
                Logger.i("case pass by output=" + output);
            } else {
                throw new AssertionError("case fail by:" + "\nexpect=" + expect + ", but output=" + output);
            }
        }
        Logger.i("All Pass");
    }
}
