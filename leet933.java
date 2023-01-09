import java.util.LinkedList;
import java.util.Queue;

public class leet933 {
    Queue<Integer> queue = new LinkedList<>();

    public leet933() {
    }

    public int ping(int t) {
        queue.add(t);
        while (queue.peek() < (t-3000)){
            queue.poll();
        }
        return queue.size();
    }

    public static void main(String[] args) {
        //You have a RecentCounter class which counts the number of recent requests within a certain time frame.
        //Implement the RecentCounter class:
        //RecentCounter() Initializes the counter with zero recent requests.
        //int ping(int t) Adds a new request at time t, where t represents some time in milliseconds,
        // and returns the number of requests that has happened in the past 3000 milliseconds (including the new request). Specifically, return the number of requests that have happened in the inclusive range [t - 3000, t].
        //It is guaranteed that every call to ping uses a strictly larger value of t than the previous call.
        //Example 1:
        //Input
        //["RecentCounter", "ping", "ping", "ping", "ping"]
        //[[], [1], [100], [3001], [3002]]
        //Output
        //[null, 1, 2, 3, 3]
        //Explanation
        //RecentCounter recentCounter = new RecentCounter();
        //recentCounter.ping(1);     // requests = [1], range is [-2999,1], return 1
        //recentCounter.ping(100);   // requests = [1, 100], range is [-2900,100], return 2
        //recentCounter.ping(3001);  // requests = [1, 100, 3001], range is [1,3001], return 3
        //recentCounter.ping(3002);  // requests = [1, 100, 3001, 3002], range is [2,3002], return 3
        leet933 recentcall=new leet933();
        int ping1 = recentcall.ping(1);
//        int ping2 = recentcall.ping(2);
//        int ping3 = recentcall.ping(3001);
//        int ping4 = recentcall.ping(3002);
        System.out.println(ping1);
//        System.out.println(ping2);
//        System.out.println(ping3);
//        System.out.println(ping4);
    }



}
