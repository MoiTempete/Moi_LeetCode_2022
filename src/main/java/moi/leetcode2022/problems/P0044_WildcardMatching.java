package moi.leetcode2022.problems;

import moi.leetcode2022.utils.Logger;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*' where:

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).
The matching should cover the entire input string (not partial).

Example 1:

Input: s = "aa", p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".
Example 2:

Input: s = "aa", p = "*"
Output: true
Explanation: '*' matches any sequence.
Example 3:

Input: s = "cb", p = "?a"
Output: false
Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.

Constraints:

0 <= s.length, p.length <= 2000
s contains only lowercase English letters.
p contains only lowercase English letters, '?' or '*'.
 */
public class P0044_WildcardMatching {

    public static void main(String[] args) {
        test();
    }

    public static boolean isMatch(String s, String p) {
        int sLength = s.length();
        int pLength = p.length();
        boolean[][] dp = new boolean[sLength + 1][pLength + 1];
        dp[0][0] = true;

        for (int j = 1; j < pLength + 1; j++) {
            dp[0][j] = p.charAt(j - 1) == '*' && dp[0][j - 1];
        }

        for (int i = 1; i < sLength + 1; i++) {
            for (int j = 1; j < pLength + 1; j++) {
                if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j - 1] &&
                            (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?');
                }
            }
        }

        return dp[sLength][pLength];
    }

    public static void test() {
        Map<String[], Boolean> cases = new HashMap<>();
        cases.put(new String[]{"aa", "a"}, false);
        cases.put(new String[]{"aa", "*"}, true);
        cases.put(new String[]{"cb", "*a"}, false);
        cases.put(new String[]{"axb", "*a*b"}, true);
        cases.put(new String[]{"acdcb", "a*c?b"}, false);

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
