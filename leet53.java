public class leet53 {
    public static void main(String[] args) {
//        Given an integer array nums, find the contiguous subarray (containing at least one number)
//        which has the largest sum and return its sum.A subarray is a contiguous part of an array.

//        Example 1:
//        Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
//        Output: 6
//        Explanation: [4,-1,2,1] has the largest sum = 6.
//        Example 2:
//        Input: nums = [1]
//        Output: 1
//        Example 3:
//        Input: nums = [5,4,-1,7,8]
//        Output: 23
//        int[] nums={-2,1,-3,4,-1,2,1,-5,4};
//        int[] nums={1};
//        int[] nums={5,4,-1,7,8};
        int[] nums={-2,1};
        int num=maxSubArray(nums);
        System.out.println(num);
        //time limit exceeded
    }
    public static int maxSubArray(int[] nums) {
        int max;
        int[] arr_maxsum=new int[nums.length];
        int maxsum_at_index_i;
        int temp_sumofsub;

        if(nums.length==1){
           return nums[0];
        }

        for(int i=0; i<nums.length;i++){
            maxsum_at_index_i=nums[i];
            temp_sumofsub=maxsum_at_index_i;
            for(int j=i+1; j<nums.length;j++){
                temp_sumofsub=temp_sumofsub+nums[j];
                if(maxsum_at_index_i>temp_sumofsub){
                    continue;
                }else{
                    maxsum_at_index_i=temp_sumofsub;
                }
            }
            arr_maxsum[i]=maxsum_at_index_i;
        }
        max= getMax(arr_maxsum);
        return max;
    }
    public static int getMax(int[] arr){
        int max=arr[0];
        for(int i=1; i<arr.length;i++){
            if(max>arr[i]){
                continue;
            }else{
                max=arr[i];
            }
        }
        return max;
    }


    public static int maxSubArray2(int[] nums) {
        //前一个数如果是负数，那我认可从自己开头subarray
        //前一个数如果是正数，那我可以加上我自己放在我自己的位置上成为sum at index i
        //前一个数不管是nums里的数还是已经成为一些数的sum，我只关心是正是负，负数我就再从我自己当subarray的头开始
        //最后动态规划找最大就行了，最后的nums里面会有原始数据和sum
        int maxsum=nums[0];
        for(int i=1; i<nums.length;i++){
            if(nums[i-1]>0){
                nums[i]=nums[i]+nums[i-1];
            }
            if(nums[i]>maxsum){
                maxsum=nums[i];
            }
        }
        return maxsum;
    }
}
