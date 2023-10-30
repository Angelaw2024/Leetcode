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
public class Solution {
    // 主函数
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        if (root == null) return root;
        // 调用 depth 函数并返回包含所有最深节点的最小子树的根。
        return depth(root).getValue();
    }

    // 辅助函数，返回一个 Pair 对象，包含深度和最小子树的根。
    private Pair<Integer, TreeNode> depth(TreeNode node) {
        // 如果节点是叶子节点，返回深度 0 和当前节点。
        if (node.left == null && node.right == null) {
            return new Pair<>(0, node);
        }

        // 如果左孩子存在，递归地找出左子树的深度和最小子树的根。
        // 否则，返回深度为 -1 和 null。
        Pair<Integer, TreeNode> left = node.left != null ? depth(node.left) : new Pair<>(-1, null);

        // 如果右孩子存在，递归地找出右子树的深度和最小子树的根。
        // 否则，返回深度为 -1 和 null。
        Pair<Integer, TreeNode> right = node.right != null ? depth(node.right) : new Pair<>(-1, null);

        // 如果左右子树的深度相同，则当前节点就是包含所有最深节点的最小子树的根。
        if (left.getKey() == right.getKey()) {
            return new Pair<>(left.getKey() + 1, node);
        }

        // 如果左子树比右子树更深，返回左子树的深度和最小子树的根。
        if (left.getKey() > right.getKey()) {
            return new Pair<>(left.getKey() + 1, left.getValue());
        } else {
            // 如果右子树比左子树更深，返回右子树的深度和最小子树的根。
            return new Pair<>(right.getKey() + 1, right.getValue());
        }
    }
}