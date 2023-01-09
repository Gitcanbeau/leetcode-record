import java.util.Arrays;

public class leet977 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/squares-of-a-sorted-array/
        int[] nums={-4,-1,0,3,10};
        int[] res=sortedSquares(nums);
        for(int i=0; i<res.length;i++){
            System.out.print(res[i]+" ");
        }
        //Runtime: 16 ms, faster than 14.12% of Java online submissions for Squares of a Sorted Array.
        //Memory Usage: 55.9 MB, less than 32.74% of Java online submissions for Squares of a Sorted Array.
    }
    public static int[] sortedSquares(int[] nums) {
        //brute force
        for(int i=0; i<nums.length;i++){
            nums[i]= (int) Math.pow(nums[i],2);
        }
        Arrays.sort(nums);
        return nums;
    }



    public static int[] sortedSquares2(int[] nums) {
        //双指针，找到正负交界的地方，从两头读，添加到新的arr里面，没说in-place我就可以新建一个arr
        int[] res=new int[nums.length];
        int left=0;
        int right=nums.length-1;
        int indexinres=nums.length-1;
        while(indexinres>=0){
            if(Math.abs(nums[left])<=Math.abs(nums[right])){
                res[indexinres]=nums[right];
                indexinres--;
                right--;
            }else{
                res[indexinres]=nums[left];
                indexinres--;
                left++;
            }
        }

        for(int i=0; i<res.length;i++){
            res[i]=(int) Math.pow(res[i],2);
        }

        return res;
    }


}
