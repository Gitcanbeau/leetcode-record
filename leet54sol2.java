import java.util.ArrayList;
import java.util.List;

public class leet54sol2 {
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
        //Memory Usage: 40.6 MB, less than 86.52% of Java online submissions for Spiral Matrix.
    }
    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> lst = new ArrayList<>();
        int m = matrix.length;
        int n = matrix[0].length;
        int c=0;
        if((n+1)/2 > (m+1)/2){
            c=(m+1)/2;
        }else{
            c=(n+1)/2;
        }
        // fr lc, lr fc
        // first row
        int fr_start =0; //0
        int fr_end =n-1; // n-1
        // last column
        int lc_start =1;
        int lc_end =m-2;
        //last row
        int lr_start =n-1;
        int lr_end =0;
        //first column
        int fc_start =m-2;
        int fc_end =1;
        for(int i =0;i<c;i++){

            // first row
            for(int j=fr_start;j<=fr_end;j++){
                lst.add(matrix[i][j]);
            }
            fr_start +=1; //0
            fr_end -=1; // n-1

            // last column
            for(int j=lc_start;j<=lc_end;j++){
                if(i == lc_end +1 || i == lc_end){
                    break;
                }
                lst.add(matrix[j][n-i-1]);
            }
            lc_start +=1;
            lc_end -=1;

            //last row
            for(int j=lr_start;j>=lr_end;j--){
                if(i == lc_end +1 +1){ // changing by 1 in this loop
                    break;
                }
                lst.add(matrix[m-i-1][j]);
            }
            lr_start -=1;
            lr_end +=1;

            //first column
            for(int j=fc_start;j>=fc_end;j--){
                if(i == lc_end +1+1 || i == lc_end +1 || i == fr_end +1){ // changing by 1 in this loop
                    break;
                }
                lst.add(matrix[j][i]);
            }
            fc_start -=1;
            fc_end +=1;
        }

        return lst;
    }
}
