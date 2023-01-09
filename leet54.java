import java.util.ArrayList;
import java.util.List;

public class leet54 {
    public static void main(String[] args) {
        //Given an m x n matrix, return all elements of the matrix in spiral order.
        //Example 1:
        //Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
        //Output: [1,2,3,6,9,8,7,4,5]
        //Example 2:
        //Input: matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
        //Output: [1,2,3,4,8,12,11,10,9,5,6,7]
        //Constraints:
        //m == matrix.length
        //n == matrix[i].length
        //1 <= m, n <= 10
        //-100 <= matrix[i][j] <= 100

        int[][] matrix={{1,2,3},{4,5,6},{7,8,9}};
        List<Integer> integers = spiralOrder(matrix);
        System.out.println(integers);
        //思路对但是边界条件不对劲，应该重写或者再看看
    }
    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list1=new ArrayList<>();
        int m=matrix.length;
        int n=matrix[0].length;
        int mini=0;
        int nini=0;
        int i=0;
        int j=0;
        while(m>=mini && n>=nini){
            while(j<n){
                list1.add(matrix[i][j]);
                j++;
            }
            mini++;
            j--;
            i++;
            while (i < m) {
                list1.add(matrix[i][j]);
                i++;
            }
            m=m-1;
            i--;
            j--;
            while(j>=nini){
                list1.add(matrix[i][j]);
                j--;
            }
            n=n-1;
            j++;
            i--;
            while(i>=mini){
                list1.add(matrix[i][j]);
                i--;
            }
            nini--;
            i++;
            j++;
        }
        return list1;
    }

}
