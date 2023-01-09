public class leet338sol2 {
    public static void main(String[] args) {
        //Given an integer n, return an array ans of length n + 1 such that for each i (0 <= i <= n), ans[i] is the number of 1's in the binary representation of i.
        //Example 1:
        //Input: n = 2
        //Output: [0,1,1]
        //Explanation:
        //0 --> 0
        //1 --> 1
        //2 --> 10
        //Example 2:
        //Input: n = 5
        //Output: [0,1,1,2,1,2]
        //Explanation:
        //0 --> 0
        //1 --> 1
        //2 --> 10
        //3 --> 11
        //4 --> 100
        //5 --> 101
        //Constraints:
        //0 <= n <= 105
        //Follow up:
        //It is very easy to come up with a solution with a runtime of O(n log n). Can you do it in linear time O(n) and possibly in a single pass?
        //Can you do it without using any built-in function (i.e., like __builtin_popcount in C++)?
        int n=17;
        int[] countbits=countBits(n);
        for(int i=0; i<countbits.length;i++){
            System.out.print(countbits[i]+",");
        }
        //Runtime: 9 ms, faster than 23.22% of Java online submissions for Counting Bits.
        //Memory Usage: 47.5 MB, less than 83.16% of Java online submissions for Counting Bits.
    }
    public static int[] countBits(int n) {

        int[] ans = new int[n+1];
        for(int i=0; i<=n; i++){
            int res=countbitsofone(i);
            ans[i] = res;
        }

        return ans;

    }
    public static int countbitsofone(int n){

        int res=0;
        while(n!=0){
            n=(n&(n-1));//自动把n转换成二进制数字，不用你自己转换，n=2进来的他自己就写成10,你要是直接传一个二进制数字那就直接运算
            res++;
            //This operator is a binary operator二进制数字运算符, denoted by ‘&.’
            // It returns bit by bit AND of input values,
            // i.e., if both bits are 1, it gives 1, else it shows 0.
            //去掉n最靠后的1方法1：n&(n-1)
            //n&-n 可以得到仅保留最后一位1的数字
            //110&-110 可以得到10
            //去掉n最靠后的1方法2：n-(n&-n) 110去掉10就得到100
        }
        return res;
    }
}
