import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class leet347sol4 {
    public static void main(String[] args) {
    //Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.
    //Example 1:
    //Input: nums = [1,1,1,2,2,3], k = 2
    //Output: [1,2]
    //Example 2:
    //Input: nums = [1], k = 1
    //Output: [1]
    //Constraints:
    //1 <= nums.length <= 105
    //-104 <= nums[i] <= 104
    //k is in the range [1, the number of unique elements in the array].
    //It is guaranteed that the answer is unique.

    int[] nums = {1, 1, 1, 2, 2, 2, 2, 3};
    int k = 2;
    int[] ints = topKFrequent(nums, k);
    for (int i = 0; i < ints.length; i++) {
        System.out.print(ints[i] + " ");
    }
}
    public static int[] topKFrequent(int[] nums, int k) {
        Set<Map.Entry<Integer, Integer>> sortedSet = new TreeSet<>((o1, o2) -> o2.getValue() - o1.getValue() != 0 ? o2.getValue() - o1.getValue() : 1);
        Map<Integer, Integer> numMap = new HashMap<>();
        for(int i = 0; i < nums.length; i++) {
            numMap.merge(nums[i], 1, Integer::sum);
        }
        sortedSet.addAll(numMap.entrySet());
        int[] answer = new int[k];
        int j = 0;
        for(Map.Entry entry: sortedSet) {
            answer[j] = (int)entry.getKey();
            j++;
            if (j == k) {
                return answer;
            }
        }
        return answer;
    }
}
