import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class leet950sol2 {
    public static void main(String[] args) {
        //You are given an integer array deck. There is a deck of cards where every card has a unique integer. The integer on the ith card is deck[i].
        //You can order the deck in any order you want. Initially, all the cards start face down (unrevealed) in one deck.
        //You will do the following steps repeatedly until all cards are revealed:
        //Take the top card of the deck, reveal it, and take it out of the deck.
        //If there are still cards in the deck then put the next top card of the deck at the bottom of the deck.
        //If there are still unrevealed cards, go back to step 1. Otherwise, stop.
        //Return an ordering of the deck that would reveal the cards in increasing order.
        //Note that the first entry in the answer is considered to be the top of the deck.
        //Example 1:
        //Input: deck = [17,13,11,2,3,5,7]
        //Output: [2,13,3,11,5,17,7]
        //Explanation:
        //We get the deck in the order [17,13,11,2,3,5,7] (this order does not matter), and reorder it.
        //After reordering, the deck starts as [2,13,3,11,5,17,7], where 2 is the top of the deck.
        //We reveal 2, and move 13 to the bottom.  The deck is now [3,11,5,17,7,13].
        //We reveal 3, and move 11 to the bottom.  The deck is now [5,17,7,13,11].
        //We reveal 5, and move 17 to the bottom.  The deck is now [7,13,11,17].
        //We reveal 7, and move 13 to the bottom.  The deck is now [11,17,13].
        //We reveal 11, and move 17 to the bottom.  The deck is now [13,17].
        //We reveal 13, and move 17 to the bottom.  The deck is now [17].
        //We reveal 17.
        //Since all the cards revealed are in increasing order, the answer is correct.
        //Example 2:
        //Input: deck = [1,1000]
        //Output: [1,1000]

        //1 <= deck[i] <= 106 可以利用>1这个限制条件

        int[] arr={17,13,11,2,3,5,7};
//        int[] arr={1,1000};
        int[] ints = deckRevealedIncreasing(arr);
        System.out.print("[");
        for(int i=0; i<ints.length;i++){
            System.out.print(ints[i]+",");
        }
        System.out.println("]");
        //Runtime: 8 ms, faster than 14.26% of Java online submissions for Reveal Cards In Increasing Order.
        //Memory Usage: 43.8 MB, less than 77.49% of Java online submissions for Reveal Cards In Increasing Order.
    }
    public static int[] deckRevealedIncreasing(int[] deck) {
        Deque<Integer> deque = new LinkedList<>();
        int[] result = new int[deck.length];
        // first sort it in ascending order
        Arrays.sort(deck);
        // do the operation reverse of the steps that are given in the question
        /* If the deque is empty then we simply add deck at i element to the deque. Else if the deque is not empty then we add deck at i to the front of the deque and take the last element of the deque to the front of the deque if it is not an end element of the deck. If it is the last element of the deck then we simply terminate the loop i.e. we do not shift the last element to the first.
         */
        for(int i=deck.length-1; i>=0; i--){
            deque.offerFirst(deck[i]);
            if(!deque.isEmpty()){
                if(i==0) break;
                deque.offerFirst(deque.pollLast());
            }
        }

        /* Hence we have the combination of the cards that reveal in increasing order in the deque. But we cant return deque because the return type of deckRevealedIncreasing is Array. So, we need to transfer the deque element to the Array.
         */
        for(int i=0; i<result.length; i++) result[i] = deque.poll();

        return result;
    }
}
