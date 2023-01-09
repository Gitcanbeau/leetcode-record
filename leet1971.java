import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class leet1971 {
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
        //Runtime: 163 ms, faster than 59.54% of Java online submissions for Find if Path Exists in Graph.
        //Memory Usage: 210.8 MB, less than 42.95% of Java online submissions for Find if Path Exists in Graph.
    }
    public static boolean validPath(int n, int[][] edges, int source, int destination) {
        //bfs-queue
        List<List<Integer>> graph=buildgraph(n,edges);//生成图
        boolean[] visited=new boolean[n];//标记是否走过，默认false
        Queue<Integer> queue1=new LinkedList<>();//bfs队列存储要被检查的节点
        queue1.offer(source);//source节点加入queue1

        while(!queue1.isEmpty()){
            int current=queue1.poll();
            if(current==destination)  return true; //先弹一个万一成功了直接返回true
            visited[current]=true;//不成功记得标记走过该点
            for(int neighbor: graph.get(current)){
                if(visited[neighbor]==false){//没走过的点再添加到队列中防止重复行为
                    queue1.offer(neighbor);
                    //某节点的所有一级邻接点遍历以后全加入queue1，然后才是一级邻接点的二级邻接点遍历以后全加入queue1
                    //因为先进先出，一级邻接点走完了才是二级邻接点，就是优先宽度
                }
            }
        }
        return false;
    }
    private static List<List<Integer>> buildgraph(int n, int[][] edges){
        List<List<Integer>> graph=new ArrayList<>();
        for(int i=0; i<n;i++){
            graph.add(new ArrayList<>());//初始化邻接表
        }

        for(int[] edge: edges){//每个节点的邻接表添加邻接点
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        return graph;//图是列表的列表
    }
}
