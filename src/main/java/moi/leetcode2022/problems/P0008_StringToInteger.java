package moi.leetcode2022.problems;

import moi.leetcode2022.utils.Logger;

import java.util.HashMap;
import java.util.Map;

public class P0008_StringToInteger {

    public static void main(String[] args) {
        test();
    }

    public static int myAtoi(String s) {
        int ans = 0;
        int sign = 1;
        boolean signSeen = false;
        boolean digitSeen = false;

        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                int digit = ch - '0';
                digitSeen = true;
                if (ans > Integer.MAX_VALUE / 10 || ans == Integer.MAX_VALUE / 10 && digit > Integer.MAX_VALUE % 10) {
                    return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                }
                ans = ans * 10 + digit;
            } else if (ch == '-' || ch == '+') {
                if (digitSeen) {
                    break;
                }
                if (signSeen) {
                    return 0;
                }
                sign = ch == '-' ? -1 : 1;
                signSeen = true;
            } else if (ch == ' ') {
                if (signSeen || digitSeen)
                    break;
            } else {
                break;
            }
        }

        return sign * ans;
    }

    public static void test() {
        Map<String, Integer> cases = new HashMap<>();
        cases.put("  0000000000012345678", 12345678);

        for (Map.Entry<String, Integer> entry : cases.entrySet()) {
            String input = entry.getKey();
            int expect = entry.getValue();
            Logger.i("input=" + input + ", except=" + expect);
            int output = myAtoi(input);
            if (output == expect) {
                Logger.i("case pass by output=" + output);
            } else {
                throw new AssertionError("case fail by:" + "\ninput=" + input + ", expect=" + expect + ", but output=" + output);
            }
        }
        Logger.i("All Pass");
    }
}
