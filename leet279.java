import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class leet279 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/perfect-squares/
//        int n=12;
        int n=13;
//        int n=0;
        System.out.println(numSquares(n));
        System.out.println(numSquares2(n));
    }
    public static int numSquares(int n) {
        //Runtime: 57 ms, faster than 58.83% of Java online submissions for Perfect Squares.
        //Memory Usage: 42.7 MB, less than 81.08% of Java online submissions for Perfect Squares.

        //物品种类从i=0到i=n,其价值是i^2
        //背包最大重量j从j=0到j=n
        //dp[j]就是最少的物品个数
        //递推关系就是dp[j]=math.min(dp[j],dp[j-i^2]+1)
        //dp[0]=1;dp[j==i^2]=1; 其他的都是最大值
        //可以考虑为组合问题，所以外层物品种类，内层背包容量，因为是完全背包，所以内层循环从小到大遍历
        int[] dp=new int[n+1];
        Arrays.fill(dp,Integer.MAX_VALUE-1);
        int maxi= (int) Math.sqrt(n);
        for(int i=0;i<=maxi;i++){
            for(int j=0;j<=n;j++){
                if(j==i*i) dp[j]=1;
            }
        }


        for(int i=0;i<=maxi;i++){
            for(int j=i*i;j<=n;j++){
                dp[j]=Math.min(dp[j],dp[j-i*i]+1);
            }
        }
//        for(int i=0;i<=maxi;i++){
//            for(int j=0;j<=n;j++){
//                System.out.print(dp[j]+",");
//            }
//            System.out.println();
//        }

        return dp[n]==Integer.MAX_VALUE-1?-1:dp[n];
    }




    public static int numSquares2(int n) {
        //Runtime: 275 ms, faster than 19.74% of Java online submissions for Perfect Squares.
        //Memory Usage: 118 MB, less than 8.18% of Java online submissions for Perfect Squares.

        //自己照着正确答案改的，没太看懂

        // Similar to Coin change Problem
        List<Integer> coins=new ArrayList<>();

        for(int i=0 ; i*i<=n ; i++) {
            coins.add(i*i);
        }
        // A bag of perfect square numbers till n

        // 2-D DP solution

        int[][] dp=new int[coins.size()][n+1];
        for(int i=0;i<coins.size();i++){
            for(int j=0;j<n+1;j++){
                dp[i][j]=Integer.MAX_VALUE-1;
            }
        }
        // 0 th Row
        for(int i=0 ; i<coins.size(); i++) {
            dp[i][0] = 0;
        }

        // 0 th Column
        for(int j=1 ; j<n+1 ; j++) {
            dp[0][j] = j;
        }

        for(int i=1 ; i< coins.size() ; i++) {
            for(int j=1 ; j<n+1 ; j++) {
                if(j >= coins.get(i)) {
                    // Check above and left
                    dp[i][j] = Math.min(dp[i-1][j] , 1+ dp[i][j- coins.get(i)]);
                } else {
                    // Take value from above cell
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[coins.size()-1][n];
    }


}
