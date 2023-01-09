import java.util.ArrayList;
import java.util.List;

public class leet1380sol2 {
    public static void main(String[] args) {
        //Given an m x n matrix of distinct numbers, return all lucky numbers in the matrix in any order.
        //A lucky number is an element of the matrix such that it is the minimum element in its row and maximum in its column.
        //Example 1:
        //Input: matrix = [[3,7,8],[9,11,13],[15,16,17]]
        //Output: [15]
        //Explanation: 15 is the only lucky number since it is the minimum in its row and the maximum in its column.
        //Example 2:
        //Input: matrix = [[1,10,4,2],[9,3,8,7],[15,16,17,12]]
        //Output: [12]
        //Explanation: 12 is the only lucky number since it is the minimum in its row and the maximum in its column.
        //Example 3:
        //Input: matrix = [[7,8],[1,2]]
        //Output: [7]
        //Explanation: 7 is the only lucky number since it is the minimum in its row and the maximum in its column.
        //Constraints:
        //m == mat.length
        //n == mat[i].length
        //1 <= n, m <= 50
        //1 <= matrix[i][j] <= 105.
        //All elements in the matrix are distinct.
        int[][] matrix = {{3, 7, 8}, {9, 11, 13}, {15, 16, 17}};
        System.out.println(matrix[1][1]);
        List<Integer> integers = luckyNumbers(matrix);
        System.out.println(integers);
        //    Runtime: 2 ms, faster than 87.95% of Java online submissions for Lucky Numbers in a Matrix.
        //    Memory Usage: 50 MB, less than 59.39% of Java online submissions for Lucky Numbers in a Matrix.
    }

    public static List<Integer> luckyNumbers(int[][] matrix) {
        ArrayList<Integer> al = new ArrayList<>();

        for (int[] arr : matrix) {

            int col = checkMinInRow(arr);
            int max = Integer.MIN_VALUE;

            for (int r = 0; r < matrix.length; r++) {

                if (matrix[r][col] > max) {
                    max = matrix[r][col];

                }

            }

            if (max == arr[col]) al.add(max);


        }

        return al;

    }

    public static int checkMinInRow(int[] row) {

        int min = Integer.MAX_VALUE;
        int col = 0;
        for (int i = 0; i < row.length; i++) {

            if (row[i] < min) {
                min = row[i];
                col = i;
            }
        }

        return col;

    }
}
