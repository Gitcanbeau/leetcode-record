import java.util.HashSet;
import java.util.Set;

public class leet1559 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/detect-cycles-in-2d-grid/
        char[][] grid={{'a','a','a','a'},
                {'a','b','b','a'},
                {'a','b','b','a'},
                {'a','a','a','a'}};
//        char[][] grid={{'b','a','c'},
//                {'c','a','c'},
//                {'d','d','c'},
//                {'b','c','c'}};
//        char[][] grid={{'b','c','c'},
//                {'c','c','c'},
//                {'d','c','c'},
//                {'b','c','c'}};
        System.out.println(containsCycle(grid));
    }

    private static int count=0;
    public static boolean containsCycle(char[][] grid) {
        //这道题挺不错的

        //Runtime: 23 ms, faster than 91.71% of Java online submissions for Detect Cycles in 2D Grid.
        //Memory Usage: 102.3 MB, less than 86.29% of Java online submissions for Detect Cycles in 2D Grid.

        int[][] visited=new int[grid.length][grid[0].length];

        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                char checkthisch=grid[i][j];
                System.out.println(visited[i][j]);
                if(visited[i][j]==0 && dfs(grid,visited,i,j,i,j,checkthisch)) {
                    System.out.println(visited[i][j]);
                    return true;
                }

            }
        }

        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                System.out.print(visited[i][j]+",");
            }
            System.out.println();
        }

        return false;

    }


    private static boolean dfs(char[][] grid, int[][] visited,int i, int j, int previ, int prevj, char target){
        if(i<0||j<0||i>=grid.length||j>=grid[0].length|| grid[i][j]!=target ) return false;


        System.out.println(i+","+j+","+visited[i][j]+","+previ+","+prevj+","+visited[previ][prevj]);
        System.out.println(visited[i][j]-visited[previ][prevj]);
        if(visited[i][j]-visited[previ][prevj]>=3) return true;


        if(visited[i][j]!=0) return false;
        visited[i][j]=count++;


        return dfs(grid,visited,i+1,j,i,j,target)
                || dfs(grid,visited,i-1,j,i,j,target)
                || dfs(grid,visited,i,j+1,i,j,target)
                || dfs(grid,visited,i,j-1,i,j,target);
    }
}
