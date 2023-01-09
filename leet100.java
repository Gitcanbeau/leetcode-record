import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class leet100 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/same-tree/
        TreeNode t1a=new TreeNode(1,null,null);
        TreeNode t1b=new TreeNode(2,null,null);
        TreeNode t1c=new TreeNode(1,null,null);
//        TreeNode t1d=new TreeNode(1,null,null);
        TreeNode t2a=new TreeNode(1,null,null);
        TreeNode t2b=new TreeNode(2,null,null);
        TreeNode t2c=new TreeNode(1,null,null);
        t1a.left=t1b;
        t1a.right=t1c;
//        t1c.left=t1d;
        t2a.left=t2b;
        t2b.right=t2c;
        System.out.println(isSameTree1(t1a,t2a));
        System.out.println(isSameTree2(t1a,t2a));
        System.out.println(isSameTree3(t1a,t2a));
        System.out.println(isSameTree4(t1a,t2a));
    }
    public static boolean isSameTree1(TreeNode p, TreeNode q) {
        //dfs-recursion
        //返回值就定位boolean就行
        //最上头写终止条件，一旦一个空一个不空或者都不空但是值不相等，就立刻返回false,并且层层返回false才能最后返回false
        //最下头也要写结束递归的条件，能走到最后一步，就是都是null了，就返回true，并且层层返回true
//        if((p==null && q!=null) || (p!=null && q==null) || (p.val!=q.val)) return false;
//        if((p==null && q==null) || (p.val==q.val)) return true;
        //这三个终止条件也是有顺序的，好容易写错
        //不能把两个false合并写,短路或是第一项为真就不再往后面走了，万一都是null，就能走到判断(p.val!=q.val)这步，就会因为null没有val而报错
        //true的不能合并也同理，而且把false写明白就行，中间过程有一个false最后就会返回false
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        if (p.val != q.val) return false;

        boolean bleft=isSameTree1(p.left,q.left); //不用判断left是否为空，递归进去的出口条件就判断了为空怎么样不为空又怎么样
        boolean bright=isSameTree1(p.right, q.right);

        return bleft&&bright; //都真才为真，中间递归过程中但凡有一个false就返回false了
    }

    private static boolean isSAME=true;
    public static boolean isSameTree2(TreeNode p, TreeNode q) {
        if(p==null && q==null) return true;
        testSame(p,q);
        return isSAME;
    }

    private static void testSame(TreeNode p, TreeNode q){
        //Runtime: 0 ms, faster than 100.00% of Java online submissions for Same Tree.
        //Memory Usage: 40.9 MB, less than 80.25% of Java online submissions for Same Tree.
        if (p == null && q == null) return;
        if ((p == null && q!=null) || (p!=null && q == null)) {
            isSAME=false;
            return;
        }
        if (p.val != q.val) {
            isSAME=false;
            return;
        }

        testSame(p.left,q.left);
        testSame(p.right,q.right);
        return;
    }


    public static boolean isSameTree3(TreeNode p, TreeNode q) {
        //dfs-iteration
        //整两个stack,一模一样的操作就是往外弹的东西只要有一个不一样就立马return false离开循环，如果能顺利出循环就return true
        if(p==null && q==null) return true;

        Stack<TreeNode> stack1=new Stack<>();
        stack1.push(p);
        stack1.push(q);
        while(!stack1.isEmpty()){
            TreeNode curr1=stack1.pop();
            TreeNode curr2=stack1.pop();
            if((curr1==null && curr2!=null) || (curr1!=null && curr2==null) || (curr1.val!=curr2.val)){
                return false;
            }
            if(curr1.left!=null) stack1.push(curr1.left);
            if(curr2.left!=null) stack1.push(curr2.left);
            if(curr1.right!=null) stack1.push(curr1.right);
            if(curr2.right!=null) stack1.push(curr1.right);
        }
        return true;
    }

    public static boolean isSameTree4(TreeNode p, TreeNode q) {
        //bfs-iteration
//Runtime: 1 ms, faster than 8.23% of Java online submissions for Same Tree.
//Memory Usage: 42 MB, less than 14.93% of Java online submissions for Same Tree.

        Queue<TreeNode> deque = new LinkedList<>();
        deque.offer(p);
        deque.offer(q);
        while (!deque.isEmpty()) {
            TreeNode leftNode = deque.poll();
            TreeNode rightNode = deque.poll();
            if (leftNode == null && rightNode == null) {
                continue;
            }
//            if (leftNode == null && rightNode != null) {
//                return false;
//            }
//            if (leftNode != null && rightNode == null) {
//                return false;
//            }
//            if (leftNode.val != rightNode.val) {
//                return false;
//            }
            // 以上三个判断条件合并
            if (leftNode == null || rightNode == null || leftNode.val != rightNode.val) {
                return false;
            }
            // 这里顺序与使用Deque不同
            deque.offer(leftNode.left);
            deque.offer(rightNode.left);
            deque.offer(leftNode.right);
            deque.offer(rightNode.right);
        }
        return true;
    }
}
