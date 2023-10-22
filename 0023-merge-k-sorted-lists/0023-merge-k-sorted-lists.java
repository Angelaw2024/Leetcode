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

//  1 , 2, 3 , 4, 5
//  1 + 5, 2 + 4, 3
//  1 + 5 + 3, 2 + 4
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        PriorityQueue<ListNode> q = new PriorityQueue<>((a, b) -> (a.val - b.val));
        for (ListNode i : lists) {
            if (i != null) {
                q.offer(i);
            }
        }
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (!q.isEmpty()) {
            cur.next = q.poll();
            cur = cur.next;
            if (cur.next != null) {
                q.offer(cur.next);
            }
        }
        return dummy.next;
    }

    // public ListNode mergeKLists(ListNode[] lists) {
    //     if (lists == null || lists.length <= 0) return null;
    //     if (lists.length == 1) return lists[0];
    //     if (lists.length == 2) return mergeTwoLists(lists[0], lists[1]);

    //     int len = lists.length;
    //     while (len > 1) {
    //         int curLen = len;
    //         for (int i = 0; i < curLen / 2; i++) {
    //             int l2 = curLen - i - 1;
    //             lists[i] = mergeTwoLists(lists[i], lists[l2]);
    //         }
    //         if (curLen % 2 == 1) {
    //             lists[0] = mergeTwoLists(lists[0], lists[curLen / 2]);
    //         }
    //         len /= 2;
    //     }
    //     return lists[0];
    // }
    // public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
    //     if (list1 == null || list2 == null) {
    //         return list1 == null ? list2 : list1;
    //     }
    //     ListNode head = new ListNode();
    //     ListNode cur = head;
    //     ListNode p1 = list1;
    //     ListNode p2 = list2;
    //     while (p1!= null && p2 != null) {
    //         if (p1.val <= p2.val) {
    //             cur.next = p1;
    //             cur = cur.next;
    //             p1 = p1.next;
    //         } else {
    //             cur.next = p2;
    //             cur = cur.next;
    //             p2 = p2.next;
    //         }
    //     }
    //     if (p1 != null || p2 != null) {
    //         cur.next = p1 != null ? p1 : p2;
    //     }
    //     return head.next;
    // }
}

