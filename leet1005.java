import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class leet1005 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/maximize-sum-of-array-after-k-negations/
//        int[] nums={4,2,3}; //5
//        int k=1;
//        int[] nums={3,-1,0,2}; //6
//        int k=3;
//        int[] nums={2,-3,-1,5,-4}; //13
//        int k=2;
//        int[] nums={-2,9,9,8,4}; //32
//        int k=5;
//        int[] nums={0,9,9,8,4}; //30
//        int k=5;
//        int[] nums={1,9,9,8,4}; //29
//        int k=5;
//        int[] nums={-8,3,-5,-3,-5,-2};//22
//        int k=6;
        int[] nums={-4,-2,-3};//22
        int k=4;
//        System.out.println(binarysearch_index_zero(nums));
        System.out.println(largestSumAfterKNegations(nums,k));
    }
    // Runtime: 11 ms, faster than 34.04% of Java online submissions for Maximize Sum Of Array After K Negations.
    // Memory Usage: 42.4 MB, less than 76.49% of Java online submissions for Maximize Sum Of Array After K Negations.
    public static int largestSumAfterKNegations(int[] nums, int k) {
        Queue<Integer> pQueue = new PriorityQueue<>();
        int sum = 0;
        for(int n : nums) pQueue.offer(n);
        for(int i = 0 ; i < k; i++){
            if(pQueue.peek() == 0) continue;
            pQueue.offer(pQueue.poll() * -1);
        }
        while(!pQueue.isEmpty()){
            sum += pQueue.poll();
        }
        return sum;
    }
    // Runtime: 2 ms, faster than 99.53% of Java online submissions for Maximize Sum Of Array After K Negations.
    // Memory Usage: 41.7 MB, less than 95.09% of Java online submissions for Maximize Sum Of Array After K Negations.
    public static int largestSumAfterKNegations2(int[] nums, int k) {
        // [-8,3,-5,-3,-5,-2]
        Arrays.sort(nums);
        // [-8,-5,-5,-3,-2,3]
        int negativeCount = 0;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] < 0)  negativeCount++;
        }
        if(negativeCount >= k) {
            for(int i = 0; i < k; i++){
                nums[i] = -1 * nums[i];
            }
        }
        else {
            // [-8,-5,-5,-3,-2,3]
            for(int i = 0; i < negativeCount; i++){
                nums[i] = -1 * nums[i];
            }
            // [8,5,5,3,2,3], intent here is to pick 2, instead of 3, after being careful about boundary conditions
            int nextIndex = negativeCount == nums.length || (negativeCount > 0 && nums[negativeCount - 1] < nums[negativeCount]) ? negativeCount - 1 : negativeCount;
            // we only need to play with nextIndex for all subsequent negation as nextIndex would be the smallest element
            for(int i = negativeCount; i < k; i++){
                nums[nextIndex] = -1 * nums[nextIndex];
            }
            // [8,5,5,3,-2,3] = > 22
        }
        int result = 0;
        for(int n : nums) result += n;
        return result;
    }
}
