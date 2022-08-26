package moi.leetcode2022.problems;

import moi.leetcode2022.utils.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/*
Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence of one or more dictionary words.

Note that the same word in the dictionary may be reused multiple times in the segmentation.



Example 1:

Input: s = "leetcode", wordDict = ["leet","code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".
Example 2:

Input: s = "applepenapple", wordDict = ["apple","pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
Note that you are allowed to reuse a dictionary word.
Example 3:

Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
Output: false


Constraints:

1 <= s.length <= 300
1 <= wordDict.length <= 1000
1 <= wordDict[i].length <= 20
s and wordDict[i] consist of only lowercase English letters.
All the strings of wordDict are unique.
 */
public class P0139_WordBreak {

    public static void main(String[] args) {
        test();
    }

    //DP
    public boolean wordBreak2(String s, List<String> wordDict) {
        int n = s.length();
        // dp[i] means if subString[0, i-1] can be segmented into a space-separated sequence of one or more dictionary words.
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }


    static List<String> dir;
    static Stack<Integer> visit;
    static int[][] mem;

    // DFS with memory
    public static boolean wordBreak(String s, List<String> wordDict) {
        dir = wordDict;
        visit = new Stack<>();
        mem = new int[s.length() + 1][s.length() + 1];

        dfs(s, 0);
        for (int[] ints : mem) {
            if (ints[mem.length - 1] == 1) {
                return true;
            }
        }
        return false;
    }

    public static void dfs(String s, int startIndex) {
        if (startIndex == s.length()) {
            return;
        }
        Logger.i("########startIndex=" + startIndex + ", visit=" + visit);
        String sub;
        boolean isMatch = false;
        for (int i = 1; i <= s.length() - startIndex; i++) {
            sub = s.substring(startIndex, startIndex + i);
            if (mem[startIndex][startIndex + i] == 0) {
                isMatch = dir.contains(sub);
                if (isMatch) {
                    mem[startIndex][startIndex + i] = 1;
                    visit.push(i);
                    startIndex += i;
                    Logger.i("i=" + i + ", sub=" + sub + ", match !!!");
                    break;
                } else {
                    mem[startIndex][startIndex + i] = -1;
                    Logger.i("i=" + i + ", sub=" + sub + ", not match!!!");
                }
            }
        }
        if (isMatch) {
            Logger.i("match, go next");
            dfs(s, startIndex);
        } else if (!visit.empty()) {
            Logger.i("not match, go back");
            dfs(s, startIndex - visit.pop());
        }
    }

    public static void test() {
        Map<List<String>, Boolean> cases = new HashMap<>();
        cases.put(List.of("aaaaaaa", "aaaa", "aa"), false);
        cases.put(List.of("aabaab", "aab", "aa"), true);
        cases.put(List.of("leetcode", "leet", "code"), true);
        cases.put(List.of("applepenapple", "apple", "pen"), true);
        cases.put(List.of("catsandog", "cats", "dog", "sand", "and", "cat"), false);

        for (Map.Entry<List<String>, Boolean> entry : cases.entrySet()) {
            List<String> input = entry.getKey();
            boolean expect = entry.getValue();
            Logger.i("input=" + input + ", except=" + expect);
            boolean output = wordBreak(input.get(0), input.subList(1, input.size()));
            if (output == expect) {
                Logger.i("case pass by output=" + output);
            } else {
                throw new AssertionError("case fail by:" + "\nexpect=" + expect + ", but output=" + output);
            }
        }
        Logger.i("All Pass");
    }
}
