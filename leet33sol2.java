public class leet33sol2 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/search-in-rotated-sorted-array/
        int[] nums={4,5,6,7,0,1,2};
//        int[] nums={1};
//        int[] nums={1,3};
//        int[] nums={5,1,3};
        int target=5;
        System.out.println(findPivot(nums));
        System.out.println(search(nums,target));
        //Runtime: 1 ms, faster than 76.95% of Java online submissions for Search in Rotated Sorted Array.
        //Memory Usage: 43.2 MB, less than 13.55% of Java online submissions for Search in Rotated Sorted Array.
    }
    public static int search(int[] nums, int target) {
        int pivot = findPivot(nums);
        if(pivot == -1){
            return findTarget(nums, target, 0 , nums.length - 1) ;
        }
        if(nums[pivot] == target) return pivot;
        else if (target >= nums[0]) return findTarget(nums, target, 0, pivot-1) ;

        return findTarget(nums, target , pivot+1 , nums.length-1) ;
    }
    private static int findPivot(int[] nums){
        int start = 0;
        int end = nums.length -1 ;
        while(start <= end){
            int mid = start + (end-start) / 2 ;
            if(mid < end && nums[mid] > nums[mid+1] ) return mid;
            else if (mid > start && nums[mid] < nums[mid -1]) return mid-1 ;
            else if (nums[mid] <= nums[start]) end = mid - 1;
            else start = mid + 1;
        }
        return -1 ;
    }
    private static int findTarget(int[] nums, int target, int start, int end){
        while(start <= end){
            int mid = start + (end-start) / 2 ;
            if(nums[mid] == target) return mid;
            else if (nums[mid] > target ) end = mid-1;
            else start = mid + 1;
        }
        return -1;
    }
}
