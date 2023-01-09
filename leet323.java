import java.util.ArrayList;
import java.util.List;

public class leet323 {
    public static void main(String[] args) {
        //Number of Connected Components in an Undirected Graph
        //Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes),
        // write a function to find the number of connected components in an undirected graph.
        //
        //Example 1:
        //     0          3
        //     |          |
        //     1 --- 2    4
        //Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], return 2.
        //
        //Example 2:
        //     0           4
        //     |           |
        //     1 --- 2 --- 3
        //Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]], return 1.
        //
        //Note:
        //You can assume that no duplicate edges will appear in edges. Since all edges are undirected,
        // [0, 1] is the same as [1, 0] and thus will not appear together in edges.
//        int n=5;
//        int[][] edges={{0,1},{1,2},{3,4}};
//        int n=5;
//        int[][] edges={{0,1},{1,2},{2,3},{3,4}};
//        int n=5;
//        int[][] edges={{0,1},{1,2},{2,3},{3,4},{4,1}};
        int n=5;
        int[][] edges={{0,1},{1,2}};
        System.out.println(countComponenets(n,edges));
    }
    public static int countComponenets(int n, int[][] edges){
        List<List<Integer>> adj=build_adj(n,edges);
        boolean[] visited=new boolean[n];
        int count=0;
        for(int i=0;i<n;i++){
            if(visited[i]==false){
                dfs(adj,visited,i);
                count++;
            }
        }
        return count;

    }
    private static void dfs (List<List<Integer>> adj, boolean[] visited, int i){
        if(visited[i]) return;


        visited[i]=true;
        for(int next:adj.get(i)){
            dfs(adj,visited,next);
        }

        return;
    }
    private static List<List<Integer>> build_adj (int n, int[][] edges){
        List<List<Integer>> adj=new ArrayList<>();
        for(int i=0; i<n;i++){
            adj.add(new ArrayList<>());
        }

        for(int[] edge: edges){
            int end1=edge[0];
            int end2=edge[1];
            adj.get(end1).add(end2);
            adj.get(end2).add(end1);
        }
        return adj;
    }
}
