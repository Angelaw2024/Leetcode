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
    int res = 0;
    public int maxAncestorDiff(TreeNode root) {
        if (root == null) return 0;

        helper(root, root.val, root.val);
        return res;
    }
    public void helper(TreeNode node, int max, int min) {
        if (node == null) return;
        max = Math.max(max, node.val);
        min = Math.min(min, node.val);
        res = Math.max(Math.abs(max - min), res);
        helper(node.left, max, min);
        helper(node.right, max, min);
    }
}