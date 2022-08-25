package moi.leetcode2022.utils;

import moi.leetcode2022.structure.Node;

public class NodeUtil {

    public static Node of(String str) {
        str = str.replaceAll("\\[|\\]", "");
        return generateNode(str);
    }

    private static Node generateNode(String str) {
        int []arr = ArrayUtil.of(str);
        Node []nodes = new Node[arr.length + 1];
        for (int i = 1; i < nodes.length; i++) {
            if (arr[i - 1] != Integer.MAX_VALUE) {
                nodes[i] = new Node(arr[i - 1]);
            }else {
                nodes[i] = null;
            }
        }

        Node node;
        for (int i = 1; i < nodes.length / 2; i++) {
            node = nodes[i];
            if (node == null) continue;
            node.left = nodes[2 * i];
            node.right = nodes[2 * i + 1];
        }
        return nodes[1];
    }
}
