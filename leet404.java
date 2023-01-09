import java.util.*;

public class leet404 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/sum-of-left-leaves/
        TreeNode t1=new TreeNode(1,null,null);
        TreeNode t2=new TreeNode(2,null,null);
        TreeNode t3=new TreeNode(3,null,null);
        TreeNode t4=new TreeNode(4,null,null);
        TreeNode t5=new TreeNode(5,null,null);
        TreeNode t6=new TreeNode(6,null,null);
        TreeNode t7=new TreeNode(7,null,null);
        t1.left=t2;
        t1.right=t3;
        t2.left=t4;
        t3.left=t5;
        t3.right=t6;
        t5.left=t7;
        System.out.println(sumOfLeftLeaves1(t1));
        System.out.println(sumOfLeftLeaves2(t1));
        System.out.println(sumOfLeftLeaves3(t1));
        System.out.println(sumOfLeftLeaves4(t1));
    }
    public static int sumOfLeftLeaves1(TreeNode root) {
//        bfs-iteration
        //某节点的左节点不为空以后，除了把该节点的左节点加入到queue里面，还要多一个判断就是该节点的左节点是不是叶子节点，如果是的话，就更新sum
        //Runtime: 2 ms, faster than 6.37% of Java online submissions for Sum of Left Leaves.
        //Memory Usage: 42.5 MB, less than 13.16% of Java online submissions for Sum of Left Leaves.
        int sum = 0;
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size -- > 0) {
                TreeNode curr = queue.poll();
                if (curr.left != null) { // 左节点不为空
                    queue.offer(curr.left);
                    if (curr.left.left == null && curr.left.right == null){ // 左叶子节点
                        sum += curr.left.val;
                    }
                }
                if (curr.right != null) queue.offer(curr.right);
            }
        }
        return sum;
    }

    public static int sumOfLeftLeaves2(TreeNode root) {
        //dfs-iteration
        //Runtime: 3 ms, faster than 7.93% of Java online submissions for Sum of Left Leaves.
        //Memory Usage: 41.4 MB, less than 79.89% of Java online submissions for Sum of Left Leaves.
        if (root == null) return 0;
        Stack<TreeNode> stack = new Stack<> ();
        stack.add(root);
        int result = 0;
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            if (curr.right != null) stack.add(curr.right);
            if (curr.left != null){
                stack.add(curr.left);
                if(curr.left.left == null && curr.left.right == null) {
                    result += curr.left.val;
                }
            }
        }
        return result;
    }

    private static int sum=0;
    public static int sumOfLeftLeaves3(TreeNode root) {
        //dfs-preorder top-down recursion
        //Runtime: 0 ms, faster than 100.00% of Java online submissions for Sum of Left Leaves.
        //Memory Usage: 42 MB, less than 36.09% of Java online submissions for Sum of Left Leaves.
        traversal(root);
        return sum;
    }
    public static void traversal(TreeNode root){
        if(root==null) return;//可有可无，是安全检查，不是出口
        if(root.left==null && root.right==null) return; //这个才是出口。写不写这句都能运行出来，但是我说过回溯不点明出口return就很慢很慢
        if(root.left!=null){
            if(root.left.left==null && root.left.right==null) {
                sum += root.left.val;
            }
            traversal(root.left);
        }

        if(root.right!=null){
            traversal(root.right);
        }

        return;
    }

    public static int sumOfLeftLeaves4(TreeNode root) {
        //dfs-recursion-bottomup
        //Runtime: 0 ms, faster than 100.00% of Java online submissions for Sum of Left Leaves.
        //Memory Usage: 41.8 MB, less than 55.68% of Java online submissions for Sum of Left Leaves.
        if (root == null) return 0;
        if(root.left==null && root.right==null) return 0;
        int leftValue = sumOfLeftLeaves3(root.left);    // 左
        int rightValue = sumOfLeftLeaves3(root.right);  // 右

        if (root.left != null && root.left.left == null && root.left.right == null) {
            leftValue = root.left.val;
        }
        int sum =leftValue + rightValue;  // 中
        return sum;
    }
}
