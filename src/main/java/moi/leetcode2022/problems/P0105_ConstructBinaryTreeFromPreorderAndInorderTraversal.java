package moi.leetcode2022.problems;

import moi.leetcode2022.structure.TreeNode;
import moi.leetcode2022.utils.ArrayUtil;
import moi.leetcode2022.utils.Logger;
import moi.leetcode2022.utils.TreeNodeUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
Given two integer arrays preorder and inorder where
preorder is the preorder traversal of a binary tree and inorder is the inorder traversal of the same tree,
construct and return the binary tree.

Example 1:


Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
Output: [3,9,20,null,null,15,7]
Example 2:

Input: preorder = [-1], inorder = [-1]
Output: [-1]


Constraints:

1 <= preorder.length <= 3000
inorder.length == preorder.length
-3000 <= preorder[i], inorder[i] <= 3000
preorder and inorder consist of unique values.
Each value of inorder also appears in preorder.
preorder is guaranteed to be the preorder traversal of the tree.
inorder is guaranteed to be the inorder traversal of the tree.
 */
public class P0105_ConstructBinaryTreeFromPreorderAndInorderTraversal {

    public static void main(String[] args) {
        test();
    }

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        return generateTreeNode(preorder, inorder, null);
    }

    static HashMap<Integer, Integer> map;
    static int preIndex;

    public static TreeNode buildTree2(int[] preorder, int[] inorder) {
        map = new HashMap<>();
        preIndex = 0;
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        return create(preorder, 0, inorder.length - 1);
    }

    public static TreeNode create(int[] pre, int s, int e) {
        if (s > e) {
            return null;
        }
        TreeNode root = new TreeNode(pre[preIndex++]);
        if (s == e) {
            return root;
        }
        int index = map.get(root.val);
        root.left = create(pre, s, index - 1);
        root.right = create(pre, index + 1, e);

        return root;
    }

    public static TreeNode generateTreeNode(int[] preorder, int[] inorder, Boolean isLeft) {
        Logger.i((isLeft == null ? "root" : (isLeft ? "left" : "right")) + ", preorder=" + Arrays.toString(preorder) + ", inorder=" + Arrays.toString(inorder));
        int preLength = preorder.length;
        int inLength = inorder.length;
        if (preLength == 0 && inLength == 0) {
            return null;
        }
        int val = preorder[0];
        int valIndex = findNumberInArray(inorder, preorder[0]);
        Logger.i("length=" + preLength + ", val=" + val + ", valIndex=" + valIndex);
        TreeNode node = new TreeNode(val);
        if (preLength == 1) {
            return node;
        }

        if (valIndex > 0) {
            node.left = generateTreeNode(Arrays.copyOfRange(preorder, 1, preLength), Arrays.copyOfRange(inorder, 0, valIndex), true);
        }
        if (valIndex < inLength - 1) {
            node.right = generateTreeNode(Arrays.copyOfRange(preorder, valIndex > 0 ? 2 : 1, preLength), Arrays.copyOfRange(inorder, valIndex + 1, inLength), false);
        }

        return node;
    }

    public static int findNumberInArray(int[] nums, int key) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == key) {
                return i;
            }
        }
        return -1;
    }

    public static void test() {
        Map<int[][], TreeNode> cases = new HashMap<>();
        cases.put(ArrayUtil.of2("[[3,9,20,15,7],[9,3,15,20,7]]"), TreeNodeUtil.of("[3,9,20,null,null,15,7]"));
        cases.put(ArrayUtil.of2("[[1,2],[1,2]]"), TreeNodeUtil.of("[1,null,2]"));
        cases.put(ArrayUtil.of2("[3,1,2,4],[1,2,3,4]"), TreeNodeUtil.of("[3,1,4,null,2]"));

        for (Map.Entry<int[][], TreeNode> entry : cases.entrySet()) {
            int[][] input = entry.getKey();
            TreeNode expect = entry.getValue();
            Logger.i("input=" + Arrays.deepToString(input) + ", except=" + expect);
            TreeNode output = buildTree2(input[0], input[1]);
            if (output.equals(expect)) {
                Logger.i("case pass by output=" + output);
            } else {
                throw new AssertionError("case fail by:" + "\nexpect=" + expect + ", but output=" + output);
            }
        }
        Logger.i("All Pass");
    }
}
