/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
a -> a' -> b -> b'
*/

class Solution {
    public Node copyRandomList(Node head) {
        if (head == null) return head;

        // 复制节点，并把新节点放进next
        // a -> b, a -> a' -> b -> b'
        Node cur = head;
        while (cur != null) {
            Node newNode = new Node(cur.val);
            newNode.next = cur.next;
            cur.next = newNode;
            cur = cur.next.next;
        }
        
        // 找到新节点的random
        cur = head;
        while (cur != null && cur.next != null) {
            cur.next.random = cur.random != null ? cur.random.next : null;
            cur = cur.next.next;
        }

        // 还原两个list
        Node newHead = head.next;
        Node newCur = newHead;
        cur = head;
        while (cur != null) {
            cur.next = cur.next.next;
            newCur.next = newCur.next != null ? newCur.next.next : null;
            newCur = newCur.next;
            cur = cur.next;
        }
        return newHead;
    }
}