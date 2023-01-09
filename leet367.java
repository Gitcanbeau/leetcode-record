public class leet367 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/valid-perfect-square/
//        int num=16;
        int num=14;
        System.out.println(isPerfectSquare(num));
    }
    public static boolean isPerfectSquare(int num) {
        //Runtime: 0 ms, faster than 100.00% of Java online submissions for Valid Perfect Square.
        //Memory Usage: 41 MB, less than 42.00% of Java online submissions for Valid Perfect Square.
        long left=1;
        long right=num;

        while(left<=right){
            long mid=left+(right-left)/2;
            if(mid*mid==num){
                return true;
            }else if(mid*mid>num){
                right=mid-1;
            }else if(mid*mid<num){
                left=mid+1;
            }
        }
        return false;
    }

}
