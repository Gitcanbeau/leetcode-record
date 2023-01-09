import java.util.HashSet;

public class leet1358 {
    public static void main(String[] args) {
        //Given a string s consisting only of characters a, b and c.
        //Return the number of substrings containing at least one occurrence of all these characters a, b and c.
        //Example 1:
        //Input: s = "abcabc"
        //Output: 10
        //Explanation: The substrings containing at least one occurrence of the characters a, b and c are "abc", "abca", "abcab", "abcabc", "bca", "bcab", "bcabc", "cab", "cabc" and "abc" (again).
        //Example 2:
        //Input: s = "aaacb"
        //Output: 3
        //Explanation: The substrings containing at least one occurrence of the characters a, b and c are "aaacb", "aacb" and "acb".
        //Example 3:
        //Input: s = "abc"
        //Output: 1
        //Constraints:
        //3 <= s.length <= 5 x 10^4
        //s only consists of a, b or c characters.
//        String s="abcabc";
//        String s = "aaacb";
        String s = "abc";
        System.out.println(numberOfSubstrings(s));
        //time limit exceeded
    }
    public static int numberOfSubstrings(String s) {
        int left=0;
        int right=left+1;
        int count=0;
        int last_index=s.length()-1;
        while(right<=last_index){//细节<=
            String temp=s.substring(left,right+1);
            if(containsabc(temp)){
                count=count+1+(last_index-right);
                left++;
            }else{
                right++;
            }
        }
        return count;
    }

    public static boolean containsabc (String s){
        HashSet<Character> set1=new HashSet<>();
        for(int i=0; i<s.length();i++){
            set1.add(s.charAt(i));
        }

        if(set1.contains('a') && set1.contains('b') && set1.contains('c')){
            return true;
        }

        return false;
    }
}
