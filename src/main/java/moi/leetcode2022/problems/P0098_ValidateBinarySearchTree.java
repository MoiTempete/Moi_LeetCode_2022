package moi.leetcode2022.problems;

import moi.leetcode2022.structure.TreeNode;
import moi.leetcode2022.utils.Logger;
import moi.leetcode2022.utils.TreeNodeUtil;

import java.util.HashMap;
import java.util.Map;

/*
Given the root of a binary tree, determine if it is a valid binary search tree (BST).

A valid BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.


Example 1:


Input: root = [2,1,3]
Output: true
Example 2:


Input: root = [5,1,4,null,null,3,6]
Output: false
Explanation: The root node's value is 5 but its right child's value is 4.


Constraints:

The number of nodes in the tree is in the range [1, 10^4].
-2^31 <= Node.val <= 2^31 - 1
 */
public class P0098_ValidateBinarySearchTree {

    public static void main(String[] args) {
        test();
    }

    public static boolean isValidBST(TreeNode root) {
        return traverse(root, null, null);
    }

    public static boolean traverse(TreeNode node, TreeNode max, TreeNode min) {
        //  Recursive WAY
        if (node == null) {
            return true;
        }
        if (max != null && max.val <= node.val) return false;
        if (min != null && min.val >= node.val) return false;

        return traverse(node.left, node, min) && traverse(node.right, max, node);
    }

    public static void test() {
        Map<TreeNode, Boolean> cases = new HashMap<>();
        cases.put(TreeNodeUtil.of("[5,4,6,null,null,3,7]"), false);
        cases.put(TreeNodeUtil.of("[5,1,4,null,null,3,6]"), false);
        cases.put(TreeNodeUtil.of("[2,1,3]"), true);

        for (Map.Entry<TreeNode, Boolean> entry : cases.entrySet()) {
            TreeNode input = entry.getKey();
            boolean expect = entry.getValue();
            Logger.i("input=" + input + ", except=" + expect);
            boolean output = isValidBST(input);
            if (output == expect) {
                Logger.i("case pass by output=" + output);
            } else {
                throw new AssertionError("case fail by:" + "\ninput=" + input + ", expect=" + expect + ", but output=" + output);
            }
        }
        Logger.i("All Pass");
    }
}
