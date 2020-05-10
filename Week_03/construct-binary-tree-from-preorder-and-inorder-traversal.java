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

    private int pre_idx = 0;
    private Map<Integer, Integer> inx_map = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++)
            inx_map.put(inorder[i], i);
        return _buildTree(0, inorder.length, preorder, inorder);
    }

    private TreeNode _buildTree(int in_left, int in_right, int[] preorder, int[] inorder) {

        //terminator
        if (in_left == in_right) return null;

        //process current logic
        TreeNode root = new TreeNode(preorder[pre_idx++]);
        int index = inx_map.get(root.val);

        //drill down
        root.left = _buildTree(in_left, index, preorder, inorder);
        root.right = _buildTree(index + 1, in_right, preorder, inorder);

        return root;
        //reverse status
    }
}