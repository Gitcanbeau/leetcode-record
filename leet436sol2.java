import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class leet436sol2 {
    public static void main(String[] args) {
        //You are given an array of intervals, where intervals[i] = [starti, endi] and each starti is unique.
        //The right interval for an interval i is an interval j such that startj >= endi and startj is minimized. Note that i may equal j.
        //Return an array of right interval indices for each interval i. If no right interval exists for interval i, then put -1 at index i.
        //Example 1:
        //Input: intervals = [[1,2]]
        //Output: [-1]
        //Explanation: There is only one interval in the collection, so it outputs -1.
        //Example 2:
        //Input: intervals = [[3,4],[2,3],[1,2]]
        //Output: [-1,0,1]
        //Explanation: There is no right interval for [3,4].
        //The right interval for [2,3] is [3,4] since start0 = 3 is the smallest start that is >= end1 = 3.
        //The right interval for [1,2] is [2,3] since start1 = 2 is the smallest start that is >= end2 = 2.
        //Example 3:
        //Input: intervals = [[1,4],[2,3],[3,4]]
        //Output: [-1,2,-1]
        //Explanation: There is no right interval for [1,4] and [3,4].
        //The right interval for [2,3] is [3,4] since start2 = 3 is the smallest start that is >= end1 = 3.
        //Constraints:
        //1 <= intervals.length <= 2 * 104
        //intervals[i].length == 2
        //-106 <= starti <= endi <= 106
        //The start point of each interval is unique.
//        int[][] intervals={{1,2}};
//        int[][] intervals = {{3, 4}, {2, 3}, {1, 2}};
//        int[][] intervals={{1,4},{2,3},{3,4}};
//        int[][] intervals={{1,2},{2,3},{0,1},{3,4}};
        int[][] intervals={{2,3},{1,2}};
        int[] rightInterval = findRightInterval(intervals);
        for (int i = 0; i < rightInterval.length; i++) {
            System.out.print(rightInterval[i] + " ");
        }
        //time limit exceeded
    }

    public static int[] findRightInterval(int[][] intervals) {
        if (intervals.length == 1) {
            return new int[]{-1};
        }

        int[] res = new int[intervals.length];
        Arrays.fill(res, -1);
        HashMap<Integer, Integer> hm1 = new HashMap<>();
        int[] storeallleftend=new int[intervals.length];

        for (int i = 0; i < intervals.length; i++) {
            hm1.put(intervals[i][0], i);
            storeallleftend[i]=intervals[i][0];
        }

        //sorting all left end
        Arrays.sort(storeallleftend);
        for(int i=0; i < intervals.length; i++){
            int j=0;
            while(j<storeallleftend.length){
                if(hm1.get(storeallleftend[j])!=i && storeallleftend[j]>=intervals[i][1]) {
                    res[i] = hm1.get(storeallleftend[j]);
                    break;
                }
                j++;
            }
        }

        return res;
    }
}
