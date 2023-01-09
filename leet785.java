import java.util.*;

public class leet785 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/is-graph-bipartite/
    }
    public static boolean isBipartite(int[][] graph) {
        //Runtime: 1 ms, faster than 91.63% of Java online submissions for Is Graph Bipartite?.
        //Memory Usage: 54.2 MB, less than 20.04% of Java online submissions for Is Graph Bipartite?.
        ////Graph Coloring - DFS
        ////0==no color
        ////1=blue
        ////-1=red color
        int[] colors = new int[graph.length];
        for (int i = 0; i < graph.length; i++)
            if (colors[i] == 0 && !validColor(graph, colors, 1, i))
                return false;
        return true;
    }

    private static boolean validColor(int[][] graph, int[] colors, int color, int start) {
        if (colors[start] != 0)
            return colors[start] == color;
        colors[start] = color;
        for (int adjacent : graph[start])
            if (!validColor(graph, colors, -color, adjacent))
                return false;
        return true;
    }


    public static boolean isBipartite2(int[][] g) {
        //Runtime: 3 ms, faster than 39.20% of Java online submissions for Is Graph Bipartite?.
        //Memory Usage: 53.9 MB, less than 32.79% of Java online submissions for Is Graph Bipartite?.
        //bfs
        int[] colors = new int[g.length];
        for (int i = 0; i < g.length; i++)
            if (colors[i] == 0) {
                Queue<Integer> q = new LinkedList<>();
                q.add(i);
                colors[i] = 1;
                while (!q.isEmpty()) {
                    Integer node = q.poll();
                    for (int adjacent : g[node])
                        if (colors[adjacent] == colors[node])
                            return false;
                        else if (colors[adjacent] == 0) {
                            q.add(adjacent);
                            colors[adjacent] = -colors[node];
                        }
                }
            }

        return true;
    }




    //this function is using BFS travesal to check bipartite
    private boolean isbfs(int[][] graph,int i,int[] color){
        Queue<Integer> q = new LinkedList<>();
        q.add(i);
        color[i]=1;
        while(!q.isEmpty()){
            Integer node = q.poll();
            for(int it : graph[node]){
                if(color[it]==-1){
                    q.add(it);
                    color[it]=1-color[node];
                }
                else{
                    if(color[it]==color[node]) return false;
                }
            }
        }
        return true;
    }

    //this function is using DFS travesal to check bipartite
    private boolean isdfs(int[][] graph,int node,int[] color){
        if(color[node]==-1) color[node]=1;
        for(int it: graph[node]){
            if(color[it]==-1){
                color[it]=1-color[node];
                if(!isdfs(graph,it,color)) return false;
            }
            else{
                if(color[it]==color[node]) return false;
            }
        }
        return true;
    }


    //function to check bipartite
    public boolean isBipartite3(int[][] graph) {
        int n = graph.length;
        int color[] = new int[n];
        Arrays.fill(color,-1);
        for(int i=0;i<n;i++){
            if(color[i]==-1){
                // if(!isdfs(graph,i,color)) return false;  //bfs
                if(!isdfs(graph,i,color)) return false; //dfs

            }
        }
        return true;
    }

}
