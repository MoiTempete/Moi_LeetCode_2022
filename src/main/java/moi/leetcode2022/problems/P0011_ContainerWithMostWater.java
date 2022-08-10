package moi.leetcode2022.problems;

import moi.leetcode2022.utils.Logger;

import java.util.*;

/*
11. Container With Most Water
Medium

18854

1027

Add to List

Share
You are given an integer array height of length n. There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).

Find two lines that together with the x-axis form a container, such that the container contains the most water.

Return the maximum amount of water a container can store.

Notice that you may not slant the container.



Example 1:


Input: height = [1,8,6,2,5,4,8,3,7]
Output: 49
Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.
Example 2:

Input: height = [1,1]
Output: 1


Constraints:

n == height.length
2 <= n <= 10^5
0 <= height[i] <= 10^4
 */
public class P0011_ContainerWithMostWater {

    public static void main(String[] args) {
        test();
    }

    public static int maxArea(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int result = 0;
        int leftP = 0;
        int rightP = height.length - 1;
        while (leftP < rightP) {
            result = Math.max(result, Math.min(height[leftP], height[rightP]) * (rightP - leftP));
            if (height[leftP] < height[rightP]) {
                leftP++;
            } else {
                rightP--;
            }
        }

        return result;
    }

    public static void test() {
        Map<int[], Integer> cases = new HashMap<>();
        cases.put(null, 0);
        cases.put(new int[]{}, 0);
        cases.put(new int[]{1, 1}, 1);
        cases.put(new int[]{1,8,6,2,5,4,8,3,7}, 49);

        for (Map.Entry<int[], Integer> entry : cases.entrySet()) {
            int[] input = entry.getKey();
            int expect = entry.getValue();
            Logger.i("input=" + Arrays.toString(input));
            int output = maxArea(input);
            if (output == expect) {
                Logger.i("case pass by output=" + output);
            } else {
                throw new AssertionError("case fail by:" + "\ninput=" + Arrays.toString(input) + ", expect=" + expect + ", but output=" + output);
            }
        }
        Logger.i("All Pass");
    }
}
