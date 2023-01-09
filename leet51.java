import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class leet51 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/n-queens/
        int n=4;
        System.out.println(solveNQueens(n));
    }
    //这里我明确给出了棋盘的宽度就是for循环的长度，递归的深度就是棋盘的高度，这样就可以套进回溯法的模板里了


    public static List<List<String>> solveNQueens(int n) {
        //Runtime: 2 ms, faster than 98.36% of Java online submissions for N-Queens.
        //Memory Usage: 42.4 MB, less than 94.52% of Java online submissions for N-Queens.
        List<List<String>> res = new ArrayList<>();
        char[][] chessboard = new char[n][n];
        for (char[] c : chessboard) {
            Arrays.fill(c, '.');
        }
        backTrack(n, res,0, chessboard);
        return res;
    }


    public static void backTrack(int n, List<List<String>> res, int row, char[][] chessboard) {
        if (row == n) {
            res.add(Array2List(chessboard));
            return;
        }

        for (int col = 0;col < n; ++col) {
            if (isValid (row, col, n, chessboard)) {
                chessboard[row][col] = 'Q';
                backTrack(n, res,row+1, chessboard);
                chessboard[row][col] = '.';
            }
        }

    }


    public static List Array2List(char[][] chessboard) {
        List<String> list = new ArrayList<>();

        for (char[] c : chessboard) {
            list.add(String.copyValueOf(c));
        }
        return list;
    }


    public static boolean isValid(int row, int col, int n, char[][] chessboard) {
        // 检查列
        for (int i=0; i<row; ++i) { // 相当于剪枝
            if (chessboard[i][col] == 'Q') {
                return false;
            }
        }

        // 检查45度对角线
        for (int i=row-1, j=col-1; i>=0 && j>=0; i--, j--) {
            if (chessboard[i][j] == 'Q') {
                return false;
            }
        }

        // 检查135度对角线
        for (int i=row-1, j=col+1; i>=0 && j<=n-1; i--, j++) {
            if (chessboard[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }

}
