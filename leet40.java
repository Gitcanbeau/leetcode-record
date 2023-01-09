import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class leet40 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/combination-sum-ii/
        int[] candidates = {10,1,2,7,6,1,5};
        int target = 8;
        System.out.println(combinationSum2(candidates,target));
    }
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        //Runtime: 4 ms, faster than 91.46% of Java online submissions for Combination Sum II.
        //Memory Usage: 44.1 MB, less than 35.77% of Java online submissions for Combination Sum II.
        List<List<Integer>> res=new ArrayList<>();
        Arrays.sort(candidates);
        if(candidates==null || candidates.length==0 || candidates[0]>target) return res;
        List<Integer> templist=new ArrayList<>();
        backtracking(candidates,target,0,res,templist,0);
        return res;
    }
    public static void backtracking(int[] candidates, int target,int sum,List<List<Integer>> res,List<Integer> templist,int start){
        if(sum==target){
            res.add(new ArrayList<>(templist));
            return;
        }

        for(int i=start;i<candidates.length;i++){
            if(i>start && candidates[i]==candidates[i-1]) continue; //去重
            if(sum+candidates[i]>target) break;
            templist.add(candidates[i]);
            sum+=candidates[i];
            backtracking(candidates,target,sum,res,templist,i+1);
            templist.remove(templist.size()-1);
            sum-=candidates[i];
        }
        return;
    }
}
