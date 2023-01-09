import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class leet787 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/cheapest-flights-within-k-stops/
    }
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        //Runtime: 10 ms, faster than 58.45% of Java online submissions for Cheapest Flights Within K Stops.
        //Memory Usage: 48.8 MB, less than 17.16% of Java online submissions for Cheapest Flights Within K Stops.

        // Use a matrix rather than HashMap to speed up lookups as this is just a 100 x 100 graph
        int[][] adjacencyMatrix = new int[100][100];
        for (int[] flight : flights) {
            adjacencyMatrix[flight[0]][flight[1]] = flight[2];
        }

        Map<Integer, Integer> minPathCost = new HashMap<>();
        Queue<int[]> bfsQueue = new LinkedList<>();
        bfsQueue.add(new int[] { src, 0 });

        // Perform BFS upto no more than k - depth
        int cheapestPrice = Integer.MAX_VALUE;
        while (!bfsQueue.isEmpty() && k >= -1) {
            int len = bfsQueue.size();
            for (int i = 0; i < len; i++) {
                int key = bfsQueue.peek()[0];
                int val = bfsQueue.peek()[1];
                bfsQueue.poll();

                if (key == dst) {
                    cheapestPrice = Math.min(cheapestPrice, val);
                } else {
                    for (int x = 0; x < 100; x++) {
                        // Any route will have a positive (>0) cost
                        if (adjacencyMatrix[key][x] > 0) {
                            int cost = val + adjacencyMatrix[key][x];
                            // BFS Prunning - only proceed if:
                            // 1. The current cost is less than the cheapest price observed so far, and,
                            // 2. The current price at node 'x' is lesser than any previously observed price for the same node
                            if ((cost < cheapestPrice) && (!minPathCost.containsKey(x) || minPathCost.get(x) > cost)) {
                                bfsQueue.add(new int[] { x, cost });
                                minPathCost.put(x, cost);
                            }
                        }
                    }
                }
            }
            k--;
        }

        return cheapestPrice == Integer.MAX_VALUE ? -1 : cheapestPrice;
    }
}
