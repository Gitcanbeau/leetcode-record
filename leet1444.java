public class leet1444 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/number-of-ways-of-cutting-a-pizza/
    }

    //Top down with memorization;
    //1: getSumRange of 2D matrix: Leetcode 304 how to find Range Sum Query 2D - Immutable
    //2: f[m][n][k]: # of ways to cut pizza [m:M][n:N] with k cuts
    //Normal case: try all posible cuts;
    //f[m][n][k]: sum(hasApple(left) * dfs(right, k - 1) + hasApple(top) * dfs(bottom, k - 1));
    //* 2.1. cut by horizonal: hasApple(left) * dfs(right, k - 1)
    //* 2.1 cut by vertical: hasApple(top) * dfs(bottom, k - 1)
    //3: hasApple() > 0 ? 1 : 0;
    //return dfs(0, 0, k - 1) // ways to cut pizza [0:M][0:N] with k cuts;
    //Time complexity: O(mnk) * O(m+n) Space complexity: O(mnk)

    private final int MOD = (int)1e9+7;
    private int[][] sum;
    private Integer[][][] cache;
    private int M, N;

    public int ways(String[] pizza, int k) {
        M = pizza.length;
        N = pizza[0].length();

        if (M == 0 || N == 0) {
            return 0;
        }

        sum = new int[M + 1][N + 1];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                sum[i + 1][j + 1] = sum[i + 1][j] + sum[i][j + 1]
                        + (pizza[i].charAt(j) == 'A' ? 1 : 0) - sum[i][j];
            }
        }

        // step2:
        cache = new Integer[M][N][k];

        return dfs(0, 0, k - 1);
    }

    private int hasApple(int x1, int y1, int x2, int y2) {
        int res = 0;
        res = sum[x2 + 1][y2 + 1] - sum[x2 + 1][y1] - sum[x1][y2 + 1] + sum[x1][y1];

        return res > 0 ? 1 : 0;
    }

    private int dfs(int m, int n, int k) {
        if (k == 0) {
            return hasApple(m, n, M - 1, N - 1);
        }
        if (cache[m][n][k] != null) {
            return cache[m][n][k];
        }

        int res = 0;
        // cut by horizontal
        for (int i = m; i < M - 1; i++) {
            res = (res + hasApple(m, n, i, N - 1) * dfs(i + 1, n, k - 1)) % MOD;
        }
        // cut by vertical
        for (int j = n; j < N - 1; j++) {
            res = (res + hasApple(m, n, M - 1, j) * dfs(m, j + 1, k - 1)) % MOD;
        }
        cache[m][n][k] = res;

        return cache[m][n][k];
    }
    private static int M2 = 1000000007;
    public static int ways2(String[] pizza, int k) {
        int m = pizza.length, n = pizza[0].length();
        long[][][] dp = new long[m][n][k + 1];
        int[][] grid = new int[m][n];
        int[][] cnt = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            String row = pizza[i];
            for (int j = 0; j < n; j++) {
                if (row.charAt(j) == 'A') {
                    grid[i][j] = 1;
                }
            }
        }
        for (int i = m - 1; i >= 0; --i) {
            for (int j = n - 1; j >= 0; --j) {
                cnt[i][j] = cnt[i + 1][j] + grid[i][j] + cnt[i][j + 1] - cnt[i + 1][j + 1];
            }
        }
        dfs(grid, cnt, dp, k, 0, 0);
        return (int) dp[0][0][k];
    }

    private static void dfs(int[][] grid, int[][] cnt, long[][][] dp, int k, int x, int y) {
        if (k == 1 && cnt[x][y] > 0) {
            dp[x][y][k] = 1;
            return;
        }
        if (cnt[x][y] < k || k < 1) {
            return;
        }
        if (dp[x][y][k] != 0) {
            return;
        }
        int m = grid.length, n = grid[0].length;
        for (int i = x + 1; i < m; i++) {
            if (cnt[x][y] - cnt[i][y] == 0) {
                continue;
            }
            dfs(grid, cnt, dp, k - 1, i, y);
            dp[x][y][k] += dp[i][y][k - 1];
        }
        for (int j = y + 1; j < n; j++) {
            if (cnt[x][y] - cnt[x][j] == 0) {
                continue;
            }
            dfs(grid, cnt, dp, k - 1, x, j);
            dp[x][y][k] += dp[x][j][k - 1];
        }
        dp[x][y][k] %= M2;
    }
}
