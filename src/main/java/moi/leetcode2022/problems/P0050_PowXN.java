package moi.leetcode2022.problems;

import moi.leetcode2022.utils.Logger;

import java.util.HashMap;
import java.util.Map;

/*
50. Pow(x, n)
Medium

5384

6061

Add to List

Share
Implement pow(x, n), which calculates x raised to the power n (i.e., xn).



Example 1:

Input: x = 2.00000, n = 10
Output: 1024.00000
Example 2:

Input: x = 2.10000, n = 3
Output: 9.26100
Example 3:

Input: x = 2.00000, n = -2
Output: 0.25000
Explanation: 2-2 = 1/22 = 1/4 = 0.25


Constraints:

-100.0 < x < 100.0
-2^31 <= n <= 2^31-1
-10^4 <= xn <= 10^4
 */
public class P0050_PowXN {

    public static void main(String[] args) {
        test();
    }

    public static double myPow(double x, int n) {
        double result = 1;
        int countRemaining = n;
        if (x == 1) {
            return 1.0;
        }
        if (countRemaining < 0) {
            if (n == Integer.MIN_VALUE) {
                return 0.0;
            }
            countRemaining = -1 * countRemaining;
        }
        while (countRemaining > 0) {
            if (countRemaining % 2 == 0) {
                x = x * x;
                countRemaining = countRemaining / 2;
            } else {
                result = result * x;
                countRemaining = countRemaining - 1;
            }
        }
        if (n < 0) {
            result = 1.0 / result;
        }

        return result;
    }

    public static void test() {
        Map<double[], Integer> cases = new HashMap<>();
        cases.put(new double[]{2.00000, 1024.00000}, 10);
        cases.put(new double[]{2.10000, 9.26100}, 3);
        cases.put(new double[]{2.00000, 0.25}, -2);
        cases.put(new double[]{1.00000, 1.00000}, -2000);

        for (Map.Entry<double[], Integer> entry : cases.entrySet()) {
            double input = entry.getKey()[0];
            double expect = entry.getKey()[1];
            Logger.i("input=" + input + ", n=" + entry.getValue() + ", except=" + expect);
            double output = myPow(input, entry.getValue());
            if (output == expect) {
                Logger.i("case pass by output=" + output);
            } else {
                throw new AssertionError("case fail by:" + "\ninput=" + input + ", expect=" + expect + ", but output=" + output);
            }
        }
        Logger.i("All Pass");
    }
}
