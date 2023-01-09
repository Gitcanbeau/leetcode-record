public class leet209 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/minimum-size-subarray-sum/
//        int target=7;
//        int[] nums={2,3,1,2,4,3};
//        int target=4;
//        int[] nums={1,3,4};
//        int target=11;
//        int[] nums={1,1,1,1,1,1,1};
        int[] nums={1};
        int target=11;
//        int[] nums={1,2,3,4,5};
        System.out.println(minSubArrayLen(target,nums));
    }
    public static int minSubArrayLen(int target, int[] nums) {
        //Runtime: 5 ms, faster than 16.43% of Java online submissions for Minimum Size Subarray Sum.
        //Memory Usage: 58 MB, less than 53.67% of Java online submissions for Minimum Size Subarray Sum.
        int left=0;
        int right=left;
        int mincount=Integer.MAX_VALUE;
        int currentsum=nums[left];
        while( right<nums.length){
            if(currentsum>=target){
                int count=right-left+1;
                mincount=Math.min(mincount,count);
                currentsum = currentsum - nums[left];
                left++;
            }else {
                right++;
                if(right<nums.length){
                    currentsum=currentsum+nums[right];
                }
            }
        }
        return mincount==Integer.MAX_VALUE ? 0 : mincount;
    }
}
