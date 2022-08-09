package moi.leetcode2022.problems;

import moi.leetcode2022.structure.ListNode;
import moi.leetcode2022.utils.ListNodeUtil;
import moi.leetcode2022.utils.Logger;

import java.util.*;

/*
21. Merge Two Sorted Lists
Easy

13920

1251

Add to List

Share
You are given the heads of two sorted linked lists list1 and list2.

Merge the two lists in a one sorted list. The list should be made by splicing together the nodes of the first two lists.

Return the head of the merged linked list.


Example 1:
Input: list1 = [1,2,4], list2 = [1,3,4]
Output: [1,1,2,3,4,4]

Example 2:
Input: list1 = [], list2 = []
Output: []

Example 3:
Input: list1 = [], list2 = [0]
Output: [0]

Constraints:

The number of nodes in both lists is in the range [0, 50].
-100 <= Node.val <= 100
Both list1 and list2 are sorted in non-decreasing order.
 */
public class Problem0021_MergeTwoSortedLists {

    static ListNode result;
    static ListNode node;
    static ListNode temp;

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null && list2 == null) {
            return null;
        }
        return mergeKLists(new ListNode[]{list1, list2});
    }

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
        Map<List<int[]>, int[]> cases = new HashMap<>();
        cases.put(Arrays.asList(new int[]{1, 2, 4}, new int[]{1, 3, 4}), new int[]{1,1,2,3,4,4});
        cases.put(Arrays.asList(new int[]{1, 4, 5}, new int[]{0, 2}), new int[]{0, 1, 2, 4, 5});

        for (Map.Entry<List<int[]>, int[]> entry : cases.entrySet()) {
            ListNode[] input = ListNodeUtil.toListNodeArray(entry.getKey());
            ListNode expect = ListNodeUtil.toListNode(entry.getValue());
            Logger.i("input=" + Arrays.toString(input));
            ListNode output = mergeTwoLists(input[0], input[1]);
            if (ListNodeUtil.isEqual(output, expect)) {
                Logger.i("case pass by output=" + output);
            } else {
                throw new AssertionError("case fail by output=" + output + ", but expect=" + expect);
            }
        }
        Logger.i("All Pass");
    }
}
