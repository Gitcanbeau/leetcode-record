import java.util.*;

public class leet76 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/minimum-window-substring/
//        String s = "ADOBECODEBANC";
//        String t = "ABC";
//        String s = "a";
//        String t = "a";
        String s = "a";
        String t = "aa";
        System.out.println(minWindow(s,t));
    }
    public static String minWindow(String s, String t) {
        String res="";

        //hashmap记录种类个数
        Map<Character,Integer> mapt=new HashMap<>();
        char[] chart=t.toCharArray();
        for(int i=0; i<chart.length;i++){
            mapt.put(chart[i],mapt.getOrDefault(chart[i],0)+1);
        }


        int unsatisfiedmaptsize= mapt.size();
        int left=0;
        int right=0;
        char[] chars=s.toCharArray();
        ArrayList<String> listofwindow=new ArrayList<>();
        int minlen=Integer.MAX_VALUE;
        int maxleft=Integer.MIN_VALUE;
        while(right<chars.length){
            if(mapt.containsKey(chars[right])){
                mapt.put(chars[right],mapt.get(chars[right])-1);
                if(mapt.get(chars[right])==0){
                    unsatisfiedmaptsize--;
                }
            }

            while(unsatisfiedmaptsize==0){
                minlen=Math.min(minlen,right-left+1);
//                listofwindow.add(s.substring(left-1,right+1));//放这里存了很多没用的string，说我memory limit exceeded
                if(mapt.containsKey(chars[left])){
                    mapt.put(chars[left],mapt.get(chars[left])+1);
                    if(mapt.get(chars[left])==1){
                        unsatisfiedmaptsize++;
                    }
                }
                left++; //我不管chars[left]在不在这个mapt里面，只要unsatisfiedmaptsize==0，我就要尝试缩小窗口
                if(unsatisfiedmaptsize!=0){
                    //要出循环了，当前的left就是最大的left，就是当前right的最短窗口，每一个符合条件的窗口我都只存最短的，
                    // 一堆选择里面，用minlen查找最短的窗口
                    listofwindow.add(s.substring(left-1,right+1));
                }
            }
            right++;
        }
        for(String str: listofwindow){
            if(str.length()==minlen){
                res=str;
                break;
            }
        }
        return res;
    }
}
