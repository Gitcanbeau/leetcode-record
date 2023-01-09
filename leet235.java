public class leet235 {
    public static void main(String[] args) {
        //Given a binary search tree (BST), find the lowest common ancestor (LCA) node of two given nodes in the BST.
        //According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”
        //Example 1:
        //Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
        //Output: 6
        //Explanation: The LCA of nodes 2 and 8 is 6.
        //Example 2:
        //Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
        //Output: 2
        //Explanation: The LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself according to the LCA definition.
        //Example 3:
        //Input: root = [2,1], p = 2, q = 1
        //Output: 2
        //Constraints:
        //The number of nodes in the tree is in the range [2, 105].
        //-109 <= Node.val <= 109
        //All Node.val are unique.
        //p != q
        //p and q will exist in the BST.
        TreeNode node0=new TreeNode(0,null,null);
        TreeNode node1=new TreeNode(1,null,null);
        TreeNode node2=new TreeNode(2,null,null);
        TreeNode node3=new TreeNode(3,null,null);
        TreeNode node4=new TreeNode(4,null,null);
        TreeNode node5=new TreeNode(5,null,null);
        TreeNode node6=new TreeNode(6,null,null);
        TreeNode node7=new TreeNode(7,null,null);
        TreeNode node8=new TreeNode(8,null,null);
        TreeNode node9=new TreeNode(9,null,null);
        node6.left=node2;
        node6.right=node8;
        node2.left=node0;
        node2.right=node4;
        node4.left=node3;
        node3.right=node5;
        node8.left=node7;
        node8.right=node9;
        System.out.println(lowestCommonAncestor(node6,node3,node5));
        System.out.println(lowestCommonAncestor2(node6,node3,node5));
        //Runtime: 13 ms, faster than 7.55% of Java online submissions for Lowest Common Ancestor of a Binary Search Tree.
        //Memory Usage: 50 MB, less than 38.84% of Java online submissions for Lowest Common Ancestor of a Binary Search Tree.
    }
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //Runtime: 8 ms, faster than 75.43% of Java online submissions for Lowest Common Ancestor of a Binary Search Tree.
        //Memory Usage: 50.3 MB, less than 14.71% of Java online submissions for Lowest Common Ancestor of a Binary Search Tree.
        if(root == null) return null;

        if(root.val < p.val && root.val < q.val)
            return lowestCommonAncestor(root.right, p, q);

        if(root.val > p.val && root.val > q.val)
            return lowestCommonAncestor(root.left, p, q);

        return root;
    }
    public static TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        //Runtime: 4 ms, faster than 100.00% of Java online submissions for Lowest Common Ancestor of a Binary Search Tree.
        //Memory Usage: 43.5 MB, less than 87.25% of Java online submissions for Lowest Common Ancestor of a Binary Search Tree.
        if (root == null) return null;
        TreeNode res=root;

        if (root.val < p.val && root.val < q.val) {
            res = lowestCommonAncestor2(root.right, p, q);
        }else if (root.val > p.val && root.val > q.val){
            res = lowestCommonAncestor2(root.left, p, q);
        }

        return res;
    }
}
