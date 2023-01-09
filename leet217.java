import java.util.HashSet;
import java.util.Set;

public class leet217 {
    //    Given an integer array nums,
//     return true if any value appears at least twice in the array,
//     and return false if every element is distinct.
//    Example 1:
//    Input: nums = [1,2,3,1]
//    Output: true
//    Example 2:
//    Input: nums = [1,2,3,4]
//    Output: false
//    Example 3:
//    Input: nums = [1,1,1,3,3,4,3,2,4,2]
//    Output: true
    public static void main(String[] args) {
//        int[] nums = {1, 2, 3, 1};
//        int[] nums={1,2,3,4};
        int[] nums={1,1,1,3,3,4,3,2,4,2};
        boolean donthaveduplicate = containsDuplicate(nums);
        System.out.println(donthaveduplicate);
        //Runtime: 16 ms, faster than 43.43% of Java online submissions for Contains Duplicate.
        //Memory Usage: 69.8 MB, less than 48.02% of Java online submissions for Contains Duplicate.
    }

    public static boolean containsDuplicate(int[] nums) {
        Set<Integer> set1 = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set1.contains(nums[i])) {
                return true;
            } else {
                set1.add(nums[i]);
            }
        }
        return false;
    }
}
