package moi.leetcode2022.problems;

import moi.leetcode2022.utils.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
5. Longest Palindromic Substring
Medium

20235

1164

Add to List

Share
Given a string s, return the longest palindromic substring in s.



Example 1:

Input: s = "babad"
Output: "bab"
Explanation: "aba" is also a valid answer.
Example 2:

Input: s = "cbbd"
Output: "bb"


Constraints:

1 <= s.length <= 1000
s consist of only digits and English letters.
 */
public class Problem5 {

    public static String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        if (s.length() == 1) {
            return s;
        }

        char[] bytes = s.toCharArray();
        Map<String, ArrayList<Integer>> referenceIndexes = getReferenceIndexes(s);
//        Logger.i("input=" + "s" + ", referenceIndexes=" + referenceIndexes);
        if (referenceIndexes.size() == 1) {
            return s;
        }
        String longestResult = "";
        String current = "";

        for (Map.Entry<String, ArrayList<Integer>> entry : referenceIndexes.entrySet()) {
            current = getPalindromicString(bytes, entry);
            if (current.length() > longestResult.length()) {
                longestResult = current;
            }
        }

        return longestResult.length() == 0 ? String.valueOf(s.charAt(0)) : longestResult;
    }

    public static String getPalindromicString(char[] bytes, Map.Entry<String, ArrayList<Integer>> entry) {
        ArrayList<Integer> references;
        references = entry.getValue();
        int size = references.size();
        String result;
        if (size > 1) {
            for (int i = 0; i <= references.size() / 2; i++) {
                for (int j = references.size() - 1; j >= references.size() / 2; j--) {
                    if (isPalindromic(bytes, references.get(i), references.get(j))) {
                        result = String.valueOf(Arrays.copyOfRange(bytes, references.get(i), references.get(j)));
                        result = result + entry.getKey();
                        return result;
                    }
                }
            }
        }
        return "";
    }

    public static boolean isPalindromic(char[] s, int start, int end) {
        if (end - start <= 2) {
            return true;
        }
        int interp = (int) ((end - start) % 2 == 0 ? (end - start) / 2f : (end - start + 1) / 2f);
        for (int i = 1; i < interp; i++) {
            if (s[start + i] != s[end - i]) {
                return false;
            }
        }
        return true;
    }

    public static Map<String, ArrayList<Integer>> getReferenceIndexes(String s) {
        Map<String, ArrayList<Integer>> referenceIndexes = new HashMap<>();
        String element;
        ArrayList<Integer> elementIndexes;

        for (int i = 0; i < s.length(); i++) {
            element = String.valueOf(s.charAt(i));
            elementIndexes = referenceIndexes.get(element);
            if (elementIndexes == null || elementIndexes.size() == 0) {
                elementIndexes = new ArrayList<>();
                elementIndexes.add(i);
                referenceIndexes.put(element, elementIndexes);
            } else {
                elementIndexes.add(i);
                referenceIndexes.replace(element, elementIndexes);
            }
        }
        return referenceIndexes;
    }

    public static void test() {
        Map<String, String[]> cases = new HashMap<>();
        cases.put("babad", new String[]{"bab", "aba"});
        cases.put("cbbd", new String[]{"bb"});
        cases.put("a", new String[]{"a"});
        cases.put("ac", new String[]{"a"});
        cases.put("bacabab", new String[]{"bacab"});
        cases.put("aacabdkacaa", new String[]{"aca"});
        cases.put("1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111",
                new String[]{"1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111"});

        for (Map.Entry<String, String[]> entry : cases.entrySet()) {
            String input = entry.getKey();
            String[] expects = entry.getValue();
            Logger.i("input=" + input);
            String output = longestPalindrome(input);
            int equalsCount = 0;
            for (String expect : expects) {
                if (output.equals(expect)) {
                    equalsCount++;
                    Logger.i("case pass by output=" + output);
                    break;
                }
            }
            if (equalsCount == 0) {
                throw new AssertionError("case fail by output=" + output + ", but expect=" + Arrays.toString(expects));
            }
        }
        Logger.i("All Pass");
    }
}
