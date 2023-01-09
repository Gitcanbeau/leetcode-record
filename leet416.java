import java.util.*;

public class leet416 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/partition-equal-subset-sum/
//        int[] nums={1,1,2,3,5,11};
        int[] nums={1,5,5,11};
        System.out.println(canPartition1(nums));
        System.out.println(canPartition2(nums));
    }

    public static boolean canPartition(int[] nums) {
        if(nums == null || nums.length == 0) return false;
        int n = nums.length;
        int sum = 0;
        for(int num : nums){
            sum += num;
        }
        //总和为奇数，不能平分
        if(sum % 2 != 0) return false;
        int target = sum / 2;
        int[] dp = new int[target + 1];
        for(int i = 0; i < n; i++){
            for(int j = target; j >= nums[i]; j--){
                //物品 i 的重量是 nums[i]，其价值也是 nums[i]
                dp[j] = Math.max(dp[j], dp[j-nums[i]] + nums[i]);
            }
        }
        return dp[target] == target;
    }

    public static boolean canPartition1(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }

        if (sum % 2 == 1)
            return false;
        int target = sum / 2;

        //dp[i][j]代表可装物品为0-i，背包容量为j的情况下，背包内容量的最大价值
        int[][] dp = new int[nums.length][target + 1];

        //初始化,dp[0][j]的最大价值nums[0](if j > weight[i])
        //dp[i][0]均为0，不用初始化
        for (int j = nums[0]; j <= target; j++) {
            dp[0][j] = nums[0];
        }

        //遍历物品，遍历背包
        //递推公式:
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j <= target; j++) {
                //背包容量可以容纳nums[i]
                if (j >= nums[i]) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - nums[i]] + nums[i]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[nums.length - 1][target] == target;
    }
    public static boolean canPartition2(int[] nums) {
        //time limit exceeded
        Arrays.sort(nums);
        int sum=0;
        for(int i=0; i<nums.length;i++){
            sum+=nums[i];
        }

        if(sum%2!=0) return false;
        int target=sum/2;

        return backtracking(nums,target,0,0);
    }
    public static boolean backtracking(int[] nums, int target,int cursum, int startidx){
        if(cursum==target) return true;

        for(int i=startidx; i<nums.length;i++){
            if(cursum+nums[i]>target) continue;
            //可以每层新建一个boolean[] 去重，但是本题完全不必要，题里只问有还是没有，没让你求不重复的组合个数
            //写这些就是复习
            //就是boolean[] visitedthislayer
            //if(i>startidx && nums[i]==nums[i-1] && visitedthislayer[i-1]==false) continue;
            if(backtracking(nums,target,cursum+nums[i],i+1)) return true;
        }
        return false;
    }
}
