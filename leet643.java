public class leet643 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/maximum-average-subarray-i/
//        int[] nums={1,12,-5,-6,50,3};
//        int k=4;
//        int[] nums={4};
//        int k=1;
        int[] nums={0,4,0,3,2};
        int k=1;
        System.out.println(findMaxAverage(nums,k));
        //Runtime: 11 ms, faster than 24.48% of Java online submissions for Maximum Average Subarray I.
        //Memory Usage: 104.1 MB, less than 56.28% of Java online submissions for Maximum Average Subarray I.
    }
    public static double findMaxAverage(int[] nums, int k) {

        //左右指针数值差4
        //index=0，1，2，3，4，5，6，7
        //   left         right
        //只要nums[right]比nums[left]大，我才有必要进行计算，小的话我就left，right一起向右移动，直到右指针到最后一个

        double currentsum=0;
        for(int right=0;right<k;right++){
            currentsum=currentsum+nums[right];
        }

        double maxsum=currentsum;
        for(int right=k;right<nums.length;right++){
            currentsum=currentsum-nums[right-k]+nums[right];
            maxsum=Math.max(maxsum,currentsum);
        }
        double maxavg=maxsum/k;
        return maxavg;
    }

}
