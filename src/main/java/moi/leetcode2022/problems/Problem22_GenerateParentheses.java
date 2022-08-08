package moi.leetcode2022.problems;

import moi.leetcode2022.utils.Logger;

import java.util.*;

/*
22. Generate Parentheses
Medium

14414

541

Add to List

Share
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.



Example 1:

Input: n = 3
Output: ["((()))","(()())","(())()","()(())","()()()"]
Example 2:

Input: n = 1
Output: ["()"]


Constraints:

1 <= n <= 8
 */
public class Problem22_GenerateParentheses {

    public static List<String> generateParenthesis(int n) {
        List<String> combinations = new ArrayList<>();
        generateAll(new char[2 * n], 0, combinations);
        return combinations;
    }

    public static void generateAll(char[] current, int pos, List<String> result) {
        if (pos == current.length) {
            if (valid(current))
                result.add(new String(current));
        } else {
            current[pos] = '(';
            generateAll(current, pos + 1, result);
            current[pos] = ')';
            generateAll(current, pos + 1, result);
        }
    }

    public static boolean valid(char[] current) {
        int balance = 0;
        for (char c : current) {
            if (c == '(') balance++;
            else balance--;
            if (balance < 0) return false;
        }
        return (balance == 0);
    }

    public static void test() {
        Map<Integer, List<String>> cases = new HashMap<>();
        cases.put(3, List.of("((()))", "(()())", "(())()", "()(())", "()()()"));
        cases.put(1, List.of("()"));
        cases.put(4, List.of("()"));

        for (Map.Entry<Integer, List<String>> entry : cases.entrySet()) {
            int input = entry.getKey();
            List<String> expect = entry.getValue();
            Logger.i("input=" + input + ", except=" + expect);
            List<String> output = generateParenthesis(input);
            if (output.equals(expect)) {
                Logger.i("case pass by output=" + output);
            } else {
                throw new AssertionError("case fail by:" + "\ninput=" + input + ", expect=" + expect + ", but output=" + output);
            }
        }
        Logger.i("All Pass");
    }
}
