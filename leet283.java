import java.util.ArrayList;
import java.util.Arrays;
public class leet283 {
    public static void main(String[] args) {
        //Note that you must do this in-place without making a copy of the array.
        int[] nums = {0, 1, 0, 3, 12};
        moveZeroes(nums);
        for(Integer in: nums){
            System.out.println(in);
        }
        //Runtime: 2 ms, faster than 83.05% of Java online submissions for Move Zeroes.
        //Memory Usage: 55.3 MB, less than 14.21% of Java online submissions for Move Zeroes.
    }

    public static void moveZeroes(int[] nums) {
        int slowindex=0;
        for(int fastindex=0; fastindex<nums.length; fastindex++){
            if(nums[fastindex]!=0){
                nums[slowindex]=nums[fastindex];
                slowindex++;
            }
        }

        for(int j=slowindex; j<nums.length; j++){
            nums[j]=0;
        }
    }

    public static void moveZeroes2(int[] nums) {
        int count=0;
        int temp = 0;

        for(int i=0; i<nums.length; i++)
        {
            if(nums[i]==0)
            {
                count++;
                continue;
            }
            if(count > 0)
            {
                temp = nums[i];
                nums[i] = nums[i-count];
                nums[i-count] = temp;
            }

        }
    }

}
