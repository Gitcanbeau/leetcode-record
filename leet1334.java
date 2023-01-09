import java.util.*;

public class leet1334 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/find-the-city-with-the-smallest-number-of-neighbors-at-a-threshold-distance/
//        int n = 4; //ans=3
//        int[][] edges = {{0,1,3},{1,2,1},{1,3,4},{2,3,1}};
//        int distanceThreshold = 4;
//        int n = 5; //ans=0
//        int[][] edges = {{0,1,2},{0,4,8},{1,2,3},{1,4,2},{2,3,1},{3,4,1}};
//        int distanceThreshold = 2;
        int n = 6; //ans=5
        int[][] edges={{0,3,5},{2,3,7},{0,5,2},{0,2,5},{1,2,6},{1,4,7},{3,4,4},{2,5,5},{1,5,8}};
        int distanceThreshold =8279;
//        int n=6; //ans=5
//        int[][] edges={{0,1,10},{0,2,1},{2,3,1},{1,3,1},{1,4,1},{4,5,10}};
//        int distanceThreshold=20;
        System.out.println(findTheCity(n,edges,distanceThreshold));
    }

    static class Pair{
        int numberofstopsSinceSrc;
        int startfromhere;
        int distanceSinceSrc;
        public Pair(int numberofstops,int start,int distance){
            numberofstopsSinceSrc=numberofstops;
            startfromhere=start;
            distanceSinceSrc=distance;
        }
    }
    public static int findTheCity(int n, int[][] edges, int distanceThreshold) {
        //Runtime: 259 ms, faster than 7.10% of Java online submissions for Find the City With the Smallest Number of Neighbors at a Threshold Distance.
        //Memory Usage: 42.6 MB, less than 97.24% of Java online submissions for Find the City With the Smallest Number of Neighbors at a Threshold Distance.
        //自己写出来的，不过真的好慢
        //dijkstra
        //就是求src到各个点的最短距离，如果这个最短距离小于阈值，就代表能到达然后更新dist数组。。出来以后遍历每一个顶点作为src传入，看每个顶点最少能到达几个点，返回顶点的号码
        Map<Integer,Map<Integer,Integer>> adj=build_adj(edges);
//        System.out.println(adj);
        int idealcity=-1;
        int minres=Integer.MAX_VALUE;
        int okcities=0;
        for(int i=0;i<n;i++){
            okcities=findOkCitiesAtSpecificSrc(n,adj,distanceThreshold,i);
            if(minres>okcities){
                idealcity=i;
                minres=okcities;
            }else if(minres==okcities && i>idealcity){
                idealcity=i;
                minres=okcities;
            }
            okcities=0; //复原，下一次要重新计数
        }

        return idealcity;
    }

    private static int findOkCitiesAtSpecificSrc(int n, Map<Integer,Map<Integer,Integer>> adj, int distanceThreshold,int src){
        //step2-initialize dist array which stores the maximum distance from src to here
        int[] dist=new int[n];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[src]=0;
        Queue<Pair> bfsque=new LinkedList<>();
        bfsque.offer(new Pair(0,src,0));
        //step3
        while(!bfsque.isEmpty()){
            Pair curr=bfsque.poll();
            int numberofstopstohere=curr.numberofstopsSinceSrc;
            int currstart=curr.startfromhere;
            int currdistance=curr.distanceSinceSrc;
            for(int end:adj.getOrDefault(currstart,new HashMap<>()).keySet()){
                int nextstart=end;
                int nextdistance=adj.get(currstart).get(end);
                if(currdistance+nextdistance<=distanceThreshold && currdistance+nextdistance<dist[nextstart]){
                    dist[nextstart]=currdistance+nextdistance;
                    bfsque.offer(new Pair(numberofstopstohere+1,nextstart,currdistance+nextdistance));
                }
            }
        }
        int okcities=0;
        for(int i=0; i<n;i++){
            if(dist[i]!=Integer.MAX_VALUE && dist[i]!=0){
                okcities++;
            }
        }
        return okcities;
    }

    private static Map<Integer,Map<Integer,Integer>> build_adj(int[][] edges){
        //step1-build adjacent map
        Map<Integer,Map<Integer,Integer>> adj=new HashMap<>();
        for(int[] edge:edges){
            int start=edge[0];
            int end=edge[1];
            int weight=edge[2];
            adj.putIfAbsent(start, new HashMap<>());
            adj.putIfAbsent(end, new HashMap<>());
            adj.get(start).put(end,weight);
            adj.get(end).put(start,weight);
        }
        return adj;
    }


    public static int findTheCity2(int n, int[][] edges, int distanceThreshold) {
        //floyd-warshall
        //Runtime: 50 ms, faster than 43.00% of Java online submissions for Find the City With the Smallest Number of Neighbors at a Threshold Distance.
        //Memory Usage: 45.7 MB, less than 77.12% of Java online submissions for Find the City With the Smallest Number of Neighbors at a Threshold Distance.

        float[][] dp = new float[n][n];
        for(int i = 0; i < n; i++){
            Arrays.fill(dp[i],Integer.MAX_VALUE);
            dp[i][i] = 0;
        }
        for(int[] edge : edges){
            dp[edge[0]][edge[1]] = edge[2];
            dp[edge[1]][edge[0]] = edge[2];
        }

        for(int k = 0; k < n; k++){
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    dp[i][j] = Math.min(dp[i][j],dp[i][k] + dp[k][j]);
                }
            }
        }
        int minVisits = Integer.MAX_VALUE;
        int result = -1;

        for(int i = 0; i < n; i++) {
            int visit = 0;
            for(int j = 0; j < n; j++) {
                if(dp[i][j] <= distanceThreshold) visit++;
            }
            if(visit <= minVisits){
                result = i;
                minVisits = Math.min(minVisits,visit);
            }
        }
        return result;

    }
}
