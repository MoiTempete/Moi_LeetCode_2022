package moi.leetcode2022.utils;

import moi.leetcode2022.structure.TreeNode;

import java.util.ArrayList;
import java.util.List;

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

    public static List<List<Integer>> ofList(TreeNode root) {
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
            list  = result.get(level);
        }
        if (list == null) {
            list = new ArrayList<>();
            list.add(node.val);
            result.add(level, list);
        } else {
            list.add(node.val);
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
}
