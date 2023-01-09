import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class leet1380 {
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
        //Runtime: 12 ms, faster than 5.59% of Java online submissions for Lucky Numbers in a Matrix.
        //Memory Usage: 50.8 MB, less than 5.86% of Java online submissions for Lucky Numbers in a Matrix.
    }

    public static List<Integer> luckyNumbers(int[][] matrix) {
        int n = matrix.length; //行数
        int m = matrix[0].length; //列数

        ArrayList<Integer> temp1 = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();


        for (int i = 0; i < n; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < m; j++) {
                min = Math.min(min, matrix[i][j]);
            }
            temp1.add(min);//存储每一行i的最小值
        }


        for (int j = 0; j < m; j++) {
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < n; i++) {
                max = Math.max(max, matrix[i][j]);
            }
            map.put(max, 1);//每一列的最大值作为key
        }

        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i < temp1.size(); i++) {
            if (map.containsKey(temp1.get(i))) //那我hashset就行了
                ans.add(temp1.get(i));
        }
        return ans;
    }
}
