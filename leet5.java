public class leet5 {
    public static void main(String[] args) {
        //Given a string s, return the longest palindromic substring in s.
        //Example 1:
        //Input: s = "babad"
        //Output: "bab"
        //Explanation: "aba" is also a valid answer.
        //Example 2:
        //Input: s = "cbbd"
        //Output: "bb"
        //Constraints:
        //1 <= s.length <= 1000
        //s consist of only digits and English letters.
//        String s ="babad";//bab or aba
//        String s ="cbbd";//bb
//        String s ="ac";
//        String s ="ccc";
        String s ="abcda";
        String s1 = longestPalindrome(s);
        System.out.println(s1);
//        System.out.println(isPalindrome(s,0,2));//true
        //dynamic programming思想，&&连接很重要
        // 1层筛选：这head和tail一样才有判断的必要
        // 2层筛选：就是说没用的比较我不做，你比我max还大，我才去判断，你要是比我max小赶紧走开
        //双指针或者叫滑动窗口，
        //Runtime: 324 ms, faster than 26.94% of Java online submissions for Longest Palindromic Substring.
        //Memory Usage: 43.3 MB, less than 57.27% of Java online submissions for Longest Palindromic Substring.
    }
    public static String longestPalindrome(String s) {

        if(s.length()==1){ //细节1
            return s;
        }

        char[] chararry = s.toCharArray();
        int maxlength=0;
        int leftmost=0;
        int rightmost=0; //细节，要返回的东西的默认值，可得想清楚，就是一切都不成功的时候返回的东西就是默认返回
        int left=0;
        int right=chararry.length-1;
        while(left<chararry.length-1){
            if(chararry[left]==chararry[right] && right-left+1>maxlength && isPalindrome(s,left,right)){
                maxlength=right-left+1; //细节，别忘了写啊，心里想了但是没写，做好手写很重要
                leftmost=left;
                rightmost=right;
                left++;
                right=chararry.length-1;
            }else{
                right--;
            }

            if(right<=left){
                left++;
                right=chararry.length-1;
            }
        }
        String substring=s.substring(leftmost,rightmost+1);//细节，你要返回的东西substring(leftmost,rightmost);
        return substring;
    }

    public static boolean isPalindrome(String s, int i, int j){
        while (i < j) {
            if(s.charAt(i)!=s.charAt(j)){
                return false;
            }else{
                i++;
                j--;
            }
        }
        return true;
    }
}
