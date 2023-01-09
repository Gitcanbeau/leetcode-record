import java.util.Arrays;

public class leet714 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/
//        int[] prices={1,3,7,5,10,3};//6
//        int fee=3;
        int[] prices={1,3,7,3,10,3};//7
        int fee=3;
//        int[] prices={1,3,2,8,4,9};//8
//        int fee=2;
//        int[] prices={1,4,6,2,8,3,10,14};//13, 1-6, 2-8, 3-14
//        int fee=3;
        System.out.println(maxProfit2(prices,fee));
    }
    public static int maxProfit(int[] prices, int fee) {
        //prices = [1,3,7,5,10,3]
        //profit1 = [0,2,4,-2,5,-7]
        //accu_profit=[0,2,6,4,9,2] //（6-3）+（9-5-3）<=9-3 的话就是1，10划算 变形以后就是prices[i]-fee<=prices[i+1]就不用更新minprices
        //prices = [1,3,7,3,10,3]
        //profit1 = [0,2,4,-4,7,-7]
        //accu_profit=[0,2,6,2,9,2] //（6-3）+（9-2-3）>9-3 的话就是1，7 和3，10划算 变形以后就是prices[i]-fee>prices[i+1]就需要更新minprices
        int[] profit_dybyday=new int[prices.length];
        int[] profit_accumulated=new int[prices.length];
        profit_dybyday[0]=0;
        profit_accumulated[0]=0;
        for(int i=1; i<prices.length;i++){
            profit_dybyday[i]=prices[i]-prices[i-1];
            profit_accumulated[i]=profit_accumulated[i-1]+profit_dybyday[i];
        }

        int profit_pre=0;
        int profit_curr=0;
        for(int i=1; i<profit_accumulated.length;i++){
            if(profit_accumulated[i]>profit_accumulated[i-1]){
                profit_curr=profit_accumulated[i];
            }
            profit_pre=Math.max(profit_curr-fee,(profit_pre+profit_accumulated[i]-profit_accumulated[i-1]-fee));

        }
        if(profit_pre<=0) return 0;
        return profit_pre;
    }
    //买入日期：其实很好想，遇到更低点就记录一下。
    //卖出日期：这个就不好算了，但也没有必要算出准确的卖出日期，只要当前价格大于（最低价格+手续费），就可以收获利润，至于准确的卖出日期，就是连续收获利润区间里的最后一天（并不需要计算是具体哪一天）。
    //所以我们在做收获利润操作的时候其实有三种情况：
    //
    //情况一：收获利润的这一天并不是收获利润区间里的最后一天（不是真正的卖出，相当于持有股票），所以后面要继续收获利润。
    //情况二：前一天是收获利润区间里的最后一天（相当于真正的卖出了），今天要重新记录最小价格了。
    //情况三：不作操作，保持原有状态（买入，卖出，不买不卖）
    //从代码中可以看出对情况一的操作，因为如果还在收获利润的区间里，表示并不是真正的卖出，而计算利润每次都要减去手续费，所以要让minPrice = prices[i] - fee;，
    // 这样在明天收获利润的时候，才不会多减一次手续费！
    public static int maxProfit2(int[] prices, int fee) {
        //Runtime: 23 ms, faster than 43.23% of Java online submissions for Best Time to Buy and Sell Stock with Transaction Fee.
        //Memory Usage: 68.3 MB, less than 81.69% of Java online submissions for Best Time to Buy and Sell Stock with Transaction Fee.
        int result = 0;
        int minPrice = prices[0]; // 记录最低价格
        //int[] prices={1,4,6,2,8,3,10,14};
        for (int i = 1; i < prices.length; i++) {
            // 情况二：相当于买入
            if (prices[i] < minPrice) minPrice = prices[i];
            // 情况三：保持原有状态（因为此时买则不便宜，卖则亏本）
            if (prices[i] >= minPrice && prices[i] <= minPrice + fee) {
                continue;
            }
            // 计算利润，可能有多次计算利润，最后一次计算利润才是真正意义的卖出
            if (prices[i] > minPrice + fee) {
                result += prices[i] - minPrice - fee;
                minPrice = prices[i] - fee; // 情况一，这一步很关键
            }
        }
        return result;
    }
    public static int maxProfit3(int[] prices, int fee) {
        if (prices == null || prices.length < 2) {
            return 0;
        }

        int[][] dp = new int[prices.length][2];

        // bad case
        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i] - fee);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }

        return dp[prices.length - 1][0];
    }
}
