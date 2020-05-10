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
    TreeNode ans = null;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        recur(root, p, q);
        return ans;
    }

    private boolean recur(TreeNode currentNode, TreeNode p, TreeNode q) {
        //terminator
        if (currentNode == null) {
            return false;
        }

        //process current logic
        int mid = currentNode == q || currentNode == p ? 1 : 0;

        //drill down
        int left = recur(currentNode.left, p, q) ? 1 : 0;
        int right = recur(currentNode.right, p, q) ? 1 : 0;

        if ((left + right + mid) >= 2) ans = currentNode;

        return (left + right + mid) > 0;
        //reverse status
    }
}