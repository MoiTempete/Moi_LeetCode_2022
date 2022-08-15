package moi.leetcode2022.problems;

import moi.leetcode2022.utils.Logger;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

A subarray is a contiguous part of an array.



Example 1:

Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.
Example 2:

Input: nums = [1]
Output: 1
Example 3:

Input: nums = [5,4,-1,7,8]
Output: 23


Constraints:

1 <= nums.length <= 10^5
-10^4 <= nums[i] <= 10^4


Follow up: If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
 */
public class P0053_MaximumSubarray {

    public static void main(String[] args) {
        test();
    }

    public static int maxSubArray(int[] nums) { //Kadane's algorithm
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int maxEndingHere = nums[0];
        int maxSofar = nums[0];

        for (int i = 1; i < nums.length; i++) {
            maxEndingHere = maxEndingHere > 0 ? maxEndingHere + nums[i] : nums[i];
            maxSofar = Math.max(maxEndingHere, maxSofar);
        }

        return maxSofar;
    }

    public static void test() {
        Map<int[], Integer> cases = new HashMap<>();
        cases.put(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}, 6);
        cases.put(new int[]{5, 4, -1, 7, 8}, 23);
        cases.put(new int[]{1}, 1);
        cases.put(new int[]{-1, -2}, -1);

        for (Map.Entry<int[], Integer> entry : cases.entrySet()) {
            int[] input = entry.getKey();
            int expect = entry.getValue();
            Logger.i("input=" + Arrays.toString(input) + ", except=" + expect);
            int output = maxSubArray(input);
            if (output == expect) {
                Logger.i("case pass by output=" + output);
            } else {
                throw new AssertionError("case fail by:" + "\ninput=" + Arrays.toString(input) + ", expect=" + expect + ", but output=" + output);
            }
        }
        Logger.i("All Pass");
    }
}
