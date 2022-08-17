package moi.leetcode2022.problems;

import moi.leetcode2022.utils.Logger;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
Given n non-negative integers representing an elevation map where the width of each bar is 1,
compute how much water it can trap after raining.

Example 1:


Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1].
 In this case, 6 units of rain water (blue section) are being trapped.
Example 2:

Input: height = [4,2,0,3,2,5]
Output: 9


Constraints:

n == height.length
1 <= n <= 2 * 104
0 <= height[i] <= 105
 */
public class P0042_TrappingRainWater {

    public static void main(String[] args) {
        test();
    }

    public static int trap(int[] height) {
        // left max & right max overlay by dynamic programming
        if (height == null || height.length == 0) {
            return 0;
        }
        int ans = 0;
        int length = height.length;
        int[] leftMaxHeight = new int[length];
        int[] rightMaxHeight = new int[length];

        leftMaxHeight[0] = height[0];
        for (int i = 1; i < length; i++) {
            leftMaxHeight[i] = Math.max(height[i], leftMaxHeight[i - 1]);
        }
        Logger.i("leftMaxHeight=" + Arrays.toString(leftMaxHeight));

        rightMaxHeight[length - 1] = height[length - 1];
        for (int i = length - 2; i >= 0; i--) {
            rightMaxHeight[i] = Math.max(height[i], rightMaxHeight[i + 1]);
        }
        Logger.i("rightMaxHeight=" + Arrays.toString(rightMaxHeight));

        for (int i = 1; i < length - 1; i++) {
            ans += Math.min(leftMaxHeight[i], rightMaxHeight[i]) - height[i];
        }

        return ans;
    }

    //too slow
    public static int trap2(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int result = 0;
        int level = 1;
        int[] nextLevelIndexes = new int[2];
        nextLevelIndexes[1] = height.length - 1;
        while (nextLevelIndexes[1] - nextLevelIndexes[0] > 0) {
            int count = 0;
            boolean startCount = false;
            int start = nextLevelIndexes[0];
            int end = nextLevelIndexes[1];
            nextLevelIndexes[0] = 0;
            nextLevelIndexes[1] = 0;
            for (int i = start; i <= end; i++) {
                if (height[i] >= level) {
                    if (!startCount) {
                        startCount = true;
                        nextLevelIndexes[0] = i;
                    } else {
                        result = result + count;
                        nextLevelIndexes[1] = i;
                        count = 0;
                    }
                } else {
                    if (startCount) {
                        count++;
                    }
                }
            }
            Logger.i("##########level=" + level + ", result=" + result);
            level++;
        }

        return result;
    }

    public static void test() {
        Map<int[], Integer> cases = new HashMap<>();
        cases.put(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}, 6);
        cases.put(new int[]{4, 2, 0, 3, 2, 5}, 9);

        for (Map.Entry<int[], Integer> entry : cases.entrySet()) {
            int[] input = entry.getKey();
            int expect = entry.getValue();
            Logger.i("input=" + Arrays.toString(input) + ", except=" + expect);
            int output = trap(input);
            if (output == expect) {
                Logger.i("case pass by output=" + output);
            } else {
                throw new AssertionError("case fail by:" + "\ninput=" + Arrays.toString(input) + ", expect=" + expect + ", but output=" + output);
            }
        }
        Logger.i("All Pass");
    }
}
