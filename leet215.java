import java.util.Collections;
import java.util.PriorityQueue;

public class leet215 {
    public static void main(String[] args) {
        //Given an integer array nums and an integer k, return the kth largest element in the array.
        //Note that it is the kth largest element in the sorted order, not the kth distinct element.
        //You must solve it in O(n) time complexity.
        //Example 1:
        //Input: nums = [3,2,1,5,6,4], k = 2
        //Output: 5
        //Example 2:
        //Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
        //Output: 4
        int[] nums={3,2,3,1,2,4,5,5,6};
        int k = 4;
        int kthLargest = findKthLargest(nums, k);
        System.out.println(kthLargest);
        //Collections. sort() is O(n*log(n)) 不能用
        //Runtime: 146 ms, faster than 9.67% of Java online submissions for Kth Largest Element in an Array.
        //Memory Usage: 70.9 MB, less than 55.46% of Java online submissions for Kth Largest Element in an Array.
    }
    public static int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> maxheap=new PriorityQueue<>(Collections.reverseOrder());
        for(int i=0; i<nums.length;i++){
            maxheap.add(nums[i]);
        }
        int count=0;
        int returnnumis = 0;
        while(count<k){
            returnnumis=maxheap.poll();
            count++;
        }
        return returnnumis;
    }
}
