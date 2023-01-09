import java.util.Arrays;
import java.util.LinkedList;

public class leet57sol3 {
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
        //Runtime: 4 ms, faster than 23.04% of Java online submissions for Insert Interval.
        //Memory Usage: 48.3 MB, less than 8.38% of Java online submissions for Insert Interval.
    }
    public static int[][] insert(int[][] intervals, int[] newInterval) {
        int n = intervals.length;
        LinkedList<int[]> merged = new LinkedList<int[]>();

        int idx = 0;
        while(idx < n && newInterval[0] > intervals[idx][1]) {
            merged.add(intervals[idx]);
            idx++;
        }
        merged.add(newInterval);
        while(idx < n) {
            if(merged.getLast()[1] >= intervals[idx][0]) {
                int []newI = merged.removeLast();
                newI[0] = Math.min(newI[0], intervals[idx][0]);
                newI[1] = Math.max(newI[1], intervals[idx][1]);
                merged.add(newI);
            }
            else {
                merged.add(intervals[idx]);
            }
            idx++;
        }
        return merged.toArray(new int[merged.size()][2]);
    }

    private void print(int [][]m) {
        System.out.printf("\n");
        for(int [] row: m) {
            System.out.printf("%s\t", Arrays.toString(row));
        }
    }
}
