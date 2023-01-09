import java.util.ArrayList;
import java.util.Stack;

public class leet530 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/minimum-absolute-difference-in-bst/
        TreeNode node1=new TreeNode(4,null,null);
        TreeNode node2=new TreeNode(2,null,null);
        TreeNode node3=new TreeNode(7,null,null);
        TreeNode node4=new TreeNode(1,null,null);
        TreeNode node5=new TreeNode(3,null,null);
        node1.left=node2;
        node1.right=node3;
        node2.left=node4;
        node2.right=node5;
        System.out.println(getMinimumDifference1(node1));
    }
    public static int getMinimumDifference1(TreeNode root) {
        int min=Integer.MAX_VALUE;
        ArrayList<Integer> arrlst1=new ArrayList<>();
        inorder2(root,arrlst1);
        for(int i=0; i<arrlst1.size()-1;i++){
            min=Math.min(min,arrlst1.get(i+1)-arrlst1.get(i));
        }
        return min;
    }
    public static void inorder1(TreeNode root,ArrayList<Integer> arrlst1){
        //dfs-iteration
        //Runtime: 6 ms, faster than 13.53% of Java online submissions for Minimum Absolute Difference in BST.
        //Memory Usage: 46.1 MB, less than 12.24% of Java online submissions for Minimum Absolute Difference in BST.
        Stack<TreeNode> stack1=new Stack<>();
        TreeNode curr=root;
        while(curr!=null || !stack1.isEmpty()){
            if(curr!=null){
                stack1.push(curr);
                curr=curr.left;
            }else {
                curr = stack1.pop();
                arrlst1.add(curr.val);
                curr=curr.right;
            }
        }
    }

    public static void inorder2(TreeNode root,ArrayList<Integer> arrlst1){
        //dfs-recursion-inorder好像不想完全的topdown
        //Runtime: 3 ms, faster than 34.09% of Java online submissions for Minimum Absolute Difference in BST.
        //Memory Usage: 45.8 MB, less than 27.28% of Java online submissions for Minimum Absolute Difference in BST.
        if(root==null) return;
        if(root.left!=null){
            inorder2(root.left,arrlst1);
        }
        arrlst1.add(root.val);
        if(root.right!=null){
            inorder2(root.right,arrlst1);
        }
        return;
    }




    //////上面是普通方法
    //下面是前后指针
    public static TreeNode pre1;// 记录上一个遍历的结点
    public static int result = Integer.MAX_VALUE;
    public static int getMinimumDifference2(TreeNode root) {
        if(root==null)return 0;
        traversal(root);
        return result;
    }
    public static void traversal(TreeNode root){
        if(root==null)return;
        //左
        traversal(root.left);
        //中
        if(pre1!=null){
            result = Math.min(result,root.val-pre1.val);
        }
        pre1 = root;
        //右
        traversal(root.right);
    }

    ////////
    public static TreeNode pre2;
    public static Stack<TreeNode> stack;
    public static int getMinimumDifference3(TreeNode root) {
        if (root == null) return 0;
        stack = new Stack<>();
        TreeNode cur = root;
        int result = Integer.MAX_VALUE;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur); // 将访问的节点放进栈
                cur = cur.left; // 左
            }else {
                cur = stack.pop();
                if (pre2 != null) { // 中
                    result = Math.min(result, cur.val - pre2.val);
                }
                pre2 = cur;
                cur = cur.right; // 右
            }
        }
        return result;
    }
}
