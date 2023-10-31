/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        return helpS(root, "");
    }
    public String helpS(TreeNode root, String s) {
        if (root == null) {
            s += "null,";
        } else {
            s += root.val + ",";
            s = helpS(root.left, s);
            s = helpS(root.right, s);
        }
        return s;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] array = data.split(",");
        List<String> list = new LinkedList<String>(Arrays.asList(array));
        return helperD(list);
    }
    
    public TreeNode helperD(List<String> list) {
        if (list.get(0).equals("null")) {
            list.remove(0);
            return null;
        }
        TreeNode root = new TreeNode(Integer.valueOf(list.get(0)));
        list.remove(0);
        root.left = helperD(list);
        root.right = helperD(list);
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));