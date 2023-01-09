public class  leet376 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/wiggle-subsequence/
    }
    public static int wiggleMaxLength(int[] nums) {
        //Runtime: 1 ms, faster than 44.33% of Java online submissions for Wiggle Subsequence.
        //Memory Usage: 42 MB, less than 24.69% of Java online submissions for Wiggle Subsequence.
        //局部最优：删除单调坡度上的节点（不包括单调坡度两端的节点），那么这个坡度就可以有两个局部峰值。
        //
        //整体最优：整个序列有最多的局部峰值，从而达到最长摆动序列。
        //
        //局部最优推出全局最优，并举不出反例，那么试试贪心！
        //
        //（为方便表述，以下说的峰值都是指局部峰值）
        //
        //实际操作上，其实连删除的操作都不用做，因为题目要求的是最长摆动子序列的长度，所以只需要统计数组的峰值数量就可以了（相当于是删除单一坡度上的节点，然后统计长度）
        //
        //这就是贪心所贪的地方，让峰值尽可能的保持峰值，然后删除单一坡度上的节点。
        if (nums.length <= 1) {
            return nums.length;
        }
        //当前差值
        int curDiff = 0;
        //上一个差值
        int preDiff = 0;
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            //得到当前差值
            curDiff = nums[i] - nums[i - 1];
            //如果当前差值和上一个差值为一正一负
            //等于0的情况表示初始时的preDiff
            if ((curDiff > 0 && preDiff <= 0) || (curDiff < 0 && preDiff >= 0)) {
                count++;
                preDiff = curDiff;
            }
        }
        return count;
    }
}
