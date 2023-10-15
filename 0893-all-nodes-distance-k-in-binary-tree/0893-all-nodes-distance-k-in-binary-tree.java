/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        List<Integer> res = new ArrayList<>();
        getNodes(root, target, k, res);
        return res;
    }

    public int getNodes(TreeNode root, TreeNode target, int k, List<Integer> res){
        if(root == null){
            return -1;
        }
        if(root == target){
            getSub(root, 0, k, res);
            return 1;
        }else{
            int left = getNodes(root.left, target, k, res);
            int right = getNodes(root.right, target, k, res);

            if(left == k || right == k){
                res.add(root.val);
                return -1;
            }else if(left != -1){
                getSub(root.right, left + 1, k, res);
                return left + 1;
            }else if(right != -1){
                getSub(root.left, right + 1, k, res);
                return right + 1;
            }else{
                return -1;
            }
        }
    }

    public void getSub(TreeNode root, int cur, int k, List<Integer> res){
        if(root == null){
            return;
        }
        if(k == cur){
            res.add(root.val);
            return;
        }
        getSub(root.left, cur + 1, k, res);
        getSub(root.right, cur + 1, k, res);
    }
}