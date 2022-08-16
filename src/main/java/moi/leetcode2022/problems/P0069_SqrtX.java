package moi.leetcode2022.problems;

import moi.leetcode2022.utils.Logger;

import java.util.HashMap;
import java.util.Map;

/*
Given a non-negative integer x, compute and return the square root of x.

Since the return type is an integer, the decimal digits are truncated, and only the integer part of the result is returned.

Note: You are not allowed to use any built-in exponent function or operator, such as pow(x, 0.5) or x ** 0.5.

Example 1:

Input: x = 4
Output: 2
Example 2:

Input: x = 8
Output: 2
Explanation: The square root of 8 is 2.82842..., and since the decimal part is truncated, 2 is returned.

Constraints:

0 <= x <= 231 - 1
 */
public class P0069_SqrtX {

    public static void main(String[] args) {
        test();
    }

    public static int mySqrt(int x) { // binary search
        int l = 1;
        int h = x;
        int pos = 0;

        while (l <= h) {
            int m = l + (h - l) / 2;
            if (m > x / m) h = m - 1;
            else {
                pos = m;
                l = m + 1;
            }
        }
        return pos;
    }

    public static void test() {
        Map<Integer, Integer> cases = new HashMap<>();
        cases.put(0, 0);
        cases.put(1, 1);
        cases.put(2, 1);
        cases.put(3, 1);
        cases.put(4, 2);
        cases.put(8, 2);
        cases.put(2147395599, 46339);

        for (Map.Entry<Integer, Integer> entry : cases.entrySet()) {
            int input = entry.getKey();
            int expect = entry.getValue();
            Logger.i("input=" + input + ", except=" + expect);
            int output = mySqrt(input);
            if (output == expect) {
                Logger.i("case pass by output=" + output);
            } else {
                throw new AssertionError("case fail by:" + "\ninput=" + input + ", expect=" + expect + ", but output=" + output);
            }
        }
        Logger.i("All Pass");
    }
}
