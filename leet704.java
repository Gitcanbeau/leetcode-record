public class leet704 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/binary-search/
        int[] nums={-1,0,3,5,9,12};
        int target=9;
//        int target=2;
        System.out.println(search(nums,target));
    }
//    public static int search(int[] nums, int target) { //左闭右开写法
    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Binary Search.
    //Memory Usage: 54.6 MB, less than 25.00% of Java online submissions for Binary Search.
//        int left=0;
//        int right=nums.length;
//        while(left<right){
//            int mid=left+(right-left)/2;
//            if(nums[mid]==target){
//                return mid;
//            }else if(nums[mid]>target){
//                right=mid;
//            }else if(nums[mid]<target){
//                left=mid+1;
//            }
//        }
//        return -1;
//    }

    public static int search(int[] nums, int target) { //左闭右闭写法
        //Runtime: 0 ms, faster than 100.00% of Java online submissions for Binary Search.
        //Memory Usage: 54.2 MB, less than 60.38% of Java online submissions for Binary Search.
        int left=0;
        int right=nums.length-1; //第一个不同
        while(left<=right){ //第二个不同
            int mid=left+(right-left)/2;
            if(nums[mid]==target){
                return mid;
            }else if(nums[mid]>target){
                right=mid-1;//第三个不同
            }else if(nums[mid]<target){
                left=mid+1;
            }
        }
        return -1;
    }
}
