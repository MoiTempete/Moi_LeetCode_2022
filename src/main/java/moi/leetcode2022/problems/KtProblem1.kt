package moi.leetcode2022.problems

import java.util.*

/**
 * 1. Two Sum
 *
 *
 * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
 *
 *
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 *
 *
 * You can return the answer in any order.
 *
 *
 *
 *
 * Example 1:
 *
 *
 * Input: nums = [2,7,11,15], target = 9
 * Output: [0,1]
 * Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
 * Example 2:
 *
 *
 * Input: nums = [3,2,4], target = 6
 * Output: [1,2]
 * Example 3:
 *
 *
 * Input: nums = [3,3], target = 6
 * Output: [0,1]
 *
 *
 *
 *
 * Constraints:
 *
 *
 * 2 <= nums.length <= 10^4
 * -10^9 <= nums[i] <= 10^9
 * -10^9 <= target <= 10^9
 * Only one valid answer exists.
 *
 *
 *
 *
 * Follow-up: Can you come up with an algorithm that is less than O(n2) time complexity?
 */
object KtProblem1 {
    private fun twoSum(nums: IntArray?, target: Int): IntArray? {
        if (nums == null || nums.isEmpty()) {
            return null
        }
        val tempNums: MutableList<Int> = ArrayList()
        for (i in nums.indices) {
            if (tempNums.size > 0) {
                for (tempNum in tempNums) {
                    if (nums[tempNum] + nums[i] == target) {
                        Logger.i("result=$i, $tempNum")
                        return intArrayOf(i, tempNum)
                    }
                }
            }
            tempNums.add(i)
        }
        return null
    }

    @JvmStatic
    fun test() {
        assert(twoSum(intArrayOf(-3, 4, 3, 90), 0)!!.sortedArray().contentEquals(intArrayOf(0, 2)))

//        Logger.i(Boolean.toString(Arrays.equals(twoSum(new int[]{3, 3}, 6), new int[]{0, 1})));
//        Logger.i(Boolean.toString(Arrays.equals(twoSum(new int[]{3, 2, 4}, 6), new int[]{1, 2})));
//        Logger.i(Boolean.toString(Arrays.equals(twoSum(new int[]{2, 7, 11, 15}, 6), new int[]{0, 1})));
//        Logger.i(Boolean.toString(Arrays.equals(twoSum(new int[]{0, 4, 3, 0}, 6), new int[]{0, 3})));
        Logger.i("All Pass")
    }
}