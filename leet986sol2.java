import java.util.ArrayList;
import java.util.List;

public class leet986sol2 {
    public static void main(String[] args) {
        //You are given two lists of closed intervals, firstList and secondList, where firstList[i] = [starti, endi] and secondList[j] = [startj, endj]. Each list of intervals is pairwise disjoint and in sorted order.
        //
        //Return the intersection of these two interval lists.
        //
        //A closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b.
        //
        //The intersection of two closed intervals is a set of real numbers that are either empty or represented as a closed interval. For example, the intersection of [1, 3] and [2, 4] is [2, 3].
        //
        //
        //
        //Example 1:
        //
        //
        //Input: firstList = [[0,2],[5,10],[13,23],[24,25]], secondList = [[1,5],[8,12],[15,24],[25,26]]
        //Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
        //Example 2:
        //
        //Input: firstList = [[1,3],[5,9]], secondList = []
        //Output: []
        //Constraints:
        //0 <= firstList.length, secondList.length <= 1000
        //firstList.length + secondList.length >= 1
        //0 <= starti < endi <= 109
        //endi < starti+1
        //0 <= startj < endj <= 109
        //endj < startj+1
//        int[][] firstList = {{0, 2}, {5, 10}, {13, 23}, {24, 25}};
//        int[][] secondList = {{1, 5}, {8, 12}, {15, 24}, {25, 26}};
//        int[][] firstList ={{1,3},{5,9}};;
//        int[][] secondList ={{}};
//        int[][] firstList ={{14,16}};
//        int[][] secondList ={{7,13},{16,20}};
        int[][] firstList ={{1,8},{16,20}};
        int[][] secondList ={{2,11},{12,13}};
        int[][] ints = intervalIntersection(firstList, secondList);
//        boolean isnull= ints==null;
//        System.out.println(isnull);
        for (int[] arr : ints) {
            for (int i : arr) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
        //为什么人家写代码这么简洁啊，我好像不会写代码。。
        //Runtime: 5 ms, faster than 63.09% of Java online submissions for Interval List Intersections.
        //Memory Usage: 54.7 MB, less than 56.41% of Java online submissions for Interval List Intersections.
    }

    public static int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        int n = firstList.length;
        int m = secondList.length;
        List<int[]> res = new ArrayList<>();

        if (n == 0 || m == 0) {
            return res.toArray(new int[res.size()][]);
        }

        int i = 0;
        int j = 0;
        while (i < n && j < m) {
            if (firstList[i][1] <= secondList[j][1]) {
                if (firstList[i][1] >= secondList[j][0]) {
                    int val = Math.max(firstList[i][0], secondList[j][0]);
                    res.add(new int[] {val, firstList[i][1]});
                }
                i++;//如果我的一个区间一直都碰不到另一个区间的开头，那我就一直动一直动直到我这个list没有了或者终于有交集了
            } else {
                if (secondList[j][1] >= firstList[i][0]) {
                    int val = Math.max(firstList[i][0], secondList[j][0]);
                    res.add(new int[] {val, secondList[j][1]});
                }
                j++;
            }
        }

        return res.toArray(new int[res.size()][]);
    }
}
