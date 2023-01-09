import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class leet559 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/maximum-depth-of-n-ary-tree/
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
        System.out.println(maxDepth(nodefirst1));
        System.out.println(maxDepth2(nodefirst1));
        System.out.println(maxDepth3(nodefirst1));
    }
    public static int maxDepth(NTreeNode root) {
        //dfs-recursion
        //Runtime: 0 ms, faster than 100.00% of Java online submissions for Maximum Depth of N-ary Tree.
        //Memory Usage: 42.4 MB, less than 93.49% of Java online submissions for Maximum Depth of N-ary Tree.
        if (root == null) return 0;

        if(root.children.size()==0) return 1; //写不写这句都行，因为root==null的时候就会返回0，返回parent的时候就会得到1
//        if(root.children.size()==0) return 0;//不应该return 0， 应该return 1
        int max=0;
        for(NTreeNode curr: root.children){
            int depth=maxDepth(curr);
            max=Math.max(max,depth);
        }
        return max+1;
    }
    public static int maxDepth2(NTreeNode root) {
        //dfs-iteration 我用两个stack，不想用visited set费劲想
        //Runtime: 8 ms, faster than 6.10% of Java online submissions for Maximum Depth of N-ary Tree.
        //Memory Usage: 44.3 MB, less than 31.26% of Java online submissions for Maximum Depth of N-ary Tree.
        if (root == null) return 0;

        int max=0;
        Stack<NTreeNode> stack1=new Stack<>();
        Stack<Integer> depth=new Stack<>();
        stack1.push(root);
        depth.push(1);
        while(!stack1.isEmpty()) {
            NTreeNode curr = stack1.pop();
            int tempdepth=depth.pop();
            if (curr.children.size() == 0) {
                max=Math.max(tempdepth,max);
            } else {
                for (int i = 0; i < curr.children.size(); i++) {
                    stack1.push(curr.children.get(i));
                    depth.push(tempdepth+1);
                }
            }
        }
        return max;
    }
    public static int maxDepth3(NTreeNode root) {
        //bfs-iteration
        //Runtime: 5 ms, faster than 9.30% of Java online submissions for Maximum Depth of N-ary Tree.
        //Memory Usage: 44.6 MB, less than 15.95% of Java online submissions for Maximum Depth of N-ary Tree.
        if (root == null) return 0;

        int count=0; //开头可以是0，因为加完一层我再depth+1；
        Queue<NTreeNode> queue1=new LinkedList<>();
        queue1.offer(root);
        while(!queue1.isEmpty()){
            int residual_layer_size= queue1.size();
            while(residual_layer_size>0){
                NTreeNode curr=queue1.poll();
                for(int i=0; i<curr.children.size();i++){
                    queue1.offer(curr.children.get(i));
                }
                residual_layer_size--;
            }
            count++;
        }
        return count;
    }
}
