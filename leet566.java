import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.SynchronousQueue;

public class leet566 {
    public static void main(String[] args) {
        //In MATLAB, there is a handy function called reshape which can reshape an m x n matrix into a new one with a different size r x c keeping its original data.
        //You are given an m x n matrix mat and two integers r and c representing the number of rows and the number of columns of the wanted reshaped matrix.
        //The reshaped matrix should be filled with all the elements of the original matrix in the same row-traversing order as they were.
        //If the reshape operation with given parameters is possible and legal, output the new reshaped matrix; Otherwise, output the original matrix.
        //Example 1:
        //Input: mat = [[1,2],[3,4]], r = 1, c = 4
        //Output: [[1,2,3,4]]
        //Example 2:
        //Input: mat = [[1,2],[3,4]], r = 2, c = 4
        //Output: [[1,2],[3,4]]
        //Constraints:
        //m == mat.length
        //n == mat[i].length
        //1 <= m, n <= 100
        //-1000 <= mat[i][j] <= 1000
        //1 <= r, c <= 300
        int[][] mat={{1,2},{3,4}};
        int r=1;
        int c=4;
        int[][] ints = matrixReshape(mat, r, c);
        for(int[] arr: ints){
            for(int i: arr){
                System.out.println(i);
            }
        }
        //Runtime: 6 ms, faster than 7.97% of Java online submissions for Reshape the Matrix.
        //Memory Usage: 51.1 MB, less than 18.41% of Java online submissions for Reshape the Matrix.
    }
    public static int[][] matrixReshape(int[][] mat, int r, int c) {
        int m= mat.length;
        int n=mat[0].length;
        if(m*n!=r*c){
            return mat;
        }

        Queue<Integer> queue1=new LinkedList<>();
        for(int i=0; i<m;i++){
            for(int j=0; j<n;j++){
                queue1.add(mat[i][j]);
            }
        }
        int[][] matrix = new int[r][c];
        for(int i=0; i<r;i++){
            for(int j=0; j<c;j++){
                matrix[i][j]=queue1.poll();
            }
        }

        return matrix;
    }
}
