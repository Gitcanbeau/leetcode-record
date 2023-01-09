import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class leet80 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/
//        int[] nums={0,0,1,1,1,1,2,3,3};//7
//        int[] nums={1,1,1,2,2,3};//5
        int[] nums={1};
        System.out.println(removeDuplicates(nums));
        //Runtime: 1 ms, faster than 88.64% of Java online submissions for Remove Duplicates from Sorted Array II.
        //Memory Usage: 44.4 MB, less than 74.12% of Java online submissions for Remove Duplicates from Sorted Array II.
    }
    public static int removeDuplicates(int[] nums) {
        //in-place 就是array的覆盖，整一个快慢指针，再整一个hashset存个数如果<=2可以动慢指针存上，如果>2就一直动快指针直到值与慢指针的值不同
        int slow=0;
        nums[slow]=nums[0];
        int count=1;
        slow=1;
        for(int fast=1; fast<nums.length;fast++){
            if( count==2 && nums[fast-1]==nums[fast]){
                continue;
            }else if(count<2 && nums[fast-1]==nums[fast]){
                count++;
                nums[slow]=nums[fast];
                slow++;
            }else if(count<=2 && nums[fast-1]!=nums[fast]){
                count=1;
                nums[slow]=nums[fast];
                slow++;
            }
        }


        return slow; //slow是index但是出来之前已经多加了一个1，所以个数上就是slow
    }
}
