public class leet647 {
    public static void main(String[] args) {
        //Given a string s, return the number of palindromic substrings in it.
        //A string is a palindrome when it reads the same backward as forward.
        //A substring is a contiguous sequence of characters within the string.
        //Example 1:
        //Input: s = "abc"
        //Output: 3
        //Explanation: Three palindromic strings: "a", "b", "c".
        //Example 2:
        //Input: s = "aaa"
        //Output: 6
        //Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
        //Constraints:
        //1 <= s.length <= 1000
        //s consists of lowercase English letters.
//        String s = "abc";
//        String s = "aaa";
//        String s = "a";
//        String s ="abccbadd";//12
        String s="aaabbbaaam";//23
        int i = countSubstrings(s);//22
        System.out.println(i);
        //Runtime: 790 ms, faster than 7.28% of Java online submissions for Palindromic Substrings.
        //Memory Usage: 41.9 MB, less than 74.77% of Java online submissions for Palindromic Substrings.
    }
    public static int countSubstrings(String s) {
        char[] chararr = s.toCharArray();
        int count=chararr.length;
        int left=0;
        int right=chararr.length-1;
        while(left<chararr.length-1){
            if(chararr[left]==chararr[right] && isPalinedorm(s,left,right)){
                count++;
            }
            right--;//注意细节和优化，你别不动啊

            if(right<=left){
                left++;
                right=chararr.length-1;
            }
        }
        return count;
    }
    public static boolean isPalinedorm(String s, int i, int j){
        while(i<j){
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
