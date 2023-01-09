import java.util.Stack;

public class leet1021 {
    public static void main(String[] args) {
        //A valid parentheses string is either empty "", "(" + A + ")", or A + B, where A and B are valid parentheses strings, and + represents string concatenation.
        //For example, "", "()", "(())()", and "(()(()))" are all valid parentheses strings.
        //A valid parentheses string s is primitive if it is nonempty, and there does not exist a way to split it into s = A + B, with A and B nonempty valid parentheses strings.
        //Given a valid parentheses string s, consider its primitive decomposition: s = P1 + P2 + ... + Pk, where Pi are primitive valid parentheses strings.
        //Return s after removing the outermost parentheses of every primitive string in the primitive decomposition of s.

        //Example 1:
        //Input: s = "(()())(())"
        //Output: "()()()"
        //Explanation:
        //The input string is "(()())(())", with primitive decomposition "(()())" + "(())".
        //After removing outer parentheses of each part, this is "()()" + "()" = "()()()".
        //Example 2:
        //Input: s = "(()())(())(()(()))"
        //Output: "()()()()(())"
        //Explanation:
        //The input string is "(()())(())(()(()))", with primitive decomposition "(()())" + "(())" + "(()(()))".
        //After removing outer parentheses of each part, this is "()()" + "()" + "()(())" = "()()()()(())".
        //Example 3:
        //Input: s = "()()"
        //Output: ""
        //Explanation:
        //The input string is "()()", with primitive decomposition "()" + "()".
        //After removing outer parentheses of each part, this is "" + "" = "".
        //Constraints:
        //1 <= s.length <= 105
        //s[i] is either '(' or ')'.
        //s is a valid parentheses string.

        String s = "(()())(())";
//        String s = "(()())(())(()(()))";
//        String s = "()()";
        String s1 = removeOuterParentheses(s);
        System.out.println(s1);
        //Runtime: 7 ms, faster than 58.78% of Java online submissions for Remove Outermost Parentheses.
        //Memory Usage: 41.8 MB, less than 98.39% of Java online submissions for Remove Outermost Parentheses.
    }

    public static String removeOuterParentheses(String s) {
        return String.valueOf(removeOuter(s));
    }

    private static StringBuilder removeOuter(String input) {
        int i = 0;
        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        while (i < input.length()) {
            if (input.charAt(i) == ')') {
                if (stack.size() != 1) sb.append(input.charAt(i));
                stack.pop();
            } else if (input.charAt(i) == '(') {
                if (!stack.isEmpty()) sb.append(input.charAt(i));
                stack.push('(');
            }
            i++;
        }
        return sb;
    }
}
