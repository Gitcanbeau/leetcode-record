import java.util.*;

public class leet692 {
    public static void main(String[] args) {
        //Given an array of strings words and an integer k, return the k most frequent strings.
        //Return the answer sorted by the frequency from highest to lowest. Sort the words with the same frequency by their lexicographical order.
        //Example 1:
        //Input: words = ["i","love","leetcode","i","love","coding"], k = 2
        //Output: ["i","love"]
        //Explanation: "i" and "love" are the two most frequent words.
        //Note that "i" comes before "love" due to a lower alphabetical order.
        //Example 2:
        //Input: words = ["the","day","is","sunny","the","the","the","sunny","is","is"], k = 4
        //Output: ["the","is","sunny","day"]
        //Explanation: "the", "is", "sunny" and "day" are the four most frequent words, with the number of occurrence being 4, 3, 2 and 1 respectively.
        //Constraints:
        //1 <= words.length <= 500
        //1 <= words[i].length <= 10
        //words[i] consists of lowercase English letters.
        //k is in the range [1, The number of unique words[i]]
        String[] words={"i","love","leetcode","i","love","coding"};
        int k=1;
//        String[] words={"the","day","is","sunny","the","the","the","sunny","is","is"};
//        int k=4;
        List<String> strings = topKFrequent(words, k);
        System.out.println(strings);
        //Runtime: 29 ms, faster than 5.33% of Java online submissions for Top K Frequent Words.
        //Memory Usage: 47.4 MB, less than 5.69% of Java online submissions for Top K Frequent Words.
    }
    public static List<String> topKFrequent(String[] words, int k) {

        List<List<String>> buckets=new ArrayList<>();
        Map<String,Integer> hm1=new HashMap<>();
        for(String str: words){
            hm1.put(str,hm1.getOrDefault(str,0)+1);
            buckets.add(new ArrayList<>());
        }

        for(String str: hm1.keySet()){
            int count=hm1.get(str);
            buckets.get(count).add(str);
        }
        System.out.println(hm1.toString());
        System.out.println(buckets.toString());


        List<String> res=new ArrayList<>();
        for(int i=buckets.size()-1;i>=0 ;i--){
            if(buckets.get(i)!=null){
                Collections.sort(buckets.get(i));
                for(String str: buckets.get(i)){
                    res.add(str);
                    k--;
                    if(k<=0) return res;
                }
            }
        }
        return res;
    }
}
