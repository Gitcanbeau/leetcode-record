import java.util.Arrays;

public class leet496 {
    public static void main(String[] args) {
//The next greater element of some element x in an array is the first greater element that is to the right of x in the same array.
//You are given two distinct 0-indexed integer arrays nums1 and nums2, where nums1 is a subset of nums2.
//For each 0 <= i < nums1.length, find the index j such that nums1[i] == nums2[j] and determine the next greater element of nums2[j] in nums2.
// If there is no next greater element, then the answer for this query is -1.
//Return an array ans of length nums1.length such that ans[i] is the next greater element as described above.
//Example 1:
//Input: nums1 = [4,1,2], nums2 = [1,3,4,2]
//Output: [-1,3,-1]
//Explanation: The next greater element for each value of nums1 is as follows:
//- 4 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so the answer is -1.
//- 1 is underlined in nums2 = [1,3,4,2]. The next greater element is 3.
//- 2 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so the answer is -1.
//Example 2:
//Input: nums1 = [2,4], nums2 = [1,2,3,4]
//Output: [3,-1]
//Explanation: The next greater element for each value of nums1 is as follows:
//- 2 is underlined in nums2 = [1,2,3,4]. The next greater element is 3.
//- 4 is underlined in nums2 = [1,2,3,4]. There is no next greater element, so the answer is -1.

//Constraints:
//1 <= nums1.length <= nums2.length <= 1000
//0 <= nums1[i], nums2[i] <= 104
//All integers in nums1 and nums2 are unique.
//All the integers of nums1 also appear in nums2.
        int[] nums1={4,1,2};
        int[] nums2={1,3,4,2};
        int[] ints = nextGreaterElement(nums1, nums2);
        System.out.print("[");
        for(int i=0; i<ints.length;i++){
            System.out.print(ints[i]+" ");
        }
        System.out.println("]");
        //Runtime: 5 ms, faster than 72.17% of Java online submissions for Next Greater Element I.
        //Memory Usage: 43.8 MB, less than 76.59% of Java online submissions for Next Greater Element I.
    }
    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {

        int[] special={-1};
        if(nums2.length==1){
            return special;
        }

        int[] nextgreat=new int[nums1.length];
        Arrays.fill(nextgreat,-1);

        int index = 0;
        for(int i=0; i<nums1.length;i++){
            for(int j=0; j<nums2.length;j++){
                if(nums1[i]==nums2[j]){
                    index=j+1;
                    break;
                }
            }

            for(int k=index; k<nums2.length;k++){
                if(nums2[k]>nums1[i]){
                    nextgreat[i]=nums2[k];
                    break;
                }
            }
        }
        return nextgreat;
    }

}
