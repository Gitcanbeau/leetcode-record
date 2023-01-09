import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class leet47 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/permutations-ii/
        int[] nums={1,1,2};
        System.out.println(permuteUnique(nums));
    }
    public static List<List<Integer>> permuteUnique(int[] nums) {
        //Runtime: 1 ms, faster than 100.00% of Java online submissions for Permutations II.
        //Memory Usage: 42.9 MB, less than 93.61% of Java online submissions for Permutations II.
        List<List<Integer>> res=new ArrayList<>();
        Arrays.sort(nums);
        List<Integer> templist=new ArrayList<>();
        boolean[] used=new boolean[nums.length];
        backtracking(nums,res,templist,used);
        return res;
    }
    public static void backtracking(int[] nums,List<List<Integer>> res,List<Integer> templist,boolean[] used){
        if(templist.size()==nums.length){
            res.add(new ArrayList<>(templist));
            return;
        }
        for(int i=0; i<nums.length;i++){
            if (i > 0 && nums[i] == nums[i - 1] && used[i - 1] == false) {
                continue;
            }
            if(used[i]==true) continue;
            templist.add(nums[i]);
            used[i]=true;
            backtracking(nums,res,templist,used);
            templist.remove(templist.size()-1);
            used[i]=false;
        }
        return;
    }
}
