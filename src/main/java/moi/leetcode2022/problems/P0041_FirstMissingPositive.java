package moi.leetcode2022.problems;

import moi.leetcode2022.utils.Logger;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
41. First Missing Positive
Hard

10976

1418

Add to List

Share
Given an unsorted integer array nums, return the smallest missing positive integer.

You must implement an algorithm that runs in O(n) time and uses constant extra space.



Example 1:

Input: nums = [1,2,0]
Output: 3
Example 2:

Input: nums = [3,4,-1,1]
Output: 2
Example 3:

Input: nums = [7,8,9,11,12]
Output: 1


Constraints:

1 <= nums.length <= 5 * 10^5
-231 <= nums[i] <= 231 - 1
 */
public class P0041_FirstMissingPositive {

    public static void main(String[] args) {
        test();
    }

    /*
    https://leetcode.com/problems/first-missing-positive/discuss/2409688/Time-O(n)-%2B-Space-O(1)-solution.-No-sort-function-using-and-No-extra-spaces
     */
    public static int firstMissingPositive(int[] nums) {
    /*
	step1:
	covert 0 and negative number to Integer.MAX_VALUE
	eg:
    case1: [3, 4, -1, -1] => [3, 4, MAX_VALUE, MAX_VALUE]
    case2: [3, 1, 2, 5] => [3, 1, 2, 5]
    case3: [4, 3, 2, 1] => [4, 3, 2, 1]
     */
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= 0) {
                nums[i] = Integer.MAX_VALUE;
            }
        }
    /*
	step2:
	multiply nums[i-1] by -1 when find a positive number like nums[j] = i
	eg:
    case1: [3, 4, MAX_VALUE, MAX_VALUE] => [3, 4, -3, -4]
    case2: [3, 1, 2, 5] => [-1, -2, -3, 5]
    case3: [4, 3, 2, 1] => [-1, -2, -3, -4]
     */
        for (int j = 0; j < nums.length; j++) {
            if (Math.abs(nums[j]) <= nums.length) {
                if (nums[(Math.abs(nums[j])) - 1] > 0) {
                    nums[(Math.abs(nums[j])) - 1] = -nums[(Math.abs(nums[j])) - 1];
                }
            }
        }
    /*
	step3:
	After step2, when there is a positive number in array like num[k].
	It means we didn't find the number k+1.
	if every num[k] is negative in this step,
	it means we find all number in [1, 2, 3, ... , nums.length]
	So the smallest missing positive integer should be nums.length + 1
	eg:
    case1: [3, 4, -3, -4], nums[0]=3, 3>=0, missing one is 1
    case2: [-1, -2, -3, 5], nums[3]=5, 5>=0, missing one is 4
    case3: [-1, -2, -3, -4], everyone < 0, missing one is nums.length + 1
     */
        int k = 0;
        while (k < nums.length) {
            if (nums[k] >= 0) {
                break;
            }
            k++;
        }
        return k + 1;
    }

    public static void test() {
        Map<int[], Integer> cases = new HashMap<>();
        cases.put(new int[]{1, 2, 0}, 3);
        cases.put(new int[]{3, 4, -1, 1}, 2);
        cases.put(new int[]{7, 8, 9, 11, 12}, 1);
        cases.put(new int[]{3, 1, 2, 5}, 4);
        cases.put(new int[]{4, 3, 2, 1}, 5);

        for (Map.Entry<int[], Integer> entry : cases.entrySet()) {
            int[] input = entry.getKey();
            int expect = entry.getValue();
            Logger.i("input=" + Arrays.toString(input) + ", except=" + expect);
            int output = firstMissingPositive(input);
            if (output == expect) {
                Logger.i("case pass by output=" + output);
            } else {
                throw new AssertionError("case fail by:" + "\ninput=" + Arrays.toString(input) + ", expect=" + expect + ", but output=" + output);
            }
        }
        Logger.i("All Pass");
    }
}
