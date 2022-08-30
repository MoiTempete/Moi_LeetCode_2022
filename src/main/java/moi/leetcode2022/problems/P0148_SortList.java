package moi.leetcode2022.problems;

import moi.leetcode2022.structure.ListNode;
import moi.leetcode2022.utils.ListNodeUtil;
import moi.leetcode2022.utils.Logger;

import java.util.HashMap;
import java.util.Map;

/*
Given the head of a linked list, return the list after sorting it in ascending order.



Example 1:


Input: head = [4,2,1,3]
Output: [1,2,3,4]
Example 2:


Input: head = [-1,5,3,4,0]
Output: [-1,0,3,4,5]
Example 3:

Input: head = []
Output: []


Constraints:

The number of nodes in the list is in the range [0, 5 * 104].
-10^5 <= Node.val <= 10^5


Follow up: Can you sort the linked list in O(n logn) time and O(1) memory (i.e. constant space)?
 */
public class P0148_SortList {

    public static void main(String[] args) {
        test();
    }

    // cuz need time in O(n log n), so use merge sort
    // cuz need space in O(1), so use bottom-to-up merge sort
    public static ListNode sortList(ListNode head) {
        ListNode prefixHead = new ListNode(Integer.MAX_VALUE);
        prefixHead.next = head;

        int length = getListLength(head);

        ListNode tail;
        ListNode temp;
        for (int step = 1; step < length; step <<= 1) {
            temp = prefixHead.next;
            tail = prefixHead;
            while (temp != null) {
                ListNode left = temp;
                ListNode right = cutByLength(left, step);
                temp = cutByLength(right, step);
                tail.next = mergeTwoList(left, right);
                while (tail.next != null) {
                    tail = tail.next;
                }
            }
        }
        return prefixHead.next;
    }

    public static int getListLength(ListNode head) {
        int length = 0;
        ListNode temp = head;
        while (temp != null) {
            temp = temp.next;
            length++;
        }
        return length;
    }

    public static ListNode cutByLength(ListNode head, int length) {
        while (length - 1 > 0 && head != null) {
            head = head.next;
            length--;
        }
        if (head == null) {
            return null;
        }
        ListNode next = head.next;
        head.next = null;
        return next;
    }

    public static ListNode mergeTwoList(ListNode listNode1, ListNode listNode2) {
        ListNode temp = new ListNode(0);
        ListNode head = temp;
        while (listNode1 != null && listNode2 != null) {
            if (listNode1.val < listNode2.val) {
                temp.next = listNode1;
                listNode1 = listNode1.next;
            } else {
                temp.next = listNode2;
                listNode2 = listNode2.next;
            }
            temp = temp.next;
        }
        temp.next = listNode1 != null ? listNode1 : listNode2;

        return head.next;
    }

    public static void test() {
        Map<ListNode, ListNode> cases = new HashMap<>();
//        cases.put(ListNodeUtil.of("[4,2,1,3]"), ListNodeUtil.of("[1,2,3,4]"));
        cases.put(ListNodeUtil.of("[-1,5,3,4,0]"), ListNodeUtil.of("[-1,0,3,4,5]"));

        for (Map.Entry<ListNode, ListNode> entry : cases.entrySet()) {
            ListNode input = entry.getKey();
            ListNode expect = entry.getValue();
            Logger.i("input=" + input + ", except=" + expect);
            ListNode output = sortList(input);
            if (output.equals(expect)) {
                Logger.i("case pass by output=" + output);
            } else {
                throw new AssertionError("case fail by:" + "\nexpect=" + expect + ", but output=" + output);
            }
        }
        Logger.i("All Pass");
    }
}
