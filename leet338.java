public class leet338 {
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
        //Runtime: 120 ms, faster than 5.40% of Java online submissions for Counting Bits.
        //Memory Usage: 46.5 MB, less than 92.52% of Java online submissions for Counting Bits.
    }
    public static int[] countBits(int n) {

        int[] ans = new int[n+1];
        for(int i=0; i<=n; i++){
            int cnt = 0;
            for(int j=0; j<Integer.toBinaryString(i).length(); j++){
                if(Integer.toBinaryString(i).charAt(j) == '1')
                    cnt++;
            }
            ans[i] = cnt;
        }

        return ans;

    }
}
