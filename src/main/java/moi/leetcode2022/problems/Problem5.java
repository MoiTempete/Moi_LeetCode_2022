package moi.leetcode2022.problems;

import moi.leetcode2022.utils.Logger;

import java.util.*;

/**
5. Longest Palindromic Substring
Medium

20235

1164

Add to List

Share
Given a string s, return the longest palindromic substring in s.



Example 1:

Input: s = "babad"
Output: "bab"
Explanation: "aba" is also a valid answer.
Example 2:

Input: s = "cbbd"
Output: "bb"


Constraints:

1 <= s.length <= 1000
s consist of only digits and English letters.
 */
public class Problem5 {

    public static String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        if (s.length() == 1) {
            return s;
        }

        char[] bytes = s.toCharArray();
        HashMap<String, ArrayList<Integer>> referenceIndexes = getReferenceIndexes(s);
//        Logger.i("input=" + s + "\nreferenceIndexes=" + referenceIndexes);
        if (referenceIndexes.size() == 1) {
            return s;
        }
        ArrayList<Integer> references;
        String longestResult = "";
        String currentResult;

        if (referenceIndexes.size() > 2) {
            referenceIndexes = sortByValue(referenceIndexes);
//            Logger.i("sorted referenceIndexes=" + referenceIndexes);
        }

        for (Map.Entry<String, ArrayList<Integer>> entry : referenceIndexes.entrySet()) {
            references = entry.getValue();
            if (references.size() > 1) {
                for (int i = 0; i < references.size(); i++) {
                    for (int j = references.size() - 1; j >= i + 1; j--) {
                        int start = references.get(i);
                        int end = references.get(j);
                        if ((end - start + 1) <= longestResult.length()) {
                            break;
                        }
                        if (isPalindromic(bytes, start, end)) {
                            currentResult = String.valueOf(Arrays.copyOfRange(bytes, start, end)) + entry.getKey();
//                            Logger.i("start=" + start + ", end=" + end + ", currentResult=" + currentResult);
                            longestResult = currentResult;
                        }
                    }
                }
            }
        }

        return longestResult.length() == 0 ? String.valueOf(s.charAt(0)) : longestResult;
    }

    public static boolean isPalindromic(char[] s, int start, int end) {
        if (end - start <= 2) {
            return true;
        }
        int interp = (int) ((end - start) % 2 == 0 ? (end - start) / 2f : (end - start + 1) / 2f);
        for (int i = 1; i < interp; i++) {
            if (s[start + i] != s[end - i]) {
                return false;
            }
        }
        return true;
    }

    public static HashMap<String, ArrayList<Integer>> getReferenceIndexes(String s) {
        HashMap<String, ArrayList<Integer>> referenceIndexes = new HashMap<>();
        String element;
        ArrayList<Integer> elementIndexes;

        for (int i = 0; i < s.length(); i++) {
            element = String.valueOf(s.charAt(i));
            elementIndexes = referenceIndexes.get(element);
            if (elementIndexes == null || elementIndexes.size() == 0) {
                elementIndexes = new ArrayList<>();
                elementIndexes.add(i);
                referenceIndexes.put(element, elementIndexes);
            } else {
                elementIndexes.add(i);
                referenceIndexes.replace(element, elementIndexes);
            }
        }
        return referenceIndexes;
    }

    public static HashMap<String, ArrayList<Integer>> sortByValue(HashMap<String, ArrayList<Integer>> hm) {
        List<Map.Entry<String, ArrayList<Integer>>> list = new LinkedList<>(hm.entrySet());

        list.sort((o1, o2) -> (
                (o2.getValue().get(o2.getValue().size() - 1) - o2.getValue().get(0)) -
                        (o1.getValue().get(o1.getValue().size() - 1) - o1.getValue().get(0))));

        HashMap<String, ArrayList<Integer>> temp = new LinkedHashMap<>();
        for (Map.Entry<String, ArrayList<Integer>> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }


    public static void test() {
        Map<String, String[]> cases = new HashMap<>();
        cases.put("babad", new String[]{"bab", "aba"});
        cases.put("cbbd", new String[]{"bb"});
        cases.put("a", new String[]{"a"});
        cases.put("ac", new String[]{"a"});
        cases.put("bacabab", new String[]{"bacab"});
        cases.put("aacabdkacaa", new String[]{"aca"});
        cases.put("xaabacxcabaaxcabaax", new String[]{"xaabacxcabaax"});

        for (Map.Entry<String, String[]> entry : cases.entrySet()) {
            String input = entry.getKey();
            String[] expects = entry.getValue();
            String output = longestPalindrome(input);
            int equalsCount = 0;
            for (String expect : expects) {
                if (output.equals(expect)) {
                    equalsCount++;
                    Logger.i("case pass by input=" + input + ", output=" + output);
                    break;
                }
            }
            if (equalsCount == 0) {
                throw new AssertionError("case fail by input=" + input +
                        ", output=" + output + ", but expect=" + Arrays.toString(expects));
            }
        }
        Logger.i("All Pass");
    }
}
