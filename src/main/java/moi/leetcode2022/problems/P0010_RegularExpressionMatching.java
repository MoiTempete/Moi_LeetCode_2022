package moi.leetcode2022.problems;

import moi.leetcode2022.utils.Logger;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
10. Regular Expression Matching
Hard

8831

1377

Add to List

Share
Given an input string s and a pattern p, implement regular expression matching with support for '.' and '*' where:

'.' Matches any single character.​​​​
'*' Matches zero or more of the preceding element.
The matching should cover the entire input string (not partial).



Example 1:

Input: s = "aa", p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".
Example 2:

Input: s = "aa", p = "a*"
Output: true
Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
Example 3:

Input: s = "ab", p = ".*"
Output: true
Explanation: ".*" means "zero or more (*) of any character (.)".


Constraints:

1 <= s.length <= 20
1 <= p.length <= 30
s contains only lowercase English letters.
p contains only lowercase English letters, '.', and '*'.
It is guaranteed for each appearance of the character '*', there will be a previous valid character to match.
 */
public class P0010_RegularExpressionMatching {

    public static void main(String[] args) {
        test();
    }

    public static boolean isMatch(String s, String p) {
        return s.matches(p);
    }

    public static String[] getSplitResultWithRegex(String str, char[] patterns) {
        StringBuilder result = new StringBuilder();
        byte[] bytes = str.getBytes();
        boolean matched;
        boolean lastMatched = false;
        for (byte letter : bytes) {
            matched = false;
            for (char pattern : patterns) {
                if (letter == ((byte) pattern)) {
                    if (!lastMatched) {
                        result.append(",");
                    }
                    result.append((char) letter);
                    result.append(",");
                    matched = true;
                    break;
                }
            }
            if (!matched) {
                result.append((char) letter);
            }
            lastMatched = matched;
        }
        return result.toString().split(",");
    }

    public static void test() {
        Map<String[], Boolean> cases = new HashMap<>();
        cases.put(new String[]{"aa", "a"}, false);
        cases.put(new String[]{"aa", "a*"}, true);
        cases.put(new String[]{"ab", ".*"}, true);
        cases.put(new String[]{"abbbbcba", "a.*ba"}, true);
        cases.put(new String[]{"mississippi", "mis*is*p*."}, false);

        for (Map.Entry<String[], Boolean> entry : cases.entrySet()) {
            String[] input = entry.getKey();
            boolean expect = entry.getValue();
            Logger.i("input=" + Arrays.toString(input) + ", except=" + expect);
            boolean output = isMatch(input[0], input[1]);
            if (output == expect) {
                Logger.i("case pass by output=" + output);
            } else {
                throw new AssertionError("case fail by:" + "\ninput=" + Arrays.toString(input) + ", expect=" + expect + ", but output=" + output);
            }
        }
        Logger.i("All Pass");
    }
}
