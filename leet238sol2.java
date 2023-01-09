public class leet238sol2 {
    public static void main(String[] args) {
//        Given an integer array nums, return an array answer such that answer[i]
//        is equal to the product乘积 of all the elements of nums except nums[i].
//        The product of any prefix前缀 or suffix后缀 of nums is guaranteed to fit in a 32-bit integer.
//        You must write an algorithm that runs in O(n) time and without using the division operation.

//        32-bit integer: A signed integer is a 32-bit datum
//        that encodes an integer in the range [-2147483648 to 2147483647].

//        Example 1:
//        Input: nums = [1,2,3,4]
//        Output: [24,12,8,6]
//        Example 2:
//        Input: nums = [-1,1,0,-3,3]
//        Output: [0,0,9,0,0]

//        int[] nums={1,2,3,4};
        int[] nums = {-1, 1, 0, -3, 3};
        int[] productwithoutselfarray = productExceptSelf(nums);
        System.out.print("[");
        for (int i = 0; i < productwithoutselfarray.length; i++) {
            System.out.print(productwithoutselfarray[i] + ",");
        }
        System.out.println("]");
       //Runtime: 3 ms, faster than 53.67% of Java online submissions for Product of Array Except Self.
        //Memory Usage: 58 MB, less than 38.77% of Java online submissions for Product of Array Except Self.
    }

    public static int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];
        int l=1;
        for(int i=0; i<nums.length; i++){
            res[i]=l;
            l*=nums[i];
        }
        int r = 1;
        for(int i=nums.length-1; i>=0; i--){
            res[i]*=r;
            r*=nums[i];
        }

        return res;
    }
}
