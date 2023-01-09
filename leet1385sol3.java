import java.util.Arrays;

public class leet1385sol3 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/find-the-distance-value-between-two-arrays/
        int[] arr1={4,5,8};
        int[] arr2={10,9,1,8};
        int d=2;
        System.out.println(findTheDistanceValue(arr1,arr2,d));
        //Runtime: 10 ms, faster than 19.59% of Java online submissions for Find the Distance Value Between Two Arrays.
        //Memory Usage: 45.1 MB, less than 14.66% of Java online submissions for Find the Distance Value Between Two Arrays.
    }
    public static int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        Arrays.sort(arr2);
        int c = 0;
        for ( int i = 0; i < arr1.length; i++ )
            if (findNum(arr2, 0, arr2.length - 1, arr1[i] - d, arr1[i] + d)==false) c++;
        return c;
    }
    private static boolean findNum ( int[] arr, int s, int e, int min, int max ) {
        if ( e < s ) return false;
        int mid = (int) (s + e) / 2;
        if ( arr[mid] >= min && arr[mid] <= max ) return true;
        if ( arr[mid] < min ) return findNum(arr, mid + 1, e, min, max);
        if ( arr[mid] > max ) return findNum(arr, s, mid - 1, min, max);
        return false;
    }
}
