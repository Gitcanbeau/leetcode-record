public class leet2073sol2 {
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
        //Runtime: 0 ms, faster than 100.00% of Java online submissions for Time Needed to Buy Tickets.
        //Memory Usage: 42.4 MB, less than 15.97% of Java online submissions for Time Needed to Buy Tickets.
    }
    public static int timeRequiredToBuy(int[] tickets, int k) {
        int res = 0;
        for(int i = 0; i < tickets.length; i++) {
            if(tickets[i] < tickets[k]){
                res = res + tickets[i];
            }
            if(tickets[i] > tickets[k]) {
                if( i < k)
                    res = res + tickets[k];
                if(i > k)
                    res = res + (tickets[k] - 1);
            }
            if(tickets[i] == tickets[k]){
                if(i < k)
                    res = res + tickets[k];
                if(i > k)
                    res = res + tickets[k] - 1;
                if(i == k)
                    res = res + tickets[k];
            }
        }
        return res;
    }

}
