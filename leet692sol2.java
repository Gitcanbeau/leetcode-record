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
                return a.compareTo(b); //如果值一样，字母靠前的在堆顶, a.compareto(b)>0 if a在b前面
            }else{
                return  hm.get(b)-hm.get(a); //谁value大谁在堆顶，后面-前面是降序，前面-后面是升序,
                // this.compareto(传进来的） 是升序， 前面的.compareto(后面的） 是升序，前面的值-后面的值 是升序
                // 升序就是小的东西放前面，放上面
                //  x在前y在后
                // x.compareto(y) 本来就是升序，==1说明x就是大于y，需要把x移动到y后面才能保证是升序，==-1说明x<y，正好前面的小于后面的是升序不用动
                // y.compareto(x) 就是想要降序，==1说明y就是大于x，需要把y移动到x前面才能保证是降序，==-1说明y<x，正好后面的小于前面的是降序不用动
                //升序降序跟==1还是-1没关系，只和你怎么写compareto有关系，==1就移动x,y，==-1就不移动
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
