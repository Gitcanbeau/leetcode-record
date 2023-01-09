import java.util.Arrays;

public class leet1385sol4_bs {
    public static void main(String[] args) {
        //https://leetcode.com/problems/find-the-distance-value-between-two-arrays/
//        int[] arr1={4,5,8};
//        int[] arr2={10,9,1,8};
        int[] arr1={4};
        int[] arr2={10};
        int d=2;
        System.out.println(findTheDistanceValue(arr1,arr2,d));
    }
    public static int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        //Runtime: 5 ms, faster than 74.39% of Java online submissions for Find the Distance Value Between Two Arrays.
        //Memory Usage: 44.4 MB, less than 59.56% of Java online submissions for Find the Distance Value Between Two Arrays.
        int count_not_valid=0;
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        for(int i=0; i<arr1.length;i++){
            boolean issmaller=smallerthangivendistance(arr2,arr1[i],d);
            if(issmaller){
                count_not_valid++;
            }
        }
        int res=arr1.length-count_not_valid;
        return res;
    }
    public static boolean smallerthangivendistance(int[] arr2, int target, int d){
        int left=0;
        int right=arr2.length-1;
        while(left<=right){
            int mid=left+(right-left)/2;
            int tempdis=Math.abs(arr2[mid]-target)-d;
            if(tempdis<=0){
                return true;
            }else if(tempdis>0 && arr2[mid]>target){
                right=mid-1;
            }else if(tempdis>0 && arr2[mid]<target){
                left=mid+1;
            }
        }
        return false;
    }
}
