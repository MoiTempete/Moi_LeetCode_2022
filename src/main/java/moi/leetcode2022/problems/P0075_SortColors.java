package moi.leetcode2022.problems;

import moi.leetcode2022.utils.Logger;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white, and blue.

We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.

You must solve this problem without using the library's sort function.

Example 1:

Input: nums = [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]
Example 2:

Input: nums = [2,0,1]
Output: [0,1,2]

Constraints:

n == nums.length
1 <= n <= 300
nums[i] is either 0, 1, or 2.

Follow up: Could you come up with a one-pass algorithm using only constant extra space?
 */
public class P0075_SortColors {

    public static void main(String[] args) {
        test();
    }

    public static void sortColors(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int left = 0;
        int right = 0;
        int temp;
        int i = 0;
        Logger.i("length=" + nums.length);
        while (i <= nums.length - 1 - right) {
            Logger.i("left=" + left + ", right=" + right + ", i=" + i);
            if (nums[i] > 1) {
                if (nums[i] != nums[nums.length - 1 - right]) {
                    temp = nums[i];
                    nums[i] = nums[nums.length - 1 - right];
                    nums[nums.length - 1 - right] = temp;
                    Logger.i("\ni=" + i + ", nums[" + i + "]=" + nums[i] + ">1, swap with nums[" + (nums.length - 1 - right) + "]");
                    Logger.i("nums=" + Arrays.toString(nums));
                }
                right++;
                continue;
            } else if (nums[i] < 1) {
                if (nums[i] != nums[left]) {
                    temp = nums[i];
                    nums[i] = nums[left];
                    nums[left] = temp;
                    Logger.i("\ni=" + i + ", nums[" + i + "]=" + nums[i] + "<1, swap with nums[" + (left) + "]");
                    Logger.i("nums=" + Arrays.toString(nums));
                }
                left++;
            }
            i++;
        }
        Logger.i("\nnums=" + Arrays.toString(nums));
    }

    public static void test() {
        Map<int[], int[]> cases = new HashMap<>();
        cases.put(new int[]{2, 0, 2, 1, 1, 0}, new int[]{0, 0, 1, 1, 2, 2});
        cases.put(new int[]{2, 0, 1}, new int[]{0, 1, 2});
        cases.put(new int[]{2, 1, 2}, new int[]{1, 2, 2});
        cases.put(new int[]{1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 0, 0, 0}, new int[]{0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2});

        for (Map.Entry<int[], int[]> entry : cases.entrySet()) {
            int[] input = entry.getKey();
            int[] originalInput = Arrays.copyOf(input, input.length);
            int[] expect = entry.getValue();
            Logger.i("input=" + Arrays.toString(originalInput) + ", except=" + Arrays.toString(expect));
            sortColors(input);
            if (Arrays.equals(input, expect)) {
                Logger.i("case pass by output=" + Arrays.toString(originalInput));
            } else {
                throw new AssertionError("case fail by:" + "\ninput=" + Arrays.toString(originalInput) +
                        ", expect=" + Arrays.toString(expect) + ", but output=" + Arrays.toString(input));
            }
        }
        Logger.i("All Pass");
    }
}
