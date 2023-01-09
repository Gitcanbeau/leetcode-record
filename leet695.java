import java.util.List;

public class leet695 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/max-area-of-island/
        int[][] grid = {{0,0,1,0,0,0,0,1,0,0,0,0,0},
                {0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,1,1,0,1,0,0,0,0,0,0,0,0},
                {0,1,0,0,1,1,0,0,1,0,1,0,0},
                {0,1,0,0,1,1,0,0,1,1,1,0,0},
                {0,0,0,0,0,0,0,0,0,0,1,0,0},
                {0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,0,0,0,0,0,0,1,1,0,0,0,0}};
        System.out.println(maxAreaOfIsland(grid));
    }

    private static int countofthisisland;
    public static int maxAreaOfIsland(int[][] grid) {
        //Runtime: 3 ms, faster than 88.75% of Java online submissions for Max Area of Island.
        //Memory Usage: 46.9 MB, less than 72.87% of Java online submissions for Max Area of Island.
        if(grid.length==0) return 0;

        int res=Integer.MIN_VALUE;

        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]==1){
                    countofthisisland=0;
                    dfs(grid,i,j);
                    res=Math.max(res,countofthisisland);
                }
            }
        }
        return res==Integer.MIN_VALUE?0:res;
    }


    private static void dfs(int[][] grid, int i, int j){
        if(i<0 || j<0 || i>=grid.length || j>=grid[0].length || grid[i][j]!=1) return;
        if(grid[i][j]==1) countofthisisland++;

        grid[i][j]=0;
        dfs(grid,i+1,j);
        dfs(grid,i-1,j);
        dfs(grid,i,j+1);
        dfs(grid,i,j-1);

        return;
    }
}
