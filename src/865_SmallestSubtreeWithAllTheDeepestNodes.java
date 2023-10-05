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
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        if (root == null) return root;
        return depth(root).getValue();
    }
    private Pair<Integer, TreeNode> depth(TreeNode node) {
        if (node.left == null && node.right == null) {
            return new Pair<>(0, node);
        }
        Pair<Integer, TreeNode> left = node.left != null ? depth(node.left) : new Pair<>(-1, null);
        Pair<Integer, TreeNode> right = node.right != null ? depth(node.right) : new Pair<>(-1, null);
        if (left.getKey() == right.getKey()) {
            return new Pair<>(left.getKey() + 1, node);
        }
        if (left.getKey() > right.getKey()) {
            return new Pair<>(left.getKey() + 1, left.getValue());
        } else {
            return new Pair<>(right.getKey() + 1, right.getValue());
        }
        
    }
}