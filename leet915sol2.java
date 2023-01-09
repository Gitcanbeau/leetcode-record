public class leet915sol2 {
    public static void main(String[] args) {
        //Given an integer array nums, partition it into two (contiguous) subarrays left and right so that:
        //Every element in left is less than or equal to every element in right.
        //left and right are non-empty.
        //left has the smallest possible size.
        //Return the length of left after such a partitioning.
        //Test cases are generated such that partitioning exists.
        //Example 1:
        //Input: nums = [5,0,3,8,6]
        //Output: 3
        //Explanation: left = [5,0,3], right = [8,6]
        //Example 2:
        //Input: nums = [1,1,1,0,6,12]
        //Output: 4
        //Explanation: left = [1,1,1,0], right = [6,12]
        //Constraints:
        //2 <= nums.length <= 105
        //0 <= nums[i] <= 106
        //There is at least one valid answer for the given input.
        //Accepted
        //69,667
        //Submissions
//        int[] nums={5,0,3,8,6};
//        int[] nums={1,1,1,0,6,12};
//        int[] nums={1,8,1,6,12};
        int[] nums={32,57,24,19,0,24,49,67,87,87};
//        int[] nums={3,1,8,4,9,7,12,0,0,12,6,12,6,19,24,90,87,54,92,60,31,59,75,90,20,38,52,51,74,70,86,20,27,91,55,47,54,86,15,16,74,32,68,27,19,54,13,22,34,74,76,50,74,97,87,42,58,95,17,93,39,33,22,87,96,90,71,22,48,46,37,18,17,65,54,82,54,29,27,68,53,89,23,12,90,98,42,87,91,23,72,35,14,58,62,79,30,67,44,48};
        int i = partitionDisjoint(nums);
        System.out.println(i);
        //Runtime: 3 ms, faster than 93.19% of Java online submissions for Partition Array into Disjoint Intervals.
        //Memory Usage: 87.9 MB, less than 73.07% of Java online submissions for Partition Array into Disjoint Intervals.
    }
    public static int partitionDisjoint(int[] nums) {
        int leftmax=nums[0]; //max value in left part of the array
        int max=nums[0]; //absolute max value in the already traversed part of the array
        int length=1; //minimum length is 1
        for(int i=0;i<nums.length;i++){
            if(nums[i]<leftmax){ //if current element is less than the max value in left part of the array
                length=i+1; //increase length by 1 ie extend left array to index i
                leftmax=max; //assign the absolute max value to left max because we may have already traversed past a value greater than leftmax but assumed it was in the right part!! luckily we are storing it in the max variable
            } else {
                max = Math.max(nums[i],max); //update absolute max & stop left there
            }
        }
        return length; //don't add 1 here as it's possible it will never get updated in the for loop and will return a value of 2 instead of expected 1!
    }
}
