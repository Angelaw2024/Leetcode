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
    List<TreeNode> nums = new ArrayList<>();
    public TreeNode balanceBST(TreeNode root) {
        if (root == null) return root;
        inorder(root);
        return helper(0, nums.size() - 1);
    }

    public void inorder(TreeNode node) {
        if (node == null) return;
        inorder(node.left);
        nums.add(node);
        inorder(node.right);
    }

    public TreeNode helper(int left, int right) {
        if (left > right) return null;

        int mid = left + (right - left) / 2;
        TreeNode root = nums.get(mid);
        root.left = helper( left, mid - 1);
        root.right = helper( mid + 1, right);
        return root;
    }
}