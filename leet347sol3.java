import java.util.*;

public class leet347sol3 {
    public static void main(String[] args) {
        //Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.
        //Example 1:
        //Input: nums = [1,1,1,2,2,3], k = 2
        //Output: [1,2]
        //Example 2:
        //Input: nums = [1], k = 1
        //Output: [1]
        //Constraints:
        //1 <= nums.length <= 105
        //-104 <= nums[i] <= 104
        //k is in the range [1, the number of unique elements in the array].
        //It is guaranteed that the answer is unique.

        int[] nums = {1, 1, 1, 2, 2, 2, 2, 3};
        int k = 2;
        int[] ints = topKFrequent(nums, k);
        for (int i = 0; i < ints.length; i++) {
            System.out.print(ints[i] + " ");
        }
        //Runtime: 33 ms, faster than 13.50% of Java online submissions for Top K Frequent Elements.
        //Memory Usage: 53.8 MB, less than 5.04% of Java online submissions for Top K Frequent Elements.
    }
    public static int[] topKFrequent(int[] nums, int k) {
        List<List<Integer>> buckets=new ArrayList<>();
        buckets.add(new ArrayList<>());

        Map<Integer,Integer> counts=new HashMap<>();
        for(int num: nums){
            counts.put(num,counts.getOrDefault(num,0)+1); //键是数，值是出现次数
            buckets.add(new ArrayList<>());//每一个位置我都放一个初始化的list占位置，只不过list里面没东西
        }

        for(int num:counts.keySet()){
            int count=counts.get(num);
            buckets.get(count).add(num);
        }

        int[] res=new int[k];
        for(int i=buckets.size()-1;i>0;i--){
            if(k<=0) return res;
            if(buckets.get(i).size()==0) continue;
            for(int num:buckets.get(i)){
                res[--k]=num;
            }
        }
        return res;
    }
}
