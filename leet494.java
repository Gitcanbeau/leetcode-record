import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class leet494 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/target-sum/
        int[] nums={1,1,1,1,1}; //5
        int k=3;
//        int[] nums={1}; //1
//        int k=1;
//        int[] nums={1,0,0};//4
//        int k=1;
//        int[] nums={10}; //1
//        int k=-10;
//        int[] nums={0,0,0,0,1}; //16
//        int k=1;
//        int[] nums={0,0,0,0}; //16
//        int k=0;
        System.out.println(findTargetSumWays1(nums,k));
        System.out.println(findTargetSumWays2(nums,k));
        System.out.println(findTargetSumWays(nums,k));
    }
    //本题则是装满有几种方法。其实这就是一个组合问题了。
    //
    //确定dp数组以及下标的含义
    //dp[j] 表示：填满j（包括j）这么大容积的包，有dp[j]种方法
    //
    //其实也可以使用二维dp数组来求解本题，dp[i][j]：使用 下标为[0, i]的nums[i]能够凑满j（包括j）这么大容量的包，有dp[i][j]种方法。
    public static int findTargetSumWays1(int[] nums, int target) {
        //Runtime: 7 ms, faster than 91.26% of Java online submissions for Target Sum.
        //Memory Usage: 41.6 MB, less than 81.10% of Java online submissions for Target Sum.
        int sum = 0;
        for (int i = 0; i < nums.length; i++) sum += nums[i];
        if ((target + sum) % 2 != 0) return 0;
        int size = (target + sum) / 2;
        if(size < 0) size = -size;
        int[] dp = new int[size + 1];
        dp[0] = 1;
        for (int i = 0; i < nums.length; i++) {
            for (int j = size; j >= nums[i]; j--) {
                //所以求组合类问题的公式，都是类似这种：
                dp[j] += dp[j - nums[i]];
            }
        }
        return dp[size];
    }
    public static int findTargetSumWays2(int[] nums, int target) {
        //Runtime: 10 ms, faster than 82.11% of Java online submissions for Target Sum.
        //Memory Usage: 44.6 MB, less than 35.88% of Java online submissions for Target Sum.
        //终于自己费劲改对了，这个初始化也太他妈费劲了
        //此时问题就是在集合nums中找出和为sum1的组合。

        int sum=0;
        for(int i=0; i<nums.length;i++){
            sum+=nums[i];
        }
        if(sum<target || -sum>target) return 0;
        if((sum+target)%2!=0) return 0;


        //例外情况都被排除掉了
        int sum1=(target+sum)/2;

        int[][] dp=new int[nums.length][sum1+1]; //存的是从0-i个数中挑取一些数字的凑满容量为j的组成方式


        for(int i=0;i< nums.length;i++){
            for(int j=0;j<sum1+1;j++){
                if(nums[i]==j) {
                    dp[i][j]=1;
                }
            }
        }

        for(int i=0;i< nums.length;i++){
            if(nums[i]==0){
                dp[i][0]=2;
            }
        }


        for(int i=1;i< nums.length;i++) {
            for (int j = 0; j < sum1 + 1; j++) {

                if(j>=nums[i]) {
                    //所以求组合类问题的公式，都是类似这种：
                    dp[i][j] = dp[i - 1][j - nums[i]] + dp[i-1][j];

                }else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }


//        for(int i=0;i< nums.length;i++){
//            for(int j=0;j<sum1+1;j++){
//                System.out.print(dp[i][j]+",");
//            }
//            System.out.println();
//        }

        return dp[nums.length-1][sum1];
    }

    public static int findTargetSumWays(int[] nums, int target) {
        //分成两组，一组sum1 另一组sum2，sum1-sum2=target, sum1+sum2=sum sum1=(target+sum)/2
        //Runtime: 1245 ms, faster than 5.73% of Java online submissions for Target Sum.
        //Memory Usage: 71.1 MB, less than 10.61% of Java online submissions for Target Sum.
//        Arrays.sort(nums);
        int sum=0;
        int countofzero=0;
        for(int i=0; i<nums.length;i++){
            sum+=nums[i];
            if(nums[i]==0) countofzero++;
        }
        if(sum<target || -sum>target) return 0;
        if((sum+target)%2!=0) return 0;


        //例外情况都被排除掉了
        int sum1=(target+sum)/2;

        List<List<Integer>> res=new ArrayList<>();
        List<Integer> path=new ArrayList<>();
        boolean[] visited=new boolean[nums.length];
        backtracking(nums,sum1,res,path,visited,0,0);
        int coefficient= (int) Math.pow(2,countofzero);
        return res.size()*coefficient;
    }
    public static void backtracking(int[] nums,int sum1,List<List<Integer>> res,List<Integer> path,boolean[] visited,int cursum,int startidx){
        if(cursum==sum1){
            res.add(new ArrayList<>(path));
            return;
        }
        for(int i=startidx;i<nums.length;i++){
            if(nums[i]==0 || visited[i]==true || cursum+nums[i]>sum1) continue;
            visited[i]=true;
            path.add(i);
            backtracking(nums,sum1,res,path,visited,cursum+nums[i],i+1);
            path.remove(path.size()-1);
            visited[i]=false;
        }
        return;
    }
}
