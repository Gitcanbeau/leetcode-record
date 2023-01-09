import java.util.ArrayList;

public class leet230 {
    public static void main(String[] args) {
        //Given the root of a binary search tree, and an integer k, return the kth smallest value (1-indexed) of all the values of the nodes in the tree.
        //Example 1:
        //Input: root = [3,1,4,null,2], k = 1
        //Output: 1
        //Example 2:
        //Input: root = [5,3,6,2,4,null,null,1], k = 3
        //Output: 3
        //Constraints:
        //The number of nodes in the tree is n.
        //1 <= k <= n <= 104
        //0 <= Node.val <= 104
        TreeNode node1=new TreeNode(5,null,null);
        TreeNode node2=new TreeNode(3,null,null);
        TreeNode node3=new TreeNode(2,null,null);
        TreeNode node4=new TreeNode(1,null,null);
        TreeNode node5=new TreeNode(4,null,null);
        TreeNode node6=new TreeNode(6,null,null);
        node1.left=node2;
        node2.left=node3;
        node3.left=node4;
        node2.right=node5;
        node1.right=node6;
        System.out.println(kthSmallest(node1,3));
        //Runtime: 2 ms, faster than 36.44% of Java online submissions for Kth Smallest Element in a BST.
        //Memory Usage: 45.5 MB, less than 49.24% of Java online submissions for Kth Smallest Element in a BST.
    }
    public static int kthSmallest(TreeNode root, int k) {
        ArrayList<Integer> arrlst1=new ArrayList<>();
        inorder(root,arrlst1);
        return arrlst1.get(k-1);
    }
    public static void inorder(TreeNode x,ArrayList arrlst1){
        if(x.left!=null){
            inorder(x.left,arrlst1);
        }
        arrlst1.add(x.val);
        if(x.right!=null){
            inorder(x.right,arrlst1);
        }
    }
}
