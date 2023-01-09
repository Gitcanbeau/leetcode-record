import java.util.*;

public class leet886 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/possible-bipartition/
        int n=4;
        int[][] dislikes={{1,2},{1,3},{2,4}};
        System.out.println(possibleBipartition(n,dislikes));
    }
    public static boolean possibleBipartition(int n, int[][] dislikes) {
        //Runtime: 37 ms, faster than 52.41% of Java online submissions for Possible Bipartition.
        //Memory Usage: 73.4 MB, less than 39.14% of Java online submissions for Possible Bipartition.
        //dfs-recursion
        int[] colors=new int[n+1];
        Arrays.fill(colors,0);
        List<List<Integer>> adj=build_adj(n,dislikes);
        for(int i=1;i<=n;i++){
            if(colors[i]==0 && !validColor(adj,colors,1,i)){
                return false;
            }
        }
        return true;
    }

    private static List<List<Integer>> build_adj(int n, int[][] dislikes){
        List<List<Integer>> adj=new ArrayList<>();
        for(int i=0; i<=n;i++){
            adj.add(new ArrayList<>());
        }
        for(int[] edge: dislikes){
            int start=edge[0];
            int end=edge[1];
            adj.get(start).add(end);
            adj.get(end).add(start);
        }
        return adj;
    }

    private static boolean validColor(List<List<Integer>> adj,int[] colors, int defaultcolor,int thisperson){
        if(colors[thisperson]!=0){
            return colors[thisperson]==defaultcolor;
        }

        colors[thisperson]=defaultcolor;
        for(int nextperson:adj.get(thisperson)){
            if(!validColor(adj,colors,-defaultcolor,nextperson)){
                return false;
            }
        }
        return true;
    }



    public boolean possibleBipartition2(int n, int[][] dislikes) {
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] dislike : dislikes) {
            int u = dislike[0] - 1;
            int v = dislike[1] - 1;

            graph[u].add(v);
            graph[v].add(u);
        }

        int[] colors = new int[n];

        for (int i = 0; i < n; i++) {
            if (colors[i] != 0) {
                continue;
            }

            colors[i] = 1;

            Queue<Integer> queue = new LinkedList<>();
            queue.add(i);

            while (!queue.isEmpty()) {
                int node = queue.poll();

                for (int adj : graph[node]) {
                    if (colors[adj] == colors[node]) {
                        return false;
                    }

                    if (colors[adj] == 0) {
                        colors[adj] = -colors[node];
                        queue.add(adj);
                    }
                }
            }
        }

        return true;
    }

}
