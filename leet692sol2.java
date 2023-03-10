import java.util.*;

public class leet692sol2 {
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
        int k=2;
//        String[] words={"the","day","is","sunny","the","the","the","sunny","is","is"};
//        int k=4;
        List<String> strings = topKFrequent(words, k);
        System.out.println(strings);
        //Runtime: 29 ms, faster than 5.33% of Java online submissions for Top K Frequent Words.
        //Memory Usage: 47.4 MB, less than 5.69% of Java online submissions for Top K Frequent Words.
    }
    public static List<String> topKFrequent(String[] words, int k) {
        //https://stackoverflow.com/questions/26107921/what-determines-ascending-or-descending-order-in-comparator-comparable-collect
        //https://www.baeldung.com/java-comparator-comparable
        HashMap<String , Integer> hm = new HashMap<>();
        for(int i = 0; i < words.length; i++){
            hm.put(words[i] , hm.getOrDefault(words[i] , 0) + 1);
        }
        PriorityQueue<String> pq = new PriorityQueue<>((a , b) -> {
            if(hm.get(b) == hm.get(a)){
                return a.compareTo(b); //??????????????????????????????????????????, a.compareto(b)>0 if a???b??????
            }else{
                return  hm.get(b)-hm.get(a); //???value????????????????????????-????????????????????????-???????????????,
                // this.compareto(??????????????? ???????????? ?????????.compareto(???????????? ????????????????????????-???????????? ?????????
                // ?????????????????????????????????????????????
                //  x??????y??????
                // x.compareto(y) ?????????????????????==1??????x????????????y????????????x?????????y??????????????????????????????==-1??????x<y???????????????????????????????????????????????????
                // y.compareto(x) ?????????????????????==1??????y????????????x????????????y?????????x??????????????????????????????==-1??????y<x???????????????????????????????????????????????????
                //???????????????==1??????-1??????????????????????????????compareto????????????==1?????????x,y???==-1????????????
            }
        });
        for(String f : hm.keySet()){
            pq.add(f);
        }

        List<String> list = new ArrayList<>();
        while(k > 0){
            list.add(pq.poll());
            k--;
        }
        return list;
    }
}
