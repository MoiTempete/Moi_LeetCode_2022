package moi.leetcode2022.problems;

import moi.leetcode2022.structure.TreeNode;
import moi.leetcode2022.utils.ArrayUtil;
import moi.leetcode2022.utils.Logger;
import moi.leetcode2022.utils.TreeNodeUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
Given the root of a binary tree, return the zigzag level order traversal of its nodes' values.
 (i.e., from left to right, then right to left for the next level and alternate between).

Example 1:

Input: root = [3,9,20,null,null,15,7]
Output: [[3],[20,9],[15,7]]
Example 2:

Input: root = [1]
Output: [[1]]
Example 3:

Input: root = []
Output: []

Constraints:

The number of nodes in the tree is in the range [0, 2000].
-100 <= Node.val <= 100
 */
public class P0103_BinaryTreeZigzagLevelOrderTraversal {

    public static void main(String[] args) {
        test();
    }

    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        int level = 0;
        traversal(root, result, level);
        return result;
    }

    public static void traversal(TreeNode node, List<List<Integer>> result, int level) {
        if (node == null) {
            return;
        }
        List<Integer> list = null;
        if (result.size() != 0 && level < result.size()) {
            list = result.get(level);
        }
        if (list == null) {
            list = new ArrayList<>();
            if (level % 2 != 0) {
                list.add(0, node.val);
            } else {
                list.add(node.val);
            }
            result.add(level, list);
        } else {
            if (level % 2 != 0) {
                list.add(0, node.val);
            } else {
                list.add(node.val);
            }
            result.remove(level);
            result.add(level, list);
        }
        if (node.left != null) {
            traversal(node.left, result, level + 1);
        }
        if (node.right != null) {
            traversal(node.right, result, level + 1);
        }
    }

    public static void test() {
        Map<TreeNode, List<List<Integer>>> cases = new HashMap<>();
        cases.put(TreeNodeUtil.of("[3,9,20,null,null,15,7]"), ArrayUtil.ofList2("[[3],[20,9],[15,7]]"));
        cases.put(TreeNodeUtil.of("[1,2,3,4,null,null,5]"), ArrayUtil.ofList2("[[1],[3,2],[4,5]]"));

        for (Map.Entry<TreeNode, List<List<Integer>>> entry : cases.entrySet()) {
            TreeNode input = entry.getKey();
            List<List<Integer>> expect = entry.getValue();
            Logger.i("input=" + input + ", except=" + expect);
            List<List<Integer>> output = zigzagLevelOrder(input);
            if (expect.equals(output)) {
                Logger.i("case pass by output=" + output);
            } else {
                throw new AssertionError("case fail by:" + "\ninput=" + input + ", expect=" + expect + ", but output=" + output);
            }
        }
        Logger.i("All Pass");
    }
}
