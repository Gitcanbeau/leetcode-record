public class leet377 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/combination-sum-iv/
//        int[] nums={1,2,3};
//        int target=4;
        int[] nums={9};
        int target=3;
        System.out.println(combinationSum4(nums,target));
    }
    public static int combinationSum4(int[] nums, int target) {
        //Runtime: 1 ms, faster than 95.70% of Java online submissions for Combination Sum IV.
        //Memory Usage: 42.4 MB, less than 5.75% of Java online submissions for Combination Sum IV.
        int[] dp=new int[target+1];
        dp[0]=1;
        for(int j=0;j<=target;j++){ //完全背包求排列，外层背包，内层物品，内层从小到大遍历
            for(int i=0; i<nums.length;i++){
                if(j>=nums[i]) dp[j]+=dp[j-nums[i]];
            }
        }
        return dp[target];
    }

    public static int combinationSum412s(int[] nums, int target) {
        //Runtime: 5 ms, faster than 19.58% of Java online submissions for Combination Sum IV.
        //Memory Usage: 41.4 MB, less than 47.33% of Java online submissions for Combination Sum IV.
        int[][] dp = new int[nums.length + 1][target + 1];
        if (nums.length == 0) return 0;
        for (int i = 0; i < nums.length+1; i++)
            dp[i][0] = 1;
        for (int j = 1; j < target+1; j++) {
            for (int i = 1; i < nums.length+1; i++) {

                if (j >= nums[i - 1]) {
                    dp[i][j] = dp[i - 1][j] + dp[nums.length][j - nums[i - 1]];
                }else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[nums.length][target];
    }
}
