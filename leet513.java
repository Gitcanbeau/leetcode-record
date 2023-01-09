import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class leet513 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/find-bottom-left-tree-value/
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
        System.out.println(findBottomLeftValue1(t1));
        System.out.println(findBottomLeftValue2(t1));
    }
    public static int findBottomLeftValue1(TreeNode root) {
        //bfs-层序迭代iteration
        //最后一list里面的的第一个数
        //Runtime: 2 ms, faster than 54.13% of Java online submissions for Find Bottom Left Tree Value.
        //Memory Usage: 41.7 MB, less than 97.33% of Java online submissions for Find Bottom Left Tree Value.
        List<List<Integer>> res=new ArrayList<>();
        Queue<TreeNode> queue1=new LinkedList<>();
        queue1.offer(root);
        while(!queue1.isEmpty()){
            int residual_layer_size=queue1.size();
            List<Integer> thislayer=new ArrayList<>();
            while(residual_layer_size>0){
                TreeNode curr=queue1.poll();
                thislayer.add(curr.val);
                if(curr.left!=null) queue1.offer(curr.left);
                if(curr.right!=null) queue1.offer(curr.right);
                residual_layer_size--;
            }
            res.add(thislayer);
        }


        List<Integer> lastlayer=res.get(res.size()-1);
        int mostbottomleftleaf=lastlayer.get(0);
        return mostbottomleftleaf;
    }
    private static int maxDeep = -1;
    private static int res = 0;//全局变量不用传到函数里面，就是直接
    public static int findBottomLeftValue2(TreeNode root) {
        //dfs-preorder top-down recursion
        //Runtime: 1 ms, faster than 84.27% of Java online submissions for Find Bottom Left Tree Value.
        //Memory Usage: 43.8 MB, less than 65.39% of Java online submissions for Find Bottom Left Tree Value.
        res = root.val;
        findBottomLeftValue2_helper(root,0);
        return res;
    }

    private static void findBottomLeftValue2_helper (TreeNode root,int deep) {
        if (root == null) return; //可有可无
        if (root.left == null && root.right == null) {
            if (deep > maxDeep) { //同一层的叶子节点肯定不能比当前Deep大，所以也进不来，无法修改value的值，
                res = root.val;
                maxDeep = deep;
            }
            return;
        }
        if (root.left != null) {
            findBottomLeftValue2_helper(root.left,deep+1); //这么写简洁一些
        }
        if (root.right != null) {
            deep=deep+1;
            findBottomLeftValue2_helper(root.right,deep); //这么写比较有回溯的味道
            deep=deep-1;
        }
        return;
    }

}
