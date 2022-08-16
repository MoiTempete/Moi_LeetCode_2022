package moi.leetcode2022.problems;

import moi.leetcode2022.utils.Logger;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
You are given an integer array nums. You are initially positioned at the array's first index, and each element in the array represents your maximum jump length at that position.

Return true if you can reach the last index, or false otherwise.



Example 1:

Input: nums = [2,3,1,1,4]
Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
Example 2:

Input: nums = [3,2,1,0,4]
Output: false
Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it impossible to reach the last index.


Constraints:

1 <= nums.length <= 10^4
0 <= nums[i] <= 10^5
 */
public class P0055_JumpGame {

    public static void main(String[] args) {
        test();
    }

    public static boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int index = nums.length - 1;
        while (index > 0) {
            index = findNextReachablePositionFromTail(nums, index);
        }
        return index == 0;
    }

    public static int findNextReachablePositionFromTail(int[] nums, int index) {
        int i = index;
        while (i > 0) {
            i--;
            if (nums[i] >= index - i) { //maximum jump length >= distance
                return i;
            }
        }
        return -1;
    }

    public static void test() {
        Map<int[], Boolean> cases = new HashMap<>();
        cases.put(new int[]{2, 3, 1, 1, 4}, true);
        cases.put(new int[]{3, 2, 1, 0, 4}, false);

        for (Map.Entry<int[], Boolean> entry : cases.entrySet()) {
            int[] input = entry.getKey();
            boolean expect = entry.getValue();
            Logger.i("input=" + Arrays.toString(input) + ", except=" + expect);
            boolean output = canJump(input);
            if (output == expect) {
                Logger.i("case pass by output=" + output);
            } else {
                throw new AssertionError("case fail by:" + "\ninput=" + Arrays.toString(input) + ", expect=" + expect + ", but output=" + output);
            }
        }
        Logger.i("All Pass");
    }
}
