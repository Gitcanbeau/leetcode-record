public class leet55 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/jump-game/
//        int[] nums={2,3,1,1,4};
        int[] nums={3,2,1,0,4};
//        int[] nums={0};
        System.out.println(canJump(nums));
    }
    public static boolean canJump(int[] nums) {
        //Runtime: 3 ms, faster than 76.01% of Java online submissions for Jump Game.
        //Memory Usage: 68.4 MB, less than 29.59% of Java online submissions for Jump Game.
        //贪心算法局部最优解：每次取最大跳跃步数（取最大覆盖范围），整体最优解：最后得到整体最大覆盖范围，看是否能到终点。
        if (nums.length == 1) {
            return true;
        }
        //覆盖范围, 初始覆盖范围应该是0，因为下面的迭代是从下标0开始的
        int coverRange = 0;
        //在覆盖范围内更新最大的覆盖范围
        for (int i = 0; i <= coverRange; i++) {
            //即便遇到0，我也可以从我的coverrange里面遍历i去看别的i 跳 nums[i] 范围能否越过0的那步，越过了就能往后走，越不过就会因为i到达coverrange而出循环
            coverRange = Math.max(coverRange, i + nums[i]);
            if (coverRange >= nums.length - 1) {
                return true;
            }
        }
        return false;
    }
}
