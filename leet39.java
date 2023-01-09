import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class leet39 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/combination-sum/
//        int[] candidates={2,3,6,7};
//        int target=7;
        int[] candidates={2,3,5};
        int target=8;
//        int[] candidates={2};
//        int target=1;
        System.out.println(combinationSum(candidates,target));
    }
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        //Runtime: 4 ms, faster than 84.09% of Java online submissions for Combination Sum.
        //Memory Usage: 44.8 MB, less than 76.56% of Java online submissions for Combination Sum.
        List<List<Integer>> res=new ArrayList<>();
        Arrays.sort(candidates);
        if(candidates==null || candidates.length==0) return res;
        if(candidates[0]>target) return res;
        List<Integer> templist=new ArrayList<>();

        backtracking(candidates,target,res,templist,0,0);
        return res;
    }
    public static void backtracking(int[] candidates, int target,List<List<Integer>> res,List<Integer> templist,int sum, int idx){
//        if(numberofnums<=0) return;

//        if (sum>target) {
//            numberofnums--;
//            return;
//        } //剪枝
        //以及上面的版本一的代码大家可以看到，对于sum已经大于target的情况，其实是依然进入了下一层递归，只是下一层递归结束判断的时候，会判断sum > target的话就返回。
        //其实如果已经知道下一层的sum会大于target，就没有必要进入下一层递归了。
        //那么可以在for循环的搜索范围上做做文章了。
        //对总集合排序之后，如果下一层的sum（就是本层的 sum + candidates[i]）已经大于target，就可以结束本轮for循环的遍历。

        if (sum==target){
            res.add(new ArrayList<>(templist));
            return;
        }

        for(int i=idx; i<candidates.length;i++){
            if (sum + candidates[i] > target) break; //提前剪枝
            templist.add(candidates[i]);
            sum+=candidates[i];
            backtracking(candidates,target,res,templist,sum,i);
            //写i还是写idx 可别昏头了，这里太容易错了，千万带入实际的数字检查一下
            //保证单向选择，前面有了 2 2 3 ，后面修改下次选择范围 保证不会出现3 2 3
            templist.remove(templist.size()-1);
            sum-=candidates[i];
        }
        return;

//        本题还需要startIndex来控制for循环的起始位置，对于组合问题，什么时候需要startIndex呢？
//        我举过例子，如果是一个集合来求组合的话，就需要startIndex，例如：77.组合 (opens new window)，216.组合总和III (opens new window)。
//        如果是多个集合取组合，各个集合之间相互不影响，那么就不用startIndex，例如：17.电话号码的字母
//        本题和我们之前讲过的77.组合 (opens new window)、216.组合总和III (opens new window)有两点不同：本题和我们之前讲过的77.组合 (opens new window)、216.组合总和III (opens new window)有两点不同：
//        本题和我们之前讲过的77.组合 (opens new window)、216.组合总和III (opens new window)有两点不同：
//
//        组合没有数量要求
//        元素可无限重复选取
//        针对这两个问题，我都做了详细的分析。
//        并且给出了对于组合问题，什么时候用startIndex，什么时候不用，并用17.电话号码的字母组合 (opens new window)做了对比。
//        最后还给出了本题的剪枝优化，这个优化如果是初学者的话并不容易想到。
//        在求和问题中，排序之后加剪枝是常见的套路！
    }
}
