public class leet1385 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/find-the-distance-value-between-two-arrays/
        int[] arr1={4,5,8};
        int[] arr2={10,9,1,8};
        int d=2;
        System.out.println(findTheDistanceValue(arr1,arr2,d));
        //Runtime: 6 ms, faster than 49.66% of Java online submissions for Find the Distance Value Between Two Arrays.
        //Memory Usage: 45.1 MB, less than 14.66% of Java online submissions for Find the Distance Value Between Two Arrays.
    }
    public static int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        //brute force
        //读题读半天才懂
        int count=0;
        for(int i=0; i<arr1.length;i++){
            for(int j=0; j<arr2.length;j++){
                int dis=Math.abs(arr1[i]-arr2[j]);
                if(dis<=d){
                    count++;
                    break;
                }
            }
        }
        return arr1.length-count;
    }
}
