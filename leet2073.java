import java.util.LinkedList;
import java.util.Queue;

public class leet2073 {
    public static void main(String[] args) {
//There are n people in a line queuing to buy tickets,
// where the 0th person is at the front of the line and the (n - 1)th person is at the back of the line.
//You are given a 0-indexed integer array tickets of length n
// where the number of tickets that the ith person would like to buy is tickets[i].
//Each person takes exactly 1 second to buy a ticket.
// A person can only buy 1 ticket at a time and has to go back to the end of the line
// (which happens instantaneously) in order to buy more tickets.
// If a person does not have any tickets left to buy, the person will leave the line.
//Return the time taken for the person at position k (0-indexed) to finish buying tickets.
//Example 1:
//Input: tickets = [2,3,2], k = 2
//Output: 6
//Explanation:
//- In the first pass, everyone in the line buys a ticket and the line becomes [1, 2, 1].
//- In the second pass, everyone in the line buys a ticket and the line becomes [0, 1, 0].
//The person at position 2 has successfully bought 2 tickets and it took 3 + 3 = 6 seconds.
//Example 2:
//Input: tickets = [5,1,1,1], k = 0
//Output: 8
//Explanation:
//- In the first pass, everyone in the line buys a ticket and the line becomes [4, 0, 0, 0].
//- In the next 4 passes, only the person in position 0 is buying tickets.
//The person at position 0 has successfully bought 5 tickets and it took 4 + 1 + 1 + 1 + 1 = 8 seconds.
        int[] tickets={2,3,2};
        int time = timeRequiredToBuy(tickets,2);
        System.out.println(time);
        //Runtime: 15 ms, faster than 6.49% of Java online submissions for Time Needed to Buy Tickets.
        //Memory Usage: 46 MB, less than 5.09% of Java online submissions for Time Needed to Buy Tickets.
        //这道题是queue模型但是不一定非要用queue来做，数学方法简化问题以后用if判断省时省力
    }
    public static int timeRequiredToBuy(int[] arr, int k) {
        int ans=0,n=arr.length;

        int tar=k;

        Queue<Integer> q=new LinkedList<>();
        for(int i=0;i<n;i++){
            q.add(i==k ? -arr[i]:arr[i]);//i=k就加入-arr[k]，i!=k就加入arr[i]
        }

        while(!q.isEmpty()){
            int tickets=q.poll();
            if(tickets==0){
                q.add(tickets);
            }
            if(tickets+1==0){
                ans++;
                break;
            }else if(tickets<0){
                ans++;
                q.add(tickets+1);
            }else if(tickets>0){
                ans++;
                q.add(tickets-1);
            }

        }
        return ans;
    }
}
