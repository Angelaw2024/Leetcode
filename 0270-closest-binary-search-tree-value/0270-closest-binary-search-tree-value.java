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
    public int closestValue(TreeNode root, double target) {
        int closet = root.val;
        while (root != null) {
            double diff = Math.abs(root.val - target) - Math.abs(closet - target);
            if (diff < 0 || diff == 0 && root.val < closet) closet = root.val;
            root = target > root.val ? root.right : root.left;
        }
        return closet;
    } 
}