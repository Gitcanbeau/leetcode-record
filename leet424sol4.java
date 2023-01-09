public class  leet424sol4 {public static void main(String[] args) {
    //You are given a string s and an integer k. You can choose any character of the string and change it to any other uppercase English character. You can perform this operation at most k times.
    //Return the length of the longest substring containing the same letter you can get after performing the above operations.
    //Example 1:
    //Input: s = "ABAB", k = 2
    //Output: 4
    //Explanation: Replace the two 'A's with two 'B's or vice versa.
    //Example 2:
    //Input: s = "AABABBA", k = 1
    //Output: 4
    //Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA".
    //The substring "BBBB" has the longest repeating letters, which is 4.
    //Constraints:
    //1 <= s.length <= 105
    //s consists of only uppercase English letters.
    //0 <= k <= s.length
    String s="ABAB";//k=2,output=4
//        String s="AABABBA";//k=1,output=4
    int i = characterReplacement(s,1);
    System.out.println(i);
    //Runtime: 23 ms, faster than 38.36% of Java online submissions for Longest Repeating Character Replacement.
    //Memory Usage: 42.9 MB, less than 75.50% of Java online submissions for Longest Repeating Character Replacement.
}
    public static int characterReplacement(String s, int k) {
        int len=s.length();
        if(len<2){
            return len;
        }
        int res=0;
        char[] chararr=s.toCharArray();
        int[] freq=new int[26];
        int maxcount=0; //统计一段字符串中出现频率最多的字符个数

        int left=0;
        int right=0;
        //[left,right)滑动窗口
        while(right<len){
            freq[chararr[right]-'A']++;
            maxcount=Math.max(maxcount,freq[chararr[right]-'A']);
            right++;
            if(right-left>maxcount+k){
                freq[chararr[left]-'A']--;
                left++;
            }

            res=Math.max(res,right-left);//这句话的顺序要搞懂
        }
        return res;
    }

}
