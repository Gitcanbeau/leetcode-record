import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class leet216 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/combination-sum-iii/
        int k=9;
        int n=45;
//        int k=4;
//        int n=1;
        System.out.println(combinationSum3(k,n));
    }

    public static List<List<Integer>> combinationSum3(int k, int n) {
        //Runtime: 1 ms, faster than 87.16% of Java online submissions for Combination Sum III.
        //Memory Usage: 42.1 MB, less than 12.66% of Java online submissions for Combination Sum III.
        List<List<Integer>> res=new ArrayList<>();
        if(maxsumofk(k)<n || minsumofk(k)>n) return res;
        List<Integer> templist=new ArrayList<>();
        int sum=0;
        backtracking(k,n,res,templist,sum,1);
        return res;
    }
    public static void backtracking(int k, int n, List<List<Integer>> res,List<Integer> templist,int sum,int start){
        if(sum>n) return; //剪枝，要灵活剪枝，模版很对很好但是人也要灵活
        if(templist.size()==k && sum==n){
            res.add(new ArrayList<>(templist));
            return;
        }

        for(int i=start;i<=9-(k-templist.size())+1;i++){ //剪枝这里的可选方案的总数是9别搞错了
            templist.add(i);
            sum+=i;
            backtracking(k,n,res,templist,sum,i+1);
            templist.remove(templist.size()-1);
            sum-=i;
        }
    }
    public static int maxsumofk(int k){
        int num=9;
        int sum=0;
        for(int i=0;i<k;i++){
            sum+=num;
            num--;
        }
        return sum;
    }
    public static int minsumofk(int k){
        int num=1;
        int sum=0;
        for(int i=0;i<k;i++){
            sum+=num;
            num++;
        }
        return sum;
    }
}
