public class leet34 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/

//        int[] nums={5,7,7,8,8,10};
//        int target=8;
//        int target=9;
        int[] nums={5,7,7,8,8,8,8,8,8,8,8,8,8,8,10};
        int target=8;
        int[] res=searchRange(nums,target);
        System.out.println(res[0]+","+res[1]);
    }
    public static int[] searchRange(int[] nums, int target) {
        //Runtime: 0 ms, faster than 100.00% of Java online submissions for Find First and Last Position of Element in Sorted Array.
        //Memory Usage: 47.2 MB, less than 60.16% of Java online submissions for Find First and Last Position of Element in Sorted Array.
        if(ispresent(nums,target)==false){
            int[] res={-1,-1};
            return res;
        }

        int left=0;
        int right=nums.length-1;
        int leftindex=Integer.MAX_VALUE;
        while(left<=right){
            int mid=left+(right-left)/2;
            if(nums[mid]<target){
                left=mid+1;
            }else if(nums[mid]>target){
                right=mid-1;
            }else if(nums[mid]==target){
                leftindex=Math.min(leftindex,mid);
                right=mid-1;
            }
        }

        left=0;
        right=nums.length-1;
        int rightindex=Integer.MIN_VALUE;
        while(left<=right){
            int mid=left+(right-left)/2;
            if(nums[mid]<target){
                left=mid+1;
            }else if(nums[mid]>target){
                right=mid-1;
            }else if(nums[mid]==target){
                rightindex=Math.max(rightindex,mid);
                left=mid+1;
            }
        }

        int[] res=new int[2];
        res[0]=leftindex;
        res[1]=rightindex;

        return res;
    }

    public static boolean ispresent(int[] nums,int target){
        int left=0;
        int right=nums.length-1;
        while(left<=right){
            int mid=left+(right-left)/2;
            if(nums[mid]<target){
                left=mid+1;
            }else if(nums[mid]>target){
                right=mid-1;
            }else if(nums[mid]==target){
                return true;
            }
        }
        return false;
    }
}
