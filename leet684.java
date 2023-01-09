import java.util.*;

public class leet684 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/redundant-connection/
        int[][] edges={{1,2},{2,3},{3,4},{1,4},{1,5}};
//        int[] res1=findRedundantConnection1(edges);
//        System.out.println(res1[0]+","+res1[1]);
//        int[] res2=findRedundantConnection2(edges);
//        System.out.println(res2[0]+","+res2[1]);
        int[] res3=findRedundantConnection3(edges);
        System.out.println(res3[0]+","+res3[1]);

    }


    private static class UnionFind{
        private static int[] parent;
        private static int[] rank;
        private static int countoftrees;
        public UnionFind(int n){
            parent=new int[n];
            rank=new int[n];
            countoftrees=n;
            for(int i=0;i<n;i++){
                this.parent[i]=i;
                this.rank[i]=1;
            }
        }

        public static int find(int x){
            if(x==parent[x]) return x;
            return find(parent[x]);
        }

        public static boolean union(int x,int y){
            int rootx=find(x);
            int rooty=find(y);
            if(rootx==rooty) return false;

            if(rank[rootx]<=rank[rooty]){
                parent[rootx]=rooty;
                rank[rooty]+=rank[rootx];
                rank[rootx]=0;
            }else{
                parent[rooty]=rootx;
                rank[rootx]+=rank[rooty];
                rank[rooty]=0;
            }
            countoftrees--;
            return true;
        }

    }

    public static int[] findRedundantConnection1(int[][] edges) {
        //Runtime: 1 ms, faster than 93.65% of Java online submissions for Redundant Connection.
        //Memory Usage: 44.2 MB, less than 31.57% of Java online submissions for Redundant Connection.

        UnionFind uf=new UnionFind(edges.length+1);

        for(int[] edge : edges){
            int p1=edge[0];
            int p2=edge[1];
            if(uf.union(p1, p2)==true){
                continue;
            }else {
                return new int[]{edge[0], edge[1]};
            }
        }

        return new int[]{};
    }











    public static int[] findRedundantConnection2(int[][] edges) {
        //dfs-recursion
        //Runtime: 18 ms, faster than 15.56% of Java online submissions for Redundant Connection.
        //Memory Usage: 52.5 MB, less than 6.13% of Java online submissions for Redundant Connection.

        //Since the question require us to remove the redundant edge,
        // when we constructing the graph, we do dfs on each edge to see is it redundant, if yes return the edge, if no add the edge into the graph.
        //Why dfs can find redundant. Actually a redundant edge means after we add this edge, the graph will contain a cycle.
        // So, when we came across a new edge, we do dfs from its start and its end, if there already exist a path from start to end, this new edge is redundant.
        int n=edges.length;
        List<List<Integer>>adj = new ArrayList<>(n);
        for(int i=0;i<=n;i++){
            adj.add(new ArrayList<>());
        }
        boolean[] visited; //每一个节点是否访问过
        for(int []edge:edges){
            visited=new boolean[n+1]; //每一次都新建一个visited //不要在外面声明
            int end1=edge[0];
            int end2=edge[1];
            adj.get(end1).add(end2);
            adj.get(end2).add(end1);
            if(hasCycle(adj,visited,edge[0],-1)){ //传入节点和其父节点，看是否会构成环，如果是就立刻返回该节点
                return edge;
            }

        }
        return new int[0];
    }
    public static boolean hasCycle(List<List<Integer>>adj,boolean[]visited,int curr,int pre){
        //题里说了就一个环，别想多了
        if(visited[curr]) return true;


        visited[curr]=true;
        for(int next: adj.get(curr)){
            if(next!=pre && hasCycle(adj,visited,next,curr)) return true;
        }
        return false;
    }




    public static int[] findRedundantConnection3(int[][] edges) {
        //bfs-iteration
        //Runtime: 24 ms, faster than 10.11% of Java online submissions for Redundant Connection.
        //Memory Usage: 54.5 MB, less than 5.16% of Java online submissions for Redundant Connection.
        int n=edges.length;
        List<List<Integer>> adj=new ArrayList<>();
        for(int i=0;i<=n;i++){
            adj.add(new ArrayList<>());
        }
        boolean[] visited;
        for(int[] edge: edges){
            visited=new boolean[n+1];
            int end1=edge[0];
            int end2=edge[1];
            adj.get(end1).add(end2);
            adj.get(end2).add(end1);
            if(isCyclic(adj,visited,edge[0])) return edge;
        }
        return new int[0];
    }

    private static boolean isCyclic(List<List<Integer>> adj,boolean[] visited,int curr){
        Queue<Integer> bfsque=new LinkedList<>();
        bfsque.offer(curr);
        while(!bfsque.isEmpty()){
            int top=bfsque.poll();
            if(visited[top]) return true;
            visited[top]=true;

            for(int next:adj.get(top)){
//                if(!visited[next]) bfsque.offer(next);
                if(visited[next] ){
//                    return true;//这么些不对
                    continue;
                }else{
                    bfsque.offer(next);
                }
            }
        }
        return false;
    }




    public static int[] findRedundantConnection4(int[][] edges) {
        //bfs-topological sorting
        //Runtime: 12 ms, faster than 20.77% of Java online submissions for Redundant Connection.
        //Memory Usage: 46.3 MB, less than 18.51% of Java online submissions for Redundant Connection.
        int n = edges.length;
        Map<Integer, Set<Integer>> adj=new HashMap<>();
        int[] indegree=new int[n+1];

        for(int[] edge:edges){
            int end1=edge[0];
            int end2=edge[1];
            adj.putIfAbsent(end1,new HashSet<>());
            adj.putIfAbsent(end2,new HashSet<>());
            adj.get(end1).add(end2);
            adj.get(end2).add(end1);
            indegree[end1]++;
            indegree[end2]++;
        }

        Queue<Integer> bfsque = new LinkedList<>();
        for(int i=0;i<indegree.length;i++){
            if(indegree[i]==1){
                bfsque.offer(i);
            }
        }

        while (!bfsque.isEmpty()) {
            Integer curr = bfsque.poll();

            for (int neighbor : adj.get(curr)) {
                indegree[neighbor]--;
                if (indegree[neighbor] == 1)
                    bfsque.add(neighbor);
            }
            adj.remove(curr); //只把不在环里面的点移除
        }

        for (var i = n - 1; i >= 0; i--) {
            if (adj.containsKey(edges[i][0]) && adj.containsKey(edges[i][1])) return edges[i];
        }
        return null;
    }


}
