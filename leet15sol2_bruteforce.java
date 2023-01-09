import java.util.*;

public class leet15sol2_bruteforce {
    public static void main(String[] args) {
//        int[] nums = {-1, 0, 1, 2, -1, -4, -1};
//        int[] nums = {0, 0, 0, 0, 0};
        int[] nums = {-1, 0, 1, 2, -1, -4, -2, -3, 3, 0, 4};

        List<List<Integer>> lall = threeSum(nums);
        System.out.println(lall);
        //time limit exceeded
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        Set<String> set = new HashSet<>();
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if(nums[i]+nums[j]+nums[k]==0){
                        String s=nums[i]+""+nums[j]+""+nums[k];
                        if(set.contains(s)){
                            break;
                        }
                        set.add(s);
                        System.out.println(s);
                        List<Integer> temp=new ArrayList<>();
                        temp.add(nums[i]);
                        temp.add(nums[j]);
                        temp.add(nums[k]);
                        res.add(temp);
                    }
                }
            }
        }
        return res;
    }
}

