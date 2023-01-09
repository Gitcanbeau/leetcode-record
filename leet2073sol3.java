import java.util.LinkedList;
import java.util.Queue;

public class leet2073sol3 {
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
//        int[] tickets={2,3,2};//k=2,time=6
//        int[] tickets={5,1,1,1};//k=0,time=8
//        int[] tickets={5,1,5,1,1,3};//k=0,time=15
        int[] tickets={84,49,5,24,70,77,87,8};//k=3
        int time = timeRequiredToBuy(tickets,3);
        System.out.println(time);
        //自己写出来的，对queue有了一个好的理解，但感觉写的很啰嗦
        //Runtime: 28 ms, faster than 5.99% of Java online submissions for Time Needed to Buy Tickets.
        //Memory Usage: 46.3 MB, less than 5.19% of Java online submissions for Time Needed to Buy Tickets.
    }
    public static int timeRequiredToBuy(int[] arr, int k) {
        Queue<Integer> que1=new LinkedList<>();
        Queue<Integer> que2=new LinkedList<>();
        int timetotal=0;
        int cyclenumber=1;
        int countpercycle=0;
        int lastcyclecount=0;
        int ticket;
        for(int i=0; i<arr.length;i++){
            que1.add(arr[i]);
        }
        while(cyclenumber<arr[k]){
            if(cyclenumber<arr[k]&&cyclenumber%2==1){
                while(!que1.isEmpty()){
                    ticket=que1.poll();
                    if(ticket-1>=0){
                        countpercycle++;
                        que2.add(ticket-1);
                    }
                    if(ticket==0){
                        que2.add(ticket);
                    }
                }
                timetotal=timetotal+countpercycle;
                countpercycle=0;
                cyclenumber++;
            }
            if(cyclenumber<arr[k]&&cyclenumber%2==0){
                while(!que2.isEmpty()){
                    ticket=que2.poll();
                    if(ticket-1>=0){
                        countpercycle++;
                        que1.add(ticket-1);
                    }
                    if(ticket==0){
                        que1.add(ticket);
                    }
                }
                timetotal=timetotal+countpercycle;
                countpercycle=0;
                cyclenumber++;
            }
        }
        while(cyclenumber==arr[k]){
                if(!que1.isEmpty()) {
                    while (lastcyclecount <= k) {
                        ticket = que1.poll();
                        if (ticket-1 >= 0) {
                            lastcyclecount++;
                            countpercycle++;
                            que2.add(ticket - 1);
                        }
                        if (ticket == 0) {
                            lastcyclecount++;
                            que2.add(ticket);
                        }
                    }
                }
                if(!que2.isEmpty()) {
                    while (lastcyclecount <= k) {
                        ticket = que2.poll();
                        if (ticket-1 >= 0) {
                            lastcyclecount++;
                            countpercycle++;
                            que1.add(ticket - 1);
                        }
                        if (ticket == 0) {
                            lastcyclecount++;
                            que1.add(ticket);
                        }
                    }
                }
                timetotal=timetotal+countpercycle;
                cyclenumber++;
        }
        return timetotal;
    }
}
