import java.util.HashMap;

public class leet383 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/ransom-note/
//        String ransomNote = "a";
//        String magazine = "b";
//        String ransomNote = "aa";
//        String magazine = "ab";
        String ransomNote = "aa";
        String magazine = "aab";
        System.out.println(canConstruct(ransomNote,magazine));
    }
    public static boolean canConstruct(String ransomNote, String magazine) {
        //Runtime: 41 ms, faster than 14.65% of Java online submissions for Ransom Note.
        //Memory Usage: 51.8 MB, less than 28.66% of Java online submissions for Ransom Note.
        HashMap<Character,Integer> map1=new HashMap<>();
        for(int i=0; i<magazine.length();i++){
            map1.put(magazine.charAt(i),map1.getOrDefault(magazine.charAt(i),0)+1);
        }

        for (int i=0; i<ransomNote.length();i++){
            if(!map1.containsKey(ransomNote.charAt(i))){
                return false;
            }else{
                map1.replace(ransomNote.charAt(i),map1.get(ransomNote.charAt(i))-1);
                if(map1.get(ransomNote.charAt(i))<0){
                    return false;
                }
            }
        }
        return true;
    }
}
