import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class leet77 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/combinations/
        int n=4;
        int k=2;
        System.out.println(combine(n,k));
//        System.out.println(combine2(n,k));
    }
    public static List<List<Integer>> combine(int n, int k) {
        //Runtime: 1 ms, faster than 100.00% of Java online submissions for Combinations.
        //Memory Usage: 43.6 MB, less than 91.63% of Java online submissions for Combinations.
        List<List<Integer>> res=new ArrayList<>();
        if (n<k) return res;
        List<Integer> templist=new ArrayList<>();
        backtracking(n,k, res,templist,1);
        return res;
    }
    public static void backtracking (int n, int k, List<List<Integer>> res, List<Integer> templist, int start){
        if(templist.size()==k){
            res.add(new ArrayList<>(templist));
            return;
        }

        for(int i=start; i<=n - (k - templist.size()) + 1;i++){ //剪枝
            templist.add(i);
            backtracking(n,k,res,templist,i+1);
            templist.remove(templist.size()-1);
        }
        return;
    }

}
