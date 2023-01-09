import java.util.*;

public class leet1135 {
    public static void main(String[] args) {
        //there are n cites numbered from 1 to n.
        //you are given connections, where each connections[i]==[city1,city2,cost] represents the cost to connect
        //city1 and city2 together. a connection is bidirectional.
        //return the minimum cost so that for every pair of cities, there exists a path of connections (possibly of length 1)
        //that connects those two cities together. the cost is the sum of the connection costs used.
        //if the tak is impossible, return -1.

        int n=3;
        int[][] connections={{1,2,5},{1,3,6},{2,3,1}};
//        System.out.println(minimumCost1(n,connections));
        System.out.println(minimumCost2(n,connections));
    }
    private static class UnionFind {
        private int[] parent;
        private int[] rank;
        private int countoftrees;

        public UnionFind(int n) {
            parent = new int[n + 1];
            rank = new int[n + 1];
            countoftrees = n;
            for (int i = 0; i <= n; i++) {
                parent[i] = i;
                rank[i] = 1;
            }
        }
        public boolean union ( int x, int y){
            int rootx = find(x);
            int rooty = find(y);
            if (rootx == rooty) return false;

            countoftrees--;
            if (rank[rootx] >= rank[rooty]) {
                rank[rootx] += rank[rooty];
                parent[rooty] = rootx;
            } else {
                rank[rooty] += rank[rootx];
                parent[rootx] = rooty;
            }

            return true;
        }
        public int find ( int x){
            if (x != parent[x]) {
                parent[x] = find(parent[x]);
            }
            return x;
        }
    }



    public static int minimumCost1(int n,int[][] connections){
        //prim 方法-无向图//https://www.youtube.com/watch?v=GLlIaT_PxVE
        //每次都只链接cost最小的边
        //kruskal 方法//https://www.youtube.com/watch?v=Z4jm4o2bt28
        //把cost按从小到大排序，按cost的顺序挨个检查每一条边，如果已经连上了就跳过，没连上就连上，直到边的个数=顶点个数-1
        //本方法是kruskal
        Arrays.sort(connections,(c1, c2)->c1[2]-c2[2]);
        //想用priority queue写也行

        UnionFind uf =new UnionFind(n);
        int edges=0;
        int totalcost=0;
        for(int i=0;i<connections.length;i++){
            int node1=connections[i][0];
            int node2=connections[i][1];
            int p1= uf.find(node1);
            int p2= uf.find(node2);
            if(uf.union(p1,p2)){
                edges++;
                totalcost+=connections[i][2];
            }

            if(edges==n-1){
                break;
            }
        }



        return edges==(n-1) ? totalcost:-1;
    }



    public static int minimumCost2(int n,int[][] connections){

        List<HashMap<Integer,Integer>> adj=build_adj(n,connections);
        System.out.println(adj);

        boolean[] visited=new boolean[connections.length+1];


        int edges=1;
        int curr=1;
        int mintotalcost=0;

        while(edges++<=n-1){
            visited[curr]=true;
            int minEdge=Integer.MAX_VALUE;
            int next=-1;

            for(int neighbor:adj.get(curr).keySet()){
                if(!visited[neighbor]){
                    int costbetweencurrandneighbor=adj.get(curr).get(neighbor);
                    if (costbetweencurrandneighbor<minEdge){
                        minEdge=costbetweencurrandneighbor;
                        next=neighbor;
                    }
                }

            }
            mintotalcost+=minEdge;
            curr=next;
        }
        return mintotalcost;

    }

    private static List<HashMap<Integer,Integer>> build_adj(int n,int[][] connections){
        List<HashMap<Integer,Integer>> adj=new ArrayList<>();
        for(int i=0;i<=n;i++){
            adj.add(new HashMap<>());
        }

        for(int i=0;i<connections.length;i++){
            int end1=connections[i][0];
            int end2=connections[i][1];
            int weight=connections[i][2];
            adj.get(end1).putIfAbsent(end2,weight);
            adj.get(end2).putIfAbsent(end1,weight);
        }
        return adj;
    }

}
