package moi.leetcode2022.problems;

import moi.leetcode2022.structure.ListNode;
import moi.leetcode2022.utils.ListNodeUtil;
import moi.leetcode2022.utils.Logger;

import java.util.HashMap;
import java.util.Map;

/*
Given the head of a singly linked list, return true if it is a palindrome or false otherwise.

Example 1:

Input: head = [1,2,2,1]
Output: true
Example 2:

Input: head = [1,2]
Output: false

Constraints:

The number of nodes in the list is in the range [1, 105].
0 <= Node.val <= 9

Follow up: Could you do it in O(n) time and O(1) space?
 */
public class P0234_PalindromeLinkedList {

    public static void main(String[] args) {
        test();
    }

    static ListNode revHead = null;

    public static boolean isPalindrome(ListNode head) {
        ListNode temp = head;
        int count = 0;
        while (temp != null) {
            ListNode node = new ListNode(temp.val);
            insertAtFirst(node);
            count++;
            temp = temp.next;
        }
        count /= 2;
        while (count-- > 0) {
            if (head.val != revHead.val)
                return false;
            head = head.next;
            revHead = revHead.next;
        }
        return true;
    }

    public static void insertAtFirst(ListNode node) {
        node.next = revHead;
        revHead = node;
    }

    public static void test() {
        Map<ListNode, Boolean> cases = new HashMap<>();
        cases.put(ListNodeUtil.of("[8,0,7,1,7,7,9,7,5,2,9,1,7,3,7,0,6,5,1,7,7,9,3,8,1,5,7,7,8,4,0,9,3,7,3,4,5,7,4,8,8,5,8,9,8,5,8,8,4,7,5,4,3,7,3,9,0,4,8,7,7,5,1,8,3,9,7,7,1,5,6,0,7,3,7,1,9,2,5,7,9,7,7,1,7,0,8]"), true);
        cases.put(ListNodeUtil.of("[1,2,3,4,4,3,2,1]"), true);
        cases.put(ListNodeUtil.of("[1,2,3,3,2,1]"), true);
        cases.put(ListNodeUtil.of("[1,2,3,2,1]"), true);
        cases.put(ListNodeUtil.of("[1,2,2,1]"), true);

        for (Map.Entry<ListNode, Boolean> entry : cases.entrySet()) {
            ListNode input = entry.getKey();
            boolean expect = entry.getValue();
            Logger.i("input=" + input + ", except=" + expect);
            boolean output = isPalindrome(input);
            if (output == expect) {
                Logger.i("case pass by output=" + output);
            } else {
                throw new AssertionError("case fail by:" + "\nexpect=" + expect + ", but output=" + output);
            }
        }
        Logger.i("All Pass");
    }
}
