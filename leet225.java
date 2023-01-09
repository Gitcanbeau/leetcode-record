import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class leet225 {
    Queue<Integer> queue1=new LinkedList<>();
    Queue<Integer> queue2=new LinkedList<>();

    public leet225() {
    }

    public void push(int x) {
        if(queue1.isEmpty()){
            queue1.add(x);
        }else{
            while(!queue1.isEmpty()){
                queue2.add(queue1.poll());
            }
            queue1.add(x);
            while(!queue2.isEmpty()){
                queue1.add(queue2.poll());
            }
        }
    }

    public int pop() {
        return queue1.poll();
    }

    public int top() {
        return queue1.peek();
    }

    public boolean empty() {
        return queue1.isEmpty();
    }

    public static void main(String[] args) {
        leet225 mystack=new leet225();
        mystack.push(1);
        mystack.push(2);
        mystack.push(3);
        System.out.println(mystack.top());
        mystack.push(4);
        System.out.println(mystack.pop());
        mystack.push(5);
        System.out.println(mystack.pop());
        System.out.println(mystack.pop());
        System.out.println(mystack.empty());
        //Runtime: 0 ms, faster than 100.00% of Java online submissions for Implement Stack using Queues.
        //Memory Usage: 40.3 MB, less than 89.31% of Java online submissions for Implement Stack using Queues.
    }
}
