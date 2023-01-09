public class leet844 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/backspace-string-compare/
        String s="ab#c";
        String t="ad#c";
//        String s="###ab#c";
//        String t="ad#c";
//        String s="ab##";
//        String t="a#d#";
        System.out.println(backspaceCompare(s,t));
        System.out.println(removecharr(s));
        System.out.println(removecharr(t));
        System.out.println(s);
        System.out.println(t);
    }
    public static boolean backspaceCompare(String s, String t) {
        //Can you solve it in O(n) time and O(1) space?
        //Runtime: 0 ms, faster than 100.00% of Java online submissions for Backspace String Compare.
        //Memory Usage: 40.1 MB, less than 99.30% of Java online submissions for Backspace String Compare.
        s=removecharr(s);
        t=removecharr(t);
        if(s.equals(t)){ //比较字符串内容只能用equals. 你用==比较的是地址值，有很大的可能会出错
            return true;
        }
        return false;
    }

    public static String removecharr(String str){
        int slowindex=0;
        char[] stringchararr=str.toCharArray();
        for(int fastindex=0; fastindex< str.length();fastindex++){
            if(stringchararr[fastindex]!='#'){
                stringchararr[slowindex]=stringchararr[fastindex];
                slowindex++;
            }else{
                slowindex--;
                if(slowindex<0){ //万一一开头一堆#都没有正文，反正你最多给我删除到0位置//真的聪明
                    slowindex=0;
                }
            }
        }
        str=String.valueOf(stringchararr).substring(0,slowindex); //slowindex到的是下一个位置，不用再在这里+1了
        return str;
    }
}
