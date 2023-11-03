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
    // 主要的函数，用于找到包含所有最深节点的最小子树的根。
    public TreeNode subtreeWithAllDeepest(TreeNode node) {
        // 如果是空节点，直接返回
        if(node == null) return node;

        // 找到左子树的深度
        int l = findDepth(node.left);

        // 找到右子树的深度
        int r = findDepth(node.right);

        // 比较左右子树的深度
        if(l < r) {
            // 如果右子树更深，递归进入右子树
            return subtreeWithAllDeepest(node.right);
        } else if(l > r) {
            // 如果左子树更深，递归进入左子树
            return subtreeWithAllDeepest(node.left);
        }

        // 如果左右子树的深度相同，返回当前节点
        return node;
    }

    // 缺少的辅助函数，用于计算一个节点的最大深度。
    // 这个函数在上面的代码中并没有给出，但它是必要的。
    public int findDepth(TreeNode node) {
        if(node == null) return 0;
        return 1 + Math.max(findDepth(node.left), findDepth(node.right));
    }
}