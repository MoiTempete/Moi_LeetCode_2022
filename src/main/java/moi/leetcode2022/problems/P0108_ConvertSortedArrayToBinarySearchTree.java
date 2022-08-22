package moi.leetcode2022.problems;

import moi.leetcode2022.structure.TreeNode;
import moi.leetcode2022.utils.ArrayUtil;
import moi.leetcode2022.utils.Logger;
import moi.leetcode2022.utils.TreeNodeUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
Given an integer array nums where the elements are sorted in ascending order, convert it to a height-balanced binary search tree.

A height-balanced binary tree is a binary tree in which the depth of the two subtrees of every node never differs by more than one.

Example 1:

Input: nums = [-10,-3,0,5,9]
Output: [0,-3,9,-10,null,5]
Explanation: [0,-10,5,null,-3,null,9] is also accepted:

Example 2:

Input: nums = [1,3]
Output: [3,1]
Explanation: [1,null,3] and [3,1] are both height-balanced BSTs.

Constraints:

1 <= nums.length <= 104
-104 <= nums[i] <= 104
nums is sorted in a strictly increasing order.
 */
public class P0108_ConvertSortedArrayToBinarySearchTree {

    public static void main(String[] args) {
        test();
    }

    public static TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        return generateTreeNodeFromArray(true, nums);
    }

    public static TreeNode generateTreeNodeFromArray(boolean isLeft, int[] nums) {
        Logger.i((isLeft ? "left=" : "right=") + Arrays.toString(nums));
        if (nums.length == 1) {
            return new TreeNode(nums[0]);
        }
        if (nums.length == 2) {
            return new TreeNode(nums[1], new TreeNode(nums[0]), null);
        }
        if (nums.length == 3) {
            return new TreeNode(nums[1], new TreeNode(nums[0]), new TreeNode(nums[2]));
        }
        int middle = (nums.length - 1) / 2;
        TreeNode root = new TreeNode(nums[middle]);
        root.left = generateTreeNodeFromArray(true, Arrays.copyOfRange(nums, 0, middle));
        root.right = generateTreeNodeFromArray(false, Arrays.copyOfRange(nums, middle + 1, nums.length));
        return root;
    }

    public static void test() {
        Map<int[], TreeNode> cases = new HashMap<>();
        cases.put(ArrayUtil.of("[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15]"), TreeNodeUtil.of("[8,4,12,2,6,10,14,1,3,5,7,9,11,13,15]"));

        for (Map.Entry<int[], TreeNode> entry : cases.entrySet()) {
            int[] input = entry.getKey();
            TreeNode expect = entry.getValue();
            Logger.i("input=" + Arrays.toString(input) + ", except=" + expect);
            TreeNode output = sortedArrayToBST(input);
            if (output.equals(expect)) {
                Logger.i("case pass by output=" + output);
            } else {
                throw new AssertionError("case fail by:" + "\ninput=" + Arrays.toString(input) + ", expect=" + expect + ", but output=" + output);
            }
        }
        Logger.i("All Pass");
    }
}
