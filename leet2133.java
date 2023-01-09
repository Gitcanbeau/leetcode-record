import java.util.HashSet;

public class leet2133 {
    public static void main(String[] args) {
        //An n x n matrix is valid if every row and every column contains all the integers from 1 to n (inclusive).
        //Given an n x n integer matrix matrix, return true if the matrix is valid. Otherwise, return false.
        //Example 1:
        //Input: matrix = [[1,2,3],[3,1,2],[2,3,1]]
        //Output: true
        //Explanation: In this case, n = 3, and every row and column contains the numbers 1, 2, and 3.
        //Hence, we return true.
        //Example 2:
        //Input: matrix = [[1,1,1],[1,2,3],[1,2,3]]
        //Output: false
        //Explanation: In this case, n = 3, but the first row and the first column do not contain the numbers 2 or 3.
        //Hence, we return false.
        //Constraints:
        //n == matrix.length == matrix[i].length
        //1 <= n <= 100
        //1 <= matrix[i][j] <= n
//        int[][] matrix = {{1, 2, 3}, {3, 1, 2}, {2, 3, 1}};
        int[][] matrix = {{1, 1, 1}, {1, 2, 3}, {1, 2, 3}};
        System.out.println(checkValid(matrix));
        //Runtime: 35 ms, faster than 65.03% of Java online submissions for Check if Every Row and Column Contains All Numbers.
        //Memory Usage: 43.6 MB, less than 90.31% of Java online submissions for Check if Every Row and Column Contains All Numbers.
    }
    public static boolean checkValid(int[][] matrix) {
        int m=matrix.length;
        int n=matrix[0].length;
        for(int i=0; i<m; i++){
            HashSet<Integer> hs1=new HashSet<>();
            for (int j=0; j<n; j++){
                if(hs1.contains(matrix[i][j])){
                    return false;
                }else{
                    hs1.add(matrix[i][j]);
                }
            }
            hs1.clear();
        }

        for(int j=0; j<n; j++){
            HashSet<Integer> hs2=new HashSet<>();
            for(int i=0; i<m; i++){
                if(hs2.contains(matrix[i][j])){
                    return false;
                }else{
                    hs2.add(matrix[i][j]);
                }
            }
            hs2.clear();
        }

        return true;
    }
}
