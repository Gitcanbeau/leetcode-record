import java.util.Arrays;

public class leet268 {
    public static void main(String[] args) {
        //Given an array nums containing n distinct numbers in the range [0, n], return the only number in the range that is missing from the array.
        //Example 1:
        //Input: nums = [3,0,1]
        //Output: 2
        //Explanation: n = 3 since there are 3 numbers, so all numbers are in the range [0,3]. 2 is the missing number in the range since it does not appear in nums.
        //Example 2:
        //Input: nums = [0,1]
        //Output: 2
        //Explanation: n = 2 since there are 2 numbers, so all numbers are in the range [0,2]. 2 is the missing number in the range since it does not appear in nums.
        //Example 3:
        //Input: nums = [9,6,4,2,3,5,7,0,1]
        //Output: 8
        //Explanation: n = 9 since there are 9 numbers, so all numbers are in the range [0,9]. 8 is the missing number in the range since it does not appear in nums.
        //Constraints:
        //n == nums.length
        //1 <= n <= 104
        //0 <= nums[i] <= n
        //All the numbers of nums are unique.
        int[] nums={9,6,4,2,3,5,7,0,1};
//        int[] nums={3,0,1};
//        int[] nums={0,1};
        System.out.println(missingNumber(nums));
        //Runtime: 9 ms, faster than 17.97% of Java online submissions for Missing Number.
        //Memory Usage: 51.4 MB, less than 33.52% of Java online submissions for Missing Number.
    }
    public static int missingNumber(int[] nums) {
        int lo=0;
        int hi=nums.length; //左闭右开,hi是数组长度不是最后一个index
        Arrays.sort(nums);

        while(lo<hi){
            int mid=lo+(hi-lo)/2;
            if(nums[mid]-mid>0){ //leet1539一样的题目，这里的0就是第index k为0个missing number，文字就是第一个missing number
                hi=mid;
            }else{
                lo=mid+1;
            }
        }

        return lo;//画画图就知道了，别光在脑子里面想。。。//返回lo返回hi都行，因为出循环的条件就是lo==hi
    }

}
