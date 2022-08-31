package moi.leetcode2022.problems;

import moi.leetcode2022.utils.ArrayUtil;
import moi.leetcode2022.utils.Logger;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/*
Given an integer array nums, return true if any value appears at least twice in the array, and return false if every element is distinct.

Example 1:

Input: nums = [1,2,3,1]
Output: true
Example 2:

Input: nums = [1,2,3,4]
Output: false
Example 3:

Input: nums = [1,1,1,3,3,4,3,2,4,2]
Output: true

Constraints:

1 <= nums.length <= 10^5
-10^9 <= nums[i] <= 10^9
 */
public class P0217_ContainsDuplicate {

    public static void main(String[] args) {
        test();
    }

    public static boolean containsDuplicate(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (!set.add(num)) {
                return true;
            }
        }
        return false;
    }

    public static void test() {
        Map<int[], Boolean> cases = new HashMap<>();
        cases.put(ArrayUtil.of("[1,1,1,3,3,4,3,2,4,2]"), true);

        for (Map.Entry<int[], Boolean> entry : cases.entrySet()) {
            int[] input = entry.getKey();
            boolean expect = entry.getValue();
            Logger.i("input=" + Arrays.toString(input) + ", except=" + expect);
            boolean output = containsDuplicate(input);
            if (output == expect) {
                Logger.i("case pass by output=" + output);
            } else {
                throw new AssertionError("case fail by:" + "\nexpect=" + expect + ", but output=" + output);
            }
        }
        Logger.i("All Pass");
    }
}
