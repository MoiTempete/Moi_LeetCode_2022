package moi.leetcode2022.problems;

import moi.leetcode2022.utils.Logger;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
29. Divide Two Integers
Medium

3360

11332

Add to List

Share
Given two integers dividend and divisor, divide two integers without using multiplication, division, and mod operator.

The integer division should truncate toward zero, which means losing its fractional part. For example, 8.345 would be truncated to 8, and -2.7335 would be truncated to -2.

Return the quotient after dividing dividend by divisor.

Note: Assume we are dealing with an environment that could only store integers within the 32-bit signed integer range: [−231, 231 − 1]. For this problem, if the quotient is strictly greater than 231 - 1, then return 231 - 1, and if the quotient is strictly less than -231, then return -231.



Example 1:

Input: dividend = 10, divisor = 3
Output: 3
Explanation: 10/3 = 3.33333.. which is truncated to 3.
Example 2:

Input: dividend = 7, divisor = -3
Output: -2
Explanation: 7/-3 = -2.33333.. which is truncated to -2.


Constraints:

-2^31 <= dividend, divisor <= 2^31 - 1
divisor != 0
 */
public class Problem0029_DivideTwoIntegers {

    /*
    bad way
     */
//    public static int divide(int dividend, int divisor) {
//        if (divisor == 0) {
//            throw new AssertionError("Invalid Input: can not divide by 0");
//        }
//        if (dividend == 0) {
//            return 0;
//        }
//        if (dividend == divisor) {
//            return 1;
//        }
//        if (divisor == 1) {
//            return dividend;
//        }
//        if (divisor == -1) {
//            if (dividend == Integer.MIN_VALUE) {
//                return Integer.MAX_VALUE;
//            }
//        }
//        int result = 0;
//        int inflatedDivisor = divisor;
//        if ((dividend < 0 && divisor < 0)) { // -150 / -2
//            dividend = dividend - divisor;
//            if (dividend > 0) {
//                return result;
//            }
//            result++;
//            while (dividend - inflatedDivisor <= 0) {
//                result = result + result;
//                dividend = dividend - inflatedDivisor;
//                inflatedDivisor = inflatedDivisor + inflatedDivisor;
//                if (inflatedDivisor >= 0) {
//                    break;
//                }
//            }
//            while (dividend - divisor <= 0) {
//                dividend = dividend - divisor;
//                if (result == Integer.MAX_VALUE) {
//                    return result;
//                }
//                result++;
//            }
//        } else if (dividend < 0) { // -150 / 2
//            dividend = dividend + divisor;
//            if (dividend > 0) {
//                return result;
//            }
//            result--;
//            while (dividend + inflatedDivisor <= 0) {
//                result = result + result;
//                dividend = dividend + inflatedDivisor;
//                inflatedDivisor = inflatedDivisor + inflatedDivisor;
//                if (inflatedDivisor <= 0) {
//                    break;
//                }
//            }
//            while (dividend + divisor <= 0) {
//                dividend = dividend + divisor;
//                if (result == Integer.MAX_VALUE) {
//                    return result;
//                }
//                result--;
//            }
//        } else if (divisor > 0) { // 150 /2
//            dividend = dividend - divisor;
//            if (dividend < 0) {
//                return result;
//            }
//            result++;
//            while (dividend - inflatedDivisor >= 0) {
//                dividend = dividend - inflatedDivisor;
//                inflatedDivisor = inflatedDivisor + inflatedDivisor;
//                result = result + result;
//            }
//            while (dividend - divisor >= 0) {
//                dividend = dividend - divisor;
//                if (result == Integer.MAX_VALUE) {
//                    return result;
//                }
//                result++;
//            }
//        } else { // 150 / -2
//            dividend = dividend + divisor;
//            if (dividend < 0) {
//                return result;
//            }
//            result--;
//            while (dividend + inflatedDivisor >= 0) {
//                dividend = dividend + inflatedDivisor;
//                inflatedDivisor = inflatedDivisor + inflatedDivisor;
//                result = result + result;
//            }
//            while (dividend + divisor >= 0) {
//                dividend = dividend + divisor;
//                if (result == Integer.MAX_VALUE) {
//                    return result;
//                }
//                result--;
//            }
//        }
//
//        return result;
//    }

    //cheat way !!!{divide two integers without using multiplication, division, and mod operator.}
    public static int divide(int dividend, int divisor) {
        if (dividend == 1 << 31 && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        boolean sign = (dividend >= 0) == (divisor >= 0);
        dividend = Math.abs(dividend); //cheat!!
        divisor = Math.abs(divisor); //cheat!!
        int result = 0;
        while (dividend - divisor >= 0) {
            int count = 0;
            while (dividend - (divisor << 1 << count) >= 0) {
                count++;
            }
            result += 1 << count;
            dividend -= divisor << count;
        }
        return sign ? result : -result;
    }

    public static void test() {
        Map<int[], Integer> cases = new HashMap<>();
        cases.put(new int[]{-150, -2}, 75);
        cases.put(new int[]{-150, 2}, -75);
        cases.put(new int[]{150, -2}, -75);
        cases.put(new int[]{150, 2}, 75);
        cases.put(new int[]{-151, -2}, 75);
        cases.put(new int[]{-151, 2}, -75);
        cases.put(new int[]{151, -2}, -75);
        cases.put(new int[]{151, 2}, 75);
        cases.put(new int[]{-2147483648, -1}, 2147483647);
        cases.put(new int[]{-2147483648, 1}, -2147483648);
        cases.put(new int[]{-2147483648, -2147483648}, 1);
        cases.put(new int[]{2147483647, 1}, 2147483647);
        cases.put(new int[]{2147483647, -1}, -2147483647);
        cases.put(new int[]{-2147483648, 2}, -1073741824);
        cases.put(new int[]{2147483647, 2}, 1073741823);
        cases.put(new int[]{2147483647, -2}, -1073741823);
        cases.put(new int[]{-2147483648, -2}, 1073741824);

        for (Map.Entry<int[], Integer> entry : cases.entrySet()) {
            int[] input = entry.getKey();
            int expect = entry.getValue();
            Logger.i("input=" + Arrays.toString(input) + ", except=" + expect);
            int output = divide(input[0], input[1]);
            if (output == expect) {
                Logger.i("case pass by output=" + output);
            } else {
                throw new AssertionError("case fail by:" + "\ninput=" + Arrays.toString(input) + ", expect=" + expect + ", but output=" + output);
            }
        }
        Logger.i("All Pass");
    }
}
