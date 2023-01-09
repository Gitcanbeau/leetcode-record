import java.util.ArrayList;
import java.util.Arrays;

public class leet300 {
    public static void main(String[] args) {
        //Given an integer array nums, return the length of the longest strictly increasing subsequence.
        //A subsequence is a sequence that can be derived from an array by deleting some or no elements without changing the order of the remaining elements. For example, [3,6,2,7] is a subsequence of the array [0,3,1,6,2,2,7].
        //Example 1:
        //Input: nums = [10,9,2,5,3,7,101,18]
        //Output: 4
        //Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
        //Example 2:
        //Input: nums = [0,1,0,3,2,3]
        //Output: 4
        //Example 3:
        //Input: nums = [7,7,7,7,7,7,7]
        //Output: 1
        //Constraints:
        //1 <= nums.length <= 2500
        //-104 <= nums[i] <= 104
//        int[] nums={10,9,2,5,3,7,101,18};
        int[] nums={10,9,2,5,3,7,101,1,2,18};
        System.out.println(lengthOfLIS(nums));
        //Runtime: 4 ms, faster than 92.45% of Java online submissions for Longest Increasing Subsequence.
        //Memory Usage: 44.8 MB, less than 32.40% of Java online submissions for Longest Increasing Subsequence.
    }
    public static int lower_bound(ArrayList<Integer> ans, int target){
        int first=0;
        int last=ans.size();
        while(first<last){
            int mid = first+(last-first)/2;
            if(ans.get(mid)>=target){
                last=mid;
            }else{
                first=mid+1;
            }
        }
        return first;
    }

    public static int lengthOfLIS(int[] nums){
        int n = nums.length;
        if(n==0){
            return 0;
        }


        ArrayList<Integer> ans = new ArrayList<>();
        ans.add(nums[0]);
        for(int i=1; i<n; i++){
            if(nums[i]>ans.get(ans.size()-1)){ //我的ans就是升序排列，你比我的最后一个数大，你才加进来
                ans.add(nums[i]);
            }else{
                //要是不能加进来，你就替换我这个ans里面的数字，万一把我ans最后的数字update小一些，
                // 我nums有更大的机会比ans最后一个数字大，就有更大的机会添加到后面
                int index = lower_bound(ans, nums[i]);
                ans.set(index, nums[i]);
            }
        }
        return ans.size();
    }



    public int lengthOfLIS2(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int res = 0;
        for (int i = 0; i < dp.length; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
