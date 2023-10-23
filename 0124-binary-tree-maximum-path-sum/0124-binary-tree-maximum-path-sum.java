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
    int max;
    public int maxPathSum(TreeNode root) {
        if (root == null) return 0;
        max = root.val;
        helper(root);
        return max;
    }
    public int helper(TreeNode node) {
        if (node == null) return 0;

        int left = Math.max(helper(node.left), 0);
        int right = Math.max(helper(node.right), 0);
        max = Math.max(max, left + right + node.val);
        return Math.max(left, right) + node.val;
    }
}