/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        if (root == null) return new ArrayList<>();

        Map<Integer, List<Integer>> map = new HashMap<>();
        Queue<Pair<Integer, TreeNode> > queue = new LinkedList<>();
        int col = 0;
        int min = 0;
        int max = 0;
        queue.offer(new Pair(0, root));
        while (!queue.isEmpty()) {
            Pair<Integer, TreeNode> p = queue.poll();
            TreeNode curNode = p.getValue();
            col = p.getKey();

            if (!map.containsKey(col)) {
                map.put(col, new ArrayList<>());
            }
            map.get(col).add(curNode.val);

            min = Math.min(min, col);
            max = Math.max(max, col);

            if (curNode.left != null) queue.offer(new Pair(col - 1, curNode.left));
            if (curNode.right != null) queue.offer(new Pair(col + 1, curNode.right));   
        }

        List<List<Integer>> result = new ArrayList<>();
        for (int i = min; i <= max; i++) {
            result.add(map.get(i));
        }
        return result;
    }
}