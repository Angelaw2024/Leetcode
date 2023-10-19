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
    // pre order(L boundary) + post order (R boundary)
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        if (root == null) return new ArrayList<>();
        List<Integer> res = new ArrayList<>();
        res.add(root.val);
        getBounds(root.left, res, true, false);
        getBounds(root.right, res, false, true);
        return res;
    }
    public void getBounds(TreeNode node, List<Integer> res, boolean isLBound, boolean isRBound) {
        if (node == null) return;

        if(node.left == null && node.right == null) {
            res.add(node.val);
            return;
        }
        // preOrder for L
        if (isLBound) {
            res.add(node.val);
        }
        // If a node in the left boundary and has a left child, then the left child is in the left boundary
        // isLBound && node.left != null :
        // If a node is in the left boundary, has no left child, but has a right child, 
        // then the right child is in the left boundary.
        // isLBound && node.left == null
        getBounds(node.left, res, isLBound && node.left != null, isRBound && node.right == null);
        getBounds(node.right, res, isLBound && node.left == null, isRBound && node.right != null);

        // postOrder for R
        if(isRBound) {
            res.add(node.val);
        }
    }
}