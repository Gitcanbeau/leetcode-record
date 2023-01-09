import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class leet617 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/merge-two-binary-trees/
        TreeNode a1=new TreeNode(1,null,null);
        TreeNode b1=new TreeNode(1,null,null);
        TreeNode b2=new TreeNode(2,null,null);
        TreeNode b3=new TreeNode(3,null,null);
        TreeNode b4=new TreeNode(4,null,null);
        b1.left=b2;
        b2.left=b3;
        b3.right=b4;
//        System.out.println(mergeTrees(a1,b1));
        TreeNode c1=new TreeNode(1,null,null);
        TreeNode c2=new TreeNode(3,null,null);
        TreeNode c3=new TreeNode(2,null,null);
        TreeNode c4=new TreeNode(5,null,null);
        TreeNode d1=new TreeNode(2,null,null);
        TreeNode d2=new TreeNode(1,null,null);
        TreeNode d3=new TreeNode(3,null,null);
        TreeNode d4=new TreeNode(4,null,null);
        TreeNode d5=new TreeNode(7,null,null);
        c1.left=c2;
        c1.right=c3;
        c2.left=c4;
        d1.left=d2;
        d1.right=d3;
        d2.right=d4;
        d3.right=d5;
//        System.out.println(mergeTrees1(c1,d1));
        System.out.println(mergeTrees2(c1,d1));
    }
    public static TreeNode mergeTrees1(TreeNode root1, TreeNode root2) {
        //bfs-iteration
        //Runtime: 4 ms, faster than 5.03% of Java online submissions for Merge Two Binary Trees.
        //Memory Usage: 51.7 MB, less than 5.32% of Java online submissions for Merge Two Binary Trees.
        if(root1==null && root2==null) return null;
        if(root1==null && root2!=null) return root2;
        if(root1!=null && root2==null) return root1;

        Queue<TreeNode> bfsque=new LinkedList<>();
        bfsque.offer(root1);
        bfsque.offer(root2);
        root1.val=root1.val+root2.val;

        while(!bfsque.isEmpty()){
            int residual_layer_size= bfsque.size();
            while(residual_layer_size>0){
                TreeNode curr1=bfsque.poll();
                TreeNode curr2=bfsque.poll();
                if(curr1==null && curr2==null) {
                    residual_layer_size-=2;
                    continue;
                }
                if(curr1!=null && curr2==null){
                    residual_layer_size-=2;
                    continue;
                }
                if(curr1.left!=null && curr2.left!=null) curr1.left.val=curr1.left.val+curr2.left.val;
                if(curr1.right!=null && curr2.right!=null) curr1.right.val=curr1.right.val+curr2.right.val;
                if(curr1.left!=null && curr2.left==null) curr1.left.val=curr1.left.val;
                if(curr1.right!=null && curr2.right==null) curr1.right.val=curr1.right.val;
                if(curr1.left==null && curr2.left!=null){
                    TreeNode curr1newleft=new TreeNode(curr2.left.val,null,null);
                    curr1.left=curr1newleft;
                }
                if(curr1.right==null && curr2.right!=null){
                    TreeNode curr1newright=new TreeNode(curr2.right.val,null,null);
                    curr1.right=curr1newright;
                }

                if (curr1!=null) bfsque.offer(curr1.left);
                if (curr2!=null) bfsque.offer(curr2.left);
                if (curr1!=null) bfsque.offer(curr1.right);
                if (curr2!=null) bfsque.offer(curr2.right);

                residual_layer_size-=2;
            }
        }
        return root1;
    }

    public static TreeNode mergeTrees1_a(TreeNode root1, TreeNode root2) {
        if (root1 == null) return root2;
        if (root2 ==null) return root1;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root1);
        queue.offer(root2);
        while (!queue.isEmpty()) {
            TreeNode node1 = queue.poll();
            TreeNode node2 = queue.poll();
            // 此时两个节点一定不为空，val相加
            node1.val = node1.val + node2.val;
            // 如果两棵树左节点都不为空，加入队列
            if (node1.left != null && node2.left != null) {
                queue.offer(node1.left);
                queue.offer(node2.left);
            }
            // 如果两棵树右节点都不为空，加入队列
            if (node1.right != null && node2.right != null) {
                queue.offer(node1.right);
                queue.offer(node2.right);
            }
            // 若node1的左节点为空，直接赋值
            if (node1.left == null && node2.left != null) {
                node1.left = node2.left;
            }
            // 若node2的左节点为空，直接赋值
            if (node1.right == null && node2.right != null) {
                node1.right = node2.right;
            }
        }
        return root1;
    }

    public static TreeNode mergeTrees1_b(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root2);
        stack.push(root1);
        while (!stack.isEmpty()) {
            TreeNode node1 = stack.pop();
            TreeNode node2 = stack.pop();
            node1.val += node2.val;
            if (node2.right != null && node1.right != null) {
                stack.push(node2.right);
                stack.push(node1.right);
            } else {
                if (node1.right == null) {
                    node1.right = node2.right;
                }
            }
            if (node2.left != null && node1.left != null) {
                stack.push(node2.left);
                stack.push(node1.left);
            } else {
                if (node1.left == null) {
                    node1.left = node2.left;
                }
            }
        }
        return root1;
    }

    public static TreeNode mergeTrees2(TreeNode root1, TreeNode root2) {
        //只能从上往下遍历，preorder
        //Runtime: 1 ms, faster than 84.17% of Java online submissions for Merge Two Binary Trees.
        //Memory Usage: 50.6 MB, less than 67.13% of Java online submissions for Merge Two Binary Trees.
        if(root1==null && root2==null) return null;
        if(root1==null && root2!=null) return root2;
        if(root1!=null && root2==null) return root1;
        if(root1!=null && root2!=null) {
            root1.val=root1.val+root2.val;
//            return root1; 千万不能写return root1，会直接覆盖
        }

        root1.left=mergeTrees2(root1.left,root2.left);
        root1.right=mergeTrees2(root1.right,root2.right);

        return root1;
    }
}
