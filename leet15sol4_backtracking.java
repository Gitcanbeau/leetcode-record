import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class leet15sol4_backtracking {
    public static void main(String[] args) {
        int[] nums = {-1,0,1,2,-1,-4,-1};
//        int[] nums = {0,0,0,0,0};
//        int[] nums = {-1,0,1,2,-1,-4,-2,-3,3,0,4};

        List<List<Integer>> lall = threeSum(nums);
        System.out.println(lall);

    }
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> superlist = new ArrayList<>();
        Arrays.sort(nums);
        backtracking(nums, new ArrayList<>(), 0,superlist);
        return superlist;
    }

    public static void backtracking(int[] nums, List<Integer> list, int start,List<List<Integer>> superlist){
        //time limit exceeded
        /* is list full? then chek if list elements added = 0 */
        if(list.size() == 3){
            int check = 0;
            for(int i : list) check += i;
            if(check == 0) superlist.add(new ArrayList<>(list));
        }
        else{  /* add to list if not full and recursion call itself */
            for(int i=start; i<nums.length; i++){
                if(i>start && nums[i]==nums[i-1]) continue; //这人真聪明
                list.add(nums[i]);
                backtracking(nums, list, i+1,superlist);
                list.remove(list.size()-1);
            }
        }
        return;
    }



}
