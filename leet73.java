import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

public class leet73 {
    public static void main(String[] args) {
        //Given an m x n integer matrix matrix, if an element is 0, set its entire row and column to 0's.
        //You must do it in place.
        //Example 1:
        //Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
        //Output: [[1,0,1],[0,0,0],[1,0,1]]
        //Example 2:
        //Input: matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
        //Output: [[0,0,0,0],[0,4,5,0],[0,3,1,0]]
        //Constraints:
        //m == matrix.length
        //n == matrix[0].length
        //1 <= m, n <= 200
        //-231 <= matrix[i][j] <= 231 - 1
        //Follow up:
        //A straightforward solution using O(mn) space is probably a bad idea.
        //A simple improvement uses O(m + n) space, but still not the best solution.
        //Could you devise a constant space solution?
//        int[][] matrix={{1,1,1},{1,0,1},{1,1,1}};
        int[][] matrix={{0,1,2,0},{3,4,5,2},{1,3,1,5}};
        setZeroes(matrix);
        for(int[] arr: matrix){
            for(int i: arr){
                System.out.print(i);
            }
            System.out.println();
        }
        //Runtime: 2 ms, faster than 47.35% of Java online submissions for Set Matrix Zeroes.
        //Memory Usage: 43.7 MB, less than 97.43% of Java online submissions for Set Matrix Zeroes.
    }
    public static void setZeroes(int[][] matrix) {
        int m =matrix.length;
        int n=matrix[0].length;
        HashSet<Integer> zeroatrownum=new HashSet<>();
        HashSet<Integer> zeroatcolnum=new HashSet<>();
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(matrix[i][j]==0){
                    zeroatrownum.add(i);
                    zeroatcolnum.add(j);
                }
            }
        }
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(zeroatrownum.contains(i) || zeroatcolnum.contains(j)){
                    matrix[i][j]=0;
                }
            }
        }

    }

}
