import java.util.Arrays;

public class leet698 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/partition-to-k-equal-sum-subsets/
//        int[] nums={4,3,2,3,5,2,1};
//        int k=4;
//        int[] nums={2,2,2,2,3,4,5};
//        int k=4;
//        int[] nums={1,2,3,4};
//        int k=3;
        int[] nums={1,1,1,1};
        int k=4;
        System.out.println(canPartitionKSubsets(nums,k));
    }
    public static boolean canPartitionKSubsets(int[] nums, int k) {
        //time limit exceeded
        if(nums==null || nums.length<k) return false;
        int sum=0;
        for(int i=0; i<nums.length;i++){
            sum+=nums[i];
        }
        if(sum%k!=0) return false;
        int target=sum/k;

        boolean[] visited=new boolean[nums.length];
        return backtracking(nums,target,k,visited,0,0);
    }
    public static boolean backtracking(int[] nums,int target, int leftnumberofsets,boolean[] visited,int cursum,int startidx){
        if(leftnumberofsets==1) return true;
        if(cursum==target){
            return backtracking(nums,target,leftnumberofsets-1,visited,0,0);
        }
        for(int i=startidx;i<nums.length;i++){
            if(visited[i]==true || cursum+nums[i]>target) continue;
            cursum+=nums[i];
            visited[i]=true;
            if(backtracking(nums,target,leftnumberofsets,visited,cursum,i+1)==true) return true;
            visited[i]=false;
            cursum-=nums[i];
        }
        return false;
    }


    public static boolean canPartitionKSubsets2(int[] nums, int k) {
        int sum=0;
        for(int i:nums){
            sum+=i;
        }

        //sum%k must equal to 0 if not just return false
        //if we have to to divide the array greater than array size retun false(we can't)
        if(sum%k!=0 || nums.length<k) return false;

        //sort so we can take last element and start filling our bucket
        Arrays.sort(nums);

        //our target is sum/k and we have to find this in nums, k times then it is valid
        int[] bucket=new int[k];
        return canPartitionKSubsets2(nums,sum/k,nums.length-1,bucket);

    }
    public static boolean canPartitionKSubsets2(int a[],int target,int i,int[] bucket){

        //we have taken all the elements
        if(i==-1)
            return true;

        //start filling the buckets
        for(int j=0;j<bucket.length;j++){

            //can we take this ith element
            if(bucket[j]+a[i]<=target){

                //if we take this element
                bucket[j]+=a[i];

                //go to next element (in our case go to smallest ele bcz we sorted)
                //if we can fill all buckets then return true
                if(canPartitionKSubsets2(a,target,i-1,bucket))
                    return true;

                //means we can't fill other buckets if we take ith element so leave this element
                bucket[j]-=a[i];

            }

            //if our bucket is empty means we have not taken any elements in the buckets
            if(bucket[j]==0)
                break;

        }

        //all buckets are full but i is pointing to some element (elements still left)
        //or our bucket is empty means we haven't take any element (not valid)
        return false;

    }

    public static int lastStoneWeightII2(int[] stones) {
        int sum = 0;
        for (int i : stones) {
            sum += i;
        }
        int target = sum >> 1;
        //初始化dp数组
        int[] dp = new int[target + 1];
        for (int i = 0; i < stones.length; i++) {
            //采用倒序
            for (int j = target; j >= stones[i]; j--) {
                //两种情况，要么放，要么不放
                dp[j] = Math.max(dp[j], dp[j - stones[i]] + stones[i]);
            }
        }
        return sum - 2 * dp[target];
    }
}
