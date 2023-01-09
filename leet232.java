import java.util.Stack;

public class leet232 {

    Stack<Integer> stack1=new Stack<>();
    Stack<Integer> stack2=new Stack<>();
    public leet232() {
//        this.stack1=stack1;
//        this.stack2=stack2;
        //感觉不用写
    }

    public void push(int x) {
        if(stack1.isEmpty()){
            stack1.push(x);
        }else{
            while(!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
            stack1.push(x);
            while(!stack2.isEmpty()){
                stack1.push(stack2.pop());
            }
        }
    }

    public int pop() {
        return stack1.pop();
    }

    public int peek() {
        return stack1.peek();
    }

    public boolean empty() {
        return stack1.isEmpty();
    }
    public static void main(String[] args) {
        leet232 myqueue=new leet232();
        myqueue.push(1);
        myqueue.push(2);
        myqueue.push(3);
        myqueue.push(4);
//        int peek = myqueue.peek();
//        System.out.println(peek);
        int pop = myqueue.pop();
        System.out.println(pop);
        myqueue.push(5);
        System.out.println(myqueue.pop());
        System.out.println(myqueue.pop());
        System.out.println(myqueue.pop());
        boolean empty = myqueue.empty();
        System.out.println(empty);
        //Runtime: 0 ms, faster than 100.00% of Java online submissions for Implement Queue using Stacks.
        //Memory Usage: 40.3 MB, less than 89.50% of Java online submissions for Implement Queue using Stacks.
    }
}
