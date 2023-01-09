import java.util.Arrays;

public class leet16 {
    public static void main(String[] args) {
        int[] nums={-1,2,1,-4};
        int target=1;
        System.out.println(threeSumClosest(nums,target));
    }
    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int res=nums[0]+nums[1]+nums[2];
        for(int i=0; i<nums.length-2;i++){
            if(i!=0 && nums[i-1]==nums[i]) continue;
            int left=i+1;
            int right=nums.length-1;
            while(left<right) {
                int threesum = nums[i] + nums[left] + nums[right];
                int temp_res = Math.abs(Math.abs(threesum) - Math.abs(target));
                if (temp_res == 0) {
                    return target;
                }

            }




        }
        return res;
    }
}
