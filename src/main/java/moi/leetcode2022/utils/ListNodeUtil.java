package moi.leetcode2022.utils;

import moi.leetcode2022.structure.ListNode;

import java.util.*;

public class ListNodeUtil {
    public static ListNode toListNode(int[] array) {
        ListNode result = new ListNode();
        if (array == null || array.length == 0) {
            return result;
        }
        ListNode last = new ListNode();
        last.val = array[0];
        if (array.length == 1) {
            return last;
        }
        result = last;
        for (int i = 1; i < array.length; i++) {
            last.next = new ListNode(array[i]);
            last = last.next;
        }
        return result;
    }

    public static ListNode toListNode(ArrayList<Integer> array) {
        ListNode result = new ListNode();
        if (array == null || array.size() == 0) {
            return result;
        }
        ListNode last = new ListNode();
        last.val = array.get(0);
        if (array.size() == 1) {
            return last;
        }
        result = last;
        for (int i = 1; i < array.size(); i++) {
            last.next = new ListNode(array.get(i));
            last = last.next;
        }
        return result;
    }


    public static ArrayList<Integer> toArrayList(ListNode node) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        while (node != null) {
            arrayList.add(node.val);
            node = node.next;
        }

        return arrayList;
    }

    public static int[] toArray(ListNode node) {
        ArrayList<Integer> arrayList =  toArrayList(node);
        return Arrays.stream(arrayList.toArray(new Integer[0])).mapToInt(Integer::intValue).toArray();
    }

    public static ListNode[] toListNodeArray(List<int[]> list) {
        if (list == null || list.size() == 0) {
            return new ListNode[]{};
        }
        ListNode[] listNodes = new ListNode[list.size()];
        for (int i = 0; i < list.size(); i++) {
            listNodes[i] = toListNode(list.get(i));
        }
        return listNodes;
    }



    public static boolean isEqual(ListNode node, ListNode nodeCompare) {
        if (node == null || node.toString().equals("[0]")) {
            return nodeCompare == null || nodeCompare.toString().equals("[0]");
        }
        return node.toString().equals(nodeCompare.toString());
    }
}
