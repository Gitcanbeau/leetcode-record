import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class leet436sol4 {
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
        //Runtime: 27 ms, faster than 64.02% of Java online submissions for Find Right Interval.
        //Memory Usage: 56.3 MB, less than 37.31% of Java online submissions for Find Right Interval.
    }
    public static int[] findRightInterval(int[][] intervals) {
        int n = intervals.length;
        int[] arr = new int[n];
        int[] ans = new int[n];
        for(int i = 0; i < n; ++i)
            arr[i] = intervals[i][0];
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0; i < n; ++i)
            map.put(arr[i],i);
        Arrays.sort(arr);
        for(int i = 0; i < n; ++i){
            int val = binarySearch(arr,intervals[i][1]);
            if(val == -1)
                ans[i] = -1;
            else
                ans[i] = map.get(arr[val]);
        }
        return ans;
    }
    public static int binarySearch(int[] arr,int target){ //返回的是合格的左端点值在 sorted arr中的index
        int l = 0;
        int r = arr.length - 1;
        int ans = -1;
        while(l <= r){
            int mid = l + (r - l)/2;
            if(arr[mid] == target)
                return mid;
            else if(arr[mid] > target){
                ans = mid;
                r = mid - 1;
            }
            else
                l = mid + 1;
        }
        return ans;
    }
}
