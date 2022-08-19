package moi.leetcode2022.problems;

import moi.leetcode2022.structure.TreeNode;
import moi.leetcode2022.utils.ArrayUtil;
import moi.leetcode2022.utils.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
Given the root of a binary tree, return the inorder traversal of its nodes' values.

Example 1:


Input: root = [1,null,2,3]
Output: [1,3,2]
Example 2:

Input: root = []
Output: []
Example 3:

Input: root = [1]
Output: [1]


Constraints:

The number of nodes in the tree is in the range [0, 100].
-100 <= Node.val <= 100

Follow up: Recursive solution is trivial, could you do it iteratively?
 */
public class P0094_BinaryTreeInorderTraversal {

    public static void main(String[] args) {
        test();
    }

    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();

        inorderTraversal(root, list);
        return list;
    }

    public static void inorderTraversal(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        if (node.left != null) {
            inorderTraversal(node.left, list);
        }
        list.add(node.val);
        if (node.right != null) {
            inorderTraversal(node.right, list);
        }
    }

    public static void test() {
        Map<TreeNode, List<Integer>> cases = new HashMap<>();
        cases.put(new TreeNode(1, null, new TreeNode(2, new TreeNode(3), null)), ArrayUtil.ofList("[1,3,2]"));

        for (Map.Entry<TreeNode, List<Integer>> entry : cases.entrySet()) {
            TreeNode input = entry.getKey();
            List<Integer> expect = entry.getValue();
            Logger.i("input=" + input + ", except=" + expect);
            List<Integer> output = inorderTraversal(input);
            if (output.equals(expect)) {
                Logger.i("case pass by output=" + output);
            } else {
                throw new AssertionError("case fail by:" + "\ninput=" + input + ", expect=" + expect + ", but output=" + output);
            }
        }
        Logger.i("All Pass");
    }
}
