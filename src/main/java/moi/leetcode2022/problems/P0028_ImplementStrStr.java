package moi.leetcode2022.problems;

import moi.leetcode2022.utils.Logger;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
28. Implement strStr()
Easy

4726

3859

Add to List

Share
Implement strStr().

Given two strings needle and haystack, return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.

Clarification:

What should we return when needle is an empty string? This is a great question to ask during an interview.

For the purpose of this problem, we will return 0 when needle is an empty string. This is consistent to C's strstr() and Java's indexOf().



Example 1:

Input: haystack = "hello", needle = "ll"
Output: 2
Example 2:

Input: haystack = "aaaaa", needle = "bba"
Output: -1


Constraints:

1 <= haystack.length, needle.length <= 10^4
haystack and needle consist of only lowercase English characters.
 */
public class P0028_ImplementStrStr {

    public static void main(String[] args) {
        test();
    }

    public static int strStr(String haystack, String needle) {
        if (haystack == null || haystack.length() == 0) {
            return 0;
        }
        if (needle == null || needle.length() == 0) {
            return 0;
        }
        int hayIndex = 0;
        int needleIndex = 0;
        char hayChar;
        char needleChar;
        int matchedCount = 0;
        while (hayIndex < haystack.length()) {
            hayChar = haystack.charAt(hayIndex);
            needleChar = needle.charAt(needleIndex);
            if (hayChar == needleChar) {
                hayIndex++;
                needleIndex++;
                matchedCount++;
                if (needleIndex == needle.length()) {
                    return hayIndex - needle.length();
                }
            } else {
                if (needleIndex == 0) {
                    hayIndex++;
                } else {
                    needleIndex = 0;
                    if (matchedCount != 0) {
                        hayIndex = hayIndex - matchedCount + 1;
                        matchedCount = 0;
                    }
                }
            }
        }

        return -1;
    }

    public static void test() {
        Map<String[], Integer> cases = new HashMap<>();
//        cases.put(new String[]{"hello", "ll"}, 2);
//        cases.put(new String[]{"aaaaa", "bba"}, -1);
//        cases.put(new String[]{"aabba", "bba"}, 2);
        cases.put(new String[]{"mississippi", "issip"}, 4);

        for (Map.Entry<String[], Integer> entry : cases.entrySet()) {
            String[] input = entry.getKey();
            int expect = entry.getValue();
            Logger.i("input=" + Arrays.toString(input) + ", except=" + expect);
            int output = strStr(input[0], input[1]);
            if (output == (expect)) {
                Logger.i("case pass by output=" + output);
            } else {
                throw new AssertionError("case fail by:" + "\ninput=" + Arrays.toString(input) + ", expect=" + expect + ", but output=" + output);
            }
        }
        Logger.i("All Pass");
    }
}
