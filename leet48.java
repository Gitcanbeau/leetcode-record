import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class leet48 {
    public static void main(String[] args) {
        //You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).
        //You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.
        //Example 1:
        //Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
        //Output: [[7,4,1],[8,5,2],[9,6,3]]
        //Example 2:
        //Input: matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
        //Output: [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
        //Constraints:
        //n == matrix.length == matrix[i].length
        //1 <= n <= 20
        //-1000 <= matrix[i][j] <= 1000
        int[][] matrix = {{5, 1, 9, 11}, {2, 4, 8, 10}, {13, 3, 6, 7}, {15, 14, 12, 16}};
        rotate(matrix);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(matrix[i][j] + ",");
            }
            System.out.println();
        }
        //Runtime: 5 ms, faster than 8.31% of Java online submissions for Rotate Image.
        //Memory Usage: 42 MB, less than 82.40% of Java online submissions for Rotate Image.
    }

    public static void rotate(int[][] matrix) {
        int m = matrix.length;
        int countofonecycle = m / 2;
        int left_col_start = m - 1;
        int left_col_end = 0;
        int top_row_start = 0;
        int top_row_end = m - 1;
        int right_col_start = 0;
        int right_col_end = m - 1;
        int down_row_start = m - 1;
        int down_row_end = 0;
        int c = 0;
        while (c < countofonecycle) {
            ArrayList<Integer> queue1=new ArrayList<>();
            ArrayList<Integer> queue2=new ArrayList<>();
            ArrayList<Integer> queue3=new ArrayList<>();
            ArrayList<Integer> queue4=new ArrayList<>();

            for (int i = left_col_start - c; i >= left_col_end + c; i--) {
                queue1.add(matrix[i][c]);
            }
            System.out.println(queue1.toString());
            for (int j = top_row_start + c; j <= top_row_end - c; j++) {
                queue2.add(matrix[c][j]);
            }
            System.out.println(queue2.toString());
            for (int i = right_col_start + c; i <= right_col_end - c; i++) {
                queue3.add(matrix[i][m - 1 - c]);
            }
            System.out.println(queue3.toString());
            for (int j = down_row_start - c; j >= down_row_end + c; j--) {
                queue4.add(matrix[m - 1 - c][j]);
            }
            System.out.println(queue4.toString());

            System.out.println(c);
            int k=0;
            for (int j = top_row_start + c; j <= top_row_end - c; j++) {
                matrix[c][j] = queue1.get(k);
                k++;
            }


            k=0;
            for (int i = right_col_start + c; i <= right_col_end - c; i++) {
                matrix[i][m - 1 - c] = queue2.get(k);
                k++;
            }


            k=0;
            for (int j = down_row_start - c; j >= down_row_end + c; j--) {
                matrix[m - 1 - c][j] = queue3.get(k);
                k++;
            }

            k=0;
            for (int i = left_col_start - c; i >= left_col_end + c; i--) {
                matrix[i][c] = queue4.get(k);
                k++;
            }

            c++;
        }

    }

}
