import java.util.*;

public class leet347 {
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
        //Runtime: 50 ms, faster than 5.46% of Java online submissions for Top K Frequent Elements.
        //Memory Usage: 50.7 MB, less than 37.16% of Java online submissions for Top K Frequent Elements.
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


        ArrayList<Integer> arrylst1=new ArrayList<>();
        Set<Integer> keyset = map1.keySet();
        for(Integer key: keyset){
            arrylst1.add(map1.get(key));
        }
        Collections.sort(arrylst1,Collections.reverseOrder());


        int[] res=new int[k];
        Set<Integer> keyset1=map1.keySet();

        for(int count=0; count<k;count++){
            for(Integer key: keyset){
                if(map1.get(key)==arrylst1.get(count)){
                    res[count]=key;
                    map1.remove(key);
                    break;
                }
            }
        }

        return res;
    }
}
