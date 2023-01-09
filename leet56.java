import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class leet56{
    public static void main(String[] args) {
        //Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.
        //Example 1:
        //Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
        //Output: [[1,6],[8,10],[15,18]]
        //Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
        //Example 2:
        //Input: intervals = [[1,4],[4,5]]
        //Output: [[1,5]]
        //Explanation: Intervals [1,4] and [4,5] are considered overlapping.
        //Constraints:
        //1 <= intervals.length <= 104
        //intervals[i].length == 2
        //0 <= starti <= endi <= 104
//        int[][] intervals={{1,3},{2,6},{8,10},{15,18}};
//        int[][] intervals={{1,4},{5,6}};
//        int[][] intervals={{1,4},{0,0}};
        int[][] intervals={{2,3},{2,2},{3,3},{1,3},{5,7},{2,2},{4,6}};
        int[][] merge = merge(intervals);
        for(int[] arr: merge){
           for(int i: arr){
               System.out.print(i+" ");
           }
        }
        //不能用while循环，因为你的不知道里面多少overlapping的，你处理一次overlapping以后就出这个模块了，你要用for-loop+if-else
        //57leet能用while因为人家拍完序列了，new interval也只有一个，做一次判断就行
    }


    public static int[][] merge(int[][] intervals) {
        if(intervals.length<=1){
            return intervals;
        }

        //先排序
        Arrays.sort(intervals,(a,b)->Integer.compare(a[0],b[0]));

        ArrayList<int[]> arrlst1=new ArrayList<>();
        int i=0;
        while(i< intervals.length-1 && intervals[i][1]<intervals[i+1][0]){
            arrlst1.add(new int[]{intervals[i][0],intervals[i][1]});
            i++;
        }


        while(i<intervals.length-1 && intervals[i][1]>=intervals[i+1][0]){
            intervals[i+1][0]=Math.min(intervals[i][0], intervals[i+1][0]);
            intervals[i+1][1]=Math.max(intervals[i][1], intervals[i+1][1]);
            i++;
        }
        arrlst1.add(new int[]{intervals[i][0],intervals[i][1]});//这里是i

        while(i< intervals.length-1 && intervals[i][1]<intervals[i+1][0]){
            arrlst1.add(new int[]{intervals[i+1][0],intervals[i+1][1]});
            i++;
        }

        int[][] result=new int[arrlst1.size()][2];
        for(int k=0; k<arrlst1.size();k++){
            result[k][0]=arrlst1.get(k)[0];
            result[k][1]=arrlst1.get(k)[1];
        }
        return result;
    }
}
