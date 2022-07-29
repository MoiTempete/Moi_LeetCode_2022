package moi.leetcode2022.problems;

import moi.leetcode2022.utils.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1. Two Sum
 * Easy
 * <p>
 * 34904
 * <p>
 * 1106
 * <p>
 * Add to List
 * <p>
 * Share
 * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
 * <p>
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * <p>
 * You can return the answer in any order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [2,7,11,15], target = 9
 * Output: [0,1]
 * Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
 * Example 2:
 * <p>
 * Input: nums = [3,2,4], target = 6
 * Output: [1,2]
 * Example 3:
 * <p>
 * Input: nums = [3,3], target = 6
 * Output: [0,1]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 2 <= nums.length <= 104
 * -109 <= nums[i] <= 109
 * -109 <= target <= 109
 * Only one valid answer exists.
 * <p>
 * <p>
 * Follow-up: Can you come up with an algorithm that is less than O(n2) time complexity?
 */
public class Problem1 {
    public static int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length <= 0) {
            return null;
        }
        List<Integer> tempNums = new ArrayList<>();
        int tempIndex;
        int num;
        for (int i = 0; i < nums.length; i++) {
            num = nums[i];
            if (tempNums.size() > 0) {
                tempIndex = tempNums.indexOf(num);
                if (tempIndex >= 0) {
                    return new int[]{tempIndex, i};
                }
            }
            tempNums.add(i, target - num);
        }

        return null;
    }

    public static void test() {
        List<Case> cases = new ArrayList<>();
        cases.add(new Case(new int[]{3, 3}, 6, new int[]{0, 1}));
        cases.add(new Case(new int[]{3, 2, 4}, 6, new int[]{1, 2}));
        cases.add(new Case(new int[]{2, 7, 11, 15}, 9, new int[]{0, 1}));
        cases.add(new Case(new int[]{0, 4, 3, 0}, 0, new int[]{0, 3}));
        cases.add(new Case(new int[]{-3, 4, 3, 90}, 0, new int[]{0, 2}));

        for (Case c : cases) {
            int[] nums = c.nums;
            int target = c.target;
            int[] result = c.result;
            int[] output = twoSum(nums, target);
            if (output == null) {
                throw new AssertionError("nums=" + Arrays.toString(nums) + ", target=" + target + "result is null");
            }
            Arrays.sort(result);
            Arrays.sort(output);
            Logger.i("nums=" + Arrays.toString(nums) + ", target=" + target + ", result=" + Arrays.toString(result) + ", output=" + Arrays.toString(output));
            if (result == output) {
                throw new AssertionError("fail when nums=" + Arrays.toString(c.nums) + ", target=" + c.target + ", result should be " + Arrays.toString(c.result) + ". But output is " + Arrays.toString(twoSum(c.nums, c.target)));
            }
        }
        Logger.i("All Passed");
    }

    static class Case {

        int[] nums;
        int target;
        int[] result;

        public Case(int[] nums, int target, int[] result) {
            this.nums = nums;
            this.target = target;
            this.result = result;
        }
    }
}
