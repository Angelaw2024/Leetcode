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
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) return null;
        if (lists.length == 1) return lists[0];

        return merge(lists, 0, lists.length - 1);
    }

    public ListNode merge(ListNode[] lists, int l, int r) {
        if (l == r) return lists[l];
        if (l == r - 1) return mergeTwoLists(lists[l], lists[r]);
        int mid = l + (r - l) / 2;
        ListNode l1 = merge(lists, l, mid);
        ListNode l2 = merge(lists, mid + 1, r);
        return mergeTwoLists(l1, l2);
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null || list2 == null) {
            return list1 == null ? list2 : list1;
        }
        ListNode head = new ListNode();
        ListNode cur = head;
        ListNode p1 = list1;
        ListNode p2 = list2;
        while (p1!= null && p2 != null) {
            if (p1.val <= p2.val) {
                cur.next = p1;
                cur = cur.next;
                p1 = p1.next;
            } else {
                cur.next = p2;
                cur = cur.next;
                p2 = p2.next;
            }
        }
        if (p1 != null || p2 != null) {
            cur.next = p1 != null ? p1 : p2;
        }
        return head.next;
    }
}

// class Solution1 {
//     public ListNode mergeKLists(ListNode[] lists) {
//         if (lists == null || lists.length <= 0) return null;
//         if (lists.length == 1) return lists[0];
//         if (lists.length == 2) return mergeTwoLists(lists[0], lists[1]);

//         int len = lists.length;
//         while (len > 1) {
//             int curLen = len;
//             for (int i = 0; i < curLen / 2; i++) {
//                 int l2 = curLen - i - 1;
//                 lists[i] = mergeTwoLists(lists[i], lists[l2]);
//             }
//             if (curLen % 2 == 1) {
//                 lists[0] = mergeTwoLists(lists[0], lists[curLen / 2]);
//             }
//             len /= 2;
//         }
//         return lists[0];

//     }
//     public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
//         if (list1 == null || list2 == null) {
//             return list1 == null ? list2 : list1;
//         }
//         ListNode head = new ListNode();
//         ListNode cur = head;
//         ListNode p1 = list1;
//         ListNode p2 = list2;
//         while (p1!= null && p2 != null) {
//             if (p1.val <= p2.val) {
//                 cur.next = p1;
//                 cur = cur.next;
//                 p1 = p1.next;
//             } else {
//                 cur.next = p2;
//                 cur = cur.next;
//                 p2 = p2.next;
//             }
//         }
//         if (p1 != null || p2 != null) {
//             cur.next = p1 != null ? p1 : p2;
//         }
//         return head.next;
//     }
// }