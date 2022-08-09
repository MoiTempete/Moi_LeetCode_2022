package moi.leetcode2022.problems;

import moi.leetcode2022.utils.Logger;

import java.util.*;

/*
iven a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.

A mapping of digits to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.

<img https://assets.leetcode.com/uploads/2022/03/15/1200px-telephone-keypad2svg.png />

Example 1:

Input: digits = "23"
Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
Example 2:

Input: digits = ""
Output: []
Example 3:

Input: digits = "2"
Output: ["a","b","c"]


Constraints:

0 <= digits.length <= 4
digits[i] is a digit in the range ['2', '9'].
 */
public class Problem0017_LetterCombinationsOfPhoneNumber {

    static HashMap<Character, String[]> letters = new HashMap<>() {
        {
            put('2', new String[] {"a", "b", "c"});
            put('3', new String[] {"d", "e", "f"});
            put('4', new String[] {"g", "h", "i"});
            put('5', new String[] {"j", "k", "l"});
            put('6', new String[] {"m", "n", "o"});
            put('7', new String[] {"p", "q", "r", "s"});
            put('8', new String[] {"t", "u", "v"});
            put('9', new String[] {"w", "x", "y", "z"});
        }
    };

    public static List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return new ArrayList<>();
        }
        StringBuilder result = new StringBuilder();
        StringBuilder tempResult = new StringBuilder();

        for (String letter : letters.get(digits.charAt(0))) {
            result.append(letter);
            result.append(",");
        }

        if (digits.length() == 0) {
            return Arrays.asList(result.toString().split(","));
        }

        for (int i = 1; i < digits.length(); i++) {
            String[] iLetters = letters.get(digits.charAt(i));
            for (String iLetter : iLetters) {
                tempResult.append(appendLetter(result, ",", iLetter));
            }
            result = tempResult;
            tempResult = new StringBuilder();
        }

        return Arrays.asList(result.toString().split(","));
    }

    public static String appendLetter(StringBuilder input, String appendMark, String letter) {
        StringBuilder result = new StringBuilder();
        byte[] bytes = input.toString().getBytes();
        for (byte l : bytes) {
            if (l == ((byte) appendMark.charAt(0))) {
                result.append(letter);
            }
            result.append((char)l);
        }
        return result.toString();
    }

//    public static String appendLetter(StringBuilder input, String appendMark, String letter) {
//        StringBuilder result = new StringBuilder();
//        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.toString().getBytes());
//        try {
//            PushbackInputStream pushbackInputStream = new PushbackInputStream(inputStream);
//            byte aByte;
//            while ((aByte = (byte) pushbackInputStream.read()) != -1) {
//                if (aByte == appendMark.getBytes()[0]) {
////                    Logger.ic(letter.charAt(0));
//                    result.append(letter);
//                }
////                Logger.ic((char) aByte);
//                result.append((char) aByte);
//            }
//
//        } catch (Exception e) {
//        }
//        return result.toString();
//    }

    public static void test() {
        Map<String, List<String>> cases = new HashMap<>();
//        cases.put("23", Arrays.asList("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"));
//        cases.put("2", Arrays.asList("a", "b", "c"));
        cases.put("234", Arrays.asList("adg","adh","adi","aeg","aeh","aei","afg","afh","afi","bdg","bdh","bdi","beg","beh","bei","bfg","bfh","bfi","cdg","cdh","cdi","ceg","ceh","cei","cfg","cfh","cfi"));

        for (Map.Entry<String, List<String>> entry : cases.entrySet()) {
            String input = entry.getKey();
            List<String> expect = entry.getValue();
            Logger.i("input=" + input + ", except=" + expect);
            List<String> output = letterCombinations(input);
            expect.sort(String::compareTo);
            output.sort(String::compareTo);
            if (output.equals(expect)) {
                Logger.i("case pass by output=" + output);
            } else {
                throw new AssertionError("case fail by:" + "\ninput=" + input + ", expect=" + expect + ", but output=" + output);
            }
        }
        Logger.i("All Pass");
    }
}
