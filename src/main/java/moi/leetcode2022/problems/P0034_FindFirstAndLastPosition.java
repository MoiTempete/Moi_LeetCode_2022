package moi.leetcode2022.problems;

import moi.leetcode2022.utils.Logger;

import java.util.*;

/*
Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of a given target value.

If target is not found in the array, return [-1, -1].

You must write an algorithm with O(log n) runtime complexity.



Example 1:

Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]
Example 2:

Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]
Example 3:

Input: nums = [], target = 0
Output: [-1,-1]


Constraints:

0 <= nums.length <= 10^5
-10^9 <= nums[i] <= 10^9
nums is a non-decreasing array.
-10^9 <= target <= 10^9
 */
public class P0034_FindFirstAndLastPosition {

    public static void main(String[] args) {
        test();
    }

    public static int binarySearch(int[] nums, int target, int low, int high) {
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

    public static int[] searchRange(int[] nums, int target) {
        int index;
        int[] result = new int[]{-1, -1};
        //for left edge
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            index = binarySearch(nums, target, low, high);
            if (index >= 0) {
                result[0] = index;
                if (result[1] == -1) {
                    result[1] = index;
                }
                high = index - 1;
            } else {
                break;
            }
        }
        //for right edge
        low = result[1] + 1;
        high = nums.length - 1;
        while (low <= high) {
            index = binarySearch(nums, target, low, high);
            if (index >= 0) {
                result[1] = index;
                low = index + 1;
            } else {
                break;
            }
        }

        return result;
    }

    public static void test() {
        Map<List<int[]>, int[]> cases = new HashMap<>();
        cases.put(new ArrayList<>() {{
            add(new int[]{5, 7, 7, 8, 8, 10});
            add(new int[]{7});
        }}, new int[]{1, 2});
        cases.put(new ArrayList<>() {{
            add(new int[]{0, 1, 2, 3, 4, 5, 7, 7, 8, 8, 10});
            add(new int[]{7});
        }}, new int[]{6, 7});
        cases.put(new ArrayList<>(){{add(new int[]{5,7,7,8,8,10}); add(new int[]{6});}}, new int[]{-1, -1});
        cases.put(new ArrayList<>(){{add(new int[]{1, 1, 1, 1, 1}); add(new int[]{1});}}, new int[]{0,4});

        for (Map.Entry<List<int[]>, int[]> entry : cases.entrySet()) {
            List<int[]> input = entry.getKey();
            int[] expect = entry.getValue();
            Logger.i("input=" + Arrays.toString(input.get(0)) + ", target=" + Arrays.toString(input.get(1)) + ", except=" + Arrays.toString(expect));
            int[] output = searchRange(input.get(0), input.get(1)[0]);
            if (Arrays.equals(output, expect)) {
                Logger.i("case pass by output=" + Arrays.toString(output));
            } else {
                throw new AssertionError("case fail by:" + "\ninput=" + Arrays.toString(input.get(0)) + ", target=" + Arrays.toString(input.get(1)) + ", expect=" + Arrays.toString(expect) + ", but output=" + Arrays.toString(output));
            }
        }
        Logger.i("All Pass");
    }
}
