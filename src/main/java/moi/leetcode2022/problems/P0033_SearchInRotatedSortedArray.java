package moi.leetcode2022.problems;

import moi.leetcode2022.utils.Logger;

import java.util.*;

/*
33. Search in Rotated Sorted Array
Medium

16511

1005

Add to List

Share
There is an integer nums nums sorted in aschighing order (with distinct values).

Prior to being passed to your function, nums is possibly rotated at an unknown pivot index k (1 <= k < nums.length) such that the resulting nums is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].

Given the nums nums after the possible rotation and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.

You must write an algorithm with O(log n) runtime complexity.



Example 1:

Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4
Example 2:

Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1
Example 3:

Input: nums = [1], target = 0
Output: -1


Constraints:

1 <= nums.length <= 5000
-104 <= nums[i] <= 104
All values of nums are unique.
nums is an aschighing nums that is possibly rotated.
-10^4 <= target <= 10^4
 */
public class P0033_SearchInRotatedSortedArray {

    public static void main(String[] args) {
        test();
    }

    public static int search(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            int mid = low + ((high - low) >>> 1);
            if (target == nums[mid]) {
                return mid;
            }
            if (nums[low] <= nums[mid]) {
                if (nums[mid] > target && nums[low] <= target) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {
                if (nums[high] >= target && nums[mid] < target) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }

    public static int binarySearch(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            int midVal = nums[mid];
            if (midVal - target < 0) {
                low = mid + 1;
            } else if (midVal - target > 0) {
                high = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static void test() {
        Map<int[], int[]> cases = new HashMap<>();
        cases.put(new int[]{0, 1, 2, 4, 5, 6, 7}, new int[]{6, 5});
        cases.put(new int[]{13, 14, 15, 16, 17, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, new int[]{16, 3});
        cases.put(new int[]{4, 5, 6, 7, 0, 1, 2}, new int[]{0, 4});
        cases.put(new int[]{4, 5, 6, 7, 0, 1, 2}, new int[]{3, -1});
        cases.put(new int[]{1}, new int[]{0, -1});

        for (Map.Entry<int[], int[]> entry : cases.entrySet()) {
            int[] input = entry.getKey();
            int[] expect = entry.getValue();
            Logger.i("input=" + Arrays.toString(input) + ", target=" + expect[0] + ", except=" + expect[1]);
            int output = search(input, expect[0]);
            if (output == expect[1]) {
                Logger.i("case pass by output=" + output);
            } else {
                throw new AssertionError("case fail by:" + "\ninput=" + Arrays.toString(input) + ", target=" + expect[0] + ", expect=" + expect[1] + ", but output=" + output);
            }
        }
        Logger.i("All Pass");
    }
}
