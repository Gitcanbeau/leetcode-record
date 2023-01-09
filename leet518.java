public class leet518 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/coin-change-ii/
//        int amount=5;
//        int[] coins={1,2,5};
        int amount=0;
        int[] coins={1,2,5};
        System.out.println(change(amount,coins));
        System.out.println(change1(amount,coins));
    }


    public static int change(int amount, int[] coins) {
        //Runtime: 17 ms, faster than 44.71% of Java online submissions for Coin Change II.
        //Memory Usage: 51.6 MB, less than 14.36% of Java online submissions for Coin Change II.
        if (coins.length == 0) return 0;
        if (amount == 0) return 1;


        int[][] dp=new int[coins.length+1][amount+1]; //从1-i种coins里面凑面值为j有多少种方法

        for(int i=0;i<coins.length+1;i++){
            dp[i][0]=1;
        }

        for(int j=0;j<amount+1;j++){
           dp[0][j]=0;
        }



        for(int i=1;i<coins.length+1;i++){
            for(int j=1;j<amount+1;j++){
                if(j>=coins[i-1]){
                    dp[i][j]=dp[i-1][j]+dp[i][j-coins[i-1]];
                }else{
                    dp[i][j]=dp[i-1][j];
                }
            }
        }

        return dp[coins.length][amount];
    }
    public static int change1(int amount, int[] coins) {
        //Runtime: 4 ms, faster than 86.33% of Java online submissions for Coin Change II.
        //Memory Usage: 41.2 MB, less than 85.78% of Java online submissions for Coin Change II.
        //组合就是先遍历物品
        int[] dp=new int[amount+1];
        dp[0]=1;
        for (int i = 0; i < coins.length; i++) { // 遍历物品
//            System.out.println(i);
            for (int j = coins[i]; j <= amount; j++) { // 遍历背包容量
                dp[j] += dp[j - coins[i]];
//                System.out.println(dp[j]);
            }
//            System.out.println("-----------");
        }


//        for(int i=0; i<dp.length;i++){
//            System.out.print(dp[i]+",");
//        }
//        System.out.println();

        return dp[amount];
    }


    public static int change2(int amount, int[] coins) {
        //排列就是先遍历背包
        int[] dp=new int[amount+1];
        dp[0]=1;
        for (int j = 0; j <= amount; j++) { // 遍历背包容量
            System.out.println(j);
            for (int i = 0; i < coins.length; i++) { // 遍历物品
                if(j>=coins[i]) dp[j] += dp[j - coins[i]];
                System.out.println(dp[j]);
            }
            System.out.println("-----------");
        }


        for(int i=0; i<dp.length;i++){
            System.out.print(dp[i]+",");
        }
        System.out.println();

        return dp[amount];
    }
}
