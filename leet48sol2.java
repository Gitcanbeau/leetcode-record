import java.util.LinkedList;
import java.util.Queue;

public class leet48sol2 {
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
        int[][] matrix={{5,1,9,11},{2,4,8,10},{13,3,6,7},{15,14,12,16}};
        rotate(matrix);
        for(int i=0;i< matrix.length;i++){
            for(int j=0; j< matrix.length;j++){
                System.out.print(matrix[i][j]+",");
            }
            System.out.println();
        }
        //这人真他么聪明，烦躁
        //Runtime: 0 ms, faster than 100.00% of Java online submissions for Rotate Image.
        //Memory Usage: 43 MB, less than 36.65% of Java online submissions for Rotate Image.
    }
    public static void rotate(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;

        boolean[][] checked = new boolean[row][col];

        /*
			way to rotate:
				[x, y] -> [y, (col - 1) - x]
		*/

        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if (checked[i][j] == true) continue;
                int x = i;
                int y = j;
                int temp = matrix[i][j];  // first round;
                for(int r = 0; r < 4; r++) {//4edges
                    int cur = matrix[y][col - 1 - x];// keep the number that be swapped
                    matrix[y][col - 1 - x] = temp;
                    temp = cur;
                    checked[x][y] = true;
                    int nextX = y;
                    int nextY = col - x - 1;

                    x = nextX;
                    y = nextY;
                }
            }


        }
    }
}
