import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class leet3 {
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
        //Runtime: 22 ms, faster than 25.29% of Java online submissions for Longest Substring Without Repeating Characters.
        //Memory Usage: 50 MB, less than 18.79% of Java online submissions for Longest Substring Without Repeating Characters.
    }
    public static int lengthOfLongestSubstring(String s) {
        int lengthmax=0;
        int temp=0;
        int temppre;
        //整一个hashmap，键是substring，值是index，一旦遇到重复的我就更新temp，每次更新完temp我都取大更新lengthmax
        HashMap<String,Integer> hm1=new HashMap<>();
        ArrayList<String> arrlst1=new ArrayList<>();
        for(int i=0; i<s.length();i++){
            arrlst1.add(s.substring(i,i+1));
        }
        for(int i=0; i<arrlst1.size();i++){
            if(!hm1.containsKey(arrlst1.get(i))){
                hm1.put(arrlst1.get(i),i);
                temp++;
                lengthmax=Math.max(temp,lengthmax);
            }else{
                temppre=temp;
                temp=i-hm1.get(arrlst1.get(i));
                if(temppre<temp){//上一段被这次重复的给包进去了,tmmzuxt,还是应该从第二个m开始算，就只+1
                    temp=temppre+1;
                }else{//新重复的是在上一段重复之后发生的
                    temp=i-hm1.get(arrlst1.get(i));
                }
                hm1.replace(arrlst1.get(i),i);
                lengthmax=Math.max(temp,lengthmax);
            }
        }
        lengthmax=Math.max(temp,lengthmax);
        return lengthmax;
    }
}
