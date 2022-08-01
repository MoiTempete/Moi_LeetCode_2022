package moi.leetcode2022.problems;

import moi.leetcode2022.structure.ListNode;
import moi.leetcode2022.utils.ListNodeUtil;
import moi.leetcode2022.utils.Logger;

import java.util.*;

/**
 * 3. Longest Substring Without Repeating Characters
 * Medium
 *
 * 26991
 *
 * 1170
 *
 * Add to List
 *
 * Share
 * Given a string s, find the length of the longest substring without repeating characters.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 * Example 2:
 *
 * Input: s = "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * Example 3:
 *
 * Input: s = "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */
public class Problem3 {

    public static int lengthOfLongestSubstring(String s) {
        return getLongestSubstring(s).length();
    }

    public static String getLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        StringBuilder subString = new StringBuilder();
        String lastResult = "";
        String element;
        int repeatIndex;

        for (int i=0; i<s.length(); i++) {
            element = String.valueOf(s.charAt(i));
            if (subString.isEmpty()) {
                subString.append(element);
            } else {
                repeatIndex = subString.indexOf(element);
                if (repeatIndex < 0) {
                    subString.append(element);
                } else {
                    if (lastResult.isEmpty() || lastResult.length() < subString.length()) {
                        lastResult = subString.toString();
                    }
                    subString.delete(0, repeatIndex + 1);
                    subString.append(element);
                }
            }
        }

        return subString.length() > lastResult.length() ? subString.toString() : lastResult;
    }

    public static void test() {
        Map<String, String> cases = new HashMap<>();
        cases.put("abcabcbb", "abc");
        cases.put("bbbbb", "b");
        cases.put("pwwkew", "wke");
        cases.put("dvdf", "vdf");

        for (Map.Entry<String, String> entry : cases.entrySet()) {
            String input = entry.getKey();
            String expect = entry.getValue();
            Logger.i("input=" + input);
            String output = getLongestSubstring(input);
            if (output.equals(expect)) {
                Logger.i("case pass by output=" + output);
            } else {
                throw new AssertionError("case fail by output=" + output + ", but expect=" + expect);
            }
        }
        Logger.i("All Pass");
    }
}
