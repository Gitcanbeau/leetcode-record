import java.util.*;

public class leet387 {
    public static void main(String[] args) {
//Given a string s, find the first non-repeating character in it and return its index.
// If it does not exist, return -1.

//Example 1:
//Input: s = "leetcode"
//Output: 0
//Example 2:
//Input: s = "loveleetcode"
//Output: 2
//Example 3:
//Input: s = "aabb"
//Output: -1

//        String s="leetcode";//0
        String s = "loveleetcode";//2
//        String s = "aabb";//-1
        int i = firstUniqChar(s);
        System.out.println(i);
        //自己想出来的，我是聪明宝
        //先弹一遍que1，收集重复的char，再弹一遍que2，如果不重复就返回index，重复就index++
        //Runtime: 418 ms, faster than 5.01% of Java online submissions for First Unique Character in a String.
        //Memory Usage: 67.5 MB, less than 5.16% of Java online submissions for First Unique Character in a String.
    }
    public static int firstUniqChar(String s) {
        Queue<Character> que1=new LinkedList<>();
        Queue<Character> que2=new LinkedList<>();
        HashSet<Character> hs1=new HashSet<>();
        int index=0;
        for(int i=0; i<s.length();i++){
            char ch=s.charAt(i);
            que1.add(ch);
            que2.add(ch);
            }

        while(!que1.isEmpty()){
            char ch=que1.poll();
            if(que1.contains(ch)){
                hs1.add(ch);
            }
            if(!que1.contains(ch) & hs1.contains(ch)){
                hs1.add(ch);
            }
        }
        while(!que2.isEmpty()){
            char ch=que2.poll();
            if(!hs1.contains(ch)){
                return index;
            }
            index++;
        }
        return -1;
    }
}
