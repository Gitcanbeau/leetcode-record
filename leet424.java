import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class leet424 {
    public static void main(String[] args) {
        //You are given a string s and an integer k. You can choose any character of the string and change it to any other uppercase English character. You can perform this operation at most k times.
        //Return the length of the longest substring containing the same letter you can get after performing the above operations.
        //Example 1:
        //Input: s = "ABAB", k = 2
        //Output: 4
        //Explanation: Replace the two 'A's with two 'B's or vice versa.
        //Example 2:
        //Input: s = "AABABBA", k = 1
        //Output: 4
        //Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA".
        //The substring "BBBB" has the longest repeating letters, which is 4.
        //Constraints:
        //1 <= s.length <= 105
        //s consists of only uppercase English letters.
        //0 <= k <= s.length
        String s="ABAB";//k=2,output=4
//        String s="AABABBA";//k=1,output=4
        int i = characterReplacement(s,2);
        System.out.println(i);
        //没写完，sol2想用双指针做
    }
    public static int characterReplacement(String s, int k) {
        int count=0; //count<=k
        int temp=0; //temporary length, needs to compare with lengthmax
        int lengthmax=0;
        //slide window with k容错
        HashMap<String,Integer> hm1=new HashMap<>();
        ArrayList<String> arrlst1=new ArrayList<>();
        for(int i=0; i<s.length();i++){
            arrlst1.add(s.substring(i,i+1));
        }
        for(int i=0; i<s.length();i++){

            if(hm1.containsKey(arrlst1.get(i))){
                hm1.put(arrlst1.get(i),i);
                temp++;
            }else{
                hm1.put(arrlst1.get(i),i);
                if(count<=k){
                    temp++;
                    count++;
                    lengthmax=Math.max(lengthmax,temp);
                }else{
                    if(i-hm1.get(arrlst1.get(i))-1>k){
                        count--;
                        temp=i-hm1.get(arrlst1.get(i));
                        lengthmax=Math.max(lengthmax,temp);
                    }else{
                        count=i-hm1.get(arrlst1.get(i))-1;
                        temp=i-hm1.get(arrlst1.get(i))+1;
                        lengthmax=Math.max(lengthmax,temp);
                    }
                    lengthmax=Math.max(lengthmax,temp);
                }
            }
        }
        return lengthmax;
    }
}
