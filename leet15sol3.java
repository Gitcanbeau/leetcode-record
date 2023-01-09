import java.util.*;

public class leet15sol3 {
    public static void main(String[] args) {
//        int[] nums = {-1,0,1,2,-1,-4,-1};
//        int[] nums = {0,0,0,0,0};
        int[] nums = {-1,0,1,2,-1,-4,-2,-3,3,0,4};
        List<List<Integer>> lall = threeSum(nums);
        System.out.println(lall);
        //Runtime: 784 ms, faster than 12.94% of Java online submissions for 3Sum.
        //Memory Usage: 120.2 MB, less than 17.43% of Java online submissions for 3Sum.
        //Runtime: 1135 ms, faster than 8.97% of Java online submissions for 3Sum.
        //Memory Usage: 119.4 MB, less than 18.20% of Java online submissions for 3Sum.
    }


    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        Set<String> set = new HashSet<>();
        for(int i=0; i<nums.length; i++){
            if(nums[i]>0){
                break;
            }

            int j =i+1;
            int k=nums.length-1;
            while(j<k){
                if(nums[i]+nums[j]+nums[k]<0){
                    j++;
                }else if(nums[i]+nums[j]+nums[k]>0){
                    k--;
                }else{
                    String s=nums[i]+""+nums[j]+""+nums[k]; //为什么用string，其实这个方法懂了但是方法实现的else部分没有特别懂
                    if(!set.contains(s)){
                        set.add(s);
                        List<Integer> temp=new ArrayList<>();
                        temp.add(nums[i]);
                        temp.add(nums[j]);
                        temp.add(nums[k]);
                        res.add(temp);
                    }
                    j++;
                }

                }
            }
        return res;
    }
}
