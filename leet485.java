import java.util.ArrayList;
import java.util.Arrays;


public class leet485 {
    public static void main(String[] args) {
        int[] nums = {1, 1, 0, 1, 1, 1,0,1};
        int max = findMaxConsecutiveOnes(nums);
        System.out.println("max is "+max);
    }
    //Runtime: 5 ms, faster than 9.20% of Java online submissions for Max Consecutive Ones.
    //Memory Usage: 58.8 MB, less than 5.71% of Java online submissions for Max Consecutive Ones.

    public static Integer findMaxConsecutiveOnes(int[] nums) {
        if(nums == null || nums.length==0){
            return 0;
        }

        int maxoneslength = 0;
        ArrayList<Integer> alzeroindex = new ArrayList<Integer>();
        for (int i = 0; i < nums.length; i++) {

            if (nums[i] == 0) {
                alzeroindex.add(i);
            }else{
                continue;
            }
        }

        if(alzeroindex.size()==0){
            return nums.length;
        }

        ArrayList<Integer> aloneslength = new ArrayList<Integer>();
        for (int j = 0; j < alzeroindex.size(); j++) {
            if (j == 0) {
                aloneslength.add(alzeroindex.get(0));
            }else if( j >=1 ){
                aloneslength.add(alzeroindex.get(j) - alzeroindex.get(j - 1) - 1);
            }
        }
        aloneslength.add(nums.length - alzeroindex.get(alzeroindex.size()-1)-1);

        for (int h = 0; h < aloneslength.size(); h++) {
            if (maxoneslength < aloneslength.get(h)) {
                maxoneslength = aloneslength.get(h);
            }

        }

    return maxoneslength;
    }
}
