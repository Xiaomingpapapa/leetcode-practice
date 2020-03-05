package com.zhiming.p20200303;

import java.util.HashSet;
import java.util.Set;

public class LinkedList {

    public static void main(String[] args) {

    }


    public static ListNode detectCycle(ListNode head) {
        // 寻找是否有相遇点
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast) {
            // 无环
            if (fast == null || fast.next == null) return null;
            slow = slow.next;
            fast = fast.next.next;
        }

        // 有环情况，此时 slow 和 fast 相遇
        fast = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow;
    }

}




class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}

