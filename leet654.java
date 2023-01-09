import java.util.HashMap;
import java.util.Map;

public class leet654 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/maximum-binary-tree/
        int[] nums={3,2,1,6,0,5};
        System.out.println(constructMaximumBinaryTree(nums));
    }
    public static TreeNode constructMaximumBinaryTree(int[] nums) {
        //Runtime: 25 ms, faster than 7.79% of Java online submissions for Maximum Binary Tree.
        //Memory Usage: 53.8 MB, less than 65.24% of Java online submissions for Maximum Binary Tree.
        //大宝子自己想出来的，真是个聪明宝
        int numsLen=nums.length;
        Map<Integer,Integer> map1=new HashMap<>();
        for(int i=0; i<numsLen;i++) map1.put(nums[i],i);

        return construct_helper(nums,map1,0,numsLen-1);
    }
    private static TreeNode construct_helper(int[] nums,Map<Integer,Integer> map1, int left, int right){
        if(right<left) return null;
        int rootval=findMax(nums,left,right);
        int rootindex=map1.get(rootval);
        TreeNode root=new TreeNode(rootval);

        root.left=construct_helper(nums,map1,left,rootindex-1);
        root.right=construct_helper(nums,map1,rootindex+1,right);

        return root;
    }
    private static int findMax(int[] nums,int left, int right){
        int max=Integer.MIN_VALUE;
        for(int i=left;i<=right;i++){
            max=Math.max(max,nums[i]);
        }
        return max;
    }
}
