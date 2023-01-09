import java.util.HashMap;
import java.util.Set;

public class leet242 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/valid-anagram/
        String s = "anagram";
        String t = "nagaram";
        System.out.println(isAnagram(s, t));
        System.out.println(isAnagram2(s, t));
        //Runtime: 2 ms, faster than 98.84% of Java online submissions for Valid Anagram.
        //Memory Usage: 42.4 MB, less than 94.99% of Java online submissions for Valid Anagram.
    }
    public static boolean isAnagram(String s, String t) {
        if(s.length()!=t.length()){
            return false;
        }
        int[] freq1=new int[26];
        char[] schararr=s.toCharArray();
        char[] tchararr=t.toCharArray();
        for(int i=0; i<schararr.length;i++){
            freq1[schararr[i]-'a']++;
            freq1[tchararr[i]-'a']--;
        }
        for(int i=0; i< freq1.length;i++){
            if(freq1[i]!=0){
                return false;
            }
        }
        return true;
    }
    public static boolean isAnagram2(String s, String t){
        if(s.length()!=t.length()){
            return false;
        }
        HashMap<Character,Integer> maps=new HashMap<>();
        for(int i=0; i<s.length();i++){
            maps.put(s.charAt(i),maps.getOrDefault(s.charAt(i),0)+1);
            maps.put(t.charAt(i),maps.getOrDefault(t.charAt(i),0)-1);
        }

        Set<Character> keyset = maps.keySet();
        for(Character ch: keyset){
            int count=maps.get(ch);
            if(count!=0){
                return false;
            }
        }
        return true;
    }
}
