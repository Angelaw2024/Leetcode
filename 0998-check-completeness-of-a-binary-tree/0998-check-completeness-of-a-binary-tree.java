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
    public boolean isCompleteTree(TreeNode root) {
        if (root == null) return true;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean noRight = false;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < queue.size(); i++) {
                TreeNode cur = queue.poll();
                if (cur.left != null) {
                    queue.add(cur.left);
                    if (noRight) return false;
                } else if (cur.left == null) {
                    noRight = true;
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                    if (noRight) return false;
                } else if (cur.right == null) {
                    noRight = true;
                }
            }
        }
        return true;
    }
}