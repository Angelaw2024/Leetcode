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
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode>  queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            int curMax = Integer.MIN_VALUE;
            while (size-- > 0) {
                TreeNode node = queue.poll();
                curMax = Math.max(node.val, curMax);
                if (node.left != null) {
                    queue.add(node.left);
                } 
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            res.add(curMax);
        }
        return res;
    }
}