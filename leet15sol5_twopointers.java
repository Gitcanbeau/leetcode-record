import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class leet15sol5_twopointers {
    public static void main(String[] args) {
        int[] nums = {-1,0,1,2,-1,-4};
//        int[] nums = {0,0,0,0,0};
//        int[] nums = {-1,0,1,2,-1,-4,-2,-3,3,0,4};
        List<List<Integer>> lall = threeSum(nums);
        System.out.println(lall);
    }
    public static List<List<Integer>> threeSum(int[] nums) { //这个版本是错误写法，你这固定最后一个指针不对，相当于强制得0的数组里面带最后一个数，应该让最后一个指针也动起来
        Arrays.sort(nums);
        List<List<Integer>> res=new ArrayList<>();
        for(int i=0; i<nums.length-1;i++){
            int twosum=nums[i]+nums[nums.length-1];
            if(twosum>=0){
                int movingpointer=i+1;
                while(nums[movingpointer]<=0){
                    int threesum=twosum+nums[movingpointer];
                    if(threesum==0){
                        List<Integer> list=new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[movingpointer]);
                        list.add(nums[nums.length-1]);
                        res.add(list);
                        break;
                    }else if(threesum>0){
                        break;
                    }else{
                        movingpointer++;
                    }
                }
            }

            if(twosum<0){
                int movingpointer=nums.length-1-1;
                while(nums[movingpointer]>=0){
                    int threesum=twosum+nums[movingpointer];
                    if(threesum==0){
                        List<Integer> list=new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[movingpointer]);
                        list.add(nums[nums.length-1]);
                        res.add(list);
                        break;
                    }else if(threesum<0){ //2sum原本负数，加完一个右边的最大的还小于0，那后面的不用加了，指定不行
                        break;
                    }else{ //2sum原本负数，加完一个右边的最大的大于0，那再往前挪可能还有机会
                        movingpointer--;
                    }
                }
            }
        }

        return res;
    }
}
