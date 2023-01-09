import java.util.Arrays;

public class leet33 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/search-in-rotated-sorted-array/
//        int[] nums={4,5,6,7,0,1,2};
//        int[] nums={1};
//        int[] nums={1,3};
//        int[] nums = {1, 3,5};
//        int target = 5;
        int[] nums = {4,5,6,7,0,1,2};
        int target = 0;
        System.out.println(findpeak(nums));
        System.out.println(search(nums,target));
        //Runtime: 0 ms, faster than 100.00% of Java online submissions for Search in Rotated Sorted Array.
        //Memory Usage: 41.6 MB, less than 98.50% of Java online submissions for Search in Rotated Sorted Array.
    }

    public static int search(int[] nums, int target) {
        int res=-1;
        int indexofpeak=nums.length-1;
        if(nums[nums.length-1]<nums[0]){
            indexofpeak=findpeak(nums);
        }

        if((indexofpeak==nums.length-1) || (target>=nums[0] && target<=nums[indexofpeak])){
            //在左半区或者最大值就在最右侧
            int left=0;
            int right=indexofpeak;
            while(left<=right){
                int mid=left+(right-left)/2;
                if(nums[mid]==target){
                    return mid;
                }else if(nums[mid]<target){
                    left=mid+1;
                }else if(nums[mid]>target){
                    right=mid-1;
                }
            }
        }

        if((indexofpeak!=nums.length-1) && (target<=nums[nums.length-1] && target>=nums[indexofpeak+1])){
            //在右半区
            int left=indexofpeak+1;
            int right=nums.length-1;
            while(left<=right){
                int mid=left+(right-left)/2;
                if(nums[mid]==target){
                    return mid;
                }else if(nums[mid]<target){
                    left=mid+1;
                }else if(nums[mid]>target){
                    right=mid-1;
                }
            }
        }

        return res;
    }

    public static int findpeak(int[] nums) {
        int ans=-1;
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left)/2;
            if ( ( mid==0 || nums[mid] > nums[mid - 1]) && (mid== nums.length-1 || nums[mid] > nums[mid + 1])) {
                //正好找到最大的峰的索引
                return mid;
            } else if (mid==0 || nums[mid] > nums[0]) {//mid在左半区，需要left右移动
                left = mid + 1;
            } else if (mid==nums.length - 1 || nums[mid] < nums[nums.length - 1]) {//在右半区
                right = mid - 1;
            }
        }
        return ans;
    }
}
