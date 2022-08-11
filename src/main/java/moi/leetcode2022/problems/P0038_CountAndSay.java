package moi.leetcode2022.problems;

import moi.leetcode2022.utils.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

/*
The count-and-say sequence is a sequence of digit strings defined by the recursive formula:

countAndSay(1) = "1"
countAndSay(n) is the way you would "say" the digit string from countAndSay(n-1), which is then converted into a different digit string.
To determine how you "say" a digit string, split it into the minimal number of substrings such that each substring contains exactly one unique digit. Then for each substring, say the number of digits, then say the digit. Finally, concatenate every said digit.

For example, the saying and conversion for digit string "3322251":


Given a positive integer n, return the nth term of the count-and-say sequence.



Example 1:

Input: n = 1
Output: "1"
Explanation: This is the base case.
Example 2:

Input: n = 4
Output: "1211"
Explanation:
countAndSay(1) = "1"
countAndSay(2) = say "1" = one 1 = "11"
countAndSay(3) = say "11" = two 1's = "21"
countAndSay(4) = say "21" = one 2 + one 1 = "12" + "11" = "1211"


Constraints:

1 <= n <= 30
 */
public class P0038_CountAndSay {

    public static void main(String[] args) {
        test();
    }

    public static String countAndSay(int n) {
        if (n < 1) {
            return "";
        }
        if (n == 1) {
            return "1";
        }
        int i = 2;
        byte[] lastSayString = "11".getBytes();
        StringBuilder builder = new StringBuilder();
        while (i < n) {
            int index = 0;
            byte countingLetter = 0;
            byte letter;
            int count = 0;
            while (index < lastSayString.length) {
                letter = lastSayString[index];
                index++;
                if (countingLetter != letter) {
                    if (countingLetter != 0) {
                        builder.append(count).append((char) countingLetter);
                    }
                    countingLetter = letter;
                    count = 1;
                } else {
                    count++;
                }
                if (index == lastSayString.length) {
                    builder.append(count).append((char) countingLetter);
                }
            }

            lastSayString = builder.toString().getBytes();
            builder = new StringBuilder();
            i++;
        }
        return new String(lastSayString);
    }

    public static void test() {
        Map<Integer, String> cases = new HashMap<>();
        cases.put(1, "1");
        cases.put(2, "11");
        cases.put(3, "21");
        cases.put(4, "1211");
        cases.put(5, "111221");

        for (Map.Entry<Integer, String> entry : cases.entrySet()) {
            int input = entry.getKey();
            String expect = entry.getValue();
            Logger.i("input=" + input + ", except=" + expect);
            String output = countAndSay(input);
            if (output.equals(expect)) {
                Logger.i("case pass by output=" + output);
            } else {
                throw new AssertionError("case fail by:" + "\ninput=" + input + ", expect=" + expect + ", but output=" + output);
            }
        }
        Logger.i("All Pass");
    }
}
