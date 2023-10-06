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

    public int findDistance(TreeNode root, int p, int q) {
        int res = 0;
        
        if (p == q) return 0;
        return dfs(root, p, q, 0);
    }
    private int dfs(TreeNode root, int p, int q, int depth) {
        if (root == null) return 0;
        
        if (root.val == p || root.val == q) {
            int left = dfs(root.left, p, q, 1);
            int right = dfs(root.right, p, q, 1);
            return (left > 0 || right > 0) ? Math.max(left, right) : depth;
        } else {
            int left = dfs(root.left, p, q, depth + 1);
            int right = dfs(root.right, p, q, depth + 1);
            int total = left + right;
            if (left != 0 && right != 0) {
                total = total - 2 * depth;
            }
            return total;
        }



    }
}