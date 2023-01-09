import java.util.*;

public class leet18 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/4sum/
//        int[]nums={1,0,-1,0,-2,2};
//        int target=0;
//        int[]nums={2,2,2,2,2};
//        int target=8;
//        int[]nums={1000000000,1000000000,1000000000,1000000000};
//        int target=-294967296;
        int[] nums={-1000000000,-1000000000,1000000000,-1000000000,-1000000000};
        int target=294967296;
        List res=fourSum(nums, target);
        System.out.println(res);
    }
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);

        if(nums.length<4){
            List<List<Integer>> res=new ArrayList<>();
            return res;
        }

        if((target<0 && nums[0]>0) || (target>0 && nums[nums.length-1]<0) ){
            List<List<Integer>> res=new ArrayList<>();
            return res;
        }

        if(target==Integer.MAX_VALUE || target==Integer.MIN_VALUE){
            List<List<Integer>> res=new ArrayList<>();
            return res;
        }

        List<List<Integer>> res=new ArrayList<>();
        Set<String> set=new HashSet<>();

        for(int i=0;i<nums.length-3;i++){
            for(int j=nums.length-1;j>=i+3;j--){
                int left=i+1;
                int right=j-1;
                while(left<right) {
                    int foursum=nums[i]+nums[left]+nums[right]+nums[j];
                    if (foursum == target) {
                        String s = nums[i] + " " + nums[left] + " " + nums[right] + " " + nums[j];
                        if (!set.contains(s)) {
                            set.add(s);
                            List<Integer> list = new ArrayList<>();
                            list.add(nums[i]);
                            list.add(nums[left]);
                            list.add(nums[right]);
                            list.add(nums[j]);
                            res.add(list);
                        }
                        right--;
                    }else if(foursum>target){
                        right--;
                    }else if(foursum<target){
                        left++;
                    }
                }

            }
        }
        return res;
    }



    public static List<List<Integer>> fourSum2(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for(int i=0; i<nums.length;i++){
            if (nums[i] > 0 && nums[i] > target) {
                return result;
            }

            if (i > 0 && nums[i - 1] == nums[i]) {
                continue;
            }
            for(int j=i+1; j<nums.length;j++){
                if(j!=i+1 && nums[j]==nums[j-1]) continue;
                int left=j+1;
                int right=nums.length-1;
                while(left<right){
                    long sum = (long) nums[i] + nums[j] + nums[left] + nums[right];
                    if(sum==target){
                        result.add(Arrays.asList(nums[i],nums[j],nums[left],nums[right]));
                        while (right > left && nums[right] == nums[right - 1]) right--;
                        while (right > left && nums[left] == nums[left + 1]) left++;
                        left++;
                        right--;
                    }else if(sum>target){
                        right--;
                    }else if(sum<target){
                        left++;
                    }
                }
            }
        }


        return result;
    }
}
