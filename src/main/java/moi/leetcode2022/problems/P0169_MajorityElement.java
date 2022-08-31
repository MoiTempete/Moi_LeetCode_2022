package moi.leetcode2022.problems;

import moi.leetcode2022.utils.ArrayUtil;
import moi.leetcode2022.utils.Logger;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
Given an array nums of size n, return the majority element.

The majority element is the element that appears more than ⌊n / 2⌋ times.
You may assume that the majority element always exists in the array.

Example 1:

Input: nums = [3,2,3]
Output: 3
Example 2:

Input: nums = [2,2,1,1,1,2,2]
Output: 2


Constraints:

n == nums.length
1 <= n <= 5 * 10^4
-10^9 <= nums[i] <= 10^9


Follow-up: Could you solve the problem in linear time and in O(1) space?
 */
public class P0169_MajorityElement {

    public static void main(String[] args) {
        test();
    }

    public static int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int number;
        int length = nums.length;
        for (int num : nums) {
            number = num;
            if (map.containsKey(number)) {
                map.replace(num, map.get(number) + 1);
            } else {
                map.put(num, 1);
            }
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > length / 2) {
                return entry.getKey();
            }
        }
        return 0;
    }

    public static int majorityElement2(int[] nums) {
       Arrays.sort(nums);
       return nums[nums.length / 2];
    }

    public static void test() {
        Map<int[], Integer> cases = new HashMap<>();
        cases.put(ArrayUtil.of("[2,2,1,1,1,2,2]"), 2);
        cases.put(ArrayUtil.of("[3,2,3]"), 3);

        for (Map.Entry<int[], Integer> entry : cases.entrySet()) {
            int[] input = entry.getKey();
            int expect = entry.getValue();
            Logger.i("input=" + Arrays.toString(input) + ", except=" + expect);
            int output = majorityElement2(input);
            if (output == expect) {
                Logger.i("case pass by output=" + output);
            } else {
                throw new AssertionError("case fail by:" + "\nexpect=" + expect + ", but output=" + output);
            }
        }
        Logger.i("All Pass");
    }
}
