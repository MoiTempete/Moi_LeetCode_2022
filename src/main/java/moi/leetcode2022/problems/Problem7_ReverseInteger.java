package moi.leetcode2022.problems;

import moi.leetcode2022.utils.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * 7. Reverse Integer
 * Medium
 * <p>
 * 7819
 * <p>
 * 10293
 * <p>
 * Add to List
 * <p>
 * Share
 * Given a signed 32-bit integer x, return x with its digits reversed. If reversing x causes the value to go outside the signed 32-bit integer range [-231, 231 - 1], then return 0.
 * <p>
 * Assume the environment does not allow you to store 64-bit integers (signed or unsigned).
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: x = 123
 * Output: 321
 * Example 2:
 * <p>
 * Input: x = -123
 * Output: -321
 * Example 3:
 * <p>
 * Input: x = 120
 * Output: 21
 * <p>
 * <p>
 * Constraints:
 * <p>
 * -2^31 <= x <= 2^31 - 1
 */
public class Problem7_ReverseInteger {

    public static int reverse(int x) {
        int result = 0;
        int temp;
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            if (x == 0) {
                break;
            }
            temp = x % 10;
            x = x / 10;
            if (result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && temp > 7)) {
                return 0;
            }
            if (result < Integer.MIN_VALUE / 10 || (result == Integer.MIN_VALUE / 10 && temp < -8)) {
                return 0;
            }
            result = result * 10 + temp;
        }

        return result;
    }

    public static void test() {
        Map<Integer, Integer> cases = new HashMap<>();
        cases.put(123, 321);
        cases.put(-123, -321);
        cases.put(120, 21);
        cases.put(1534236469, 0);

        for (Map.Entry<Integer, Integer> entry : cases.entrySet()) {
            int input = entry.getKey();
            int expect = entry.getValue();
            Logger.i("input=" + input + ", except=" + expect);
            int output = reverse(input);
            if (output == expect) {
                Logger.i("case pass by output=" + output);
            } else {
                throw new AssertionError("case fail by:" + "\ninput=" + input + ", expect=" + expect + ", but output=" + output);
            }
        }
        Logger.i("All Pass");
    }

}
