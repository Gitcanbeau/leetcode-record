import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class leet90 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/subsets-ii/
        int[] nums={1,2,2,2};
        System.out.println(subsetsWithDup(nums));
    }
    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        //Runtime: 3 ms, faster than 52.82% of Java online submissions for Subsets II.
        //Memory Usage: 43.6 MB, less than 81.39% of Java online submissions for Subsets II.
        List<List<Integer>> res =new ArrayList<>();
        Arrays.sort(nums);
        List<Integer> templist=new ArrayList<>();
        backtracking(nums,res,templist,0);
        return res;
    }
    public static void backtracking (int[] nums,List<List<Integer>> res,List<Integer> templist, int startidx){
        res.add(new ArrayList<>(templist));
        if(startidx>=nums.length){
            return;
        }
        for(int i=startidx; i<nums.length;i++){
            if(i>startidx && nums[i]==nums[i-1]) continue; //去重
            templist.add(nums[i]);
            backtracking(nums,res,templist,i+1);
            templist.remove(templist.size()-1);
        }
        return;
    }
}
