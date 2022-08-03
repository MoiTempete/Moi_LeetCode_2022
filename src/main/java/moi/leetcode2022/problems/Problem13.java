package moi.leetcode2022.problems;

import moi.leetcode2022.utils.Logger;

import java.util.HashMap;
import java.util.Map;

import static moi.leetcode2022.problems.Problem13.Roman.I;

/*
13. Roman to Integer
Easy

5514

347

Add to List

Share
Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.

Symbol       Value
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
For example, 2 is written as II in Roman numeral, just two ones added together. 12 is written as XII, which is simply X + II. The number 27 is written as XXVII, which is XX + V + II.

Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:

I can be placed before V (5) and X (10) to make 4 and 9.
X can be placed before L (50) and C (100) to make 40 and 90.
C can be placed before D (500) and M (1000) to make 400 and 900.
Given a roman numeral, convert it to an integer.



Example 1:

Input: s = "III"
Output: 3
Explanation: III = 3.
Example 2:

Input: s = "LVIII"
Output: 58
Explanation: L = 50, V= 5, III = 3.
Example 3:

Input: s = "MCMXCIV"
Output: 1994
Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.


Constraints:

1 <= s.length <= 15
s contains only the characters ('I', 'V', 'X', 'L', 'C', 'D', 'M').
It is guaranteed that s is a valid roman numeral in the range [1, 3999].
 */
public class Problem13 {

    public static int romanToInt(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        if (s.trim().length() == 0) {
            return 0;
        }
        int result = 0;
        String temp;
        String temp2;
        int i = 0;
        while (i < s.length()) {
            temp = String.valueOf(s.charAt(i));
            switch (temp) {
                case "I" -> {
                    if (i + 1 < s.length()) {
                        temp2 = String.valueOf(s.charAt(i + 1));
                        if ("X".equals(temp2) || "V".equals(temp2)) {
                            temp += temp2;
                            result += Roman.valueOf(temp).getValue();
                            i += 2;
                            break;
                        }
                    }
                    result += Roman.valueOf(temp).getValue();
                    i++;
                }
                case "X" -> {
                    if (i + 1 < s.length()) {
                        temp2 = String.valueOf(s.charAt(i + 1));
                        if ("L".equals(temp2) || "C".equals(temp2)) {
                            temp += temp2;
                            result += Roman.valueOf(temp).getValue();
                            i += 2;
                            break;
                        }
                    }
                    result += Roman.valueOf(temp).getValue();
                    i++;
                }
                case "C" -> {
                    if (i + 1 < s.length()) {
                        temp2 = String.valueOf(s.charAt(i + 1));
                        if ("D".equals(temp2) || "M".equals(temp2)) {
                            temp += temp2;
                            result += Roman.valueOf(temp).getValue();
                            i += 2;
                            break;
                        }
                    }
                    result += Roman.valueOf(temp).getValue();
                    i++;
                }
                case "V", "L", "D", "M" -> {
                    result = result + Roman.valueOf(temp).getValue();
                    i++;
                }
                default -> i++;
            }
        }

        return result;
    }

    enum Roman {

        I("I", 1),
        V("V", 5),
        X("X", 10),
        L("L", 50),
        C("C", 100),
        D("D", 500),
        M("M", 1000),
        IV("IV", 4),
        IX("IX", 9),
        XL("XL", 40),
        XC("XC", 90),
        CD("CD", 400),
        CM("CM", 900);

        private final String name;
        private final int value;

        Roman(String name, int value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public int getValue() {
            return value;
        }
    }

    public static void test() {


        Map<String, Integer> cases = new HashMap<>();
        cases.put("III", 3);
        cases.put("LVIII", 58);
        cases.put("MCMXCIV", 1994);

        for (Map.Entry<String, Integer> entry : cases.entrySet()) {
            String input = entry.getKey();
            int expect = entry.getValue();
            Logger.i("input=" + input + ", except=" + expect);
            int output = romanToInt(input);
            if (output == expect) {
                Logger.i("case pass by output=" + output);
            } else {
                throw new AssertionError("case fail by:" + "\ninput=" + input + ", expect=" + expect + ", but output=" + output);
            }
        }
        Logger.i("All Pass");
    }
}
