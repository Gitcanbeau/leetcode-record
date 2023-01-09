import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class leet950 {
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
        //Sort array
        //make other array
        //add 1 element in new array
        //leave a space
        //add other
        //repeat it until you reach deck last
        //the new array is ans
        Arrays.sort(deck);
        int n = deck.length;
        int arr[] = new int[n];
        int k = 0;
        int click = 0;
        while (k < n) {
            for (int i = 0; i < n; i++) {
                if (arr[i] == 0 && click == 0) {
                    arr[i] = deck[k];
                    click = 1;
                    k++;
                } else if (arr[i] == 0) click = 0;
            }
        }
        return arr;
    }
}
