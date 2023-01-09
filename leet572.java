import java.util.ArrayList;
import java.util.List;

public class leet572 {
    public static void main(String[] args) {
        //Given the roots of two binary trees root and subRoot, return true if there is a subtree of root with the same structure and node values of subRoot and false otherwise.
        //A subtree of a binary tree tree is a tree that consists of a node in tree and all of this node's descendants. The tree tree could also be considered as a subtree of itself.
        //Example 1:
        //Input: root = [3,4,5,1,2], subRoot = [4,1,2]
        //Output: true
        //Example 2:
        //Input: root = [3,4,5,1,2,null,null,null,null,0], subRoot = [4,1,2]
        //Output: false
        //Constraints:
        //The number of nodes in the root tree is in the range [1, 2000].
        //The number of nodes in the subRoot tree is in the range [1, 1000].
        //-104 <= root.val <= 104
        //-104 <= subRoot.val <= 104
        TreeNode t1a=new TreeNode(3,null,null);
        TreeNode t1b=new TreeNode(4,null,null);
        TreeNode t1c=new TreeNode(5,null,null);
        TreeNode t1d=new TreeNode(1,null,null);
        TreeNode t1e=new TreeNode(2,null,null);
        t1a.left=t1b;
        t1a.right=t1c;
        t1b.left=t1d;
        t1b.right=t1e;
        TreeNode t2a=new TreeNode(4,null,null);
        TreeNode t2b=new TreeNode(1,null,null);
        TreeNode t2c=new TreeNode(2,null,null);
        t2a.left=t2b;
        t2a.right=t2c;
        System.out.println(isSubtree(t1a,t2a));
        //Runtime: 5 ms, faster than 46.14% of Java online submissions for Subtree of Another Tree.
        //Memory Usage: 46.7 MB, less than 37.80% of Java online submissions for Subtree of Another Tree.
    }
    public static boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if(root == null) return false;
        return isSameTree(root, subRoot) || isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);// 有一个真就真，就逐层直接返回真，最后也能返回真
    }


    public static boolean isSameTree(TreeNode a, TreeNode b){
        if(a==null && b==null) return true;
        if(a==null || b==null) return false;
        if(a.val!=b.val) return false;
        return isSameTree(a.left,b.left) && isSameTree(a.right,b.right);
    }
}
