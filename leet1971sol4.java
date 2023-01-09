import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class leet1971sol4 {
    public static void main(String[] args) {
        //There is a bi-directional graph with n vertices, where each vertex is labeled from 0 to n - 1 (inclusive). The edges in the graph are represented as a 2D integer array edges, where each edges[i] = [ui, vi] denotes a bi-directional edge between vertex ui and vertex vi. Every vertex pair is connected by at most one edge, and no vertex has an edge to itself.
        //You want to determine if there is a valid path that exists from vertex source to vertex destination.
        //Given edges and the integers n, source, and destination, return true if there is a valid path from source to destination, or false otherwise.
        //Example 1:
        //Input: n = 3, edges = [[0,1],[1,2],[2,0]], source = 0, destination = 2
        //Output: true
        //Explanation: There are two paths from vertex 0 to vertex 2:
        //- 0 → 1 → 2
        //- 0 → 2
        //Example 2:
        //Input: n = 6, edges = [[0,1],[0,2],[3,5],[5,4],[4,3]], source = 0, destination = 5
        //Output: false
        //Explanation: There is no path from vertex 0 to vertex 5.
        //Constraints:
        //1 <= n <= 2 * 105
        //0 <= edges.length <= 2 * 105
        //edges[i].length == 2
        //0 <= ui, vi <= n - 1
        //ui != vi
        //0 <= source, destination <= n - 1
        //There are no duplicate edges.
        //There are no self edges.
        int n=6;
        int[][] edges={{0,1},{0,2},{3,5},{5,4},{4,3}};
        int source=0;
        int destination=5;
        System.out.println(validPath(n,edges,source,destination));
        //Runtime: 16 ms, faster than 95.05% of Java online submissions for Find if Path Exists in Graph.
        //Memory Usage: 97.6 MB, less than 99.19% of Java online submissions for Find if Path Exists in Graph.
    }
    public static boolean validPath(int n, int[][] edges, int start, int end) {
        int[] parent = new int[n];
        Arrays.fill(parent, -1);

        for (int[] edge: edges) {
            int pX = find(edge[0], parent);
            int pY = find(edge[1], parent);
            if (pX != pY)
                parent[pX] = pY;
        }

        return find(start, parent) == find(end, parent);
    }

    private  static int find(int x, int[] parent) {
        if (parent[x] == -1)
            return x;
        return find(parent[x], parent);
    }
}
