package moi.leetcode2022.problems;

import moi.leetcode2022.utils.Logger;

import java.util.*;

/*
46. Permutations
Medium

12225

211

Add to List

Share
Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.



Example 1:

Input: nums = [1,2,3]
Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
Example 2:

Input: nums = [0,1]
Output: [[0,1],[1,0]]
Example 3:

Input: nums = [1]
Output: [[1]]


Constraints:

1 <= nums.length <= 6
-10 <= nums[i] <= 10
All the integers of nums are unique.
 */
public class P0046_Permutations {

    public static void main(String[] args) {
        test();
    }

    public static List<List<Integer>> permute(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }

        List<List<Integer>> resultList = new ArrayList<>();
        List<List<Integer>> currentList = new ArrayList<>();

        List<Integer> temp;
        int var;
        for (int j = 0; j < nums.length; j++) {
            var = nums[j];
            if (j == 0) {
                temp = new ArrayList<>();
                temp.add(var);
                resultList.add(temp);
                continue;
            }
            for (List<Integer> result : resultList) {
                for (int k = 0; k <= result.size(); k++) {
                    temp = new ArrayList<>(result);
                    if (k != result.size()) {
                        temp.add(k, var);
                    } else {
                        temp.add(var);
                    }
                    currentList.add(temp);
                }
            }
            resultList = currentList;
            currentList = new ArrayList<>();
        }

        return resultList;
    }

    public static void test() {
        Map<int[], List<List<Integer>>> cases = new HashMap<>();
        cases.put(new int[]{0}, List.of(List.of(0)));
        cases.put(new int[]{0, 1}, List.of(List.of(1, 0), List.of(0, 1)));
        cases.put(new int[]{0, 1, 2}, List.of(List.of(2, 1, 0), List.of(1, 2, 0), List.of(1, 0, 2), List.of(2, 0, 1), List.of(0, 2, 1), List.of(0, 1, 2)));

        for (Map.Entry<int[], List<List<Integer>>> entry : cases.entrySet()) {
            int[] input = entry.getKey();
            List<List<Integer>> expect = entry.getValue();
            Logger.i("input=" + Arrays.toString(input) + ", except=" + expect);
            List<List<Integer>> output = permute(input);
            if (expect.equals(output)) {
                Logger.i("case pass by output=" + output);
            } else {
                throw new AssertionError("case fail by:" + "\ninput=" + Arrays.toString(input) + ", expect=" + expect + ", but output=" + output);
            }
        }
        Logger.i("All Pass");
    }
}
