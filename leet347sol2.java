import java.util.*;

public class leet347sol2 {
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

        int[] nums={1,1,1,2,2,2,2,3};
        int k=1;
        int[] ints = topKFrequent(nums, k);
        for(int i=0; i<ints.length;i++){
            System.out.print(ints[i]+" ");
        }
        //Runtime: 47 ms, faster than 5.94% of Java online submissions for Top K Frequent Elements.
        //Memory Usage: 51.7 MB, less than 9.57% of Java online submissions for Top K Frequent Elements.
    }
    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer,Integer> map1=new HashMap<>();
        Arrays.sort(nums);
        for(int i=0; i<nums.length;i++){
            if(!map1.containsKey(nums[i])){
                int count=1;
                map1.put(nums[i],count);
            }else{
                int count=map1.get(nums[i]);
                count++;
                map1.replace(nums[i],count);
            }
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> map1.get(a) - map1.get(b));
        for(int key: map1.keySet()){
            pq.add(key);

            if(pq.size() > k){//我的pq只保留k个key，你加进来的key对应的值，和我之前最小的值比较，推出更小的值对应的key
                pq.poll();
            }
        }
        int res[] = new int[k];
        int i = 0;
        while(!pq.isEmpty()){
            res[i++] = pq.poll();
        }
        return res;
    }
}
