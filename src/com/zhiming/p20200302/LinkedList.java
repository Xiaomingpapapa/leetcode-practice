package com.zhiming.p20200302;


public class LinkedList {
    public static void main(String[] args) {

    }

    public static ListNode reverseList(ListNode head) {
        // 递归出口
        if (head == null || head.next == null) return head;
        ListNode pre = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return pre;
    }

    public static boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return false;

        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) return true;
            slow = slow.next;
            fast = fast.next.next;
        }

        // 说明快指针最后有从后面追上了慢指针，链表有环
        return true;
    }
}



class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
