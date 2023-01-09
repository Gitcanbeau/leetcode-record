public class leet26 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/remove-duplicates-from-sorted-array/
        int[] nums={0,0,1,1,1,2,2,3,3,4};
        System.out.println(removeDuplicates(nums));
        for(int i=0; i<nums.length;i++){
            System.out.print(nums[i]+" ");
        }
    }
    public static int removeDuplicates(int[] nums) {
        //Runtime: 1 ms, faster than 100.00% of Java online submissions for Remove Duplicates from Sorted Array.
        //Memory Usage: 47.9 MB, less than 47.82% of Java online submissions for Remove Duplicates from Sorted Array.
        int slowindex=0;
        nums[slowindex]=nums[0];
        slowindex++;
        for(int fastindex=1; fastindex<nums.length;fastindex++){
            if(nums[fastindex]!=nums[fastindex-1]){
                nums[slowindex]=nums[fastindex];
                slowindex++;
            }
        }
        for(int i=slowindex;i<nums.length;i++){
            nums[i]=-1;
        }
        return slowindex;
    }
}
