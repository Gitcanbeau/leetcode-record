import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class leet637 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/average-of-levels-in-binary-tree/
        TreeNode t1=new TreeNode(3,null,null);
        TreeNode t2=new TreeNode(9,null,null);
        TreeNode t3=new TreeNode(20,null,null);
        TreeNode t4=new TreeNode(15,null,null);
        TreeNode t5=new TreeNode(7,null,null);
        t1.left=t2;
        t1.right=t3;
        t3.left=t4;
        t3.right=t5;
        System.out.println(averageOfLevels(t1));
        //Runtime: 4 ms, faster than 56.23% of Java online submissions for Average of Levels in Binary Tree.
        //Memory Usage: 48.5 MB, less than 10.26% of Java online submissions for Average of Levels in Binary Tree.
    }
    public static List<Double> averageOfLevels(TreeNode root) {
        List<Double> res=new ArrayList<>();
        if(root==null) return res;

        Queue<TreeNode> queue1=new LinkedList<>();
        queue1.offer(root);
        while(!queue1.isEmpty()){
            double residual_layer_size=queue1.size();
            double size=queue1.size();
            double sum=0;
            while(residual_layer_size>0){
                TreeNode curr=queue1.poll();
                sum+=curr.val;
                if(curr.left!=null) queue1.offer(curr.left);
                if(curr.right!=null) queue1.offer(curr.right);
                residual_layer_size--;
            }
            double averageofthislayer=sum/size;
            res.add(averageofthislayer);
        }
        return res;
    }
}
