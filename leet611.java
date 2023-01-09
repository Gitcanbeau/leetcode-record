import java.util.Arrays;

public class leet611 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/valid-triangle-number/
        int[] nums={2,2,3,4};
        System.out.println(triangleNumber(nums));
    }
    public static int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int left=0;
        int right=2;
        int currentright=1;
        int count=0;
        while(right<nums.length ){
            if(nums[left]+nums[left+1]>nums[right]){
                if(currentright!=right){
                    int newaddedcount=combination(right-left);
                    count=count+newaddedcount;
                    currentright=right;
                }
                right++;
            }else{
                left++;
            }
        }
        return count;
    }
    public static int combination(int num){
        int[] res=new int[num+1];
        res[0]=1;
        res[1]=1; //1的阶乘
        for( int i=2; i<=num;i++){
            res[i]=res[i-1]*i;
        }

        int newaddedcount=res[num]/(res[2]*res[num-2]);

        return newaddedcount;
    }
}
