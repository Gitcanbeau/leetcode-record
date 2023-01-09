import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class leet57 {
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
        int[][] intervals = {{1, 3}, {6, 9}};
        int[] newintervals = {2, 5};
//        int[][] intervals={{1,2},{3,5},{6,7},{8,10},{12,16}};
//        int[] newintervals={4,8};
//        System.out.println(intervals.length);
        for (int i = 0; i < intervals.length; i++) {
            System.out.println(intervals[i]);
        }
//        for (int i : intervals[1]) {
//            System.out.println(i);
//        }
        int[][] insert = insert(intervals, newintervals);
        for (int[] arr : insert) {
            System.out.println(arr);
        }
        //自己写不明白，讨论的情况太多了
    }


    public static int[][] insert(int[][] intervals, int[] newInterval) {


        ArrayList<Integer> arrlst1 = new ArrayList<>();
        ArrayList<Integer> arrlst2 = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            arrlst1.add(intervals[i][0]);
            arrlst2.add(intervals[i][1]);
        }
        int leftendindex = 0;
        int leftendvalue = 0;

        for (int i = 0; i < arrlst1.size(); i++) {
            if (newInterval[0] >= arrlst1.get(i) && newInterval[0] <= arrlst2.get(i)) { //右边界落在一个区间
                leftendvalue = arrlst1.get(i);
                leftendindex = i;
            }else if(newInterval[0] > arrlst1.get(i) && newInterval[1] < arrlst1.get(i+1)){ //整个新区间

            }
        }

        int rightendindex = 0;
        int rightendvalue = 0;
        for (int i = 0; i < arrlst2.size(); i++) {
            if (newInterval[1] <= arrlst2.get(i) && newInterval[1] >= arrlst1.get(i)) {
                rightendvalue = arrlst2.get(i);
                rightendindex = i;
            }
        }

        int disappearnum = rightendindex - leftendindex;
        int newcount = intervals.length - disappearnum;
        int[][] intervals2 = new int[newcount][2];
        for (int i = 0; i < newcount; i++) {
            if (i < leftendindex) {
                intervals2[i][0] = arrlst1.get(i);
                intervals2[i][1] = arrlst2.get(i);
            } else if (i == leftendindex) {
                intervals2[i][0] = leftendvalue;
                intervals2[i][1] = rightendvalue;
            } else {
                intervals[i][0] = arrlst1.get(i + disappearnum);
                intervals2[i][1] = arrlst2.get(i + disappearnum);
            }
        }
        return intervals2;
    }
}

