/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        // trun into a graph
        Map<TreeNode, List<TreeNode>> graph = new HashMap<>();
        helper(graph, root);

        List<Integer> result = new ArrayList<>();
        Queue<TreeNode> q= new LinkedList<>();
        q.offer(target);
        Set<TreeNode> visited = new HashSet<>();
        int dist = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                TreeNode cur = q.poll();
                if (dist == k) {
                    result.add(cur.val);
                }
                visited.add(cur);
                for(TreeNode node: graph.get(cur)) {
                    if (!visited.contains(node)) {
                        q.offer(node);
                    }
                }
            }
            dist++;
        }
        return result;
    }
    private void helper(Map<TreeNode, List<TreeNode>> graph, TreeNode node) {
        if (node == null) return;
        graph.putIfAbsent(node, new ArrayList<>());
        if (node.left != null) {
            graph.putIfAbsent(node.left, new ArrayList<>());
            graph.get(node).add(node.left);
            graph.get(node.left).add(node);
            helper(graph, node.left);
        }
        if (node.right != null) {
            graph.putIfAbsent(node.right, new ArrayList<>());
            graph.get(node).add(node.right);
            graph.get(node.right).add(node);
            helper(graph, node.right);
        }
    }
}