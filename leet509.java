public class leet509 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/fibonacci-number/
    }
    public static int fib(int n) {

        //Runtime: 0 ms, faster than 100.00% of Java online submissions for Fibonacci Number.
        //Memory Usage: 39 MB, less than 92.55% of Java online submissions for Fibonacci Number.
        int[] dp=new int[n+1];


        for(int i=0;i<=n;i++){
            if(i==0){
                dp[i]=0;
            }else if(i==1){
                dp[i]=1;
            }else{
                dp[i]=dp[i-1]+dp[i-2];
            }
        }
        return dp[n];
    }
}
