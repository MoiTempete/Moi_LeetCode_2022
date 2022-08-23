package moi.leetcode2022.problems;

import moi.leetcode2022.utils.Logger;

import java.util.HashMap;
import java.util.Map;

public class P0125_ValidPalindrome {

    public static void main(String[] args) {
        test();
    }

    static int lowerMin = 65;
    static int lowerMax = 90;
    static int upperMin = 97;
    static int upperMax = 122;

    public static boolean isPalindrome(String s) {
        byte[] bytes = s.getBytes();
        int left = 0;
        int right = s.length() - 1;
        byte letterLeft;
        byte letterRight;
        int typeLeft;
        int typeRight;
        while (left <= right) {
            letterLeft = bytes[left];
            letterRight = bytes[right];
            typeLeft = isStringOrNumber(letterLeft);
            typeRight = isStringOrNumber(letterRight);
            if (typeLeft == -1) {
                left++;
                continue;
            }
            if (typeRight == -1) {
                right--;
                continue;
            }
            if (typeLeft != typeRight) {
                return false;
            }
            if (Math.abs(letterRight - letterLeft) != 0 && Math.abs(letterRight - letterLeft) != 32) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static int isStringOrNumber(byte letter) {
        if ((letter >= 97 && letter <= 122) || (letter >= 65 && letter <= 90)) {
            return 1;
        } else if (letter >= 48 && letter <= 57) {
            return 0;
        } else {
            return -1;
        }
    }

    public static void test() {
        Map<String, Boolean> cases = new HashMap<>();
        cases.put("A man, a plan, a canal: Panama", true);
        cases.put("race a car", false);
        cases.put("0P", false);

        for (Map.Entry<String, Boolean> entry : cases.entrySet()) {
            String input = entry.getKey();
            boolean expect = entry.getValue();
            Logger.i("input=" + input + ", except=" + expect);
            boolean output = isPalindrome(input);
            if (output == expect) {
                Logger.i("case pass by output=" + output);
            } else {
                throw new AssertionError("case fail by:" + "\nexpect=" + expect + ", but output=" + output);
            }
        }
        Logger.i("All Pass");
    }
}
