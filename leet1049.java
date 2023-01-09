public class leet1049 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/last-stone-weight-ii/
//        int[] stones={2,7,4,1,8,1};
//        int[] stones={31,21,26,33,40};
        int[] stones={14,1,7,17,8,10};
        System.out.println(lastStoneWeightII(stones));
    }
    public static int lastStoneWeightII(int[] stones) {
        //本题其实就是尽量让石头分成重量相同的两堆，相撞之后剩下的石头最小，这样就化解成01背包问题了。
        //Runtime: 3 ms, faster than 91.80% of Java online submissions for Last Stone Weight II.
        //Memory Usage: 40.4 MB, less than 88.62% of Java online submissions for Last Stone Weight II.
        int sum=0;
        for(int i=0; i<stones.length;i++){
            sum+=stones[i];
        }

        int target=sum/2;

        int[][] dp=new int[stones.length][target+1];
        for(int j=stones[0]; j<target+1;j++){
            dp[0][j]=stones[0];
        }

        for(int i=1; i<stones.length;i++){
            for(int j=0; j<target+1;j++){
                if(j>=stones[i]){
                    dp[i][j]=Math.max(dp[i-1][j],dp[i-1][j-stones[i]]+stones[i]);
                }else{
                    dp[i][j]=dp[i-1][j];
                }
            }
        }

        for(int i=0; i<stones.length;i++){
            for(int j=0; j<target+1;j++){
                System.out.print(dp[i][j]+",");
            }
            System.out.println();
        }

        int res=(sum-dp[stones.length-1][target])-dp[stones.length-1][target];
        return res;
    }



    public int lastStoneWeightII2(int[] stones) {
        int sum = 0;
        for (int i : stones) {
            sum += i;
        }
        int target = sum >> 1;
        //初始化dp数组
        int[] dp = new int[target + 1];
        for (int i = 0; i < stones.length; i++) {
            //采用倒序
            for (int j = target; j >= stones[i]; j--) {
                //两种情况，要么放，要么不放
                dp[j] = Math.max(dp[j], dp[j - stones[i]] + stones[i]);
            }
        }
        return sum - 2 * dp[target];
    }
}
