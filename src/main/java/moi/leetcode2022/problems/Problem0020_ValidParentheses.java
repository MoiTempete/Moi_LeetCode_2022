package moi.leetcode2022.problems;

import moi.leetcode2022.utils.Logger;

import java.util.*;

/*
20. Valid Parentheses
Easy

14730

715

Add to List

Share
Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.


Example 1:

Input: s = "()"
Output: true
Example 2:

Input: s = "()[]{}"
Output: true
Example 3:

Input: s = "(]"
Output: false


Constraints:

1 <= s.length <= 10^4
s consists of parentheses only '()[]{}'.
 */
public class Problem0020_ValidParentheses {

    public static boolean isValid(String s) {
        if (s == null || s.length() < 2 || s.length() % 2 != 0) {
            return false;
        }
        Stack<Character> patterns = new Stack<>();
        char c;
        int i;
        for (i = 0; i < s.length(); i++) {
            c = s.charAt(i);
            switch (c) {
                case '(':
                case '{':
                case '[':
                    patterns.add(c);
                    break;
                case ')':
                case ']':
                case '}':
                    if (patterns.size() > 0 && patterns.peek().equals(getPair(c))) {
                        patterns.pop();
                        break;
                    }
                default:
                    return false;

            }
        }
        return i == s.length() && patterns.size() == 0;
    }

    public static char getPair(char c) {
        return switch (c) {
            case ')' -> '(';
            case ']' -> '[';
            case '}' -> '{';
            default -> throw new AssertionError("Invalid input");
        };
    }

    public static void test() {
        Map<String, Boolean> cases = new HashMap<>();
        cases.put("()", true);
        cases.put("()[]{}", true);
        cases.put("([{}])", true);
        cases.put("(]", false);
        cases.put("([{])}", false);
        cases.put("((((", false);
        cases.put("){", false);

        for (Map.Entry<String, Boolean> entry : cases.entrySet()) {
            String input = entry.getKey();
            boolean expect = entry.getValue();
            Logger.i("input=" + input + ", except=" + expect);
            boolean output = isValid(input);
            if (output == expect) {
                Logger.i("case pass by output=" + output);
            } else {
                throw new AssertionError("case fail by:" + "\ninput=" + input + ", expect=" + expect + ", but output=" + output);
            }
        }
        Logger.i("All Pass");
    }
}
