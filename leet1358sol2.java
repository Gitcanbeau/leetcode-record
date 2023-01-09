import java.util.HashSet;

public class leet1358sol2 {
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
        //Runtime: 29 ms, faster than 33.37% of Java online submissions for Number of Substrings Containing All Three Characters.
        //Memory Usage: 45.1 MB, less than 60.31% of Java online submissions for Number of Substrings Containing All Three Characters.
    }

    public static int numberOfSubstrings(String s) {
        int left = 0;
        int res = 0;
        int[] arr = new int[3];
        for(int i = 0; i<s.length(); i++){
            char ch = s.charAt(i);
            arr[ch-'a']++;
            while(arr[0]>0 && arr[1]>0 && arr[2]>0){
                res += s.length()-i;
                //because since we have one occurence of every char
                // evry string greater than it will have this
                // so subtracting curr idx from length to get all possible strings having each of abc.
                arr[s.charAt(left++)-'a']--;
            }
        }
        return res;
    }
}
