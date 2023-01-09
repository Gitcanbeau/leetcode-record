public class leet63 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/unique-paths-ii/
//        int[][] obstacleGrid={{0,0,0},{0,1,0},{0,0,0}};//2
//        int[][] obstacleGrid={{1,0},{0,0}};//0
//        int[][] obstacleGrid={{0,0},{1,1},{0,0}};//0
//        int[][] obstacleGrid={{0,0,0,0},{0,1,0,1},{0,1,0,0},{0,1,0,0}};//2
        int[][] obstacleGrid={{0,0},{0,0},{0,0},{0,0},{0,0},{0,0},{1,0},{0,0},{0,0},{0,0},{0,0},
            {0,0},{1,0},{0,0},{0,0},{0,0},{0,0},{0,1},{0,0},{0,0},{1,0},{0,0},{0,0},{0,1},{0,0},
                {0,0},{0,0},{0,0},{0,0},{0,0},{0,0},{0,1},{0,0},{0,0},{0,0},{0,0},{1,0},{0,0},{0,0},{0,0},{0,0}};


        System.out.println(uniquePathsWithObstacles(obstacleGrid));
    }
    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        //Runtime: 2 ms, faster than 26.28% of Java online submissions for Unique Paths II.
        //Memory Usage: 42.7 MB, less than 19.50% of Java online submissions for Unique Paths II.

        int row=obstacleGrid.length;
        int col=obstacleGrid[0].length;

        int[][] dp=new int[row][col]; //dp[i][j]是到达grid[i][j]的方式
        //好长的初始化
        int wrongrowroute=row;
        int wrongcolroute=col;
        for(int i=0;i<row;i++){
            if(obstacleGrid[i][0]==1){
                wrongrowroute=i;
                break;
            }
        }
        for(int j=0;j<col;j++){
            if(obstacleGrid[0][j]==1){
                wrongcolroute=j;
                break;
            }
        }
        for(int i=0;i<wrongrowroute;i++){
            dp[i][0]=1;
        }
        for(int i=wrongrowroute;i<row;i++){
            dp[i][0]=-1;
        }
        for(int j=0; j<wrongcolroute;j++){
            dp[0][j]=1;
        }
        for(int j=wrongcolroute;j<col;j++){
            dp[0][j]=-1;
        }

        for(int i=1; i<row;i++){
            for(int j=1;j<col;j++){
                if(obstacleGrid[i][j]==1){
                    dp[i][j]=-1;
                }
            }
        }


        //两层遍历
        for(int i=1;i<row;i++){
            for(int j=1;j<col;j++){
                if(dp[i][j]==-1){
                    continue;
                }else if(dp[i][j]==0 && dp[i][j-1]==-1 && dp[i-1][j]==-1){
                    dp[i][j]=-1;
                }else if(dp[i][j]==0 && dp[i][j-1]!=-1 && dp[i-1][j]!=-1){
                    dp[i][j]=dp[i][j-1]+dp[i-1][j];
                }else if(dp[i][j]==0 && dp[i][j-1]!=-1 && dp[i-1][j]==-1){
                    dp[i][j]=dp[i][j-1];
                }else if(dp[i][j]==0 && dp[i][j-1]==-1 && dp[i-1][j]!=-1){
                    dp[i][j]=dp[i-1][j];
                }
            }
        }

        //返回
        if(dp[row-1][col-1]==-1) return 0;
        return dp[row-1][col-1];

    }


    public static int uniquePathsWithObstacles2(int[][] obstacleGrid) {
        //真他么简洁。。。
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];

        //如果在起点或终点出现了障碍，直接返回0
        if (obstacleGrid[m - 1][n - 1] == 1 || obstacleGrid[0][0] == 1) {
            return 0;
        }

        for (int i = 0; i < m && obstacleGrid[i][0] == 0; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < n && obstacleGrid[0][j] == 0; j++) {
            dp[0][j] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = (obstacleGrid[i][j] == 0) ? dp[i - 1][j] + dp[i][j - 1] : 0;
            }
        }
        return dp[m - 1][n - 1];
    }
}
