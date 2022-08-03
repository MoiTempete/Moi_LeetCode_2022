package moi.leetcode2022.problems;

import moi.leetcode2022.utils.Logger;

import java.util.*;

/*
14. Longest Common Prefix
Easy

9387

3237

Add to List

Share
Write a function to find the longest common prefix string amongst an array of strings.

If there is no common prefix, return an empty string "".



Example 1:

Input: strs = ["flower","flow","flight"]
Output: "fl"
Example 2:

Input: strs = ["dog","racecar","car"]
Output: ""
Explanation: There is no common prefix among the input strings.


Constraints:

1 <= strs.length <= 200
0 <= strs[i].length <= 200
strs[i] consists of only lowercase English letters.
 */
public class Problem14 {

    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        List<String> stringList = Arrays.asList(strs);
        stringList.sort(Comparator.comparingInt(String::length));
        String shortestString = stringList.get(0);
        if (shortestString.length() == 0) {
            return "";
        }
        int[] indexes = new int[stringList.size()];
        Arrays.fill(indexes, -1);
        int i = shortestString.length() - 1;
        int offset = 0;
        int index = -1;
        StringBuilder result = new StringBuilder();
        char letter;
        int findOutCount = 0;
        String longestString = "";

        while (i - offset  >= 0) {
            letter = shortestString.charAt(i - offset);
            indexes[0] = i - offset;
            Logger.i("\ni=" + i + ", offset=" + offset + ", letter=" + letter + ", current result=" + result);
            for (int j = 1; j < stringList.size(); j++) {
                if (indexes[j] < 0) {
                    index = stringList.get(j).lastIndexOf(String.valueOf(letter));
                    if (index >= 0) {
                        indexes[j] = index;
                        findOutCount++;
                        Logger.i("find letter " + letter + " in " + stringList.get(j) + " @index=" + index);
                    } else {
                        Logger.i("can't find letter" + letter + " in " + stringList.get(j));
                    }
                } else {
                    index = indexes[j] - 1;
                    if (index < stringList.get(j).length() && stringList.get(j).charAt(index) == letter) {
                        indexes[j] = index;
                        findOutCount++;
                        Logger.i("find letter " + letter + " in " + stringList.get(j) + " @index=" + index);
                    } else {
                        Logger.i("char at index=" + index + " in " + stringList.get(j) + " is not match with letter=" + letter);
                    }
                }
            }
            if (findOutCount == stringList.size() - 1) {
                result = new StringBuilder(String.valueOf(letter) + result);
                Logger.i("find letter " + letter + " in all string, result=" + result);
                offset++;
            } else {
                i = i - offset - 1;
                offset = 0;
                Logger.i("fail somewhere, result=" + result);
                if (longestString.length() < result.length()) {
                    longestString = result.toString();
                }
                Arrays.fill(indexes, -1);
                result = new StringBuilder();
            }
            findOutCount = 0;
        }

        return longestString.length() < result.length() ? String.valueOf(result) : longestString;
    }

    public static void test() {
        Map<String[], String> cases = new HashMap<>();
        cases.put(new String[]{"flower","flow","flight"}, "fl");
        cases.put(new String[]{"dog","racecar","car"}, "");
        cases.put(new String[]{
                "abcdeffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffgh",
                "fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffabcdedgh",
                "ffffffffffffffffffffffffffffffffffffffabcdeffffffffffffffffffffffffffffffffffffff",
                "abcde"}, "abcde");
        cases.put(new String[]{
                "abcdeffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffgh",
                "fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffabcdedgh",
                "ffffffffffffffffffffffffffffffffffffffabcdeffffffffffffffffffffffffffffffffffffff",
                "ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffabcde"}, "ffffffffffffffffffffffffffffffffffffff");

        for (Map.Entry<String[], String> entry : cases.entrySet()) {
            String[] input = entry.getKey();
            String expect = entry.getValue();
            Logger.i("input=" + Arrays.toString(input) + ", except=" + expect);
            String output = longestCommonPrefix(input);
            if (output.equals(expect)) {
                Logger.i("case pass by output=" + output);
            } else {
                throw new AssertionError("case fail by:" + "\ninput=" + Arrays.toString(input) + ", expect=" + expect + ", but output=" + output);
            }
        }
        Logger.i("All Pass");
    }
}
