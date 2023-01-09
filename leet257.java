import java.util.*;

public class leet257 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/binary-tree-paths/
        TreeNode t1=new TreeNode(1,null,null);
        TreeNode t2=new TreeNode(2,null,null);
        TreeNode t3=new TreeNode(3,null,null);
        TreeNode t4=new TreeNode(4,null,null);
        TreeNode t5=new TreeNode(5,null,null);
        TreeNode t6=new TreeNode(6,null,null);
        TreeNode t4a=new TreeNode(41,null,null);
        TreeNode t4aa=new TreeNode(411,null,null);
        t1.left=t2;
        t1.right=t3;
        t2.left=t4;
        t2.right=t5;
        t3.left=t6;
        t4.left=t4a;
        t4a.left=t4aa;
        System.out.println(binaryTreePaths(t1));
        System.out.println(binaryTreePaths2(t1));
        System.out.println(binaryTreePaths3(t1));
    }
    public static List<String> binaryTreePaths(TreeNode root) {
        //Runtime: 10 ms, faster than 60.02% of Java online submissions for Binary Tree Paths.
        //Memory Usage: 42.8 MB, less than 79.22% of Java online submissions for Binary Tree Paths.
        List<String> result = new ArrayList<>();
        if (root == null)
            return result;
        Stack<TreeNode> stack = new Stack<>();
        Stack<String> path = new Stack<>();
        // 节点和路径同时入栈
        stack.push(root);
        path.push(root.val + "");
        while (!stack.isEmpty()) {
            // 节点和路径同时出栈
            String currpath =  path.pop();
            TreeNode node = stack.pop();
            // 若找到叶子节点
            if (node.left == null && node.right == null) {
                result.add(currpath);
            }
            //右子节点不为空
            if (node.right != null) {
                stack.push(node.right);
                path.push(currpath + "->" + node.right.val);
            }
            //左子节点不为空
            if (node.left != null) {
                stack.push(node.left);
                path.push(currpath + "->" + node.left.val);
            }
        }
        return result;
    }
    public static List<String> binaryTreePaths2(TreeNode root) {
        //Runtime: 15 ms, faster than 30.62% of Java online submissions for Binary Tree Paths.
        //Memory Usage: 43.5 MB, less than 27.81% of Java online submissions for Binary Tree Paths.
        List<String> res = new ArrayList<>();
        if (root == null)
            return res;
        Stack<TreeNode> stack = new Stack<>();
        Set<TreeNode> visited =new HashSet<>();
        StringBuilder sb=new StringBuilder();
        stack.push(root);
        visited.add(root);
        sb.append(root.val+"->");
        while(!stack.isEmpty()){
            TreeNode curr= stack.peek(); //不要在peek的时候计数或者sb.append，1入栈2访问过3编辑这三步在一起
            if(curr.left==null && curr.right==null){
                //到达叶子节点可以开始编辑去掉多余的符号->是长度为2，存到res里面，
                // 准备往回走，要做两件事: sb删除该路径信息，弹栈
                sb.delete(sb.length()-2,sb.length());//我这里删除的箭头符号
                String str=sb.toString();
                res.add(str);
                sb.delete(sb.lastIndexOf(curr.val+""),sb.length());
                //有的val它长度不一定是1啊，它可以是好几位数，你就查它的lastindex，然后delete一段吧
                stack.pop();
            }else if(curr.left!=null && !visited.contains(curr.left)){
                //上面的方法是前序遍历入栈顺序中右左，
                // 但是这个方法用的是if和else if，人为限制了我只能一条道走到头再往回走，所以左节点先入栈，左节点路径就会先保存
                //你要是右节点先入栈，必须把右节点所在路径走到头才能说回来弹栈的时候发现该点的左子节点还没走过
                //所以这个方法的入栈顺序和遍历顺序是保持一致的
                //这个方法比上面的写法更能体现回溯思想
                stack.push(curr.left);
                visited.add(curr.left);
                sb.append(curr.left.val+"->");
            }else if(curr.right!=null && !visited.contains(curr.right)){
                stack.push(curr.right);
                visited.add(curr.right);
                sb.append(curr.right.val+"->");
            }else{
                //该节点的左右子节点全都走过，只有往回走才可能找到更多路径，所以sb删除该路径信息，然后弹栈
                sb.delete(sb.lastIndexOf(curr.val+"->"),sb.length());
                stack.pop();
            }
        }
        return res;
    }

    public static List<String> binaryTreePaths3(TreeNode root) {
        //Runtime: 3 ms, faster than 83.75% of Java online submissions for Binary Tree Paths.
        //Memory Usage: 43.2 MB, less than 61.07% of Java online submissions for Binary Tree Paths.
        List<String> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        List<Integer> paths = new ArrayList<>();
        traversal(root, paths, res);
        return res;
    }

    private static void traversal(TreeNode root, List<Integer> paths, List<String> res) {
        paths.add(root.val);
        // 叶子结点
        if (root.left == null && root.right == null) {
            // 输出
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < paths.size() - 1; i++) {
                sb.append(paths.get(i)).append("->");
            }
            sb.append(paths.get(paths.size() - 1));
            res.add(sb.toString());
            return;
        }
        if (root.left != null) {
            traversal(root.left, paths, res);
            paths.remove(paths.size() - 1);// 回溯
        }
        if (root.right != null) {
            traversal(root.right, paths, res);
            paths.remove(paths.size() - 1);// 回溯
        }
    }
}
