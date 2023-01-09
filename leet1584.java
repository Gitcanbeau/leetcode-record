import java.util.PriorityQueue;

public class leet1584 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/min-cost-to-connect-all-points/
    }
    public static int minCostConnectPoints2(int[][] points) {
        //minimum spanning tree
        //prim
        //Runtime: 41 ms, faster than 95.85% of Java online submissions for Min Cost to Connect All Points.
        //Memory Usage: 43.6 MB, less than 96.56% of Java online submissions for Min Cost to Connect All Points.
        int len = points.length;
        // array that keep track of the shortest distance from mst to each node
        int[] disArr = new int[len];
        for (int i = 1; i < len; i++) {
            disArr[i] = Integer.MAX_VALUE;
        }
        // visited[node] == true if node in mst
        boolean[] visited = new boolean[len];
        visited[0] = true;

        int numEdge = 0;
        // current node, used to update the disArr
        int cur = 0;
        // result
        int res = 0;

        while (numEdge++ < len - 1) {
            int minEdge = Integer.MAX_VALUE;
            int next = -1;
            for (int i = 0; i < len; ++i) {
                // if the node i is not in mst
                if (!visited[i]) {
                    // find the distance between cur and i
                    int dis = Math.abs(points[cur][0] - points[i][0]) + Math.abs(points[cur][1] - points[i][1]);
                    // update distance array
                    disArr[i] = Math.min(dis, disArr[i]);
                    // find the shortest edge
                    if (disArr[i] < minEdge) {
                        minEdge = disArr[i];
                        next = i;
                    }
                }
            }
            cur = next;
            visited[cur] = true;
            res += minEdge;
        }

        return res;
    }




    public static int findParent(int node, int[] unions) {
        if(unions[node] == node) {
            return node;
        }

        int parent = findParent(unions[node], unions);

        //Path compression
        unions[node] = parent;
        return parent;
    }

    public static boolean makeUnion(int parent1, int parent2, int[] rank, int[] unions) {
        if(parent1 == parent2) {
            return false;
        }

        // avoiding skew structure which provokes linear time
        if(rank[parent1] > rank[parent2]) {
            unions[parent2] = parent1;
            rank[parent1]++;
        } else {
            unions[parent1] = parent2;
            rank[parent2]++;
        }

        return true;
    }


    public static int minCostConnectPoints1(int[][] points) {
        //Runtime: 231 ms, faster than 66.26% of Java online submissions for Min Cost to Connect All Points.
        //Memory Usage: 127.9 MB, less than 28.03% of Java online submissions for Min Cost to Connect All Points.
        //kruskal+union find
        int n = points.length;
        int[] unions = new int[n+1];
        int[] rank = new int[n+1];

        for(int i = 0; i<=n; i++) {
            unions[i] = i;
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if(a[2] > b[2]) { //升序排列
                return 1;
            }
            return -1;
        });

        for(int i = 0; i<n; i++) {
            for(int j = i+1; j<n; j++) {
                int dist = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
                pq.add(new int[] {i, j, dist});
            }
        }

        int edgesAdded = 0, cost = 0;
        while(!pq.isEmpty()) {
            int[] top = pq.poll();
            int parent1 = findParent(top[0], unions);
            int parent2 = findParent(top[1], unions);
            if(makeUnion(parent1, parent2, rank, unions)) {
                cost += top[2];
                edgesAdded++;
            }

            if(edgesAdded == n-1) {
                return cost;
            }
        }

        return cost;
    }
}
