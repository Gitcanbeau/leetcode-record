public class leet35 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/search-insert-position/
        int[] nums={1};

//        int[] nums={1,3,5,6};
        int target=5;
//        int target=2;
//        int target=8;
//        int target=0;
        System.out.println(searchInsert(nums,target));
    }
//    public static int searchInsert(int[] nums, int target) { //左闭右开
//        int left=0;
//        int right=nums.length;
//        while(left<right){
//            int mid=left+(right-left)/2;
//            if(nums[mid]==target){
//                return mid;
//            }else if(nums[mid]<target){
//                left=mid+1;
//            }else if(nums[mid]>target){
//                right=mid;
//            }
//        }
//        return left; //return right 也一样，因为出循环的条件是left==right
//    }

    public static int searchInsert(int[] nums, int target) { //左闭右闭
        //Runtime: 0 ms, faster than 100.00% of Java online submissions for Search Insert Position.
        //Memory Usage: 42.8 MB, less than 83.57% of Java online submissions for Search Insert Position.
        int left=0;
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
        return left; // return right+1; 出循环的条件是left>right
        //还有，万一我的数target比nums[left]还小，最后一行的情况，那正好把left端点值给我这个target，
        // 如果target比我的nums[right]还大，中间那行的情况，哪正好left出来是mid+1就是left+1，就是nums.length-1+1，正好。
    }

}
