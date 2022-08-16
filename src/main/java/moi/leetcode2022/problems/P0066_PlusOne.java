package moi.leetcode2022.problems;

import moi.leetcode2022.utils.Logger;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class P0066_PlusOne {

    public static void main(String[] args) {
        test();
    }

    public static int[] plusOne(int[] digits) {
        if (digits == null || digits.length == 0) {
            return new int[]{};
        }
        int i = digits.length - 1;
        boolean carry = false;
        while (i >= 0) {
            if (digits[i] != 9) {
                digits[i] = digits[i] + 1;
                break;
            } else {
                if (i == 0) {
                    carry = true;
                }
                digits[i] = 0;
                i--;
            }
        }
        if (carry) {
            int[] result = new int[digits.length + 1];
            result[0] = 1;
            System.arraycopy(digits, 0, result, 1, digits.length);
            return result;
        } else {
            return digits;
        }
    }

    public static void test() {
        Map<int[], int[]> cases = new HashMap<>();
        cases.put(new int[]{1, 2, 3}, new int[]{1, 2, 4});
        cases.put(new int[]{4, 3, 2, 1}, new int[]{4, 3, 2, 2});
        cases.put(new int[]{9}, new int[]{1, 0});

        for (Map.Entry<int[], int[]> entry : cases.entrySet()) {
            int[] input = entry.getKey();
            int[] expect = entry.getValue();
            Logger.i("input=" + Arrays.toString(input) + ", except=" + Arrays.toString(expect));
            int[] output = plusOne(input);
            if (Arrays.equals(output, expect)) {
                Logger.i("case pass by output=" + Arrays.toString(output));
            } else {
                throw new AssertionError("case fail by:" + "\ninput=" + Arrays.toString(input) + ", expect=" + Arrays.toString(expect) + ", but output=" + Arrays.toString(output));
            }
        }
        Logger.i("All Pass");
    }
}
