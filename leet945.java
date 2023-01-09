import java.lang.reflect.Array;
import java.util.*;

public class leet945 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/minimum-increment-to-make-array-unique/
    }
    public static int minIncrementForUnique(int[] nums) {
        //一看就是贪心
        //untime: 47 ms, faster than 66.93% of Java online submissions for Minimum Increment to Make Array Unique.
        //Memory Usage: 78.9 MB, less than 25.96% of Java online submissions for Minimum Increment to Make Array Unique.
        int result = 0;
        if(nums.length == 0)
            return result;

        Arrays.sort(nums);

        int highestTillNow = nums[0];

        for(int i = 1; i<nums.length; i++){
            if(nums[i] <= highestTillNow){
                result += (highestTillNow-nums[i]+1);
                highestTillNow++;
            }
            else
                highestTillNow = nums[i];
        }
        return result;

    }
}
