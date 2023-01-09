import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class leet49sol2 {
    public static void main(String[] args) {
        //Given an array of strings strs, group the anagrams together. You can return the answer in any order.
        //An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.
        //Example 1:
        //Input: strs = ["eat","tea","tan","ate","nat","bat"]
        //Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
        //Example 2:
        //Input: strs = [""]
        //Output: [[""]]
        //Example 3:
        //Input: strs = ["a"]
        //Output: [["a"]]
        //Constraints:
        //1 <= strs.length <= 104
        //0 <= strs[i].length <= 100
        //strs[i] consists of lowercase English letters.
        String[] strs = {"eat","tea","tan","ate","nat","bat"};
//        String[] strs = {""};
//        String[] strs = {"a"};
        List<List<String>> lists = groupAnagrams(strs);
        System.out.println(lists.toString());
        //活用hashmap，
        //字符串变char arry[]，然后排序，然后变回sorted字符串，排好序的字符串作为key，list作为值。
        // 不存在就新建，存在就用key找到值再添加原字符串str
        //Runtime: 6 ms, faster than 99.48% of Java online submissions for Group Anagrams.
        //Memory Usage: 45.5 MB, less than 93.85% of Java online submissions for Group Anagrams.
    }
    public static List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        HashMap<String, List<String>> map = new HashMap();

        for (String str:strs){
            char[] str_arr = str.toCharArray();
            Arrays.sort(str_arr);
            String sorted_str = new String(str_arr);

            if (!map.containsKey(sorted_str)){
                map.put(sorted_str, new ArrayList<String>());
            }
            map.get(sorted_str).add(str);
        }

        res.addAll(map.values());
        return res;
    }
}
