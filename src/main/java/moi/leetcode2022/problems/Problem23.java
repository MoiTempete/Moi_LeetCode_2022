package moi.leetcode2022.problems;

import moi.leetcode2022.structure.ListNode;
import moi.leetcode2022.utils.ListNodeUtil;
import moi.leetcode2022.utils.Logger;

import java.util.*;

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
public class Problem23 {

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
        Map<List<int[]>, int[]> cases = new HashMap<>();
        cases.put(null, null);
        cases.put(new ArrayList<>(), new int[]{});
        cases.put(Arrays.asList(new int[]{1, 2}, new int[]{0}), new int[]{0, 1, 2});
        cases.put(Arrays.asList(new int[]{1, 4, 5}, new int[]{0, 2}), new int[]{0, 1, 2, 4, 5});
        cases.put(Arrays.asList(new int[]{1, 4, 5}, new int[]{1, 3, 4}, new int[]{2, 6}), new int[]{1, 1, 2, 3, 4, 4, 5, 6});

        for (Map.Entry<List<int[]>, int[]> entry : cases.entrySet()) {
            ListNode[] input = ListNodeUtil.toListNodeArray(entry.getKey());
            ListNode expect = ListNodeUtil.toListNode(entry.getValue());
            Logger.i("input=" + Arrays.toString(input));
            ListNode output = mergeKLists(input);
            if (ListNodeUtil.isEqual(output, expect)) {
                Logger.i("case pass by output=" + output);
            } else {
                throw new AssertionError("case fail by output=" + output + ", but expect=" + expect);
            }
        }
        Logger.i("All Pass");
    }
}
