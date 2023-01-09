import java.util.ArrayList;
import java.util.Stack;

public class leet98 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/validate-binary-search-tree/
//        TreeNode node1=new TreeNode(-2147483648,null,null);
//        TreeNode node2=new TreeNode(-2147483648,null,null);
        TreeNode node3=new TreeNode(0,null,null);
//        node1.left=node2;
//        node1.right=node3;
        System.out.println(isValidBST1(node3));
        System.out.println(isValidBST2(node3));
        System.out.println(isValidBST3(node3));
        System.out.println(isValidBST4(node3));
    }
    public static boolean isValidBST4(TreeNode root) {
        //dfs-recursion-topdown1
        //Runtime: 1 ms, faster than 59.80% of Java online submissions for Validate Binary Search Tree.
        //Memory Usage: 44.2 MB, less than 25.93% of Java online submissions for Validate Binary Search Tree.
        return testValidBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    private static boolean testValidBST(TreeNode root, Integer min, Integer max) {
        if(root==null)return true;
        if((root.val==Integer.MAX_VALUE && root.right!=null) ||
                (root.val==Integer.MIN_VALUE && root.left!=null))
            //如果一个节点的值已经是Integer.MIN_VALUE，但是他还有左节点，那么这个左节点一定没办法比当前节点的值小，直接返回false
            return false;
        if(root.val<min || root.val>max) return false;

        return testValidBST(root.left, min, root.val-1) && testValidBST(root.right, root.val+1, max);


    }

    public static boolean isValidBST1(TreeNode root) {
        //dfs-recursion-topdown2
        //Runtime: 3 ms, faster than 14.92% of Java online submissions for Validate Binary Search Tree.
        //Memory Usage: 44.2 MB, less than 31.98% of Java online submissions for Validate Binary Search Tree.
        ArrayList<Integer> l = new ArrayList<>();
        inorder(root,l); //中序遍历存上所有的值，然后挨个比是否后面的比前面的大
        for(int i = 0;i<l.size() - 1;i++)
            if(l.get(i) >= l.get(i+1)) {
                return false;
            }
        return true;
    }

    private static void inorder(TreeNode root,ArrayList l){
        if(root.left!= null)
            inorder(root.left,l);
        l.add(root.val);
        if(root.right!= null)
            inorder(root.right,l);
    }
    //这道题目比较容易陷入两个陷阱：
    //
    //陷阱1
    //不能单纯的比较左节点小于中间节点，右节点大于中间节点就完事了。
    //我们要比较的是 左子树所有节点小于中间节点，右子树所有节点大于中间节点。
    //陷阱2
    //样例中最小节点 可能是int的最小值，如果这样使用最小的int来比较也是不行的。
    //此时可以初始化比较元素为longlong的最小值。
    //问题可以进一步演进：如果样例中根节点的val 可能是longlong的最小值 又要怎么办呢？

    //只有寻找某一条边（或者一个节点）的时候，递归函数会有bool类型的返回值。
    //其实本题是同样的道理，我们在寻找一个不符合条件的节点，如果没有找到这个节点就遍历了整个树，如果找到不符合的节点了，立刻返回。
    public static boolean isValidBST3(TreeNode root) {
        //dfs-iteration-inorder
        //中序遍历模版不熟，复习一下
        //Runtime: 5 ms, faster than 9.33% of Java online submissions for Validate Binary Search Tree.
        //Memory Usage: 43.8 MB, less than 59.30% of Java online submissions for Validate Binary Search Tree.

        if (root == null) {
            return true;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;// 左
            }
            // 中，处理
            root = stack.pop();
            if (pre != null && root.val <= pre.val) { //pop一直是靠右侧的节点，pre一直是上一轮的pop，就是靠左的节点
                return false;
            }
            pre = root;

            root = root.right;// 右
        }
        return true;
    }

    private static TreeNode pre;
    public static boolean isValidBST2(TreeNode root) {
        //中序遍历不熟，然后怎么处理返回值学习一下
        //hidden test 没通过【0】应该返回true, 但是自己试就没问题啊，因为leetcode里面要把static都去掉，不然pre保存的是上一次的测试的最后一个前节点的值
        if (root == null) {
            return true;
        }
        // 左
        boolean left = isValidBST2(root.left);
//        if (!left) {
//            return false;
//        } //这样写的话会快一些

        // 中
        if (pre != null && root.val <= pre.val) { //然后这里的root都会是传进来的更靠右侧的节点，max都是上轮的偏左的root
            return false;
        }
        pre = root; //这里很巧妙，每次max都是靠近左侧的节点，
        // 右
        boolean right = isValidBST2(root.right);
        //return right;//这样写话会快一些
        return left && right; //全真为真，一假便假
    }
}
