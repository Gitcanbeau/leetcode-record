import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class leet429 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/n-ary-tree-level-order-traversal/
        NTreeNode nodefirst1=new NTreeNode(1,new ArrayList<>());
        NTreeNode nodesecond1=new NTreeNode(3,new ArrayList<>());
        NTreeNode nodesecond2=new NTreeNode(2,new ArrayList<>());
        NTreeNode nodesecond3=new NTreeNode(4,new ArrayList<>());
        NTreeNode nodethird1=new NTreeNode(5,new ArrayList<>());
        NTreeNode nodethird2=new NTreeNode(6,new ArrayList<>());
        nodefirst1.children.add(nodesecond1);
        nodefirst1.children.add(nodesecond2);
        nodefirst1.children.add(nodesecond3);
        nodesecond1.children.add(nodethird1);
        nodesecond1.children.add(nodethird2);
        System.out.println(levelOrder(nodefirst1));
        //Runtime: 2 ms, faster than 96.66% of Java online submissions for N-ary Tree Level Order Traversal.
        //Memory Usage: 43 MB, less than 97.39% of Java online submissions for N-ary Tree Level Order Traversal.
    }
    public static List<List<Integer>> levelOrder(NTreeNode root) {
        List<List<Integer>> res=new ArrayList<>();
        //之前值左右节点不空就往队列里面加东西，现在是children这个数组不空就继续往队列里面加东西，可以遍历children数组;
        if(root==null) return res;

        Queue<NTreeNode> queue1=new LinkedList<>();
        queue1.offer(root);
        while(!queue1.isEmpty()){
            int residual_layer_size=queue1.size();
            List<Integer> thislayer=new ArrayList<>();
            while(residual_layer_size>0) {
                NTreeNode curr = queue1.poll();
                thislayer.add(curr.val);
                for (int i = 0; i < curr.children.size(); i++) {
                    queue1.offer(curr.children.get(i));
                }
                residual_layer_size--;
            }
            res.add(thislayer);
        }
        return res;
    }
}
