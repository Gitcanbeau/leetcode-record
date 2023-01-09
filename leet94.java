import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class leet94 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/binary-tree-inorder-traversal/
        TreeNode node1=new TreeNode(10,null,null);
        TreeNode node2=new TreeNode(8,null,null);
        TreeNode node3=new TreeNode(14,null,null);
        TreeNode node4=new TreeNode(6,null,null);
        TreeNode node5=new TreeNode(9,null,null);
        TreeNode node6=new TreeNode(12,null,null);
        TreeNode node7=new TreeNode(16,null,null);
        TreeNode node8=new TreeNode(7,null,null);
        node1.left=node2;
        node1.right=node3;
        node2.left=node4;
        node2.right=node5;
        node3.left=node6;
        node3.right=node7;
        node4.right=node8;
        System.out.println(inorderTraversal1(node1));
        System.out.println(inorderTraversal2(node1));
        //Runtime: 1 ms, faster than 47.43% of Java online submissions for Binary Tree Inorder Traversal.
        //Memory Usage: 42.5 MB, less than 29.48% of Java online submissions for Binary Tree Inorder Traversal.
    }
    public static List<Integer> inorderTraversal1(TreeNode root) {
        List<Integer> list1=new ArrayList<>();
        inorder(root,list1);
        return list1;
    }
    public static void inorder (TreeNode root,List<Integer> list1){
        if(root==null) return;
        if(root.left!=null){
            inorder(root.left,list1);
        }
        list1.add(root.val);
        if(root.right!=null){
            inorder(root.right,list1);
        }
        return; //写不写都行，别一省略就不知道了
    }

    public static List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> res=new ArrayList<>();
        if(root==null) return res;

        Stack<TreeNode> stack2=new Stack<>();
        TreeNode curr=root;
        while(curr!=null || !stack2.isEmpty()){
            if(curr!=null){
                stack2.push(curr);
                curr=curr.left;
            }else{
                //curr从根节点进来，根节点入栈，左下一直不空就一直往左下走，一直入栈。
                // 左下走到头以后才开始弹栈，弹的就是左中，然后右下入栈。
                curr=stack2.pop();
                res.add(curr.val);
                curr=curr.right;
            }
        }
        return res;
    }
}
