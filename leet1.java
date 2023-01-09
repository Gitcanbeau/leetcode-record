import java.util.HashMap;
import java.util.Map;

public class leet1 {
    public static void main(String[] args) {
        //You may assume that each input would have exactly one solution,
        // and you may not use the same element twice.
        //You can return the answer in any order.
        int[] nums={2,7,11,15};
        int target=9;
        int[] indices=twoSum2(nums,target);
        for(Integer i: indices){
            System.out.println(i);
        }
        //Runtime: 47 ms, faster than 46.01% of Java online submissions for Two Sum.
        //Memory Usage: 42.1 MB, less than 98.41% of Java online submissions for Two Sum.
    }
    public static int[] twoSum(int[] nums, int target) {
       int[] indices={0,0};
        for(int i=0; i<nums.length;i++){
            for(int j=i+1; j<nums.length;j++){
                if(target==nums[i]+nums[j]){
                    indices[0]=i;
                    indices[1]=j;
                }
            }
        }
        return indices;
    }
    public static int[] twoSum2(int[] nums, int target) {
        int[] res = new int[2];
        if(nums == null || nums.length == 0){
            return res;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            int temp = target - nums[i];
            if(map.containsKey(temp)){
                res[1] = i;
                res[0] = map.get(temp);
            }
            map.put(nums[i], i);
        }
        return res;
    }
}
