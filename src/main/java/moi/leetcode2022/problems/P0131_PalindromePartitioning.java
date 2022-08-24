package moi.leetcode2022.problems;

import moi.leetcode2022.utils.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
Given a string s, partition s such that every substring of the partition is a palindrome. Return all possible palindrome partitioning of s.

A palindrome string is a string that reads the same backward as forward.

Example 1:

Input: s = "aab"
Output: [["a","a","b"],["aa","b"]]
Example 2:

Input: s = "a"
Output: [["a"]]

Constraints:

1 <= s.length <= 16
s contains only lowercase English letters.
 */
public class P0131_PalindromePartitioning {

    public static void main(String[] args) {
        test();
    }

    public static List<List<String>> partition(String s) {
        List<List<String>> ans = new ArrayList<>();
        find_palindromes(0, ans, new ArrayList<>(), s);

        return ans;
    }

    public static void find_palindromes(int index, List<List<String>> ans, List<String> current_list, String s) {
        if (index >= s.length()) {
            ans.add(new ArrayList<>(current_list));
        }

        for (int i = index; i < s.length(); i++) {
            if (is_palindrome(s, index, i)) {
                current_list.add(s.substring(index, i + 1));
                find_palindromes(i + 1, ans, current_list, s);
                current_list.remove(current_list.size() - 1);
            }
        }
    }

    private static boolean is_palindrome(String s, int low, int high) {
        while (low < high) {
            if (s.charAt(low++) != s.charAt(high--))
                return false;
        }
        return true;
    }

    public static void test() {
        Map<String, List<List<String>>> cases = new HashMap<>();
        cases.put("aab", List.of(List.of("a", "a", "b"), List.of("aa", "b")));

        for (Map.Entry<String, List<List<String>>> entry : cases.entrySet()) {
            String input = entry.getKey();
            List<List<String>> expect = entry.getValue();
            Logger.i("input=" + input + ", except=" + expect);
            List<List<String>> output = partition(input);
            int count = 0;
            for (List<String> strings : expect) {
                if (output.contains(strings)) {
                    count++;
                }
            }
            if (count == expect.size()) {
                Logger.i("case pass by output=" + output);
            } else {
                throw new AssertionError("case fail by:" + "\nexpect=" + expect + ", but output=" + output);
            }
        }
        Logger.i("All Pass");
    }
}
