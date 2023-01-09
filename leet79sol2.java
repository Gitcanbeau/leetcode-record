import java.util.ArrayList;
import java.util.HashMap;

public class leet79sol2 {
    public static void main(String[] args) {
        //Given an m x n grid of characters board and a string word, return true if word exists in the grid.
        //The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.
        //Example 1:
        //Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
        //Output: true
        //Example 2:
        //Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
        //Output: true
        //Example 3:
        //Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
        //Output: false
        //Constraints:
        //m == board.length
        //n = board[i].length
        //1 <= m, n <= 6
        //1 <= word.length <= 15
        //board and word consists of only lowercase and uppercase English letters.
        char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        String word = "ABCCED";
        System.out.println(exist(board, word));
        //Runtime: 208 ms, faster than 36.59% of Java online submissions for Word Search.
        //Memory Usage: 42 MB, less than 71.12% of Java online submissions for Word Search.
    }

    public static boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (helper(board, i, j, word, 0)) {
                    return true;
                }
            }
        }
        return false;
    }


    public static boolean helper(char[][] board, int i, int j, String word, int count) {
        if (count == word.length()) return true;
        if (i < 0 || i >= board.length || j < 0 || j > board[0].length || word.charAt(count) != board[i][j])
            return false;
        char temp = board[i][j];
        board[i][j] = '*';
        boolean found = helper(board, i, j + 1, word, count + 1)
                || helper(board, i, j - 1, word, count + 1)
                || helper(board, i + 1, j, word, count + 1)
                || helper(board, i - 1, j, word, count + 1);
        board[i][j] = temp;
        return found;
    }
}
