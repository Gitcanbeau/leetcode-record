import java.util.Arrays;

public class leet268sol2 {
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
        //Runtime: 1 ms, faster than 79.43% of Java online submissions for Missing Number.
        //Memory Usage: 51.3 MB, less than 33.52% of Java online submissions for Missing Number.
    }
    public static int missingNumber(int[] nums) {
        int sum = (0+nums.length)*(nums.length+1)/2;
        // calculate the sum of an arithmetic sequence of 0,1,2,...,n
        //和=（首项+末项）*项数/2

        int currentsum=0;
        for(int i=0;i<nums.length;i++){
            currentsum+=nums[i];
        }
        return sum-currentsum; // the difference is guaranteed to be the missing number
    }
}
