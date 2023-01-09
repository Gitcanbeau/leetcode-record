import java.util.ArrayList;
import java.util.List;

public class leet46 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/permutations/
        int[] nums={1,2,3};
        System.out.println(permute(nums));
        System.out.println(permute2(nums));
    }
    public static List<List<Integer>> permute(int[] nums) {
        //Runtime: 1 ms, faster than 98.37% of Java online submissions for Permutations.
        //Memory Usage: 42.6 MB, less than 88.32% of Java online submissions for Permutations.
        List<List<Integer>> res=new ArrayList<>();
        List<Integer> templist=new ArrayList<>();
        backtracking(nums,res,templist);
        return res;
    }
    public static void backtracking (int[] nums,List<List<Integer>> res,List<Integer> templist){
        //distinct,所以可以用!templist.contains(nums[i])判断
        if(templist.size()==nums.length){
            res.add(new ArrayList<>(templist));
            return;
        }
        for(int i=0; i<nums.length;i++){ //因为可以重复使用（我的意思是【1，2，3】和【1，3，2】里面的1可以），所以从0开始，而不是从startidx开始
            if(!templist.contains(nums[i])){
                templist.add(nums[i]);
                backtracking(nums,res,templist);
                templist.remove(templist.size()-1);
            }else{
                continue; //已经在templist里面我就再找别人呗。。
            }
        }
    }
    public static List<List<Integer>> permute2(int[] nums) {
        //Runtime: 1 ms, faster than 98.37% of Java online submissions for Permutations.
        //Memory Usage: 45 MB, less than 29.18% of Java online submissions for Permutations.
        List<List<Integer>> res=new ArrayList<>();
        List<Integer> templist=new ArrayList<>();
        boolean[] used=new boolean[nums.length]; //通用模版
        backtracking2(nums,res,templist,used);
        return res;
    }
    public static void backtracking2 (int[] nums,List<List<Integer>> res,List<Integer> templist,boolean[] used){
        //distinct
        if(templist.size()==nums.length){
            res.add(new ArrayList<>(templist));
            return;
        }
        for(int i=0; i<nums.length;i++){ //因为可以重复使用（我的意思是【1，2，3】和【1，3，2】里面的1可以），所以从0开始，而不是从startidx开始
            if(used[i]==true) continue;
            templist.add(nums[i]);
            used[i]=true;
            backtracking2(nums,res,templist,used);
            templist.remove(templist.size()-1);
            used[i]=false;
        }
        return;
    }
}
