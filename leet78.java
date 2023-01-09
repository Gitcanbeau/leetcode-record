import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class leet78 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/subsets/
        int[] nums={1,2,3,4};
        System.out.println(subsets(nums));
    }
    public static List<List<Integer>> subsets(int[] nums) {
        //Runtime: 1 ms, faster than 89.30% of Java online submissions for Subsets.
        //Memory Usage: 43.6 MB, less than 31.87% of Java online submissions for Subsets.
        List<List<Integer>> res=new ArrayList<>();
        List<Integer> templist=new ArrayList<>();
        backtracking(nums,res,templist,0);
        return res;
    }
    public static void backtracking(int[] nums,List<List<Integer>> res,List<Integer> templist, int startidx){
        //unique element, 不用去重
        res.add(new ArrayList<>(templist)); //遍历整棵树的节点，每个都加进来
        if(startidx>=nums.length){
            return;
        }
        for(int i=startidx;i<nums.length;i++){
            templist.add(nums[i]);
            backtracking(nums,res,templist,i+1);
            templist.remove(templist.size()-1);
        }
        return;
    }
    //那么既然是无序，取过的元素不会重复取，写回溯算法的时候，for就要从startIndex开始，而不是从0开始！
    //求取子集问题，不需要任何剪枝！因为子集就是要遍历整棵树。
    //但是要清楚子集问题和组合问题、分割问题的的区别，子集是收集树形结构中树的所有节点的结果。
    //而组合问题、分割问题是收集树形结构中叶子节点的结果。
}
