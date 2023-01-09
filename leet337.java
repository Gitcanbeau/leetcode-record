import java.util.*;

public class leet337 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/house-robber-iii/
//        TreeNode node1=new TreeNode(3,null,null);
//        TreeNode node2=new TreeNode(2,null,null);
//        TreeNode node3=new TreeNode(3,null,null);
//        TreeNode node4=new TreeNode(3,null,null);
//        TreeNode node5=new TreeNode(1,null,null);
//        node1.left=node2;
//        node1.right=node3;
//        node2.right=node4;
//        node3.right=node5;
        TreeNode node1=new TreeNode(2,null,null);
        TreeNode node2=new TreeNode(1,null,null);
        TreeNode node3=new TreeNode(3,null,null);
        TreeNode node4=new TreeNode(4,null,null);

        node1.left=node2;
        node1.right=node3;
        node2.right=node4;

        System.out.println(rob(node1));
    }
    public static int rob(TreeNode root) {
        if(root==null) return 0;


        //横向遍历
        List<Integer> house=new ArrayList<>();
        Queue<TreeNode> bfsque1=new LinkedList<>();
        bfsque1.offer(root);
        while(!bfsque1.isEmpty()){
            int residuallayersize= bfsque1.size();
            int temp=0;
            while(residuallayersize>0){
                TreeNode curr=bfsque1.poll();
                temp+=curr.val;
                if(curr.left!=null) bfsque1.offer(curr.left);
                if(curr.right!=null) bfsque1.offer(curr.right);
                residuallayersize--;
            }
            house.add(temp);
        }


        if(house.size()==1) return house.get(0);
        if(house.size()==2) return Math.max(house.get(0), house.get(1));

        int[] dp=new int[house.size()];
        dp[0]=house.get(0);
        dp[1]=Math.max(house.get(0), house.get(1));

        for(int i=2;i<dp.length;i++){
            dp[i]=Math.max(dp[i-2]+house.get(i),dp[i-1]);
        }

        return dp[dp.length-1];
    }

    // 1.递归去偷，超时
    public static int rob2(TreeNode root) {
        if (root == null)
            return 0;
        int money = root.val;
        if (root.left != null) {
            money += rob(root.left.left) + rob(root.left.right);
        }
        if (root.right != null) {
            money += rob(root.right.left) + rob(root.right.right);
        }
        return Math.max(money, rob(root.left) + rob(root.right));
    }

    // 2.递归去偷，记录状态
    // 执行用时：3 ms , 在所有 Java 提交中击败了 56.24% 的用户
    public static int rob1(TreeNode root) {
        Map<TreeNode, Integer> memo = new HashMap<>();
        return robAction(root, memo);
    }

    private static int robAction(TreeNode root, Map<TreeNode, Integer> memo) {
        if (root == null)
            return 0;
        if (memo.containsKey(root))
            return memo.get(root);
        int money = root.val;
        if (root.left != null) {
            money += robAction(root.left.left, memo) + robAction(root.left.right, memo);
        }
        if (root.right != null) {
            money += robAction(root.right.left, memo) + robAction(root.right.right, memo);
        }
        int res = Math.max(money, robAction(root.left, memo) + robAction(root.right, memo));
        memo.put(root, res);
        return res;
    }

    // 3.状态标记递归
    // 执行用时：0 ms , 在所有 Java 提交中击败了 100% 的用户
    // 不偷：Max(左孩子不偷，左孩子偷) + Max(又孩子不偷，右孩子偷)
    // root[0] = Math.max(rob(root.left)[0], rob(root.left)[1]) +
    // Math.max(rob(root.right)[0], rob(root.right)[1])
    // 偷：左孩子不偷+ 右孩子不偷 + 当前节点偷
    // root[1] = rob(root.left)[0] + rob(root.right)[0] + root.val;
    public static int rob3(TreeNode root) {
        int[] res = robAction1(root);
        return Math.max(res[0], res[1]);
    }

    private static int[] robAction1(TreeNode root) {
        int res[] = new int[2];
        if (root == null)
            return res;

        int[] left = robAction1(root.left);
        int[] right = robAction1(root.right);

        res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        res[1] = root.val + left[0] + right[0];
        return res;
    }
}
