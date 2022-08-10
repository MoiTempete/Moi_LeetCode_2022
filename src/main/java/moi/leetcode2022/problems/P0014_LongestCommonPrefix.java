package moi.leetcode2022.problems;

import moi.leetcode2022.utils.Logger;

import java.util.*;

public class P0014_LongestCommonPrefix {

    public static void main(String[] args) {
        test();
    }

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
        Logger.i("stringList=" + stringList);
        int i = 0;
        char letter;
        int matchCount = 0;
        StringBuilder result = new StringBuilder();
        while (i < shortestString.length()) {
            letter = shortestString.charAt(i);
            for (int j = 1; j < stringList.size(); j++) {
                if (stringList.get(j).charAt(i) == letter) {
                    matchCount ++;
                } else {
                    break;
                }
            }
            if (matchCount < stringList.size() - 1) {
                break;
            } else {
                result.append(letter);
                i++;
            }
            matchCount = 0;
        }
        return result.toString();
    }

    public static void test() {
        Map<String[], String> cases = new HashMap<>();
        cases.put(new String[]{"ab", "a"}, "a");
        cases.put(new String[]{"cir", "car"}, "c");
        cases.put(new String[]{"reflower","flow","flight"}, "");
        cases.put(new String[]{"flower","flow","flight"}, "fl");
        cases.put(new String[]{"dog","racecar","car"}, "");

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
