public class leet27 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/remove-element/
        int[] nums= {3,2,2,3};
        int val=3;
        int k = removeElement(nums, val);
        System.out.println("k is"+k);
        for(Integer i: nums){
            System.out.println(i);
        }
        //Runtime: 0 ms, faster than 100.00% of Java online submissions for Remove Element.
        //Memory Usage: 40.8 MB, less than 92.97% of Java online submissions for Remove Element
    }
    public static int removeElement(int[] nums, int val) {
        int slowindex=0;
        for(int fastindex=0; fastindex<nums.length; fastindex++){
            if(nums[fastindex]!=val){
                nums[slowindex]=nums[fastindex];
                slowindex++;
            }
        }
        return slowindex;
    }
}
