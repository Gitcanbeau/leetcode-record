import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class leet700 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/search-in-a-binary-search-tree/
        TreeNode node1=new TreeNode(4,null,null);
        TreeNode node2=new TreeNode(2,null,null);
        TreeNode node3=new TreeNode(7,null,null);
        TreeNode node4=new TreeNode(1,null,null);
        TreeNode node5=new TreeNode(3,null,null);
        node1.left=node2;
        node1.right=node3;
        node2.left=node4;
        node2.right=node5;
        System.out.println(searchBST1(node1,3));
        System.out.println(searchBST1(node1,5));
        System.out.println(searchBST2(node1,3));
        System.out.println(searchBST2(node1,5));
        System.out.println(searchBST3(node1,3));
        System.out.println(searchBST3(node1,5));
        System.out.println(searchBST4(node1,3));
        System.out.println(searchBST4(node1,5));
    }
    public static TreeNode searchBST1(TreeNode root, int val) {
        //bfs-iteration
        //Runtime: 1 ms, faster than 9.94% of Java online submissions for Search in a Binary Search Tree.
        //Memory Usage: 51.4 MB, less than 52.15% of Java online submissions for Search in a Binary Search Tree.
        if(root==null) return null;

        Stack<TreeNode> stack1=new Stack<>();
        stack1.push(root);

        while(!stack1.isEmpty()){
            TreeNode curr=stack1.pop();
            if(curr.val==val) {
                return curr;
            }else if(curr.val>val && curr.left!=null){
                stack1.push(curr.left);
            }else if(curr.val<val && curr.right!=null){
                stack1.push(curr.right);
            }else{
                return null;
            }
        }
        return null;
    }
    private static TreeNode desiredroot=new TreeNode(); //这里一定要new，不然还存这上一次运算的结果
    public static TreeNode searchBST2(TreeNode root, int val) {
        //bfs-recursion-topdown
        //Runtime: 0 ms, faster than 100.00% of Java online submissions for Search in a Binary Search Tree.
        //Memory Usage: 51 MB, less than 80.52% of Java online submissions for Search in a Binary Search Tree.
        if(root==null) return null;
        desiredroot=null;//这个是重点，new完之后的是节点是（0,null,null)，你没查到结果想返回的是null
        finddesiredroot(root,val);
        return desiredroot;
    }
    public static void finddesiredroot(TreeNode root, int val){
        if(root==null) return;
        if(root.val==val) {
            desiredroot=root;
            return;
        }
        if(root.val>val ) finddesiredroot(root.left, val);
        if(root.val<val ) finddesiredroot(root.right, val);
        return;
    }



    public static TreeNode searchBST3(TreeNode root, int val) {

        if(root==null) return null;
        if(root.val==val) return root;

        if(root.val>val) {
            root=searchBST3(root.left,val);
        }else if(root.val<val){
            root=searchBST3(root.right,val);
        }

        return root;

    }

    public static TreeNode searchBST3_a(TreeNode root, int val) {

        if(root==null) return null;
        if(root.val==val) return root;

        if(root.val>val) {
            return searchBST3(root.left,val);
        }
        if(root.val<val){
            return searchBST3(root.right,val);
        }

        return root;

    }


    public static TreeNode searchBST4(TreeNode root, int val) {
        //recursion-topdown
        while (root != null)
            if (val < root.val) root = root.left;
            else if (val > root.val) root = root.right;
            else return root;
        return null;
    }

}
