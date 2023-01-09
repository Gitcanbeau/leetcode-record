public class leet45 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/jump-game-ii/
        int[] nums={2,3,1,1,4}; //2
//        int[] nums={2,3,0,1,4}; //2
//        int[] nums={2,1,0,1,4}; //-1
//        int[] nums={2,2,0,1,4};//3
//        int[] nums={2,2,0,2,2,0,4};//4
//        int[] nums={2,2,0,2,2,0,2,2,0,4};//6
        System.out.println(jump(nums));
        System.out.println(jump2(nums));
        System.out.println(jump3(nums));
    }
    public static int jump(int[] nums) {
        //错误写法，我的本意也是说一次尽可能走的远，但是最远步的更新范围有可能比较远步的更新范围小
        //所以我应该是把我目前能走的最远范围的步骤都走一遍，然后找到哪一步能让我走的最远，将该最远范围记录下来作为下一次我能走的范围，
        // 等我走完目前的最远范围的时候， 我就去更新遍历范围为上一次记录的updated最远范围，更新次数就是我的步数
        if(nums.length==1){
            return 1;
        }
        int count=0;
        int cover_range=0;

        for(int i=cover_range;i>=0;i--){
            int old_cover_range=cover_range;
            cover_range=Math.max(cover_range,nums[i]+i);
            if(cover_range<=old_cover_range){
                continue;
            }

            if(cover_range>old_cover_range){ //扩大最大覆盖范围的时候，步数+1
                i=cover_range+1;
                //万一我这步扩大了范围，记得更新下一次开始跳的位置还是从最远的位置开始，因为出了本次循环立刻i--，所以我这里cover_range+1
                count++;
            }

            if(cover_range>=nums.length-1){
                return count;
            }
        }

        if(cover_range<nums.length-1){
            return -1;
        }
        return count;
    }

    public static int jump2(int[] nums) {
        //Runtime: 5 ms, faster than 63.62% of Java online submissions for Jump Game II.
        //Memory Usage: 49.2 MB, less than 64.26% of Java online submissions for Jump Game II.
        if (nums == null || nums.length == 0 || nums.length == 1) {
            return 0;
        }
        //记录跳跃的次数
        int count=0;
        //当前的覆盖最大区域
        int curDistance = 0;
        //最大的覆盖区域
        int maxDistance = 0;
        for (int i = 0; i < nums.length; i++) {
            //在可覆盖区域内更新最大的覆盖区域
            maxDistance = Math.max(maxDistance,i+nums[i]);
            //说明当前一步，再跳一步就到达了末尾
            if (maxDistance>=nums.length-1){
                count++;
                break;
            }
            //走到当前覆盖的最大区域时，更新下一步可达的最大区域
            if (i==curDistance){
                curDistance = maxDistance;
                count++;
            }
        }
        return count;
    }

    public static int jump3(int[] nums) {
        //Runtime: 4 ms, faster than 68.46% of Java online submissions for Jump Game II.
        //Memory Usage: 49.7 MB, less than 25.29% of Java online submissions for Jump Game II.
        //很喜欢这个方法
        int count = 0;
        // 当前覆盖的最远距离下标
        int end = 0;
        // 下一步覆盖的最远距离下标
        int maxcoverrange = 0;
        for (int i = 0; i <= end && end < nums.length - 1; ++i) {
            maxcoverrange = Math.max(maxcoverrange, i + nums[i]);
            // 可达位置的改变次数就是跳跃次数
            if (i == end) {
                end = maxcoverrange;
                count++;
            }
        }
        return count;
    }
}
