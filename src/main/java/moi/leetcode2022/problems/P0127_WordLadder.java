package moi.leetcode2022.problems;

import moi.leetcode2022.utils.Logger;

import java.util.*;

/*
A transformation sequence from word beginWord to word endWord using a dictionary
wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:

Every adjacent pair of words differs by a single letter.
Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
sk == endWord
Given two words, beginWord and endWord, and a dictionary wordList, return the number of words in the
shortest transformation sequence from beginWord to endWord, or 0 if no such sequence exists.



Example 1:

Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
Output: 5
Explanation: One shortest transformation sequence is "hit" -> "hot" -> "dot" -> "dog" -> cog", which is 5 words long.
Example 2:

Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
Output: 0
Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.


Constraints:

1 <= beginWord.length <= 10
endWord.length == beginWord.length
1 <= wordList.length <= 5000
wordList[i].length == beginWord.length
beginWord, endWord, and wordList[i] consist of lowercase English letters.
beginWord != endWord
All the words in wordList are unique.
 */
public class P0127_WordLadder {

    public static void main(String[] args) {
        test();
    }
    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        /*
         * std BFS
         * BFS code template:
         * bfs(s) {
         *   q = new queue()
         *   q.push(s), visited[s] = true
         *   while (!q.empty()) {
         *     u = q.pop()
         *     for each edge(u, v) {
         *       if (!visited[v]) {
         *         q.push(v)
         *         visited[v] = true
         *       }
         *     }
         *   }
         * }
         */
        Set<String> set = new HashSet<>(wordList);
        if (!set.contains(endWord)) return 0;

        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);

        Set<String> visited = new HashSet<>();
        queue.add(beginWord);

        int changes = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String word = queue.poll();
                assert word != null;
                if (word.equals(endWord)) return changes;

                for (int j = 0; j < word.length(); j++) {
                    for (int k = 'a'; k <= 'z'; k++) {
                        char[] arr = word.toCharArray();
                        arr[j] = (char) k;

                        String str = new String(arr);
                        if (set.contains(str) && !visited.contains(str)) {
                            queue.add(str);
                            visited.add(str);
                        }
                    }
                }
            }
            ++changes;
        }
        return 0;
    }

    public static void test() {
        Map<List<String>, Integer> cases = new HashMap<>();
        cases.put(List.of("hit", "cog", "hot", "dot", "dog", "lot", "log", "cog"), 5);
        cases.put(List.of("a", "c", "a", "b", "c"), 2);

        for (Map.Entry<List<String>, Integer> entry : cases.entrySet()) {
            List<String> input = entry.getKey();
            int expect = entry.getValue();
            Logger.i("input=" + input.get(0) + ", " + input.get(1) + ", " + input.subList(2, input.size()) + ", except=" + expect);
            int output = ladderLength(input.get(0), input.get(1), input.subList(2, input.size()));
            if (output == expect) {
                Logger.i("case pass by output=" + output);
            } else {
                throw new AssertionError("case fail by:" + "\nexpect=" + expect + ", but output=" + output);
            }
        }
        Logger.i("All Pass");
    }
}
