package Doordash;

//给一个array， 每次 pop最小的local peak，直到pop完为止
//example
//广告
//input [3, 5, 1, 4, 2]
//output [4, 2, 5, 3, 1]

import java.util.*;

public class Peaks {
    public static void main(String[] args) {
        int[] arr = {3, 5, 1, 4, 2};
        int[] output = popLocalPeaks(arr);
        
        // Print output array
        for (int num : output) {
            System.out.print(num + " ");
        }
    }

    public static int[] popLocalPeaks(int[] input) {
        DList dList = new DList();
        for (int i = input.length - 1; i >= 0; i--) {
            dList.add(new Node(input[i]));
        }
        int[] res = new int[input.length];
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.val, b.val));
        Node node = dList.head.next;
        while (!dList.isNull(node)) {
            if (dList.isPeak(node)) {
                pq.add(node);
            }
            node = node.next;
        }
        int i = 0;
        while (!pq.isEmpty()) {
            Node curNode = pq.poll();
            res[i++] = curNode.val;
            Node prev = curNode.prev;
            Node next = curNode.next;
            dList.remove(curNode);
            if (!dList.isNull(prev) && dList.isPeak(prev)) {
                pq.add(prev);
            }
            if (!dList.isNull(next) && dList.isPeak(next)) {
                pq.add(next);
            }
        }

        return res;
    }
}

class Node {
    int val;
    Node prev;
    Node next;
    public Node(int val) {
        this.val = val;
        prev = null;
        next = null;
    }
}

class DList {
    Node head;
    Node tail;
    public DList() {
        head = new Node(0);
        tail = new Node(0);
        head.next = tail;
        tail.prev = head;
    }

    public void add(Node node) {
        node.prev = tail.prev;
        node.prev.next = node;
        tail.prev = node;
        node.next = tail;
    }

    public void remove(Node node) {
        Node prev = node.prev;
        Node next = node.next;
        prev.next = next;
        next.prev = prev;
    }

    public boolean isNull(Node node) {
        return node == head || node == tail;
    }

    public boolean isPeak(Node node) {
        return (isNull(node.prev) || node.val > node.prev.val) && (isNull(node.next) || node.val > node.next.val);
    }
}
