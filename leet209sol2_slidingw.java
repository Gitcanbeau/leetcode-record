public class leet209sol2_slidingw {
    public static void main(String[] args) {
        //https://leetcode.com/problems/minimum-size-subarray-sum/
//        int target=7;
//        int[] nums={2,3,1,2,4,3};
//        int target=4;
//        int[] nums={1,3,4};
//        int target=11;
//        int[] nums={1,1,1,1,1,1,1};
//        int[] nums={1};
        int target=11;
        int[] nums={1,2,3,4,5};
        System.out.println(minSubArrayLen(target,nums));
    }

    // 滑动窗口
    public static int minSubArrayLen(int target, int[] nums) {
        //不要以为for里放一个while就以为是O(n^2)啊，
        // 主要是看每一个元素被操作的次数，每个元素在滑动窗后进来操作一次，出去操作一次，每个元素都是被操作两次，
        // 所以时间复杂度是 2 × n 也就是O(n)。

        //Runtime: 1 ms, faster than 100.00% of Java online submissions for Minimum Size Subarray Sum.
        //Memory Usage: 49.4 MB, less than 99.78% of Java online submissions for Minimum Size Subarray Sum.

        int left = 0;
        int sum = 0;
        int result = Integer.MAX_VALUE;
        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];
            while (sum >= target) {
                result = Math.min(result, right - left + 1);
                sum -= nums[left];
                left++;
            }
        }
        return result == Integer.MAX_VALUE ? 0 : result;
    }

}
