import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;


public class leet20 {
    public static void main(String[] args) {
        //Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
        //An input string is valid if:
        //Open brackets must be closed by the same type of brackets.
        //Open brackets must be closed in the correct order.
        //Every close bracket has a corresponding open bracket of the same type.
        //Example 1:
        //Input: s = "()"
        //Output: true
        //Example 2:
        //Input: s = "()[]{}"
        //Output: true
        //Example 3:
        //Input: s = "(]"
        //Output: false
//        String s="()";
//        String s = "()[]{}";
//        String s = "(]";
//        String s = "}{";
        String s ="([)]";
        boolean valid = isValid(s);
        System.out.println(valid);
        //Runtime: 5 ms, faster than 14.75% of Java online submissions for Valid Parentheses.
        //Memory Usage: 42.2 MB, less than 33.00% of Java online submissions for Valid Parentheses.
    }
    public static boolean isValid(String s) {
        HashMap<String, String> pair = new HashMap<>();
        Stack<String> stack = new Stack<>();
        pair.put("[", "]");
        pair.put("{", "}");
        pair.put("(", ")");

        String temp;

        for (int i = 0; i < s.length(); i++) {
            temp = s.substring(i, i+1);
            if (pair.containsKey(temp)) {
                stack.push(temp);
            } else {
                if (stack.empty() || !temp.equals(pair.get(stack.pop()))) {
                    return false;
                }
            }
        }
        return stack.empty();
    }

    //第二遍理解题目还能理解错，括号中间不能加杂别的括号。整一个stack就行了。
//    public static boolean isValid(String s) {
//        Stack<String> stack1=new Stack<>();
//        Stack<String> stack2=new Stack<>();
//        Stack<String> stack3=new Stack<>();
//        for(int i=0; i<s.length();i++){
//            char ch=s.charAt(i);
//
//            if(ch=="(".charAt(0)){
//                stack1.push("(");
//            }
//            if(ch==")".charAt(0) & stack1.size()>0){
//                stack1.pop();
//            }else if(ch==")".charAt(0) & stack1.size()==0){
//                return false;
//            }
//            //else if 这里不能拆成if，你判断ch==")"这里要么就是pop要么就是return false，
//            // 你要是把else if拆成if，不管怎么样都会判断stack1.size()是否0，万一上面刚pop完一对括号都走了，你这里为0 了
//            //核心意思就是，我要么pop要么false，走了pop干脆不用走false判断
//
//            if(ch=="{".charAt(0)){
//                stack2.push("{");
//            }
//            if(ch=="}".charAt(0) & stack2.size()>0){
//                stack2.pop();
//            }else if(ch=="}".charAt(0) & stack2.size()==0){
//                return false;
//            }
//
//            if(ch=="[".charAt(0)){
//                stack3.push("[");
//            }
//            if(ch=="]".charAt(0) & stack3.size()>0){
//                stack3.pop();
//            }else if(ch=="]".charAt(0) & stack3.size()==0){
//                return false;
//            }
//        }
//
//        if(stack1.isEmpty() & stack2.isEmpty() & stack3.isEmpty()){
//            return true;
//        }else{
//            return false;
//        }
//    }

    //能不能读懂题再写啊
    //An input string is valid if:
    //Open brackets must be closed by the same type of brackets.
    //Open brackets must be closed in the correct order.
    //Every close bracket has a corresponding open bracket of the same type.

//    public static boolean isValid(String s) {
//        HashMap<Character,Integer> hm1=new HashMap<>();
//        hm1.put("(".charAt(0),0);
//        hm1.put(")".charAt(0),0);
//        hm1.put("{".charAt(0),0);
//        hm1.put("}".charAt(0),0);
//        hm1.put("[".charAt(0),0);
//        hm1.put("]".charAt(0),0);
//        for(int i=0; i<s.length();i++){
//            char ch_s=s.charAt(i);
//            int newnum=hm1.get(ch_s);
//            hm1.replace(ch_s,newnum+1);
//        }
//        int num1=hm1.get("(".charAt(0));
//        int num2=hm1.get(")".charAt(0));
//        int num3=hm1.get("{".charAt(0));
//        int num4=hm1.get("}".charAt(0));
//        int num5=hm1.get("[".charAt(0));
//        int num6=hm1.get("]".charAt(0));
//        if(num1==num2 & num3==num4 &num5==num6){
//            return true;
//        }else{
//            return false;
//        }
//
//    }

}
