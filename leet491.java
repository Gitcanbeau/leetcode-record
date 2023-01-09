import java.util.ArrayList;
import java.util.List;

public class leet491 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/increasing-subsequences/
        int[] nums={4,6,7,7};
        System.out.println(findSubsequences(nums));
    }
    public static List<List<Integer>> findSubsequences(int[] nums) {
        //Runtime: 14 ms, faster than 81.11% of Java online submissions for Increasing Subsequences.
        //Memory Usage: 71.7 MB, less than 32.73% of Java online submissions for Increasing Subsequences.
        List<List<Integer>> res=new ArrayList<>();
        List<Integer> templist=new ArrayList<>();
//        boolean[] used=new boolean[201]; //放在这里是错误的，这道题是回溯但是跟模版一点都不一样
        backtracking(nums,res,templist,0);
        return res;
    }
    public static void backtracking(int[] nums, List<List<Integer>> res, List<Integer> templist,int startidx){
        if(templist.size()>1){
            res.add(new ArrayList<>(templist));
//            return; // 注意这里不要加return，要取树上的节点 就是每一个都加的意思，跟取子集一样，那块不加return如果能想明白就能想明白这里
        }
        boolean[] used=new boolean[201];
        for(int i=startidx;i<nums.length;i++) {
            if(!templist.isEmpty() && nums[i]<templist.get(templist.size()-1) || used[nums[i]+100] == true) continue;

            used[nums[i]+100]=true;
            templist.add(nums[i]);
            backtracking(nums,res,templist,i+1);
            templist.remove(templist.size()-1);
        }

    }
}
