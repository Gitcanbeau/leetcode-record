import java.util.ArrayList;

public class leet986 {
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
        //注意细节,思路不难想，处处是小细节
        //Runtime: 5 ms, faster than 63.09% of Java online submissions for Interval List Intersections.
        //Memory Usage: 55.3 MB, less than 5.91% of Java online submissions for Interval List Intersections.
    }

    public static int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        if(firstList.length== 1 && firstList[0].length==0){
            return null;
        }
        if(secondList.length== 1 && secondList[0].length==0){
            return null;
        }


        ArrayList<int[]> arrlst1 = new ArrayList<>();
        int firstpointer = 0;
        int secondpointer = 0;
        while (firstpointer < firstList.length && secondpointer < secondList.length) {
            if (!isjoint(firstList[firstpointer], secondList[secondpointer])) {
                if(firstpointer < firstList.length-1 && secondpointer < secondList.length-1){
                    if (firstList[firstpointer+1][0] > secondList[secondpointer+1][0]) {
                        secondpointer++;
                    } else if (firstList[firstpointer+1][0] < secondList[secondpointer+1][0]) {
                        firstpointer++;
                    } else {
                        firstpointer++;
                        secondpointer++;
                    }
                }else if(firstpointer==firstList.length-1 && secondpointer < secondList.length-1){
                    secondpointer++;
                }else if(secondpointer==secondList.length-1 && firstpointer < firstList.length-1) {
                    firstpointer++;
                }else{
                    break;
                }
            } else {
                int leftend = Math.max(firstList[firstpointer][0], secondList[secondpointer][0]);
                int rightend = Math.min(firstList[firstpointer][1], secondList[secondpointer][1]);
                arrlst1.add(new int[]{leftend, rightend});
                if(firstpointer < firstList.length-1 && secondpointer < secondList.length-1){
                    if (firstList[firstpointer+1][0] > secondList[secondpointer+1][0]) {
                        secondpointer++;
                    } else if (firstList[firstpointer+1][0] < secondList[secondpointer+1][0]) {
                        firstpointer++;
                    } else {
                        firstpointer++;
                        secondpointer++;
                    }
                }else if(firstpointer==firstList.length-1 && secondpointer < secondList.length-1){
                    secondpointer++;
                }else if(secondpointer==secondList.length-1 && firstpointer < firstList.length-1) {
                    firstpointer++;
                }else{
                    break;
                }
            }
        }
        int[][] result = new int[arrlst1.size()][2];
        for (int i = 0; i < arrlst1.size(); i++) {
            result[i][0] = arrlst1.get(i)[0];
            result[i][1] = arrlst1.get(i)[1];
        }
        return result;
    }

    public static boolean isjoint(int[] a, int[] b) {
        if (a[0] <= b[0]) {
            if (a[1] >= b[0]) {
                return true;
            } else {
                return false;
            }
        } else {
            if (b[1] >= a[0]) {
                return true;
            } else {
                return false;
            }
        }
    }
}
