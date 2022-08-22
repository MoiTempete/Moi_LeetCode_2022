package moi.leetcode2022.problems;

import moi.leetcode2022.structure.TreeNode;
import moi.leetcode2022.utils.Logger;
import moi.leetcode2022.utils.TreeNodeUtil;

import java.util.HashMap;
import java.util.Map;

/*
Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).

Example 1:

Input: root = [1,2,2,3,4,4,3]
Output: true
Example 2:

Input: root = [1,2,2,null,3,null,3]
Output: false

Constraints:

The number of nodes in the tree is in the range [1, 1000].
-100 <= Node.val <= 100

Follow up: Could you solve it both recursively and iteratively?
 */
public class P0101_SymmetricTree {

    public static void main(String[] args) {
        test();
    }

    public static boolean isSymmetric(TreeNode root) {
        if (root.left == null && root.right == null) {
            return true;
        }
        if (root.left == null || root.right == null) {
            return false;
        }
        StringBuilder leftPart = new StringBuilder();
        traversalLeft(root.left, 0, leftPart);
        Logger.i("leftPart=" + leftPart);

        StringBuilder rightPart = new StringBuilder();
        traversalRight(root.right, 0, rightPart);
        Logger.i("rightPart=" + rightPart);

        return leftPart.toString().equals(rightPart.toString());
    }

    public static void traversalLeft(TreeNode node, int level, StringBuilder list) {
        if (node == null) {
            return;
        }
        if (node.left != null) {
            traversalLeft(node.left, level + 1, list);
        } else {
            list.append(level).append("-null,");
        }
        list.append(level).append("-").append(node.val).append(",");
        if (node.right != null) {
            traversalLeft(node.right, level + 1, list);
        } else {
            list.append(level).append("-null,");
        }
    }


    public static void traversalRight(TreeNode node, int level, StringBuilder list) {
        if (node == null) {
            return;
        }
        if (node.right != null) {
            traversalRight(node.right, level + 1, list);
        } else {
            list.append(level).append("-null,");
        }
        list.append(level).append("-").append(node.val).append(",");
        if (node.left != null) {
            traversalRight(node.left, level + 1, list);
        } else {
            list.append(level).append("-null,");
        }
    }

    public static void test() {
        Map<TreeNode, Boolean> cases = new HashMap<>();
//        cases.put(TreeNodeUtil.of("[1,2,2,3,4,4,3]"), true);
        cases.put(TreeNodeUtil.of("[1,2,2,null,3,null,3]"), false);
        cases.put(TreeNodeUtil.of("[1,2,2,null,2,null,2]"), false);

        for (Map.Entry<TreeNode, Boolean> entry : cases.entrySet()) {
            TreeNode input = entry.getKey();
            boolean expect = entry.getValue();
            Logger.i("input=" + input + ", except=" + expect);
            boolean output = isSymmetric(input);
            if (output == expect) {
                Logger.i("case pass by output=" + output);
            } else {
                throw new AssertionError("case fail by:" + "\ninput=" + input + ", expect=" + expect + ", but output=" + output);
            }
        }
        Logger.i("All Pass");
    }
}
