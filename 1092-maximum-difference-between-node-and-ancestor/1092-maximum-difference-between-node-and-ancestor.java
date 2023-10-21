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
        if (node.val > max) {
            max = node.val;
        }
        if (node.val < min) {
            min = node.val;
        }
        res = Math.max(max - min, res);
        helper(node.left, max, min);
        helper(node.right, max, min);
    }
}