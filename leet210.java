import java.util.*;

public class leet210 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/course-schedule-ii/
        int numCourses=4;
        int[][] prerequisites={{1,0},{2,1},{3,2},{3,0}};
//        int[][] prerequisites={{1,0},{0,2}};
        int[] res= findOrder(numCourses,prerequisites);
        for(int i:res){
            System.out.print(i+" ");
        }
    }
    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        //根据我的有向图定义，返回顺序是拓扑排序起点第一个出现在res里面，拓扑排序的最后一个元素应该最后出现在res
        //Runtime: 5 ms, faster than 93.58% of Java online submissions for Course Schedule II.
        //Memory Usage: 49.1 MB, less than 59.58% of Java online submissions for Course Schedule II.
        //要有信心，大宝子
        List<Integer> res=new ArrayList<>();
        List<List<Integer>> graph=buildgraph(numCourses,prerequisites);
        int[] visited=new int[numCourses];
        boolean[] safe=new boolean[numCourses];
        for(int i=0; i<numCourses;i++){
            if(visited[i]==0){
                isSafe(graph,res,visited,safe,i);
            }
        }

        if(res.size()<numCourses) return new int[0];
        int[] result=new int[res.size()];
        //res都更新完一遍了
        int c=0;
        for(int i=res.size()-1;i>=0;i--){
            result[c]=res.get(i);
            c++;
        }
        return result;
    }
    private static List<List<Integer>> buildgraph(int numCourses, int[][] prerequisites){
        List<List<Integer>> res=new ArrayList<>();
        for(int i=0;i<numCourses;i++){
            res.add(new ArrayList<>());
        }
        for(int i=0; i<prerequisites.length;i++){
            res.get(prerequisites[i][1]).add(prerequisites[i][0]);
        }
        return res;
    }
    private static boolean isSafe(List<List<Integer>> graph,List<Integer> res,int[] visited, boolean[] safe,int nodei){
        if(visited[nodei]==2) return true;
        if(visited[nodei]==1) return false;
        visited[nodei]=1;
        for(int next: graph.get(nodei)){
            if(isSafe(graph,res,visited,safe,next)==false) return false;
        }
        visited[nodei]=2;
        safe[nodei]=true;
        res.add(nodei); //终点先加到res里面
        return true;
    }


    public static int[] findOrder2(int numCourses, int[][] prerequisites) {
        //bfs确实慢
        //Runtime: 25 ms, faster than 21.62% of Java online submissions for Course Schedule II.
        //Memory Usage: 51.4 MB, less than 11.76% of Java online submissions for Course Schedule II.


        //prerequsites给的不是adj，给的是边
        int[] orderofcourses=new int[numCourses];
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
            res.add(curr);
            for (int next : adj.get(curr)) {
                indegree[next]--;
                if(indegree[next]==0){
                    bfsque.offer(next);
                }
            }
        }

        if(res.size()!=numCourses) return new int[0];
        for(int i=0;i<res.size();i++){
            orderofcourses[i]=res.get(i);
        }
        return orderofcourses;
    }
}
