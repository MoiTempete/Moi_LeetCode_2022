package moi.leetcode2022.problems;

import moi.leetcode2022.structure.Node;
import moi.leetcode2022.utils.Logger;
import moi.leetcode2022.utils.TreeNodeUtil;

import java.util.HashMap;
import java.util.Map;

/*
ou are given a perfect binary tree where all leaves are on the same level, and every parent has two children.
The binary tree has the following definition:

struct Node {
  int val;
  Node *left;
  Node *right;
  Node *next;
}
Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.

Example 1:

Input: root = [1,2,3,4,5,6,7]
Output: [1,#,2,3,#,4,5,6,7,#]
Explanation: Given the above perfect binary tree (Figure A),
your function should populate each next pointer to point to its next right node, just like in Figure B.
The serialized output is in level order as connected by the next pointers, with '#' signifying the end of each level.
Example 2:

Input: root = []
Output: []


Constraints:

The number of nodes in the tree is in the range [0, 212 - 1].
-1000 <= Node.val <= 1000


Follow-up:

You may only use constant extra space.
The recursive approach is fine. You may assume implicit stack space does not count as extra space for this problem.
 */
public class P0116_PopulatingNextRightPointersInEachNode {

    public static void main(String[] args) {
        test();
    }

    public static Node connect(Node root) {
        if (root == null) {
            return null;
        }
        if (root.left != null) {
            root.left.next = root.right;
            if (root.next != null) {
                root.right.next = root.next.left;
            }
        }
        connect(root.left);
        connect(root.right);
        return root;
    }

    public static void test() {
        Map<Node, Node> cases = new HashMap<>();
        cases.put((Node) TreeNodeUtil.of("[1,2,3,4,5,6,7]"), (Node) TreeNodeUtil.of("[1,2,3,4,5,6,7]"));

        for (Map.Entry<Node, Node> entry : cases.entrySet()) {
            Node input = entry.getKey();
            Node expect = entry.getValue();
            Logger.i("input=" + input + ", except=" + expect);
            Node output = connect(input);
            if (output.equals(expect)) {
                Logger.i("case pass by output=" + output);
            } else {
                throw new AssertionError("case fail by:" + "\nexpect=" + expect + ", but output=" + output);
            }
        }
        Logger.i("All Pass");
    }
}
