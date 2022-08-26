package moi.leetcode2022.structure;

import moi.leetcode2022.utils.Logger;
import moi.leetcode2022.utils.TreeNodeUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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

    Stack<TreeNode> visit = new Stack<>();
    List<TreeNode> seen = new ArrayList<>();

    public void dfsTraversal() {
        visit.push(this);
        seen.add(this);
        dfs(this);
        Logger.ic("dfs path ");
        for (int i = 0; i < seen.size(); i++) {
            if (i == 0) {
                Logger.ic("= [" + seen.get(i).val + "]");
            } else {
                Logger.ic(", [" + seen.get(i).val + "]");
            }
        }
    }

    private void dfs(TreeNode node) {
        TreeNode nextNode =
                node.left != null && !seen.contains(node.left)
                ? node.left
                : (node.right != null && !seen.contains(node.right)
                        ? node.right
                        : null);
        if (nextNode != null) {
            visit.push(nextNode);
            seen.add(nextNode);
            dfs(nextNode);
        } else {
            visit.pop();
            if (!visit.empty()) {
                dfs(visit.peek());
            }
        }
    }
}
