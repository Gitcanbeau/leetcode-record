import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class leet1192 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/critical-connections-in-a-network/
    }
    List<List<Integer>> bridges;
    List<Integer>[] graph;
    int[] ids;
    int[] low;
    int id;

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        //tarjan algorithm
        //https://www.youtube.com/watch?v=qiAvrfMiRqE&t=757s
        //strongly connected components scc-强连通分量, 桥和割点问题

        //Runtime: 157 ms, faster than 86.40% of Java online submissions for Critical Connections in a Network.
        //Memory Usage: 305.5 MB, less than 42.80% of Java online submissions for Critical Connections in a Network.

        //1. initialize global variables;
        bridges = new ArrayList<>();
        graph = new ArrayList[n];
        ids = new int[n]; //单向时间戳
        low = new int[n]; //更新以后最小的时间戳
        id = 0;

        //2. build graph;
        buildGraph(connections);

        //3. find bridges;
        boolean[] visited = new boolean[n];
        dfs(visited, -1, 0);

        return bridges;
    }

    private void dfs(boolean[] visited, int pre, int curr) {
//        if(visited[curr]) return;

        visited[curr] = true;
        ids[curr] = low[curr] = id++;

        for (int next : graph[curr]) {
            if (next == pre) continue;
            if (!visited[next]) {
                dfs(visited, curr, next);

                //things below happen during backtracking
                low[curr] = Math.min(low[curr], low[next]);
                if (ids[curr] < low[next]) //说明curr next不在环里，所以是bridge
                    bridges.add(Arrays.asList(curr, next));
            } else {
                low[curr] = Math.min(low[curr], ids[next]);
            }
        }

        return;
    }

    private void buildGraph(List<List<Integer>> connections) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < connections.size(); i++) {
            int a = connections.get(i).get(0);
            int b = connections.get(i).get(1);
            graph[a].add(b);
            graph[b].add(a);
        }
    }
}
