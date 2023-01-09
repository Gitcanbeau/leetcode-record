import java.util.ArrayList;
import java.util.HashSet;

public class leet349 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/intersection-of-two-arrays/
        int[] nums1={4,9,5};
        int[] nums2={9,4,9,8,4};
        int[] intersection = intersection(nums1, nums2);
        for(int i=0; i<intersection.length;i++){
            System.out.print(intersection[i]+" ");
        }
    }
    public static int[] intersection(int[] nums1, int[] nums2) {
        //Runtime: 2 ms, faster than 98.34% of Java online submissions for Intersection of Two Arrays.
        //Memory Usage: 42.2 MB, less than 97.18% of Java online submissions for Intersection of Two Arrays.
        HashSet<Integer> set1=new HashSet<>();
        HashSet<Integer> set2=new HashSet<>();
        for(int i=0; i<nums1.length;i++){
            set1.add(nums1[i]);
        }
        for(int i=0; i<nums2.length;i++){
            if(set1.contains(nums2[i])){
                set2.add(nums2[i]);
            }
        }


        int[] res=new int[set2.size()];
        int c=0;
        for(Integer inte: set2){
            res[c]=inte;
            c++;
        }
        return res;
    }

}
