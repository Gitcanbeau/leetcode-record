public class leet387sol2 {
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

//        String s="leetcode";
//        String s = "loveleetcode";
        String s = "aabb";
        int i = firstUniqChar(s);
        System.out.println(i);
        //Runtime: 12 ms, faster than 68.34% of Java online submissions for First Unique Character in a String.
        //Memory Usage: 49.5 MB, less than 51.22% of Java online submissions for First Unique Character in a String.
        //盲猜字符串经常用int[] store = new int[26];这个方法
    }
    public static int firstUniqChar(String s) {
        if(s.length() == 0)  return -1;
        int[] store = new int[26];
        // Traverse string to keep track number of times each character in the string appears...
        for(char ch : s.toCharArray()){
            store[ch - 'a']++;  //字符对应的编码-a字母对应的编码就等于0，刚好index0就可以用
        }//++是说store[index]在index处的值+1

        for(int idx = 0; idx < s.length(); idx++){
            if(store[s.charAt(idx) - 'a'] == 1){
                return idx;
            }
        }
        return -1;      // if no character appeared exactly once...
    }
}
