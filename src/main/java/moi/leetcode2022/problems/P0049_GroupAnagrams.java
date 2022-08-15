package moi.leetcode2022.problems;

import moi.leetcode2022.utils.Logger;

import java.util.*;

/*
49. Group Anagrams

Given an array of strings strs, group the anagrams together. You can return the answer in any order.

An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
typically using all the original letters exactly once.

Example 1:

Input: strs = ["eat","tea","tan","ate","nat","bat"]
Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
Example 2:

Input: strs = [""]
Output: [[""]]
Example 3:

Input: strs = ["a"]
Output: [["a"]]

Constraints:

1 <= strs.length <= 10^4
0 <= strs[i].length <= 100
strs[i] consists of lowercase English letters.
 */
public class P0049_GroupAnagrams {

    public static void main(String[] args) {
        test();
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }
        List<List<String>> result = new ArrayList<>();
        HashMap<List<Integer>, List<String>> anagramsList = new HashMap<>();
        for (String words : strs) {
            int[] referenceIndex = new int[26];
            byte[] bytes = words.getBytes();
            for (byte aByte : bytes) {
                referenceIndex[aByte - 97]++;
            }
            List<Integer> indexList = Arrays.stream(referenceIndex).boxed().toList();
            if (anagramsList.containsKey(indexList)) {
                anagramsList.get(indexList).add(words);
            } else {
                List<String> anagrams = new ArrayList<>();
                anagrams.add(words);
                anagramsList.put(indexList, anagrams);
            }
        }
        for (Map.Entry<List<Integer>, List<String>> entry : anagramsList.entrySet()) {
            result.add(entry.getValue());
        }
        return result;
    }


    public static void test() {
        Map<String[], List<List<String>>> cases = new HashMap<>();
        cases.put(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"},
                List.of(List.of("bat"), List.of("tan", "nat"), List.of("eat", "tea", "ate")));
        cases.put(new String[]{"a"}, List.of(List.of("a")));
        cases.put(new String[]{""}, List.of(List.of("")));

        for (Map.Entry<String[], List<List<String>>> entry : cases.entrySet()) {
            String[] input = entry.getKey();
            List<List<String>> expect = entry.getValue();
            Logger.i("input=" + Arrays.toString(input) + ", except=" + expect);
            List<List<String>> output = groupAnagrams(input);
            int matchCount = 0;
            for (List<String> list : expect) {
                if (output.contains(list)) {
                    matchCount++;
                }
            }
            if (output.size() == expect.size() && matchCount == expect.size()) {
                Logger.i("case pass by output=" + output);
            } else {
                throw new AssertionError("case fail by:" + "\ninput=" + Arrays.toString(input) +
                        ", expect=" + expect + ", but output=" + output);
            }
        }
        Logger.i("All Pass");
    }
}
