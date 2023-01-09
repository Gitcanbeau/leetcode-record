import java.util.ArrayList;
import java.util.List;

public class leet473 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/matchsticks-to-square/
//        int[] matchsticks={1,1,2,2,2};
//        int[] matchsticks={3,3,3,3,4};
        int[] matchsticks={5,5,5,5,4,4,4,4,3,3,3,3};
        System.out.println(makesquare1(matchsticks));
    }
    public static boolean makesquare(int[] matchsticks) {
        int sum = 0;
        for (int i = 0; i < matchsticks.length; i++) {
            sum += matchsticks[i];
        }

        if (sum % 4 != 0)
            return false;
        int target = sum / 4;

        //dp[i][j]代表可装物品为0-i，背包容量为j的情况下，背包内容量的最大价值
        int[][] dp = new int[matchsticks.length][target + 1];

        //初始化,dp[0][j]的最大价值nums[0](if j > weight[i])
        //dp[i][0]均为0，不用初始化
        for (int j = matchsticks[0]; j <= target; j++) {
            dp[0][j] = matchsticks[0];
        }

        //遍历物品，遍历背包
        //递推公式:
        for (int i = 1; i < matchsticks.length; i++) {
            for (int j = 0; j <= target; j++) {
                //背包容量可以容纳nums[i]
                if (j >= matchsticks[i]) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - matchsticks[i]] + matchsticks[i]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

//        for(int i=0; i<matchsticks.length;i++){
//            for(int j=0;j<=target;j++){
//                System.out.print(dp[i][j]+",");
//            }
//            System.out.println();
//        }
        int count=0;
        for(int i=0; i<matchsticks.length;i++){
            for(int j=0;j<=target;j++){
                if (dp[i][j]==target){
                    count++;
                }
            }
        }
        if(count>=4) return true;
        return false;
    }

    public static boolean makesquare1(int[] matchsticks) {
        //不像dp
        //但是作者说把每种情况都记录下来就是dp
        //Would cause MLE if we try to memoize using the below recursive function.
        if (matchsticks == null || matchsticks.length < 4) return false;
        int n = matchsticks.length;
        return solve(matchsticks, 0, 0, 0, 0, 0);
    }

    private static boolean solve(int[] matchSticks, int left, int up, int right, int down, int index) {
        if (index == matchSticks.length) {
            return (left == right && down == up && left == down && right == up);
        }

        boolean result = false;

        result |= solve(matchSticks, left + matchSticks[index], up, right, down, index + 1);
        result |= solve(matchSticks, left, up + matchSticks[index], right, down, index + 1);
        result |= solve(matchSticks, left, up, right + matchSticks[index], down, index + 1);
        result |= solve(matchSticks, left, up, right, down + matchSticks[index], index + 1);

        return result;
    }

    public static boolean makesquare2(int[] matchsticks) {
    //Runtime: 233 ms, faster than 47.90% of Java online submissions for Matchsticks to Square.
    //Memory Usage: 41.6 MB, less than 70.36% of Java online submissions for Matchsticks to Square.
        int sum = 0;
        for (int i = 0; i < matchsticks.length; i++) {
            sum += matchsticks[i];
        }
        if (sum % 4 != 0) return false;


        int target = sum / 4;
        boolean[] picked = new boolean[matchsticks.length];
        return backtracking(matchsticks, 4, sum/4, picked, 0, 0);
    }

    public static boolean backtracking(int[] matchsticks, int sideLeft, int target, boolean[] picked, int start, int subSum ) {

        if(sideLeft==1) return true;

        if(subSum == target) {
            return backtracking(matchsticks, sideLeft-1, target, picked, 0, 0);
        }

        for(int i=start; i<matchsticks.length; i++) {
            if(picked[i] || subSum+matchsticks[i]>target) continue; //提前剪枝
            subSum += matchsticks[i];
            picked[i] = true;
            if (backtracking(matchsticks, sideLeft, target, picked, i+1, subSum)) return true;
            picked[i] = false;
            subSum -= matchsticks[i];
        }

        return false;

    }
}
