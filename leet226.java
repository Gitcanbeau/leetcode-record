import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class leet226 {
    public static void main(String[] args) {
        //Given the root of a binary tree, invert the tree, and return its root.
        //Example 1:
        //Input: root = [4,2,7,1,3,6,9]
        //Output: [4,7,2,9,6,3,1]
        //Example 2:
        //Input: root = [2,1,3]
        //Output: [2,3,1]
        //Example 3:
        //Input: root = []
        //Output: []
        //Constraints:
        //The number of nodes in the tree is in the range [0, 100].
        //-100 <= Node.val <= 100
        TreeNode t1=new TreeNode(4,null,null);
        TreeNode t2a=new TreeNode(2,null,null);
        TreeNode t2b=new TreeNode(7,null,null);
        TreeNode t3a=new TreeNode(1,null,null);
        TreeNode t3b=new TreeNode(3,null,null);
        TreeNode t3c=new TreeNode(6,null,null);
        TreeNode t3d=new TreeNode(9,null,null);
        t1.left=t2a;
        t1.right=t2b;
        t2a.left=t3a;
        t2a.right=t3b;
        t2b.left=t3c;
        t2b.right=t3d;
        System.out.println(invertTree1(t1));
        System.out.println(invertTree2(t1));
        System.out.println(invertTree3(t1));
        System.out.println(invertTree4(t1));
        //Runtime: 0 ms, faster than 100.00% of Java online submissions for Invert Binary Tree.
        //Memory Usage: 39.5 MB, less than 99.49% of Java online submissions for Invert Binary Tree.
    }
    public static TreeNode invertTree1(TreeNode x) {
//        dfs-recursion
        if(x==null) return null;

        if(x.left!=null){
            x.left=invertTree1(x.left);
        }
        if(x.right!=null){
            x.right=invertTree1(x.right);
        }
        TreeNode templeft=x.left;
        TreeNode tempright=x.right;
        x.left=tempright;
        x.right=templeft;
        return x;
    }

    public static TreeNode invertTree2(TreeNode x) {
//        dfs-recursion
        if(x==null) return null;

        TreeNode templeft=x.left;
        TreeNode tempright=x.right;
        x.left=tempright;
        x.right=templeft;

        if(x.left!=null){
            x.left=invertTree2(x.left);
        }
        if(x.right!=null){
            x.right=invertTree2(x.right);
        }

        return x;
    }

    public static TreeNode invertTree3(TreeNode x) {
        //dfs-iteration
        //Runtime: 1 ms, faster than 10.48% of Java online submissions for Invert Binary Tree.
        //Memory Usage: 41.9 MB, less than 28.38% of Java online submissions for Invert Binary Tree.
        if(x==null) return x;
        Stack<TreeNode> stack1=new Stack<>();
        stack1.push(x);
        while(!stack1.isEmpty()){
            TreeNode curr=stack1.pop();
            TreeNode templeft;
            TreeNode tempright;
            templeft=curr.left;
            tempright=curr.right;
            curr.left=tempright;
            curr.right=templeft;
            if(curr.left!=null ) stack1.push(curr.left);
            if(curr.right!=null) stack1.push(curr.right);
        }
        return x;
    }

    public static TreeNode invertTree4(TreeNode x) {
        //bfs-iteration
        //Runtime: 1 ms, faster than 10.48% of Java online submissions for Invert Binary Tree.
        //Memory Usage: 41.8 MB, less than 36.38% of Java online submissions for Invert Binary Tree.
        if(x==null) return x;
        Queue<TreeNode> queue1=new LinkedList<>();
        queue1.offer(x);
        while(!queue1.isEmpty()){
            int residual_layer_size=queue1.size();
            while(residual_layer_size>0) {
                TreeNode curr = queue1.poll();
                TreeNode templeft;
                TreeNode tempright;
                templeft = curr.left;
                tempright = curr.right;
                curr.left = tempright;
                curr.right = templeft;
                if(curr.left!=null) queue1.offer(curr.left);
                if(curr.right!=null) queue1.offer(curr.right);
                residual_layer_size--;
            }

        }
        return x;

    }

}
