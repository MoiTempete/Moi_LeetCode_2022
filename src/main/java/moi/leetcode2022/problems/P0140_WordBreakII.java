package moi.leetcode2022.problems;

import moi.leetcode2022.utils.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
Given a string s and a dictionary of strings wordDict,
add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences in any order.

Note that the same word in the dictionary may be reused multiple times in the segmentation.

Example 1:

Input: s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
Output: ["cats and dog","cat sand dog"]
Example 2:

Input: s = "pineapplepenapple", wordDict = ["apple","pen","applepen","pine","pineapple"]
Output: ["pine apple pen apple","pineapple pen apple","pine applepen apple"]
Explanation: Note that you are allowed to reuse a dictionary word.
Example 3:

Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
Output: []


Constraints:

1 <= s.length <= 20
1 <= wordDict.length <= 1000
1 <= wordDict[i].length <= 10
s and wordDict[i] consist of only lowercase English letters.
All the strings of wordDict are unique.
 */
public class P0140_WordBreakII {

    public static void main(String[] args) {
        test();
    }

    static List<String> res;
    static List<String> list;

    public static List<String> wordBreak(String s, List<String> wordDict) {
        res = new ArrayList<>();
        list = new ArrayList<>();
        backTracking(s, wordDict, 0);
        return res;
    }

    public static void backTracking(String s, List<String> wordDict, int start) {
        if (start == s.length()) {
            res.add(String.join(" ", list));
            return;
        }
        for (int i = start; i < s.length(); i++) {
            if (wordDict.contains(s.substring(start, i + 1))) {
                list.add(s.substring(start, i + 1));
                backTracking(s, wordDict, i + 1);
                list.remove(list.size() - 1);
            }
        }
    }

    public static void test() {
        Map<List<String>, List<String>> cases = new HashMap<>();
        cases.put(List.of("catsanddog", "cat", "cats", "and", "sand", "dog"), List.of("cats and dog", "cat sand dog"));
        cases.put(List.of("pineapplepenapple", "apple", "pen", "applepen", "pine", "pineapple"), List.of("pine apple pen apple", "pineapple pen apple", "pine applepen apple"));

        for (Map.Entry<List<String>, List<String>> entry : cases.entrySet()) {
            List<String> input = entry.getKey();
            List<String> expect = entry.getValue();
            Logger.i("input=" + input + ", except=" + expect);
            List<String> output = wordBreak(input.get(0), input.subList(1, input.size()));
            int count = 0;
            for (String s : expect) {
                if (output.contains(s)) {
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
