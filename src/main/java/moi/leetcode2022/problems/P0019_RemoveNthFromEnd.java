package moi.leetcode2022.problems;

import moi.leetcode2022.structure.ListNode;
import moi.leetcode2022.utils.ListNodeUtil;
import moi.leetcode2022.utils.Logger;

import java.util.HashMap;
import java.util.Map;

/*
19. Remove Nth Node From End of List
Medium

11615

522

Add to List

Share
Given the head of a linked list, remove the nth node from the end of the list and return its head.



Example 1:


Input: head = [1,2,3,4,5], n = 2
Output: [1,2,3,5]
Example 2:

Input: head = [1], n = 1
Output: []
Example 3:

Input: head = [1,2], n = 1
Output: [1]


Constraints:

The number of nodes in the list is sz.
1 <= sz <= 30
0 <= Node.val <= 100
1 <= n <= sz

 */
public class P0019_RemoveNthFromEnd {

    public static void main(String[] args) {
        test();
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head.next == null) {
            return null;
        }
        ListNode caliper = head;
        ListNode exact = head;
        int i = 0;
        while (i <= n -1) {
            caliper = caliper.next;
            i++;
        }
        if (caliper == null) {
            return head.next;
        }
        while (caliper.next != null) {
            caliper = caliper.next;
            exact = exact.next;
        }
        exact.next = exact.next.next;
        return head;
    }

//    public static ListNode toListNode(ArrayList<Integer> array) {
//        ListNode result = new ListNode();
//        if (array == null || array.size() == 0) {
//            return result;
//        }
//        ListNode last = new ListNode();
//        last.val = array.get(0);
//        if (array.size() == 1) {
//            return last;
//        }
//        result = last;
//        for (int i = 1; i < array.size(); i++) {
//            last.next = new ListNode(array.get(i));
//            last = last.next;
//        }
//        return result;
//    }

    public static void test() {
        Map<Case, int[]> cases = new HashMap<>();
        cases.put(new Case(new int[]{1, 2, 3, 4, 5}, 2), new int[]{1, 2, 3, 5});
        cases.put(new Case(new int[]{1}, 1), new int[]{});
        cases.put(new Case(new int[]{1, 2}, 1), new int[]{1});
        cases.put(new Case(new int[]{1, 2}, 2), new int[]{2});

        for (Map.Entry<Case, int[]> entry : cases.entrySet()) {
            Case input = entry.getKey();
            ListNode expect = ListNodeUtil.toListNode(entry.getValue());
            ListNode output = removeNthFromEnd(input.head, input.n);
            Logger.i("input=" + input + ", except=" + expect);
            if (ListNodeUtil.isEqual(output, expect)) {
                Logger.i("case pass by output=" + output);
            } else {
                throw new AssertionError("case fail by:" + "\ninput=" + input + ", expect=" + expect + ", but output=" + output);
            }
        }
        Logger.i("All Pass");
    }

    static class Case {

        ListNode head;
        int n;

        public Case(ListNode head, int n) {
            this.head = head;
            this.n = n;
        }

        public Case(int[] head, int n) {
            this.head = ListNodeUtil.toListNode(head);
            this.n = n;
        }

        @Override
        public String toString() {
            return "head=" + head.toString() + ", n=" + n;
        }
    }
}
