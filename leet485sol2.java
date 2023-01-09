public class leet485sol2 {
    public static void main(String[] args) {
        int[] nums = {1, 1, 0, 1, 1, 1};
        int max = findMaxConsecutiveOnes(nums);
        System.out.println("max is "+max);
    }

    //Runtime: 4 ms, faster than 25.09% of Java online submissions for Max Consecutive Ones.
    //Memory Usage: 56.7 MB, less than 58.51% of Java online submissions for Max Consecutive Ones.
    public static int findMaxConsecutiveOnes(int[] nums) {
        int count=0; //count the number of consecutive ones
        int result=0; //save the count once encountering a new zero
        for(int i =0 ; i<nums.length; i++){
            if(nums[i]==1){
                count++;
            }else{
                result= getMax(count, result);
                count=0;
            }
            result= getMax(count, result);

        }
        return result;
    }

    public static int getMax(int a, int b){
        if(a>=b){
            return a;
        }else{
            return b;
        }
    }
}
