import java.util.Arrays;
import java.util.HashMap;

public class leet436sol3 {
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
//        int[] rightInterval = findRightInterval(intervals);
//        for (int i = 0; i < rightInterval.length; i++) {
//            System.out.print(rightInterval[i] + " ");
//        }
        //Runtime: 15 ms, faster than 94.48% of Java online submissions for Find Right Interval.
        //Memory Usage: 55.1 MB, less than 67.66% of Java online submissions for Find Right Interval.
    }

    public int[] findRightInterval(int[][] intervals) {
        int[] ans=new int[intervals.length];
        int idx=0;
        Pair[] intervals_=new Pair[intervals.length];
        for(int i=0;i<intervals.length;i++)
            intervals_[i]=new Pair(intervals[i][0],intervals[i][1],i);
        Arrays.sort(intervals_,(o1,o2)->o1.st-o2.st);

        for(int[] interval:intervals){
            int st=interval[0],end=interval[1];
            //NOW RUN BINARY SEARCH TO FIND OUT RIGHT INTERVAL INDEX FOR THIS INTERVAL
            int lo=0,hi=intervals.length-1;
            int rightidx=Integer.MAX_VALUE;
            while(lo<=hi){
                int mid=(lo+hi)/2;
                if(intervals_[mid].st>=end){
                    rightidx=Math.min(rightidx,intervals_[mid].ogidx);
                    hi=mid-1;
                }
                else
                    lo=mid+1;
            }
            if(rightidx==Integer.MAX_VALUE)
                rightidx=-1;
            ans[idx++]=rightidx;
        }
        return ans;

    }

    class Pair{
        int st,end,ogidx;  //ogidx->original index
        Pair(){}
        Pair(int x, int y, int z){
            this.st=x;
            this.end=y;
            this.ogidx=z;
        }
    }
}
