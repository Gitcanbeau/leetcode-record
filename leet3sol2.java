import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class leet3sol2 {
    public static void main(String[] args) {
        //Given a string s, find the length of the longest substring without repeating characters.
        //Example 1:
        //Input: s = "abcabcbb"
        //Output: 3
        //Explanation: The answer is "abc", with the length of 3.
        //Example 2:
        //Input: s = "bbbbb"
        //Output: 1
        //Explanation: The answer is "b", with the length of 1.
        //Example 3:
        //Input: s = "pwwkew"
        //Output: 3
        //Explanation: The answer is "wke", with the length of 3.
        //Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
        //Constraints:
        //0 <= s.length <= 5 * 104
        //s consists of English letters, digits, symbols and spaces.
//        String s="abcabcbb";//3
//        String s="bbbbb";//1
//        String s="pwwkew";//3
//        String s="aab";//2
//        String s="dvdf";//3
        String s="tmmzuxt";//5
//        String s="abba";//2
        int i = lengthOfLongestSubstring(s);
        System.out.println(i);
        //没看懂
    }
    public static int lengthOfLongestSubstring(String s) {
        //slide window
        int n = s.length();
        int res = 0;
        int[] prev = new int[256];
        Arrays.fill(prev , -1);
        int i = 0;

        for(int j = 0;j<n; j++){

            i = Math.max(i, prev[(int)(s.charAt(j))]+1);
            int max = j-i+1;
            res = Math.max(res, max);
            prev[s.charAt(j)]=j;

        }
        return res;
    }
}
