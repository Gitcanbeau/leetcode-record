import java.util.HashMap;

public class  leet567sol2_fastslidingw {
    public static void main(String[] args) {
        //https://leetcode.com/problems/permutation-in-string/
//        String s1 = "ab";
//        String s2 = "eidbaooo";
        String s1 = "a";
        String s2 = "e";
        System.out.println(checkInclusion(s1,s2));
        //Runtime: 1986 ms, faster than 5.96% of Java online submissions for Permutation in String.
        //Memory Usage: 72.1 MB, less than 12.09% of Java online submissions for Permutation in String.
    }
    public static boolean checkInclusion(String s1, String s2) {

        HashMap<Character,Integer> map1=new HashMap<>();
        for(int i=0;i<s1.length();i++){
            map1.put(s1.charAt(i),map1.getOrDefault(s1.charAt(i),0)+1);
        }
        int window=map1.size();
        int i=0;
        for(int j=0;j<s2.length();j++){
            if(map1.containsKey(s2.charAt(j))){
                map1.put(s2.charAt(j),map1.get(s2.charAt(j))-1);
                if(map1.get(s2.charAt(j))==0)
                    window--;
            }
            if(j-i+1==s1.length()){
                if(window==0){
                    return true;
                }
                if(map1.containsKey(s2.charAt(i))){
                    //因为i要出window了把之前减掉的个数加回来，万一新的值为1说明之前window个数--过了，那window还要++回来
                    map1.put(s2.charAt(i),map1.get(s2.charAt(i))+1);
                    if(map1.get(s2.charAt(i))==1){
                        window++;
                    }
                }
                i++;
            }
        }
        return false;
    }
}
