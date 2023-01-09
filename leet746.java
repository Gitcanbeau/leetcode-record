public class leet746 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/min-cost-climbing-stairs/
        int[] cost={1,100,1,1,1,100,1,1,100,1};
        System.out.println(minCostClimbingStairs(cost));
    }
    public static int minCostClimbingStairs(int[] cost) {
        //Runtime: 2 ms, faster than 34.60% of Java online submissions for Min Cost Climbing Stairs.
        //Memory Usage: 43.9 MB, less than 32.76% of Java online submissions for Min Cost Climbing Stairs.

        int n=cost.length; //note: n>=2 is given

        int[] dp=new int[n];
        //dp[i] 离开第i级台阶所花费的最少费用
        dp[0]=cost[0];
        dp[1]=cost[1];
        for(int i=2;i<n;i++){
            dp[i]=Math.min(dp[i-2],dp[i-1])+cost[i];
        }

        int res=Math.min(dp[n-1],dp[n-2]);
        return res;
    }
}
