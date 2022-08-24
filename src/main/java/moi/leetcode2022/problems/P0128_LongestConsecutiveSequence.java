package moi.leetcode2022.problems;

import moi.leetcode2022.utils.ArrayUtil;
import moi.leetcode2022.utils.Logger;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.

You must write an algorithm that runs in O(n) time.

Example 1:

Input: nums = [100,4,200,1,3,2]
Output: 4
Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
Example 2:

Input: nums = [0,3,7,2,5,8,4,6,0,1]
Output: 9


Constraints:

0 <= nums.length <= 10^5
-10^9 <= nums[i] <= 10^9
 */
public class P0128_LongestConsecutiveSequence {

    public static void main(String[] args) {
        test();
    }

    public static int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>(len);
        int result = 1;
        for (int cur : nums) {
            if (!map.containsKey(cur)) {
                int leftLen = map.getOrDefault(cur - 1, 0);
                int rightLen = map.getOrDefault(cur + 1, 0);
                int curLen = leftLen + 1 + rightLen;
                if (curLen > result) {
                    result = curLen;
                }
                map.put(cur, -1);
                map.put(cur - leftLen, curLen);
                map.put(cur + rightLen, curLen);
            }
        }
        return result;
    }

    public static void test() {
        Map<int[], Integer> cases = new HashMap<>();
        cases.put(ArrayUtil.of("[100,4,200,1,3,2]"), 4);
        cases.put(ArrayUtil.of("[0,3,7,2,5,8,4,6,0,1]"), 9);

        for (Map.Entry<int[], Integer> entry : cases.entrySet()) {
            int[] input = entry.getKey();
            int expect = entry.getValue();
            Logger.i("input=" + Arrays.toString(input) + ", except=" + expect);
            int output = longestConsecutive(input);
            if (output == expect) {
                Logger.i("case pass by output=" + output);
            } else {
                throw new AssertionError("case fail by:" + "\nexpect=" + expect + ", but output=" + output);
            }
        }
        Logger.i("All Pass");
    }
}
