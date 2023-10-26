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
    public boolean isPalindrome(ListNode head) {
        // Edge case: an empty list is a palindrome.
        if (head == null) return true;

        // Step 1: Find the end of the first half of the list.
        ListNode firstHalfEnd = endOfFirstHalf(head);
        // Step 2: Reverse the second half of the list.
        ListNode secondHalfStart = reverseList(firstHalfEnd.next);

        // Step 3: Check if the list is a palindrome.
        ListNode p1 = head;
        ListNode p2 = secondHalfStart;
        while (p1!= null && p2 != null) {
            if (p1.val != p2.val) return false;
            p1 = p1.next;
            p2 = p2.next;
        }        

        // Step 4: Restore the original structure of the list.
        firstHalfEnd.next = reverseList(secondHalfStart);

        return true;
    }

    // Helper function to reverse the list.
    private ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    // Helper function to find the middle of the list.
    private ListNode endOfFirstHalf(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
}