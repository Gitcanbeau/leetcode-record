import java.util.*;

public class leet207 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/course-schedule/
        int numCourses = 3;
//        int[][] prerequisites={{1,0},{0,1}};
        int[][] prerequisites = {{1, 0}, {0, 2}};
//        System.out.println(buildGraph(numCourses, prerequisites));
//        System.out.println(canFinish(numCourses, prerequisites));
        System.out.println(canFinish2(numCourses, prerequisites));
    }

    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        //检测有环无环，以及prerequisites的限制下，能否到达dest。或者说safe点的个数比numCourses少的话就无法完成
        //Runtime: 4 ms, faster than 94.29% of Java online submissions for Course Schedule.
        //Memory Usage: 48.2 MB, less than 49.84% of Java online submissions for Course Schedule.
        //这个是一个通用模版//dfs-recursion
        //有机会掌握一下dfs-iteration 和bfs
        List<Integer> res = new ArrayList<>();
        List<List<Integer>> graph = buildGraph(numCourses, prerequisites);
        boolean[] safe = new boolean[numCourses];
        int[] visited = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (visited[i] == 0) isSafe(graph, visited, i, safe);
            //visited没访问过的默认值是0，safe默认值是false
            //每一个点都放进去修改一遍safe数组
        }
        // convert isSafe to List
        for (int i = 0; i < numCourses; i++) {
            if (safe[i]) res.add(i);
        }

        if (res.size() < numCourses) {
            return false;
        }
        return true;
    }

    private static List<List<Integer>> buildGraph(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < prerequisites.length; i++) {
            graph.get(prerequisites[i][0]).add(prerequisites[i][1]);
        }
        return graph;
    }

    private static boolean isSafe(List<List<Integer>> graph, int[] visited, int node, boolean[] safe) {
        if (visited[node] == 1) return false; // 一直是1说明出不来递归，说明有环，这个点就是不安全的
        if (visited[node] == 2) return true; //安全就返回true
        visited[node] = 1; //访问过了就是1
        for (int next : graph.get(node)) {
            if (!isSafe(graph, visited, next, safe)) return false;
            //有一个点不是安全的，它的父亲节点也必然不是安全的，所以一路往上返回false，直到返回到eventualSafeNodes4里面调用 isSafe的地方，
            //而且返回false的主要目的是为了不往下走，不想修改safe数组的值，其实返回false回到eventualSafeNodes4里面调用 isSafe的地方，也没做什么处理
        }
        visited[node] = 2; //访问过了并且安全就是2
        safe[node] = true; //不仅要修改safe数组里面的布尔值，还要返回true为了回到if条件句里面
        return true;
    }


    public static boolean canFinish2(int numCourses, int[][] prerequisites) {
        //bfs确实慢
        //Runtime: 29 ms, faster than 15.93% of Java online submissions for Course Schedule.
        //Memory Usage: 50.1 MB, less than 15.82% of Java online submissions for Course Schedule.


        //prerequsites给的不是adj，给的是边
        List<Integer> res = new ArrayList<>();
        //build adj//build indegree
        Map<Integer, Set<Integer>> adj = new HashMap<>();
        int[] indegree = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            adj.put(i, new HashSet<>());
        }

        for (int[] edge : prerequisites) {
            int from = edge[1];
            int to = edge[0];
            if (!adj.get(from).contains(to)) {
                adj.get(from).add(to);
                indegree[to]++;
            }
        }
        //offer the node with indegree of 0 into the bfsque

        Queue<Integer> bfsque = new LinkedList<>();
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                bfsque.offer(i);
            }
        }

        while (!bfsque.isEmpty()) {
            int curr = bfsque.poll();
            res.add(curr); //你要么入队列的时候加res，要么出队列的时候加res，你整统一了就行，别一会入队列加然后出队列又加
            for (int next : adj.get(curr)) {
                indegree[next]--;
                if(indegree[next]==0){
                    bfsque.offer(next);
                }
            }
        }


        return res.size()==numCourses?true:false;
    }
}
