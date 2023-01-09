import java.util.Arrays;
import java.util.Collections;

public class leet215sol2 {
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
        //Runtime: 45 ms, faster than 53.58% of Java online submissions for Kth Largest Element in an Array.
        //Memory Usage: 69.1 MB, less than 81.72% of Java online submissions for Kth Largest Element in an Array.
        //Collections. sort() is O(n*log(n)) 不能用
        //Arrays. sort(Object[]) is based on the TimSort algorithm, giving us a time complexity of O(n log(n)).
    }
    public static int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        int count=0;
        int returnnumsis=0;
        for(int i=nums.length-1; i>=0;i--){
            count++;
            returnnumsis=nums[i];
            if(count==k){
                break;
            }
        }
        return returnnumsis;
    }
}
