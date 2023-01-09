import java.util.Arrays;
import java.util.Collections;

public class leet611sol2_slidingw {
    public static void main(String[] args) {
        //https://leetcode.com/problems/valid-triangle-number/
        int[] nums={2,2,3,4}; //3
        System.out.println(triangleNumber(nums));
        //Runtime: 35 ms, faster than 86.05% of Java online submissions for Valid Triangle Number.
        //Memory Usage: 42 MB, less than 88.13% of Java online submissions for Valid Triangle Number.
    }
    public static int triangleNumber(int[] nums) {
    // 我觉得你的pointer得是比较两个较大的值和最小的值，从而判断区间能不能直接简化成+=区间；
    // 如果你是两个较小值和最大值比较的话，你没办法去判断应该增加第二小的值还是缩小最大值
    // 时间复杂度o(n^2) 内循环使用binary search可以把时间复杂度降低为o(n logn), 但是不知道为什么很慢。。。
        Arrays.sort(nums);
        int count=0;
        for(int right=nums.length-1;right>=2;right--){
            int mid=right-1; //每次走完一个mid都给我default一遍指针位置
            int left=0;
            while(left<mid) { //不相遇就一直给我走，直到这个mid边的所有选项走完
                if (nums[left] + nums[mid] > nums[right]) {
                    count = count + (mid - (left + 1) + 1);
                    mid--;
                } else {
                    left++;
                }
            }
        }
        return count;
    }
//    public static int triangleNumber(int[] nums) {
//        // 我觉得你的pointer得是比较中间值和最小的值，从而判断区间能不能直接简化成+=区间；
//        // 如果你是中间值和最大值比较的话，你没办法去判断应该增加中间值还是缩小最大值
//        // 时间复杂度o(n^2) 内循环使用binary search可以把时间复杂度降低为o(n logn)
//        //2 2 3 4 就是一个很好的例子，我中间指针在index=1位置的话，我right--，得到2 2 3
//        //但是因为中间指针没办法往右走而错过了 第一个2 3 4
//        //根本原因是你的中间指针和最大值的指针只能挑一个动
//        //4 3 2 2 的话，中间的所有可能性我都一次加完， 4 3 2和4 3 2
//        //然后是 3 2 2
//        //4 4 3 2 2 也可以 4 4 2，4 4 2，4 4 3
//        //然后是4 3 2， 4 3 2
//        //然后是3 2 2
//        //你要是2 2 3 4 4 动右指针得到2 2 3会错过2 3 4 和2 3 4，动中间指针得到2 3 4， 2 4 4，会错过2 2 3
//        //这道题最核心的是滑动窗口，但是最难想的从大边遍历
//        Arrays.sort(nums);
//        int count=0;
//        for(int left=0;left<nums.length-2;left++){
//            int mid=left+1; //每次走完一个mid都给我default一遍指针位置
//            int right=nums.length-1;
//            while(mid<right) { //不相遇就一直给我走，直到这个mid边的所有选项走完
//                if (nums[left] + nums[mid] > nums[right]) {
//                    count = count + (right - (mid + 1) + 1);
//                    right--;
//                } else {
//                    mid++;
//                }
//            }
//        }
//        return count;
//    }
}
