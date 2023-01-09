import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class leet57sol2 {
    public static void main(String[] args) {
        //You are given an array of non-overlapping intervals intervals where intervals[i] = [starti, endi] represent the start and the end of the ith interval and intervals is sorted in ascending order by starti. You are also given an interval newInterval = [start, end] that represents the start and end of another interval.
        //Insert newInterval into intervals such that intervals is still sorted in ascending order by starti and intervals still does not have any overlapping intervals (merge overlapping intervals if necessary).
        //Return intervals after the insertion.
        //Example 1:
        //Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
        //Output: [[1,5],[6,9]]
        //Example 2:
        //Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
        //Output: [[1,2],[3,10],[12,16]]
        //Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
        //Constraints:
        //0 <= intervals.length <= 104
        //intervals[i].length == 2
        //0 <= starti <= endi <= 105
        //intervals is sorted by starti in ascending order.
        //newInterval.length == 2
        //0 <= start <= end <= 105
        int[][] intervals={{1,3},{6,9}};
        int[] newintervals={2,5};
//        int[][] intervals={{1,2},{3,5},{6,7},{8,10},{12,16}};
//        int[] newintervals={4,8};
//        System.out.println(intervals.length);
        for(int i=0; i<intervals.length;i++){
            System.out.println(intervals[i]);
        }
//        for (int i : intervals[1]) {
//            System.out.println(i);
//        }
        //Runtime: 1 ms, faster than 99.76% of Java online submissions for Insert Interval.
        //Memory Usage: 44.5 MB, less than 95.93% of Java online submissions for Insert Interval.
    }
    public static int[][] insert(int[][] intervals, int[] newInterval) {
        if (newInterval == null || newInterval.length == 0) return intervals;

        List<int[]> merged = new LinkedList<>();
        int i = 0;
        // add not overlapping
        while (i < intervals.length && intervals[i][1] < newInterval[0]) {
            merged.add(intervals[i]);
            i++;
        }
        // add overlapping
        while (i < intervals.length && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(intervals[i][0], newInterval[0]);
            newInterval[1] = Math.max(intervals[i][1], newInterval[1]);
            i++;
        }//统一找完了，确定了最终的合并项以后再添加
        merged.add(newInterval);
        // add rest
        while (i < intervals.length) {
            merged.add(intervals[i]);
            i++;
        }

        //最后这里这样写也行
//        int[][] result=new int[merged.size()][];
//        for(int k=0; k< merged.size();k++){
//            result[k][0]=merged.get(k)[0];
//            result[k][1]=merged.get(k)[1];
//        }
//        return result;

        return merged.toArray(new int[merged.size()][]);
    }
}
