/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public void reorderList(ListNode head) {
        // divide list into two
        ListNode slow = head;
        ListNode fast= head;
        while (fast.next != null && fast.next.next != null ) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode half = slow.next;
        slow.next = null;

        // reverse seconde part of list
        ListNode prev = null;
        while (half != null) {
            ListNode next = half.next;
            half.next = prev;
            prev = half;
            half = next;
        }
        half = prev;

        // merge two list
        ListNode cur = head;
        while (head != null && half != null) {
            head = head.next;
            cur.next = half;
            half = half.next;
            cur.next.next = head;
            cur = cur.next.next;
        }
    }
}