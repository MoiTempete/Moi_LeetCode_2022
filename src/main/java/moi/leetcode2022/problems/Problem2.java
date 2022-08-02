package moi.leetcode2022.problems;

import moi.leetcode2022.structure.ListNode;
import moi.leetcode2022.utils.ListNodeUtil;
import moi.leetcode2022.utils.Logger;

import java.util.*;

/**
 * 2. Add Two Numbers
 * Medium
 * <p>
 * 20099
 * <p>
 * 4014
 * <p>
 * Add to List
 * <p>
 * Share
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.
 * <p>
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: l1 = [2,4,3], l2 = [5,6,4]
 * Output: [7,0,8]
 * Explanation: 342 + 465 = 807.
 * Example 2:
 * <p>
 * Input: l1 = [0], l2 = [0]
 * Output: [0]
 * Example 3:
 * <p>
 * Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * Output: [8,9,9,9,0,0,0,1]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in each linked list is in the range [1, 100].
 * 0 <= Node.val <= 9
 * It is guaranteed that the list represents a number that does not have leading zeros.
 * Accepted
 * 2,958,722
 * Submissions
 * 7,554,462
 */
public class Problem2 {

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return addTwoNumbers(l1, l2, 0);
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2, int carry) {
        if (l1 == null && l2 == null) {
            return carry == 0 ? null : new ListNode(carry);
        }
        ListNode sumNode = new ListNode();
        int sum = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + carry;
        int current = sum < 10 ? sum : sum - 10;
        int nextCarry = sum < 10 ? 0 : 1;
        sumNode.val = (current);
        sumNode.next = addTwoNumbers(l1 == null ? null : l1.next, l2 == null ? null : l2.next, nextCarry);

        return sumNode;
    }

    public static void test() {
        Map<List<int[]>, int[]> cases = new HashMap<>();
        cases.put(Arrays.asList(new int[]{2, 4, 3}, new int[]{5, 6, 4}), new int[]{7, 0, 8});
        cases.put(Arrays.asList(new int[]{0}, new int[]{0}), new int[]{0});
        cases.put(Arrays.asList(new int[]{9,9,9,9,9,9,9}, new int[]{9,9,9,9}), new int[]{8,9,9,9,0,0,0,1});

        for (Map.Entry<List<int[]>, int[]> entry : cases.entrySet()) {
            ListNode input1 = ListNodeUtil.toListNode(entry.getKey().get(0));
            ListNode input2 = ListNodeUtil.toListNode(entry.getKey().get(1));
            ListNode expect = ListNodeUtil.toListNode(entry.getValue());
            Logger.i("input1=" + input1);
            Logger.i("input2=" + input2);
            ListNode output = addTwoNumbers(input1, input2);
            if (ListNodeUtil.isEqual(output, expect)) {
                Logger.i("case pass by output=" + output);
            } else {
                throw new AssertionError("case fail by output=" + output + ", but expect=" + expect);
            }
        }
        Logger.i("All Pass");
    }

}
