package moi.leetcode2022.utils;

import moi.leetcode2022.structure.TreeNode;

public class TreeNodeUtil {

    public static TreeNode of(String str) {
        str = str.replaceAll("\\[|\\]", "");
        return generateTreeNode(str);
    }

    private static TreeNode generateTreeNode(String str) {
        int []arr = ArrayUtil.of(str);
        TreeNode []nodes = new TreeNode[arr.length + 1];
        for (int i = 1; i < nodes.length; i++) {
            if (arr[i - 1] != Integer.MAX_VALUE) {
                nodes[i] = new TreeNode(arr[i - 1]);
            }else {
                nodes[i] = null;
            }
        }

        TreeNode node;
        for (int i = 1; i < nodes.length / 2; i++) {
            node = nodes[i];
            if (node == null) continue;
            node.left = nodes[2 * i];
            node.right = nodes[2 * i + 1];
        }
        return nodes[1];
    }
}
