import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class leet1971sol2 {
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
        //Runtime: 185 ms, faster than 53.94% of Java online submissions for Find if Path Exists in Graph.
        //Memory Usage: 187.9 MB, less than 69.00% of Java online submissions for Find if Path Exists in Graph.
    }
    public static boolean validPath(int n, int[][] edges, int source, int destination) {
        //bfs-stack
        List<List<Integer>> graph=buildgraph(n,edges);
        boolean[] visited=new boolean[n];
        Stack<Integer> stack1=new Stack<>();
        stack1.push(source);

        while(!stack1.isEmpty()){
            int current=stack1.pop();
            if(current==destination) return true;
            visited[current]=true;
            for(int neighbor: graph.get(current)){
                if(visited[neighbor]==false){
                    stack1.push(neighbor);
                    //某节点的所有一级邻接点遍历以后全加入stack1，然后才是一级邻接点的二级邻接点遍历以后全加入stack1
                    //因为先进后出，二级邻接点走完了才是一级邻接点，就是优先深度
                }
            }
        }
        return false;
    }
    private static List<List<Integer>> buildgraph(int n, int[][] edges){
        List<List<Integer>> graph=new ArrayList<>();
        for(int i=0; i<n;i++){
            graph.add(new ArrayList<>());
        }

        for(int[] edge: edges){
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        return graph;
    }
}
