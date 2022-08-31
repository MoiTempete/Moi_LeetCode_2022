package moi.leetcode2022.problems;

import moi.leetcode2022.structure.ListNode;
import moi.leetcode2022.utils.ListNodeUtil;
import moi.leetcode2022.utils.Logger;

import java.util.HashMap;
import java.util.Map;

/*
Given the head of a singly linked list, reverse the list, and return the reversed list.

Example 1:

Input: head = [1,2,3,4,5]
Output: [5,4,3,2,1]
Example 2:

Input: head = [1,2]
Output: [2,1]
Example 3:

Input: head = []
Output: []

Constraints:

The number of nodes in the list is the range [0, 5000].
-5000 <= Node.val <= 5000

Follow up: A linked list can be reversed either iteratively or recursively. Could you implement both?
 */
public class P0206_ReverseLinkedList {

    public static void main(String[] args) {
        test();
    }

    public static ListNode reverseList(ListNode head) {
        ListNode result = null;
        while (head != null) {
            ListNode temp = head;
            head = head.next;
            temp.next = result;
            result = temp;
        }

        return result;
    }

    public static void test() {
        Map<ListNode, ListNode> cases = new HashMap<>();
        cases.put(ListNodeUtil.of("[1,2,3,4,5]"), ListNodeUtil.of("[5,4,3,2,1]"));

        for (Map.Entry<ListNode, ListNode> entry : cases.entrySet()) {
            ListNode input = entry.getKey();
            ListNode expect = entry.getValue();
            Logger.i("input=" + input + ", except=" + expect);
            ListNode output = reverseList(input);
            if (expect.equals(output)) {
                Logger.i("case pass by output=" + output);
            } else {
                throw new AssertionError("case fail by:" + "\nexpect=" + expect + ", but output=" + output);
            }
        }
        Logger.i("All Pass");
    }
}
