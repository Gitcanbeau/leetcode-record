import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class leet515 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/find-largest-value-in-each-tree-row/
        TreeNode t1=new TreeNode(1,null,null);
        TreeNode t2=new TreeNode(3,null,null);
        TreeNode t3=new TreeNode(2,null,null);
        TreeNode t4=new TreeNode(5,null,null);
        TreeNode t5=new TreeNode(3,null,null);
        TreeNode t6=new TreeNode(9,null,null);
        t1.left=t2;
        t1.right=t3;
        t2.left=t4;
        t2.right=t5;
        t3.right=t6;
        System.out.println(largestValues(t1));
        //Runtime: 3 ms, faster than 58.93% of Java online submissions for Find Largest Value in Each Tree Row.
        //Memory Usage: 44.5 MB, less than 82.82% of Java online submissions for Find Largest Value in Each Tree Row.
    }
    public static List<Integer> largestValues(TreeNode root) {
        //还是层序遍历，每层存一个list，然后动态求最大，把最大的放到res里面
        List<Integer> res=new ArrayList<>();
        if(root==null) return res;

        Queue<TreeNode> queue1=new LinkedList<>();
        queue1.offer(root);
        while(!queue1.isEmpty()){
            int residual_layer_size=queue1.size();
            int maxatthislayer=Integer.MIN_VALUE;
            while(residual_layer_size>0){
                TreeNode curr=queue1.poll();
                maxatthislayer=Math.max(maxatthislayer,curr.val);
                if(curr.left!=null) queue1.offer(curr.left);
                if(curr.right!=null) queue1.offer(curr.right);
                residual_layer_size--;
            }
            res.add(maxatthislayer);
        }
        return res;
    }
}
