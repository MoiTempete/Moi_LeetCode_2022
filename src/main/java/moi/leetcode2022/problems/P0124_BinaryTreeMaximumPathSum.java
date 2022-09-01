package moi.leetcode2022.problems;

import moi.leetcode2022.structure.TreeNode;
import moi.leetcode2022.utils.Logger;
import moi.leetcode2022.utils.TreeNodeUtil;

import java.util.HashMap;
import java.util.Map;

/*
A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge connecting them. A node can only appear in the sequence at most once. Note that the path does not need to pass through the root.

The path sum of a path is the sum of the node's values in the path.

Given the root of a binary tree, return the maximum path sum of any non-empty path.

Example 1:

Input: root = [1,2,3]
Output: 6
Explanation: The optimal path is 2 -> 1 -> 3 with a path sum of 2 + 1 + 3 = 6.
Example 2:

Input: root = [-10,9,20,null,null,15,7]
Output: 42
Explanation: The optimal path is 15 -> 20 -> 7 with a path sum of 15 + 20 + 7 = 42.

Constraints:

The number of nodes in the tree is in the range [1, 3 * 10^4].
-1000 <= Node.val <= 1000
 */
public class P0124_BinaryTreeMaximumPathSum {

    public static void main(String[] args) {
        test();
    }

    static int maxSum;

    public static int maxPathSum(TreeNode root) {
        maxSum = Integer.MIN_VALUE;

        dfs(root);
        return maxSum;
    }

    private static int dfs(TreeNode node) {
        if (node == null) return 0;
        int l = dfs(node.left);
        int r = dfs(node.right);
        if (l < 0) l = 0;
        if (r < 0) r = 0;
        maxSum = Math.max(maxSum, node.val + l + r);
        int t = Math.max(l, r);
        return t + node.val;
    }

    public static void test() {
        Map<TreeNode, Integer> cases = new HashMap<>();
        cases.put(TreeNodeUtil.of("[-3]"), -3);
        cases.put(TreeNodeUtil.of("[-10,9,20,null,null,15,7]"), 42);

        for (Map.Entry<TreeNode, Integer> entry : cases.entrySet()) {
            TreeNode input = entry.getKey();
            int expect = entry.getValue();
            Logger.i("input=" + input + ", except=" + expect);
            int output = maxPathSum(input);
            if (output == expect) {
                Logger.i("case pass by output=" + output);
            } else {
                throw new AssertionError("case fail by:" + "\nexpect=" + expect + ", but output=" + output);
            }
        }
        Logger.i("All Pass");
    }
}
