package moi.leetcode2022.problems;

import moi.leetcode2022.utils.ArrayUtil;
import moi.leetcode2022.utils.Logger;

import java.util.*;

/*
Given an integer array nums of unique elements, return all possible subsets (the power set).

The solution set must not contain duplicate subsets. Return the solution in any order.



Example 1:

Input: nums = [1,2,3]
Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
Example 2:

Input: nums = [0]
Output: [[],[0]]


Constraints:

1 <= nums.length <= 10
-10 <= nums[i] <= 10
All the numbers of nums are unique.
 */
public class P0078_Subsets {

    public static void main(String[] args) {
        test();
    }

    /*
     * EZ, PZ
     * Example 1:
     * Input: nums = [1,2,3]
     * Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
     *
     * Step 1: insert an empty List to List<List<Integer>> result
     * result = [[]]
     *
     * Step 2: insert number @ nums[0]=1 to every List in result
     * insert list = [1], then append it to result
     * result = [[],[1]]
     *
     * Step 3: insert number @ nums[1]=2 to every List in result
     * insert list = [[2],[1,2]], then append it to result
     * result = [[],[1],[2],[1,2]]
     *
     * Step 4: insert number @ nums[2]=3 to every List in result
     * insert list = [[3],[1,3],[2,3],[1,2,3]], then append it to result
     * result = [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
     * then 2 = nums.length - 1, no more numbers need to insert, return result.
     */
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        if (nums == null || nums.length == 0) {
            return result;
        }
        int s;
        for (int n : nums) {
            s = result.size();
            for (int i = 0; i < s; i++) {
                List<Integer> set = new ArrayList<>(result.get(i));
                set.add(n);
                result.add(set);
            }
        }
        return result;
    }

    public static void test() {
        Map<int[], List<List<Integer>>> cases = new HashMap<>();
        cases.put(new int[]{1, 2, 3}, ArrayUtil.ofList2("[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]"));

        for (Map.Entry<int[], List<List<Integer>>> entry : cases.entrySet()) {
            int[] input = entry.getKey();
            List<List<Integer>> expect = entry.getValue();
            Logger.i("input=" + Arrays.toString(input) + ", except=" + expect);
            List<List<Integer>> output = subsets(input);
            int count = 0;
            for (List<Integer> integers : expect) {
                if (output.contains(integers)) {
                    count++;
                } else {
                    break;
                }
            }
            if (count == expect.size()) {
                Logger.i("case pass by output=" + output);
            } else {
                throw new AssertionError("case fail by:" + "\ninput=" + Arrays.toString(input) + ", expect=" + expect + ", but output=" + output);
            }
        }
        Logger.i("All Pass");
    }
}
