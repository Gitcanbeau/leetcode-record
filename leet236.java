public class leet236 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
    }
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //Runtime: 11 ms, faster than 62.55% of Java online submissions for Lowest Common Ancestor of a Binary Tree.
        //Memory Usage: 47.3 MB, less than 46.23% of Java online submissions for Lowest Common Ancestor of a Binary Tree.
        if (root == null || root == p || root == q) { // 递归结束条件
            return root;
        }

        // 后序遍历
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if(left == null && right == null) { // 若未找到节点 p 或 q
            return null;
        }else if(left == null && right != null) { // 若找到一个节点
            return right;
        }else if(left != null && right == null) { // 若找到一个节点
            return left;
        }else { // 若找到两个节点
            return root;
        }
    }
}
