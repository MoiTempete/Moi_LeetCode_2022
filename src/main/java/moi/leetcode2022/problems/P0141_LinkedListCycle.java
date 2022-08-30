package moi.leetcode2022.problems;

import moi.leetcode2022.structure.ListNode;
import moi.leetcode2022.utils.ListNodeUtil;
import moi.leetcode2022.utils.Logger;

import java.util.HashMap;
import java.util.Map;

/*
Given head, the head of a linked list, determine if the linked list has a cycle in it.

There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to. Note that pos is not passed as a parameter.

Return true if there is a cycle in the linked list. Otherwise, return false.


Example 1:


Input: head = [3,2,0,-4], pos = 1
Output: true
Explanation: There is a cycle in the linked list, where the tail connects to the 1st node (0-indexed).
Example 2:


Input: head = [1,2], pos = 0
Output: true
Explanation: There is a cycle in the linked list, where the tail connects to the 0th node.
Example 3:


Input: head = [1], pos = -1
Output: false
Explanation: There is no cycle in the linked list.


Constraints:

The number of the nodes in the list is in the range [0, 104].
-105 <= Node.val <= 105
pos is -1 or a valid index in the linked-list.


Follow up: Can you solve it using O(1) (i.e. constant) memory?
 */
public class P0141_LinkedListCycle {

    public static void main(String[] args) {
        test();
    }

    public static boolean hasCycle(ListNode head) {
        while (head != null) {
            if (head.val == 100001) {
                return true;
            } else {
                head.val = 100001;
            }
            head = head.next;
        }
        return false;
    }

    public static void test() {
        Map<ListNode, Boolean> cases = new HashMap<>();
        cases.put(ListNodeUtil.of("[3,2,0,-4]"), true);

        for (Map.Entry<ListNode, Boolean> entry : cases.entrySet()) {
            ListNode input = entry.getKey();
            boolean expect = entry.getValue();
            Logger.i("input=" + input + ", except=" + expect);
            boolean output = hasCycle(input);
            if (output == expect) {
                Logger.i("case pass by output=" + output);
            } else {
                throw new AssertionError("case fail by:" + "\nexpect=" + expect + ", but output=" + output);
            }
        }
        Logger.i("All Pass");
    }
}
