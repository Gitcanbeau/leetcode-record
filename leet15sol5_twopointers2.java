import java.util.*;

public class leet15sol5_twopointers2 {
    public static void main(String[] args) {
//        int[] nums = {-1,0,1,2,-1,-4,-1};
//        int[] nums = {0,0,0,0,0};
//        int[] nums = {-1,0,1,2,-1,-4,-2,-3,3,0,4};
        int[] nums = {-2,0,1,1,2};
        List<List<Integer>> lall = threeSum(nums);
        System.out.println(lall);
        //Runtime: 1085 ms, faster than 9.52% of Java online submissions for 3Sum.
        //Memory Usage: 119.7 MB, less than 17.52% of Java online submissions for 3Sum.
    }
    public static List<List<Integer>> threeSum(int[] nums) { //这个版本是自己写出来的，好玩
        Arrays.sort(nums);
        List<List<Integer>> res=new ArrayList<>();
        Set<String> set = new HashSet<>();
        for(int i=0; i<nums.length-1;i++){
            int left=i+1;
            int right=nums.length-1;
            while(left<right) {
                int threesum=nums[i]+nums[left]+nums[right];
                if(threesum==0){
                    String s=nums[i]+""+nums[left]+""+nums[right];
                    if(!set.contains(s)){ //还是要靠string和hashset检查是否重复。
                        set.add(s);
                        List<Integer> list=new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[left]);
                        list.add(nums[right]);
                        res.add(list);
                        //break;//-2 0 2 就直接退出的话会损失 -2 1 1
                    }
                    right--; //动left也行，关键是要移动，你出循环的条件是左右指针相遇，而不是你3sum得不得0，得不得0只是决定你是否存储
                }else if(threesum>0){
                    right--;
                }else if(threesum<0){
                    left++;
                }
            }
        }
        return res;
    }




    public static List<List<Integer>> threeSum2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                return result;
            }

            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int left = i + 1;
            int right = nums.length - 1;
            while (right > left) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum > 0) {
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    while (right > left && nums[right] == nums[right - 1]) right--;
                    while (right > left && nums[left] == nums[left + 1]) left++;

                    right--;
                    left++;
                }
            }
        }
        return result;
    }
}
