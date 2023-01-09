import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class leet2121 {
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
        //Runtime: 118 ms, faster than 71.43% of Java online submissions for Intervals Between Identical Elements.
        //Memory Usage: 184.6 MB, less than 76.19% of Java online submissions for Intervals Between Identical Elements.
    }

    public static long[] getDistances(int[] arr) {

        long[] result = new long[arr.length];

        HashMap<Integer, ArrayList<Integer>> hm1 = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (!hm1.containsKey(arr[i])) {
                ArrayList<Integer> arrlstofindices = new ArrayList<>();
                arrlstofindices.add(i);
                hm1.put(arr[i], arrlstofindices);
            } else {
                hm1.get(arr[i]).add(i);
            }
        }


        for (Map.Entry<Integer, ArrayList<Integer>> entry : hm1.entrySet()) {
            ArrayList<Integer> arrlstofindices = entry.getValue();
            long sum = 0;
            for (int indexvalue : arrlstofindices) {
                sum += (long) indexvalue;
            }
            result[arrlstofindices.get(0)] = sum - arrlstofindices.size() * arrlstofindices.get(0);
            for (int i = 1; i < arrlstofindices.size(); i++) {
                result[arrlstofindices.get(i)] = result[arrlstofindices.get(i - 1)]
                        + (2*i-arrlstofindices.size()) * (arrlstofindices.get(i) - arrlstofindices.get(i - 1));
            }
        }

        return result;
    }
}
