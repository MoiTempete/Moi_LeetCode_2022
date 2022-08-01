package moi.leetcode2022.problems;

import moi.leetcode2022.structure.ListNode;
import moi.leetcode2022.utils.Logger;

/**
 * 23. Merge k Sorted Lists
 * Hard
 * <p>
 * 13271
 * <p>
 * 509
 * <p>
 * Add to List
 * <p>
 * Share
 * You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
 * <p>
 * Merge all the linked-lists into one sorted linked-list and return it.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: lists = [[1,4,5],[1,3,4],[2,6]]
 * Output: [1,1,2,3,4,4,5,6]
 * Explanation: The linked-lists are:
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * merging them into one sorted list:
 * 1->1->2->3->4->4->5->6
 * Example 2:
 * <p>
 * Input: lists = []
 * Output: []
 * Example 3:
 * <p>
 * Input: lists = [[]]
 * Output: []
 * <p>
 * <p>
 * Constraints:
 * <p>
 * k == lists.length
 * 0 <= k <= 10^4
 * 0 <= lists[i].length <= 500
 * -10^4 <= lists[i][j] <= 10^4
 * lists[i] is sorted in ascending order.
 * The sum of lists[i].length will not exceed 10^4.
 */
public class Problem3 {

    static ListNode result;
    static ListNode node;
    static ListNode temp;

    public static ListNode mergeKLists(ListNode[] lists) {
        result = null;
        node = null;
        temp = null;
        if (lists.length == 0) {
            return null;
        }
        for (ListNode list : lists) {
            if (list == null) {
                continue;
            }
            if (result == null) {
                result = list;
            } else {
                node = list;
                if (result.val > node.val) {
                    temp = node.next;
                    node.next = result;
                    result = node;
                    if (temp != null) {
                        addElement(result, temp);
                    }
                } else {
                    addElement(result, node);
                }
            }
        }
        return result;
    }

    public static void addElement(ListNode original, ListNode element) {
        if (element == null) {
            return;
        }
        if (original.next == null) { // insert at tail
            if (original.val <= element.val) {
                original.next = element;
            } else {
                temp = element;
                element = original;
                result = temp;
                addElement(result, element);
            }
        } else {
            if (original.next.val > element.val) { //insert element
                temp = original.next;
                original.next = new ListNode(element.val, temp);
                if (element.next != null) {
                    addElement(original, element.next);
                }
            } else {
                addElement(original.next, element);
            }
        }
    }

    public static void test() {
        ListNode[] cases;
        ListNode list1 = new ListNode(1, new ListNode(4, new ListNode(5)));
        ListNode list2 = new ListNode(1, new ListNode(3, new ListNode(4)));
        ListNode list3 = new ListNode(2, new ListNode(6));
        cases = new ListNode[]{list1, list2, list3};
//
//        mergeKLists(new ListNode[]{new ListNode(), new ListNode()});


//        ListNode list1 = new ListNode(1, new ListNode(4, new ListNode(5)));
//        ListNode list2 = new ListNode(0, new ListNode(2));

//        ListNode list1 = new ListNode(1, new ListNode(2));
//        ListNode list2 = new ListNode(0);
//        cases = new ListNode[]{list1, list2};

        for (ListNode list : cases) {
            Logger.i(list.toString());
        }
        mergeKLists(cases);
        Logger.i(result == null ? "" : result.toString());
    }
}
