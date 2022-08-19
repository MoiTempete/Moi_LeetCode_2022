package moi.leetcode2022.structure;

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
        if (left == null && right == null) {
            return String.valueOf(this.val);
        } else {
            return this.val + ", " + this.left + ", " + this.right;
        }
    }
}
