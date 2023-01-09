import java.util.Collections;
import java.util.PriorityQueue;

public class leet215sol3 {
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
//        int[] nums={3,2,3,1,2,4,5,5,6};
//        int k = 4;
        int[] nums={2,1};
        int k = 1;
        int kthLargest = findKthLargest(nums, k);
        System.out.println(kthLargest);
        //Runtime: 93 ms, faster than 32.13% of Java online submissions for Kth Largest Element in an Array.
        //Memory Usage: 49.7 MB, less than 97.86% of Java online submissions for Kth Largest Element in an Array.
    }
    public static int findKthLargest(int[] nums, int k) {
        quicksort(nums,0,nums.length-1);
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


    public static void quicksort(int[] nums,int lo, int hi){
        if(hi<=lo) return;//安全性检查，最后的出口
        int partition=partition(nums,lo, hi);
        quicksort(nums,lo, partition-1);
        quicksort(nums,partition+1, hi);//不需要对分界值进行排序，左边比分界值小，右边比分界值大。
    }
    public static int partition(int[] nums, int lo, int hi){
        int key=nums[lo];
        int left=lo;
        int right=hi+1;
        int temp;
        while(true){
            while(right>0&&key<nums[--right]){
                if(right==lo){
                    break;
                }
            }
            while(left<nums.length-1&&key>nums[++left]){
                if(left==lo){
                    break;
                }
            }
            if(left>=right){//判断left是否大于等于right，如果是，则说明元素扫描完毕，结束循环，如果不是则交换元素即可
                break;
            }else{
                exchange(nums,left, right);
            }
        }
        exchange(nums, lo, right);
        return right;
    }



    public static void exchange(int[] nums, int i, int j){
        int temp=nums[i];
        nums[i]=nums[j];
        nums[j]=temp;
    }
}
