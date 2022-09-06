package moi.leetcode2022.problems;

import moi.leetcode2022.utils.Logger;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
Given two strings s and t, return true if t is an anagram of s, and false otherwise.

An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
 typically using all the original letters exactly once.

Example 1:

Input: s = "anagram", t = "nagaram"
Output: true
Example 2:

Input: s = "rat", t = "car"
Output: false

Constraints:

1 <= s.length, t.length <= 5 * 104
s and t consist of lowercase English letters.

Follow up: What if the inputs contain Unicode characters? How would you adapt your solution to such a case?
 */
public class P0242_ValidAnagram {

    public static void main(String[] args) {
        test();
    }

    public static boolean isAnagram(String s, String t) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char letter = s.charAt(i);
            if (map.containsKey(letter)) {
                map.replace(letter, map.get(letter) + 1);
            } else {
                map.put(letter, 1);
            }
        }
        for (int i = 0; i < t.length(); i++) {
            char letter = t.charAt(i);
            if (map.containsKey(letter)) {
                int count = map.get(letter);
                if (count > 1) {
                    map.replace(letter, count - 1);
                } else if (count == 1) {
                    map.remove(letter);
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }

        return map.size() == 0;
    }

    public static void test() {
        Map<String[], Boolean> cases = new HashMap<>();
        cases.put(new String[]{"anagram", "nagaram"}, true);

        for (Map.Entry<String[], Boolean> entry : cases.entrySet()) {
            String[] input = entry.getKey();
            boolean expect = entry.getValue();
            Logger.i("input=" + Arrays.toString(input) + ", except=" + expect);
            boolean output = isAnagram(input[0], input[1]);
            if (output == expect) {
                Logger.i("case pass by output=" + output);
            } else {
                throw new AssertionError("case fail by:" + "\nexpect=" + expect + ", but output=" + output);
            }
        }
        Logger.i("All Pass");
    }
}
