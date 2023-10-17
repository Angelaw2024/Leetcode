/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
    Node[] visited = new Node[101];
    public Node cloneGraph(Node node) {
        if (node == null) return node;

        if (visited[node.val] != null) {
            return visited[node.val];
        }

        Node copy = new Node(node.val, new ArrayList<>());
        visited[copy.val] = copy;
        for (Node n: node.neighbors) {
            copy.neighbors.add(cloneGraph(n));
        }
        return copy;
    }
}

// class Solution1 {
//     public Node cloneGraph(Node node) {
//         if (node == null) return node;

//         if (visited.containsKey(node)) {
//             return visited.get(node);
//         }

//         Node copy = new Node(node.val, new ArrayList<>());
//         visited.put(node, copy);
//         for (Node n: node.neighbors) {
//             copy.neighbors.add(cloneGraph(n));
//         }
//         return copy;
//     }
// }