package moi.leetcode2022.structure;

import moi.leetcode2022.utils.TreeNodeUtil;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return TreeNodeUtil.ofList(this).toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TreeNode) {
            return this.val == ((TreeNode) obj).val &&
                    ((this.left == null && ((TreeNode) obj).left == null) || (this.left != null && this.left.equals(((TreeNode) obj).left))) &&
                    ((this.right == null && ((TreeNode) obj).right == null) || (this.right != null && this.right.equals(((TreeNode) obj).right)));
        } else {
            return false;
        }
    }
}
