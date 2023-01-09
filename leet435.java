import java.util.ArrayList;
import java.util.Arrays;

public class leet435 {
    public static void main(String[] args) {
        //Given an array of intervals intervals where intervals[i] = [starti, endi], return the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.
        //Example 1:
        //Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
        //Output: 1
        //Explanation: [1,3] can be removed and the rest of the intervals are non-overlapping.
        //Example 2:
        //Input: intervals = [[1,2],[1,2],[1,2]]
        //Output: 2
        //Explanation: You need to remove two [1,2] to make the rest of the intervals non-overlapping.
        //Example 3:
        //Input: intervals = [[1,2],[2,3]]
        //Output: 0
        //Explanation: You don't need to remove any of the intervals since they're already non-overlapping.
        //Constraints:
        //1 <= intervals.length <= 105
        //intervals[i].length == 2
        //-5 * 104 <= starti < endi <= 5 * 104
//        int[][] intervals={{1,2},{2,3},{3,4},{1,3}};
//        int[][] intervals = {{1, 2}, {1, 2}, {1, 2}};
        int[][] intervals={{1,2},{2,3}};
        System.out.println(eraseOverlapIntervals(intervals));
        //Runtime: 87 ms, faster than 63.60% of Java online submissions for Non-overlapping Intervals.
        //Memory Usage: 100.4 MB, less than 52.15% of Java online submissions for Non-overlapping Intervals.
        //自己想出来的，我是聪明宝
    }

    public static int eraseOverlapIntervals(int[][] intervals) {
        if(intervals.length<=1){
            return intervals.length;
        }
        ArrayList<int[]> arrlst1 = new ArrayList<>();
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        int leftValue = intervals[0][0];
        int rightValue = intervals[0][1];
        int i = 0;
        while (i < intervals.length - 1) {
            if(isOverlapped(intervals[i], intervals[i + 1])) {
                intervals[i + 1][0] = Math.max(intervals[i][0], intervals[i + 1][0]);
                intervals[i + 1][1] = Math.min(intervals[i][1], intervals[i + 1][1]);
                i++;
                if (i == intervals.length - 1) {
                    arrlst1.add(intervals[i]);
                    break;
                }
            }

            if(!isOverlapped(intervals[i], intervals[i + 1])) {
                arrlst1.add(intervals[i]);
                i++;
                if (i == intervals.length - 1) {
                    arrlst1.add(intervals[i]);
                }
            }

        }
        int count = intervals.length - arrlst1.size();
        return count;
    }

    public static boolean isOverlapped(int[] a, int[] b) {
        if (a[1] > b[0]) {
            return true;
        } else {
            return false;
        }
    }
}
