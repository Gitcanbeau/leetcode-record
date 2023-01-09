import java.util.Arrays;

public class leet435sol2 {
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
        //Runtime: 76 ms, faster than 81.79% of Java online submissions for Non-overlapping Intervals.
        //Memory Usage: 106.3 MB, less than 6.90% of Java online submissions for Non-overlapping Intervals.
    }
    public static int eraseOverlapIntervals(int[][] intervals) {
        if(intervals == null || intervals.length == 0) return 0;

        int rangeEnd = Integer.MIN_VALUE;
        int prevRangeEnd = Integer.MIN_VALUE;
        int result = 0;

        Arrays.sort(intervals, (a, b)-> a[0]-b[0]);
        rangeEnd = intervals[0][1];

        for(int i = 1; i < intervals.length; i++){
            prevRangeEnd = rangeEnd;

            if(rangeEnd > intervals[i][0]){
                result+=1;
                rangeEnd = Math.min(intervals[i][1], prevRangeEnd);
            }
            else {
                rangeEnd = intervals[i][1];
            }
        }
        return result;
    }


    public static int eraseOverlapIntervals2(int[][] intervals) {
        //总结如下难点：

        //难点一：一看题就有感觉需要排序，但究竟怎么排序，按左边界排还是右边界排。
        //难点二：排完序之后如何遍历，如果没有分析好遍历顺序，那么排序就没有意义了。
        //难点三：直接求重复的区间是复杂的，转而求最大非重复区间个数。
        //难点四：求最大非重复区间个数时，需要一个分割点来做标记。
        ////右边界排序之后，局部最优：优先选右边界小的区间，所以从左向右遍历，留给下一个区间的空间大一些，从而尽量避免交叉。全局最优：选取最多的非交叉区间
        Arrays.sort(intervals, (a, b) -> {
            // 按照区间右边界升序排序
            return a[1] - b[1];
        });

        int count = 0;
        int edge = Integer.MIN_VALUE;
        for (int i = 0; i < intervals.length; i++) {
            // 若上一个区间的右边界小于当前区间的左边界，说明无交集
            if (edge <= intervals[i][0]) {
                edge = intervals[i][1];
            } else {
                count++;
            }
        }

        return count;
    }
}
