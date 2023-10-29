class LRUCache {
    HashMap<Integer, Node> dict;
    DDLinkedList ddll;
    public LRUCache(int capacity) {
        ddll = new DDLinkedList(capacity);
        dict = new HashMap<>();
    }
    
    public int get(int key) {
        if (!dict.containsKey(key)) return -1;
        Node node = dict.get(key);
        ddll.moveFirst(node);
        return node.val;
    }
    
    public void put(int key, int value) {
        if (dict.containsKey(key)) {
            Node cur = dict.get(key);
            cur.val = value;
            ddll.moveFirst(cur);
        } else  {
            Node node = new Node(key, value);
            if (ddll.count >= ddll.capacity) {
                int keyToRemove = ddll.removeTail();
                dict.remove(keyToRemove);
            }
            ddll.addNode(node);
            dict.put(key, node);
        }
    }
}
class Node {
    int key;
    int val;
    Node prev;
    Node next;
    
    public Node(int key, int val) {
        this.key = key;
        this.val = val;
    }
}

class DDLinkedList {
    Node head;
    Node tail;
    int capacity;
    int count;

    public DDLinkedList (int capacity) {
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
        this.capacity = capacity;
        count = 0;
    }
    // add to front
    public void addNode (Node node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
        count++;
    }

    void removeNode(Node node) {
        Node prev = node.prev;
        Node next = node.next;

        prev.next = next;
        next.prev = prev;
        node.next = null;
        node.prev = null;
        count--;
    }

    void moveFirst(Node node) {
        removeNode(node);
        addNode(node);
    }

    int removeTail() {
        Node prev = tail.prev;
        removeNode(prev);
        return prev.key;
    }
}
/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */