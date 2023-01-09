public class leet1568 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/minimum-number-of-days-to-disconnect-island/
    }
    static int [][]dirs={{-1,0},{0,1},{0,-1},{1,0}};
    public void connectedComponents(int[][] grid, int i, int j, boolean[][] visited) {

        visited[i][j] = true;
        for(int d=0;d<4;d++){
            int r=i+dirs[d][0];
            int c=j+dirs[d][1];

            if(r>=0 && r<grid.length && c>=0 && c<grid[0].length && grid[r][c]==1 && visited[r][c]==false){
                connectedComponents(grid,r,c,visited);
            }
        }
    }

    public int numIslands(int[][] grid) {
        int islandCount = 0;
        boolean vis[][] = new boolean[grid.length][grid[0].length];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1 && vis[i][j] == false) {
                    islandCount++;
                    connectedComponents(grid, i, j, vis);
                }
            }
        }

        return islandCount;
    }

    public int minDays(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        //if more than 1 island is there then the graph is already disconnected
        if (numIslands(grid) > 1) {
            return 0;
        }

        //check for all 1's remove it and if there is more than 1 island then return 1
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    grid[i][j] = 0;

                    if (numIslands(grid) != 1) {
                        return 1;
                    }

                    //recorrect it while backtracking
                    grid[i][j] = 1;
                }
            }
        }

        //else we need min 2 removal
        return 2;
    }



    public static void dfs_numsIsland(int sr, int sc, int[][] grid, int[][] dir, boolean[][] vis) {
        vis[sr][sc] = true;

        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r < grid.length && c < grid[0].length && grid[r][c] == 1 && !vis[r][c]) {
                dfs_numsIsland(r, c, grid, dir, vis);
            }
        }
    }

    public static int numOfIslands(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        boolean[][] vis = new boolean[n][m];

        int noOfIslands = 0;
        int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, -1 }, { 0, 1 } };
        for (int i = 0; i < n * m; i++) {
            int r = i / m, c = i % m;
            if (!vis[r][c] && grid[r][c] == 1) {
                dfs_numsIsland(r, c, grid, dir, vis);
                noOfIslands++;
            }
        }

        return noOfIslands;
    }

    public int minDays_(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        int initialComponents = numOfIslands(grid);
        if (initialComponents > 1 || initialComponents == 0)
            return 0;

        for (int i = 0; i < n * m; i++) {
            int r = i / m, c = i % m;

            if (grid[r][c] == 1) {
                grid[r][c] = 0;
                int noOfComponents = numOfIslands(grid);
                if (noOfComponents > 1 || noOfComponents == 0)
                    return 1;
                grid[r][c] = 1;
            }
        }
        return 2;
    }

    // B Optimized using the articulation Point

    private static int[] low, disc;
    private static int time = 0;
    private static boolean[] vis;

    public static int dfs_size(int idx, int[][] grid, boolean[] vis) {
        int n = grid.length, m = grid[0].length;
        int sr = idx / m, sc = idx % m;

        vis[idx] = true;

        int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, -1 }, { 0, 1 } };
        int count = 0;

        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r < grid.length && c < grid[0].length && grid[r][c] == 1 && !vis[r * m + c]) {
                count += dfs_size(r * m + c, grid, vis);
            }
        }

        return count + 1;
    }

    public static boolean tarjans(int src, int par, int[][] grid) {
        int n = grid.length, m = grid[0].length;
        disc[src] = low[src] = time++;
        vis[src] = true;

        int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, -1 }, { 0, 1 } };

        boolean res = false;
        for (int d = 0; d < dir.length; d++) {
            int sr = src / m, sc = src % m;

            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 1) {
                int nbr = r * m + c;
                if (!vis[nbr]) {
                    res = res || tarjans(nbr, src, grid);
                    if (disc[src] < low[nbr]) { // Yahan pe equal to sign nhi kiya use. Why? ==> Kyunki hume cycle wale
                        // structure ke liye bhi true return karna tha kyunki wahan pe do vertex
                        // ko nikal ke graph disconnected ban sakta hai. Example is of a square,
                        // removing the diagonal vertex will make component disconnected.
                        return true;
                    }
                    low[src] = Math.min(low[nbr], low[src]);
                } else if (nbr != par) {
                    low[src] = Math.min(low[src], disc[nbr]);
                }
            }

        }
        return res;
    }

    public int minDays2(int[][] grid) {
        int n = grid.length, m = grid[0].length;

        disc = new int[n * m];
        low = new int[n * m];
        vis = new boolean[n * m];
        int root = -1;
        int noOfComponents = 0, size = 0;
        for (int i = 0; i < n * m; i++) {
            int r = i / m, c = i % m;

            if (grid[r][c] == 1 && !vis[i]) {
                root = i;
                size += dfs_size(i, grid, vis);
                noOfComponents++;
            }
        }

        if (noOfComponents == 0 || noOfComponents > 1) // Agar mera component 0 hai ya 1 ha se bada hai to mai already
            // disconnected hun, to 0 retun kardo
            return 0;
        else if (size <= 2) // Ab kyunki mai uper component ka check karke aaya hun to mai sure hun ki ab ek
            // he single component hai graph mai. To agar component ka size 1 ya 2 hua, to
            // ` utne he din lagte use disconnect karne mai jitna size hota.
            return size;

        vis = new boolean[n * m];
        boolean res = tarjans(root, -1, grid);
        return res ? 1 : 2; // Ab agar mujhe articulation point milta hai to mai to mai 1 return kardunga,
        // aur agar nhi milta hai to mai 2 return kardunga kyunki at most mera answer 2
        // ho sakta hai.

    }
}
