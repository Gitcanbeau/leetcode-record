import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class leet18sol2_backtracking {
    public static void main(String[] args) {
        //https://leetcode.com/problems/4sum/
        int[]nums={1,0,-1,0,-2,2};
        int target=0;
//        int[]nums={2,2,2,2,2};
//        int target=8;
//        int[]nums={1000000000,1000000000,1000000000,1000000000};
//        int target=-294967296;
//        int[] nums={-1000000000,-1000000000,1000000000,-1000000000,-1000000000};
//        int target=294967296;
        System.out.println(fourSum(nums,target));
    }
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res=new ArrayList<>();
        List<Integer> templist=new ArrayList<>();
        Arrays.sort(nums);
        backtracking(nums,target,templist,res,0);
        return res;
    }
    public static void backtracking (int[] nums, int target, List<Integer> templist,List<List<Integer>> res,int starti){
        if(templist.size()==4){
            int check=0;
            for(int number: templist) check+=number;
            if(check==target) res.add(new ArrayList<>(templist));
        }else{
            for(int i=starti; i<nums.length; i++){
                if(i>starti && nums[i]==nums[i-1]) continue;
                templist.add(nums[i]);
                backtracking(nums,target,templist,res,i+1);
                templist.remove(templist.size()-1);
            }
        }
        return;
    }
}
