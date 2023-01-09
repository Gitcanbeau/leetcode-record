import java.util.Stack;

public class leet1598 {
    public static void main(String[] args) {
        //The Leetcode file system keeps a log each time some user performs a change folder operation.
        //The operations are described below:
        //"../" : Move to the parent folder of the current folder. (If you are already in the main folder, remain in the same folder).
        //"./" : Remain in the same folder.
        //"x/" : Move to the child folder named x (This folder is guaranteed to always exist).
        //You are given a list of strings logs where logs[i] is the operation performed by the user at the ith step.
        //The file system starts in the main folder, then the operations in logs are performed.
        //Return the minimum number of operations needed to go back to the main folder after the change folder operations.
        //Example 1:
        //Input: logs = ["d1/","d2/","../","d21/","./"]
        //Output: 2
        //Explanation: Use this change folder operation "../" 2 times and go back to the main folder.
        //Example 2:
        //Input: logs = ["d1/","d2/","./","d3/","../","d31/"]
        //Output: 3
        //Example 3:
        //Input: logs = ["d1/","../","../","../"]
        //Output: 0
        //Constraints:
        //1 <= logs.length <= 103
        //2 <= logs[i].length <= 10
        //logs[i] contains lowercase English letters, digits, '.', and '/'.
        //logs[i] follows the format described in the statement.
        //Folder names consist of lowercase English letters and digits.
//        String[] s={"d1/","d2/","../","d21/","./"};
//        String[] s={"d1/","d2/","./","d3/","../","d31/"};
        String[] s={"d1/","../","../","../"};
        int i = minOperations(s);
        System.out.println(i);
        //Runtime: 6 ms, faster than 6.63% of Java online submissions for Crawler Log Folder.
        //Memory Usage: 42.9 MB, less than 37.18% of Java online submissions for Crawler Log Folder.
    }
    public static int minOperations(String[] logs) {
        Stack<String> stack1=new Stack<>();
        int count=0;
        for(String operation:logs){
            switch(operation){
                case "./":
                    break;
                case "../":
                    if(!stack1.isEmpty()) stack1.pop();
                    break;
                default:
                    stack1.push(operation);
            }
        }
        while(!stack1.isEmpty()){
            stack1.pop();
            count++;
        }
        return count;
    }
}
