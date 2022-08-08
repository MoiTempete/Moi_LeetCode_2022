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

    /* faster
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
     */

    /**
     * funny
     */
    public static List<String> generateParenthesis(int n) {

        List<String> combinations = new ArrayList<>();
        List<String> indexes = new ArrayList<>();
        StringBuilder maxBinary = new StringBuilder();
        StringBuilder minBinary = new StringBuilder();

        int i = 0;
        while (i < 2 * n) {
            /*
            eg: when n=2
            max valid binary = 111000 = ((()))
            min valid binary = 101010 = ()()()
             */
            if (i < n) {
                maxBinary.append("1");
            } else {
                maxBinary.append("0");
            }
            if (i % 2 == 0) {
                minBinary.append("1");
            } else {
                minBinary.append("0");
            }
            i++;
        }
        int min = Integer.valueOf(minBinary.toString(), 2);
        int max = Integer.valueOf(maxBinary.toString(), 2);
        if (min == max) {
            indexes.add(minBinary.toString());
            return convertToPattern(indexes, combinations);
        }
        indexes.add(minBinary.toString());
        indexes.add(maxBinary.toString());
        Logger.i("maxBinary=" + maxBinary + ", minBinary=" + minBinary);

        String binary = "";
        for (int j = min + 2; j <= max - 2; j = j + 2) {
            binary = Integer.toBinaryString(j);
            if (checkBinaryIndex(binary)) {
                indexes.add(binary);
            }
        }
        Logger.i("indexes=" + indexes);

        return convertToPattern(indexes, combinations);
    }

    public static List<String> convertToPattern(List<String> binaryIndexes, List<String> result) {
        for (String index : binaryIndexes) {
            index = index.replace("1", "(");
            index = index.replace("0", ")");
            result.add(index);
        }
        Logger.i("combinations=" + result);
        return result;
    }

    public static boolean checkBinaryIndex(String s) {
        if (s == null || s.length() < 2 || s.length() % 2 != 0) {
            return false;
        }
        Stack<Character> patterns = new Stack<>();
        char c;
        int i;
        for (i = 0; i < s.length(); i++) {
            c = s.charAt(i);
            switch (c) {
                case '1':
                    patterns.add(c);
                    break;
                case '0':
                    if (patterns.size() > 0 && patterns.peek().equals('1')) {
                        patterns.pop();
                        break;
                    }
                default:
                    return false;

            }
        }
        return i == s.length() && patterns.size() == 0;
    }

    public static void test() {
        Map<Integer, List<String>> cases = new HashMap<>();
        cases.put(3, List.of("()()()", "((()))", "()(())", "(())()", "(()())"));
        cases.put(1, List.of("()"));

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
