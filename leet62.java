public class leet62 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/unique-paths/
        System.out.println(uniquePaths(3,2)); //3
//        System.out.println(uniquePaths(3,7)); //28
    }
    public static int uniquePaths(int m, int n) {
        //Runtime: 2 ms, faster than 19.19% of Java online submissions for Unique Paths.
        //Memory Usage: 41.7 MB, less than 8.45% of Java online submissions for Unique Paths.
        int[][] dp=new int[m][n];
        //dp[i][j] 是到达grid[i][j]的方式的个数（不是步数）
        //初始化
        for(int i=0;i<m;i++){
            dp[i][0]=1;
        }
        for(int j=0;j<n;j++){
            dp[0][j]=1;
        }
        //两层遍历
        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                dp[i][j]=dp[i-1][j]+dp[i][j-1];
            }
        }
        //返回
        return dp[m-1][n-1];
    }
}
