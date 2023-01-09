public class leet674 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/longest-continuous-increasing-subsequence/
        int[] nums={1,3,5,4,7};
//        int[] nums={2,2,2,2,2};
//        int[] nums={2};
        System.out.println(findLengthOfLCIS(nums));
    }
    public static int findLengthOfLCIS(int[] nums) {
        //Runtime: 3 ms, faster than 23.70% of Java online submissions for Longest Continuous Increasing Subsequence.
        //Memory Usage: 45.5 MB, less than 62.81% of Java online submissions for Longest Continuous Increasing Subsequence.
        //感觉不像滑动窗口，或者说是left不用管的滑动窗口

        int count=1;
        int res=0;
        for(int right=0;right<nums.length-1;right++){
            if(nums[right+1]>nums[right]){
                count++;
                res=Math.max(res,count);
            }else{
                count=1;
            }
        }
        return Math.max(res,count);
    }

    public int findLengthOfLCIS2(int[] nums) {
        int res=1;
        int left=0;
        for(int right=1; right<nums.length;right++){
            while(nums[right]>nums[left]){
                res=Math.max(res,right-left+1);
            }
            left=right;
        }
        return res;
    }

}
