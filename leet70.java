import java.awt.event.ActionListener;

public class leet70 {
    public static void main(String[] args) {
        //You are climbing a staircase. It takes n steps to reach the top.
        //Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
        //Example 1:
        //Input: n = 2
        //Output: 2
        //Explanation: There are two ways to climb to the top.
        //1. 1 step + 1 step
        //2. 2 steps
        //Example 2:
        //Input: n = 3
        //Output: 3
        //Explanation: There are three ways to climb to the top.
        //1. 1 step + 1 step + 1 step
        //2. 1 step + 2 steps
        //3. 2 steps + 1 step
        //Constraints:
        //1 <= n <= 45
        System.out.println(climbStairs(1));
        System.out.println(climbStairs(2));
        System.out.println(climbStairs(3));
        System.out.println(climbStairs(4));
        System.out.println(climbStairs(5));
        //time limit exceeded
    }
    public static int climbStairs(int n) {
        int count;
        if(n<=2){
            return n;
        }else{
            //count=climbStairs(1)*climbStairs(n-1)+(climbStairs(2)-1)*climbStairs(n-2);//做一下简化啊
            count=climbStairs(n-1)+climbStairs(n-2);//改完了也还是time limit exceeded//看来递归就是慢
            return count;
        }
    }

    public static int climbStairs2(int n) {
        //Runtime: 0 ms, faster than 100.00% of Java online submissions for Climbing Stairs.
        //Memory Usage: 41.1 MB, less than 30.69% of Java online submissions for Climbing Stairs.
        if(n<=2) return n;
        int[] dp=new int[n+1];
        dp[0]=0;
        dp[1]=1;
        dp[2]=2;
        for(int i=3;i<=n;i++){
            dp[i]=dp[i-2]+dp[i-1];
        }
        return dp[n];
    }

    public int climbStairs3(int n) {
        //完全背包写法
        int[] dp = new int[n + 1];
        int[] weight = {1,2};
        dp[0] = 1;

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j < weight.length; j++) {
                if (i >= weight[j]) dp[i] += dp[i - weight[j]];
            }
        }

        return dp[n];
    }


}
