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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        // 用来保存结果的列表
        List<List<Integer>> list = new LinkedList<>();
        // 如果根节点为空，直接返回空列表
        if(root == null){
            return list;
        }
        
        // 创建一个队列来保存每一层的节点
        Queue<TreeNode> q = new LinkedList<>();
        // 将根节点加入队列
        q.add(root);
        // 设置一个标志变量，表示当前层是奇数层还是偶数层
        boolean even = true;
        
        // 开始层次遍历
        while(!q.isEmpty()){
            int n = q.size();
            // 创建一个子列表来保存当前层的节点值
            LinkedList<Integer> subList = new LinkedList<>();
            
            // 遍历当前层的所有节点
            for(int i = 0; i < n; i++){
                TreeNode top = q.poll();
                
                // 将子节点加入队列
                if(top.left != null){
                    q.add(top.left);
                }
                if(top.right != null){
                    q.add(top.right);
                }
                // 根据标志变量，决定是加在子列表的末尾还是开头
                if(even)
                    subList.add(top.val);
                else
                    subList.addFirst(top.val);
            }
            // 将子列表加入结果列表
            list.add(subList);
            // 翻转标志变量
            even = !even;
        }
        
        return list;
    }
}
