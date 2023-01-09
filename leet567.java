import java.util.HashMap;

public class leet567 {
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
        //窗口大小就是s1的长度
        //hashmap记录s1里面的char种类和相应个数
        //把从left=0 left+s1.len中间的内容，如果这个char都不在hashmap里面直接left++，可以节省时间空间
        //                                  如果在的话，就开始从该left到left+s1.len中遍历，添加道新的hashmap里面
        //如果map1 map2内容能一样，就立马return true
        //出了循环 return false

        HashMap<Character,Integer> map1=new HashMap<>();
        char[] s1arr=s1.toCharArray();
        for(int i=0; i<s1arr.length;i++){
            map1.put(s1arr[i],map1.getOrDefault(s1arr[i],1)+1);
        }

        int window=s1.length();
        char[] s2arr=s2.toCharArray();
        for(int left=0; left<=s2arr.length-window;left++){
            int right=left+window-1;
            if(!map1.containsKey(s2arr[left])) {
                continue;
            }else{
                HashMap<Character,Integer> map2=new HashMap<>();
                for(int i=left; i<=right; i++){
                    map2.put(s2arr[i],map2.getOrDefault(s2arr[i],1)+1);
                }
                if(map1.equals(map2)){ //比较内容可以用equals
                    return true;
                }
            }
        }
        return false;
    }
}
