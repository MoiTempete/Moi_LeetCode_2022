package moi.leetcode2022.problems;

import moi.leetcode2022.utils.Logger;

import java.util.HashMap;
import java.util.Map;

/*
Given a string columnTitle that represents the column title as appears in an Excel sheet, return its corresponding column number.

For example:

A -> 1
B -> 2
C -> 3
...
Z -> 26
AA -> 27
AB -> 28
...


Example 1:

Input: columnTitle = "A"
Output: 1
Example 2:

Input: columnTitle = "AB"
Output: 28
Example 3:

Input: columnTitle = "ZY"
Output: 701


Constraints:

1 <= columnTitle.length <= 7
columnTitle consists only of uppercase English letters.
columnTitle is in the range ["A", "FXSHRXW"].
 */
public class P0171_ExcelSheetColumnNumber {

    public static void main(String[] args) {
        test();
    }

    public static int titleToNumber(String columnTitle) {
        int length = columnTitle.length();
        int result = 0;
        for (int i = 0; i < length; i++) {
            result = result * 26  + columnTitle.charAt(i) - 64;
        }
        return result;
    }

    public static void test() {
        Map<String, Integer> cases = new HashMap<>();
        cases.put("AZ", 52);
        cases.put("AA", 27);
        cases.put("ZY", 701);

        for (Map.Entry<String, Integer> entry : cases.entrySet()) {
            String input = entry.getKey();
            int expect = entry.getValue();
            Logger.i("input=" + input + ", except=" + expect);
            int output = titleToNumber(input);
            if (output == expect) {
                Logger.i("case pass by output=" + output);
            } else {
                throw new AssertionError("case fail by:" + "\nexpect=" + expect + ", but output=" + output);
            }
        }
        Logger.i("All Pass");
    }
}
