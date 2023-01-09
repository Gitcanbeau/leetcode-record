public class leet213 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/house-robber-ii/
        int[] nums={2,3,2};//3
//        int[] nums={1,2,3,1};//4
//        int[] nums={1,2,3};//3
        System.out.println(rob(nums));
    }
    public static int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        if (nums.length == 1) return nums[0];
        if(nums.length==2) return Math.max(nums[0], nums[1]);
        int res1=robAction(nums, 0, nums.length - 2);
        int res2=robAction(nums, 1, nums.length-1);
        return Math.max(res1, res2);
    }

    public static int robAction(int[] nums, int start, int end) {
        if(start==end) return nums[start];
        if(end-start==1) return Math.max(nums[start], nums[end]);

        int[] dp = new int[end-start+1];
        dp[0] = nums[start];
        dp[1] = Math.max(nums[start], nums[start+1]);
        for (int i = 2; i < dp.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[start+i]);
        }

        return dp[dp.length - 1];
    }


}
