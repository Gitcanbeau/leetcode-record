import com.sun.source.tree.Tree;

import java.util.ArrayList;
import java.util.List;

public class leet538 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/convert-bst-to-greater-tree/
        TreeNode t0=new TreeNode(0,null,null);
        TreeNode t1=new TreeNode(1,null,null);
        TreeNode t2=new TreeNode(2,null,null);
        TreeNode t3=new TreeNode(3,null,null);
        TreeNode t4=new TreeNode(4,null,null);
        TreeNode t5=new TreeNode(5,null,null);
        TreeNode t6=new TreeNode(6,null,null);
        TreeNode t7=new TreeNode(7,null,null);
        TreeNode t8=new TreeNode(8,null,null);
        t4.left=t1;
        t1.left=t0;
        t1.right=t2;
        t2.right=t3;
        t4.right=t6;
        t6.left=t5;
        t6.right=t7;
        t7.right=t8;
//        System.out.println(reversepostorder(t4));
        System.out.println(convertBST(t4));
    }

    private static TreeNode rightend=null;
    private static int oldrightval=0;
    public static TreeNode convertBST(TreeNode root){
        //Runtime: 1 ms, faster than 86.91% of Java online submissions for Convert BST to Greater Tree.
        //Memory Usage: 42.4 MB, less than 90.13% of Java online submissions for Convert BST to Greater Tree.

        //自己写出来的，聪明宝
        if(root==null) return null;
        convertBST_hepler(root);
        return root;
    }
    public static void convertBST_hepler(TreeNode root) {
        //反转的中序遍历，用后前指针
        if(root==null) return;

        if(root.right!=null){
            convertBST_hepler(root.right);
        }


        if(rightend!=null){
            root.val+=oldrightval;
        }
        oldrightval=root.val;
        rightend=root;

        if(root.left!=null){
            convertBST_hepler(root.left);
        }
        return;
    }



    public static List<Integer> reversepostorder(TreeNode root){
        List<Integer> res=new ArrayList<>();
        if(root==null) return res;
        rightpostorder(root,res);
        return res;
    }

    private static void rightpostorder(TreeNode root,List<Integer> res){
        if(root==null) return;

        if(root.right!=null){
            rightpostorder(root.right,res);
        }
        res.add(root.val);
        if(root.left!=null){
            rightpostorder(root.left,res);
        }
        return;
    }

}
