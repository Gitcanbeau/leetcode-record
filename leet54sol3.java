import java.util.ArrayList;
import java.util.List;

public class leet54sol3 {
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
            //Runtime: 0 ms, faster than 100.00% of Java online submissions for Spiral Matrix.
        //Memory Usage: 42.4 MB, less than 27.77% of Java online submissions for Spiral Matrix.
    }
    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer>list=new ArrayList<>();
        int n=matrix.length;
        int m=matrix[0].length;
        int top=0;
        int right=m-1;
        int down=n-1;
        int left=0;
        int dir=0;

        while(top<=down && left<=right){
            if(dir==0){
                for(int i=left;i<=right;i++)
                    list.add(matrix[top][i]);
                top++;
            }
            else if(dir==1){
                for(int i=top;i<=down;i++)
                    list.add(matrix[i][right]);
                right--;
            }
            else if(dir==2){
                for(int i=right;i>=left;i--)
                    list.add(matrix[down][i]);
                down--;
            }
            else{
                for(int i=down;i>=top;i--)
                    list.add(matrix[i][left]);
                left++;
            }
            dir=(dir+1)%4;
        }
        return list;
    }
}
