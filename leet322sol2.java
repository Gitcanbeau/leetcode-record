import java.util.Arrays;

public class leet322sol2 {
    public static void main(String[] args) {

        int[] coins={1,2,5}; //3
        int amount=11;

//        int[] coins={1,3,4,5}; //3
//        int amount=11;
//
//        int[] coins={2}; //-1
//        int amount=3;

//        int[] coins={1}; //0
//        int amount=0;

        System.out.println(coinChange(coins, amount));
        System.out.println(coinChange2(coins, amount));

    }
    public static int coinChange(int[] coins, int amount) {
        //Runtime: 56 ms, faster than 24.75% of Java online submissions for Coin Change.
        //Memory Usage: 58.5 MB, less than 10.57% of Java online submissions for Coin Change.
        int m = coins.length;
        int[][] dp = new int[m+1][amount+1];
        //初始化，totalmount
        for (int i=0; i<=m; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE-1);
        }
        for (int i=0; i<=m; i++) {
            dp[i][0] = 0;
        }

        for(int i=1; i<=m; i++) {
            for (int j=1; j<=amount; j++) {
                if(j >= coins[i-1]) {
                    dp[i][j] = Math.min(1 + dp[i][j-coins[i-1]], dp[i-1][j]);
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        return dp[m][amount] >= Integer.MAX_VALUE-1 ? -1 : dp[m][amount];
    }

    public static int coinChange2(int[] coins, int amount) {
        //Runtime: 20 ms, faster than 86.44% of Java online submissions for Coin Change.
        //Memory Usage: 45.4 MB, less than 41.73% of Java online submissions for Coin Change.

        int[] dp=new int[amount+1];
        Arrays.fill(dp,Integer.MAX_VALUE-1);//小细节太他妈多了
        for(int i=0; i<coins.length;i++){
            for(int j=0;j<=amount;j++){
                if(j==coins[i]) dp[j]=1; //相当于初始化，只不过我没单拎出去做初始化
            }
        }
        dp[0]=0;//小细节太他妈多了

        for(int i=0; i<dp.length;i++){
            System.out.print(dp[i]+",");
        }
        System.out.println();

        //dp[j]存的是价值到达j的背包有用最少几个硬币, i是硬币种类，j是价值
        //得到dp[j]只有一个来源，dp[j - coins[i]]（没有考虑coins[i]）
        //所以dp[j] 要取所有 dp[j - coins[i]] + 1 中最小的。
        //考虑到递推公式的特性，dp[j]必须初始化为一个最大的数，否则就会在min(dp[j - coins[i]] + 1, dp[j])比较的过程中被初始值覆盖。
        //所以下标非0的元素都是应该是最大值。
        //可以考虑为组合或者排列都可以，这里我用组合来写，外层遍历物品钱币种类i，内层遍历钱币总和容量j
        //完全背包，可以重复选取，内层遍历可以从小到大遍历，
        for(int i=0; i<coins.length;i++){
            for(int j=coins[i];j<=amount;j++){
                dp[j]=Math.min(dp[j],dp[j-coins[i]]+1);
            }

        }

        for(int i=0; i<dp.length;i++){
            System.out.print(dp[i]+",");
        }
        System.out.println();

        if(dp[amount]!=Integer.MAX_VALUE-1) return dp[amount]; //为什么fills Integer.MAX_VALUE就不行？为什么非要-1？
        return -1;
    }
}
