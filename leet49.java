import java.util.*;

public class leet49 {
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
        //自己想出来的，但是不对，就是hashmap存的char freq []作为key，存的是地址值不是内容，
        // 所以即便内容相同，也会认为是不存在这个键而新建list
        //活用hashmap
    }
    public static List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> list1=new ArrayList<>();

        if(strs.length<=1){
            List<String> list2=Arrays.asList(strs);
            list1.add(list2);
            return list1;
        }

        int index=0;
        HashMap<int[],Integer> hm1=new HashMap<>();
        for(int i=0; i<strs.length;i++){
            int[] freq=getFreqAtEachChar(strs[i]);
            if(!hm1.containsKey(freq)){
                hm1.put(freq,index);
                //char freq []作为键，第一次出现的位置作为值，遇到相同的char freq [],就找index处list添加
                index++;
                List<String> list2=new ArrayList<>();//每有一个新的char freq []，我就新建一个list存储该string
                list2.add(strs[i]);
                list1.add(list2);
            }else{
                int listindex = hm1.get(freq);//通过键找list2在list1的位置，找到相应的list2就添加
                list1.get(listindex).add(strs[i]);
            }
        }
        return list1;
    }
    public static int[] getFreqAtEachChar(String s) {
        int[] freq1=new int[26];
        char[] schararr=s.toCharArray();
        for(int i=0; i<schararr.length;i++){
            freq1[schararr[i]-'a']++;
        }
        return freq1;
    }
}
