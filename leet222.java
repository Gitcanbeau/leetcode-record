import java.util.LinkedList;
import java.util.Queue;

public class leet222 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/count-complete-tree-nodes/
        //Design an algorithm that runs in less than O(n) time complexity.
        TreeNode t1=new TreeNode(1,null,null);
        TreeNode t2=new TreeNode(2,null,null);
        TreeNode t3=new TreeNode(3,null,null);
        TreeNode t4=new TreeNode(4,null,null);
        TreeNode t5=new TreeNode(5,null,null);
        TreeNode t6=new TreeNode(6,null,null);
        t1.left=t2;
        t1.right=t3;
        t2.left=t4;
        t2.right=t5;
        t3.left=t6;

        System.out.println(oldcountNodes1(t1));
        System.out.println(oldcountNodes1a(t1));
        System.out.println(oldcountNodes2(t1));
        System.out.println(countNodes(t1));
    }
    public static int oldcountNodes1(TreeNode root) {

        //O(n) bfs-iteration //竟然没超时。。。
        //Runtime: 0 ms, faster than 100.00% of Java online submissions for Count Complete Tree Nodes.
        //Memory Usage: 49.8 MB, less than 60.28% of Java online submissions for Count Complete Tree Nodes.
        if(root==null) return 0;

        if(root.left==null && root.right==null) return 1;
        int countleft=0;
        int countright=0;
        if(root.left!=null){
            countleft=oldcountNodes2(root.left);
        }
        if(root.right!=null){
            countright=oldcountNodes2(root.right);
        }
        return countleft+countright+1;
    }

    public static int oldcountNodes1a(TreeNode root) {
        //Design an algorithm that runs in less than O(n) time complexity.
        //时间复杂度：O(n)
        //空间复杂度：O(log n)，算上了递归系统栈占用的空间
        if(root==null) return 0;
        return countNodes(root.left) + countNodes(root.right) + 1;
    }
    public static int oldcountNodes2(TreeNode root) {
        //O(n) bfs-iteration
        //Runtime: 9 ms, faster than 5.06% of Java online submissions for Count Complete Tree Nodes.
        //Memory Usage: 51.5 MB, less than 5.32% of Java online submissions for Count Complete Tree Nodes.
        if(root==null) return 0;

        Queue<TreeNode> queue1=new LinkedList<>();
        queue1.offer(root);
        int count=0;
        while(!queue1.isEmpty()) {
            int residual_layer_size = queue1.size();
            while (residual_layer_size > 0) {
                TreeNode curr = queue1.poll();
                count++;
                if (curr.left != null) queue1.offer(curr.left);
                if (curr.right != null) queue1.offer(curr.right);
                residual_layer_size--;
            }
        }
        return count;
    }

    public static int countNodes(TreeNode root) {
        //Design an algorithm that runs in less than O(n) time complexity.
        //时间复杂度：O(log n × log n)
        //Runtime: 0 ms, faster than 100.00% of Java online submissions for Count Complete Tree Nodes.
        //Memory Usage: 49.8 MB, less than 60.28% of Java online submissions for Count Complete Tree Nodes.

        //完全二叉树只有两种情况，情况一：就是满二叉树，情况二：最后一层叶子节点没有满。
        //对于情况一，可以直接用 2^树深度 - 1 来计算，注意这里根节点深度为1。
        //对于情况二，分别递归左孩子，和右孩子，递归到某一深度一定会有左孩子或者右孩子为满二叉树，然后依然可以按照情况1来计算。
        if(root == null) return 0;
        int leftDepth = getDepth(root.left);
        int rightDepth = getDepth(root.right);
        if (leftDepth == rightDepth) {// 左子树是满二叉树
            // 2^leftDepth其实是 （2^leftDepth - 1） + 1 ，左子树 + 根结点
//            return (1 << leftDepth) + countNodes(root.right);
            return (int) (Math.pow(2,leftDepth)+countNodes(root.right));
        } else {// 右子树是满二叉树
//            return (1 << rightDepth) + countNodes(root.left);
            return (int) (Math.pow(2,rightDepth)+countNodes(root.left));
        }
        //<< : 左移运算符，num << 1,相当于num乘以2
        //>> : 右移运算符，num >> 1,相当于num除以2
        //>>> :无符号右移，忽略符号位，空位都以0补齐
    }

    private static int getDepth(TreeNode root) {
        int depth = 0;
        while (root != null) {
            root = root.left;
            depth++;
        }
        return depth;
    }

}
