package moi.leetcode2022.structure;

public class ListNode {

    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public String toString() {
        return "[" + val + (next != null ? ("," + next.nextToString()) : "]");
    }

    private String nextToString() {
        return val + (next != null ? ("," + next.nextToString()) : "]");
    }

}
