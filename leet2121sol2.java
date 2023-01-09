import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class leet2121sol2 {
    public static void main(String[] args) {
//You are given a 0-indexed array of n integers arr.
        //The interval between two elements in arr is defined as the absolute difference between their indices. More formally, the interval between arr[i] and arr[j] is |i - j|.
        //Return an array intervals of length n where intervals[i] is the sum of intervals between arr[i] and each element in arr with the same value as arr[i].
        //Note: |x| is the absolute value of x.
        //Example 1:
        //Input: arr = [2,1,3,1,2,3,3]
        //Output: [4,2,7,2,4,4,5]
        //Explanation:
        //- Index 0: Another 2 is found at index 4. |0 - 4| = 4
        //- Index 1: Another 1 is found at index 3. |1 - 3| = 2
        //- Index 2: Two more 3s are found at indices 5 and 6. |2 - 5| + |2 - 6| = 7
        //- Index 3: Another 1 is found at index 1. |3 - 1| = 2
        //- Index 4: Another 2 is found at index 0. |4 - 0| = 4
        //- Index 5: Two more 3s are found at indices 2 and 6. |5 - 2| + |5 - 6| = 4
        //- Index 6: Two more 3s are found at indices 2 and 5. |6 - 2| + |6 - 5| = 5
        //Example 2:
        //Input: arr = [10,5,10,10]
        //Output: [5,0,3,4]
        //Explanation:
        //- Index 0: Two more 10s are found at indices 2 and 3. |0 - 2| + |0 - 3| = 5
        //- Index 1: There is only one 5 in the array, so its sum of intervals to identical elements is 0.
        //- Index 2: Two more 10s are found at indices 0 and 3. |2 - 0| + |2 - 3| = 3
        //- Index 3: Two more 10s are found at indices 0 and 2. |3 - 0| + |3 - 2| = 4
        //Constraints:
        //n == arr.length
        //1 <= n <= 105
        //1 <= arr[i] <= 105
        int[] arr={2,1,3,1,2,3,3};
//        int[] arr={10,5,10,10};
        long[] distances = getDistances(arr);
        for(long lnum:distances){
            System.out.println(lnum);
        }
        //Runtime: 140 ms, faster than 62.58% of Java online submissions for Intervals Between Identical Elements.
        //Memory Usage: 205.2 MB, less than 54.42% of Java online submissions for Intervals Between Identical Elements.
    }
    public static long[] getDistances(int[] arr) {
        int n = arr.length;
        long[] result = new long[n];
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i=0; i<n; i++) {
            List<Integer> list = map.getOrDefault(arr[i], new ArrayList<>());
            list.add(i);
            map.put(arr[i], list);
        }

        for (Map.Entry<Integer, List<Integer>> entry: map.entrySet()) {
            List<Integer> list = entry.getValue();
            long sum = 0;
            for (int num: list) {
                sum += (long) num;
            }
            long preSum = 0;
            int size = list.size();
            for (int i=0; i<size; i++) {
                int num = list.get(i);
                preSum += num;
                result[num] = sum + (2*i+2-size)*(long)num - 2*preSum;
            }
        }
        return result;
    }
}
