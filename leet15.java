
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class leet15 {
    public static void main(String[] args) {
        int[] nums = {-1,0,1,2,-1,-4,-1};
//        int[] nums = {0,0,0,0,0};
//        int[] nums = {-1,0,1,2,-1,-4,-2,-3,3,0,4};
        List<List<Integer>> lall = threeSum(nums);
        System.out.println(lall);
    }


    public static List<List<Integer>> threeSum(int[] nums) {
//        backtracking
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> track=new ArrayList<>();
        boolean[] used=new boolean[nums.length];
        Arrays.sort(nums);
        backtracking(nums, track,used,0, res);
        return res;
    }
    public static void backtracking(int[] nums, List<Integer> track, boolean[] used, int start, List<List<Integer>> res){
        if(track.size()==3){ //弄一个出口就行了，是不是0在里面检查。size=3才是出口条件，等不等于0不是出口条件而是保存条件
            int checksum=0;
            for(int i: track){
                checksum=checksum+i;
            }
            if(checksum==0){
                List<Integer> tracksuccess=new ArrayList<>(track);//你原本的track还要取消选择走其他路径呢，所以你必须new一个list保存当前track结果
                if(!res.contains(tracksuccess)){
                    res.add(tracksuccess);
                }
            }
            return;
        }

//        for(int i=0; i<nums.length;i++){
//            if(used[i]){
//                continue;
//            }
//            track.add(nums[i]);
//            used[i]=true; //boolean[]虽然好理解，但是多走了很多无用路线，比如，-4，0，1 在-4开头的时候走了一遍， 0，-4，-1在0开头的时候又走了一遍，
//                          // 走过的组合应该减枝
//            backtracking(nums,track,used,res);
//            //取消上一次的选择
//            track.remove(track.size()-1);//移除最后一个
//            used[i]=false;
//        }

        for(int i=start; i<nums.length;i++){
            if(used[i]){
                continue;
            }
            track.add(nums[i]);
            used[i]=true; //boolean[]虽然好理解，但是多走了很多无用路线，比如，-4，0，1 在-4开头的时候走了一遍， 0，-4，-1在0开头的时候又走了一遍，

            backtracking(nums,track,used,i+1,res);// 走过的组合应该减枝 //这样就不用花心思检查res里面有没有这种重复的组合了

            //取消上一次的选择
            track.remove(track.size()-1);//移除最后一个
            used[i]=false;
        }
    }

}
