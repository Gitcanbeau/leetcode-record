import java.util.Arrays;

public class leet611sol2_slidingw_bs {
    public static void main(String[] args) {
        //https://leetcode.com/problems/valid-triangle-number/
        int[] nums={2,2,3,4}; //3
        System.out.println(triangleNumber(nums));
        //Runtime: 355 ms, faster than 18.47% of Java online submissions for Valid Triangle Number.
        //Memory Usage: 42.1 MB, less than 85.40% of Java online submissions for Valid Triangle Number.
    }
    public static int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int count=0;
        for(int right=nums.length-1;right>=2;right--){
            int mid=right-1;
            int left=0;
            //不想通过++--找到符合该right和mid的left值，我直接二分搜索返回合适的left值，然后我就直接count+=
            int indexleft=0;
            while(indexleft<mid){
                indexleft=binarysearch(nums,left,mid,nums[right]-nums[mid]);
                count=count+(mid-(indexleft+1)+1);
                mid--;
            }
        }
        return count;
    }
    public static int binarysearch(int[] nums, int start, int end, int target){
        //左闭右开

        while(start<end){
            int mid=start+(end-start)/2;
            if(nums[mid]>target){
                end=mid;
            }else{
                start=mid+1;
            }
        }
        return start;
    }
}
