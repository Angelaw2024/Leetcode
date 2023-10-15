/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node parent;
};
*/

class Solution {
    public Node lowestCommonAncestor(Node p, Node q) {
        Node a = p;
        Node b = q;
        while (a != b) {
            a = a == null ? q : a.parent;
            b = b == null ? p : b.parent;
        }
        return a;
    }
}
/*
我来解释一下，为什么把链表连起来，就可以得到相交的部分。

首先是两个链表（约定，值相同代表同一节点，0 代表空节点）
A表：[1, 2, 3, 7, 8, 9] H1
B表：[4, 5, 7, 8, 9] h2

连接两个链表（表与表之间用 0 隔开）
AB表：[1, 2, 3, 7, 8, 9, 0, 4, 5, 7, 8, 9, 0]
BA表：[4, 5, 7, 8, 9, 0, 1, 2, 3, 7, 8, 9, 0]

观察连接后的两个表，可以发现相交的部分整齐的排列在末尾，
只需要逐个比较这两张表的节点，就能找到相交的起始位置。

如果没有相交会如何？会陷入死循环吗？
A表：[1, 2, 3]
B表：[4, 5]

连接两个链表（表与表之间用 0 隔开）
AB表：[1, 2, 3, 0, 4, 5, 0] h1 + h2
BA表：[4, 5, 0, 1, 2, 3, 0] h1 + h2

观察连接后的两个表，可以发现末尾相交的部分必然为空，
参照上面的逻辑，返回首个相同的节点，为空是符合题意的。
O(h1 + h2) 
h1 < h2
O(h1 + h2) < O(2h2)
-> O(h2)
 */