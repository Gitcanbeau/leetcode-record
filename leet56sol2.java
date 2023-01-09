import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class leet56sol2 {
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
        int[][] intervals={{1,4},{0,0}};
        int[][] merge = merge(intervals);
        for(int[] arr: merge){
            for(int i: arr){
                System.out.print(i+" ");
            }
        }
        //Runtime: 14 ms, faster than 56.94% of Java online submissions for Merge Intervals.
        //Memory Usage: 55.4 MB, less than 43.73% of Java online submissions for Merge Intervals.
    }


    public static int[][] merge(int[][] intervals) {
        //那么我按照左边界排序，排序之后局部最优：每次合并都取最大的右边界，这样就可以合并更多的区间了，整体最优：合并所有重叠的区间。
        List<int[]> result = new ArrayList();
        //sort according to first index
        //without sorting, we would receive wrong output
        //using 2 parameters of sort method because its a 2d array
        Arrays.sort(intervals, (a,b) -> {return a[0]-b[0];});
        int leftValue = intervals[0][0];
        int rightValue = intervals[0][1];
        for(int interval[] : intervals){
            //if overlapping, change the right value
            if(interval[0]<=rightValue){
                rightValue = Math.max(rightValue,interval[1]);
            } else {
                //if not overlapping, add left and right values to result
                //change left and right values to current values
                result.add(new int[]{leftValue, rightValue});
                leftValue = interval[0];
                rightValue = interval[1];
            }
        }
        //last else check is never executed so we add one more
        //最后一组只参与比较大小了，更新了leftValue和rightValue 但是并没有加进result去
        result.add(new int[]{leftValue, rightValue});
        return result.toArray(new int[result.size()][]);
    }
}
