/*
// Definition for a Node.
class Node {
    public int val;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _next) {
        val = _val;
        next = _next;
    }
};
*/

class Solution {
    public Node insert(Node head, int insertVal) {
        if (head == null) {
            Node newHead = new Node(insertVal);
            newHead.next = newHead;
            return newHead;
        }

        Node cur = head;
        while(true) {
            if (insertVal >= cur.val && insertVal <= cur.next.val) {
                break;
            }
            else if (cur.next.val < cur.val && (insertVal >= cur.val || insertVal <= cur.next.val)) {
                break;
            } 
            cur = cur.next;
            if (cur == head) break;
        } 

        cur.next = new Node(insertVal, cur.next);
        return head;
    }
}