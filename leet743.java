import java.util.*;

public class leet743 {
    public static void main(String[] args) {
            //https://leetcode.com/problems/network-delay-time/
            int[][] times={{2,1,1},{2,3,1},{3,4,1}}; //2
            int n=4;
            int k=2;
//        int[][] times={{1,2,1}};//1
//        int n=2;
//        int k=1;
//        int[][] times={{1,2,1}}; //-1
//        int n=2;
//        int k=2;
//        System.out.println(networkDelayTime1(times,n,k));
            System.out.println(networkDelayTime2(times,n,k));
    }


    private static class DynimicInfo{
        int startfromhere;
        int costsinceSrc;
        public  DynimicInfo(int start,int cost){
            startfromhere=start;
            costsinceSrc=cost;
        }
    }
    public static int networkDelayTime1(int[][] times, int n, int k) {
        //dijkstra
        //Runtime: 92 ms, faster than 10.47% of Java online submissions for Network Delay Time.
        //Memory Usage: 67 MB, less than 25.31% of Java online submissions for Network Delay Time.
        //step1-build adj
        Map<Integer, Map<Integer,Integer>> adj=build_adj(times);
        //step2-build int[] dist where the ith value represents the minimum cost from node k to node i
        int[] cost=new int[n+1];
        Arrays.fill(cost,Integer.MAX_VALUE);
        cost[k]=0;
        cost[0]=0;
        //step3-bfs fill int[]cost
        Queue<DynimicInfo> bfsque=new LinkedList<>();
        bfsque.offer(new DynimicInfo(k,0));
        while(!bfsque.isEmpty()){
            DynimicInfo curr=bfsque.poll();
            int currstart=curr.startfromhere;
            int currcost=curr.costsinceSrc;
            for(int end:adj.getOrDefault(currstart,new HashMap<>()).keySet()){
                int nextstart=end;
                int nextcost=adj.get(currstart).get(end);
                if(currcost+nextcost<cost[nextstart]){
                    cost[nextstart]=currcost+nextcost;
                    bfsque.offer(new DynimicInfo(nextstart,currcost+nextcost));
                }
            }
        }

        int res=-1;
        for(int i=1;i<=n;i++){
            if(cost[i]==Integer.MAX_VALUE) return -1;
            if(cost[i]>res){
                res=cost[i];
            }
        }
        return res;
    }


    private static Map<Integer, Map<Integer,Integer>>  build_adj(int[][] times){
        Map<Integer, Map<Integer,Integer>> adj=new HashMap<>();
        for(int i=0;i<times.length;i++){
            int start=times[i][0];
            int end=times[i][1];
            int cost=times[i][2];
            adj.putIfAbsent(start,new HashMap<>());
            adj.get(start).put(end,cost);
        }
        return adj;
    }


    public static int networkDelayTime2(int[][] times, int n, int k) {
        //dijkstra-heap
        //step1-build adj
        Map<Integer, Map<Integer,Integer>> adj=build_adj(times);
        //step2-build int[] dist where the ith value represents the minimum cost from node k to node i
        int[] cost=new int[n+1];
        Arrays.fill(cost,Integer.MAX_VALUE);
        cost[k]=0;
        cost[0]=0;
        //step3-bfs fill int[]cost
        PriorityQueue<DynimicInfo> pq = new PriorityQueue<>((a,b)->a.costsinceSrc-b.costsinceSrc);
        boolean []vis = new boolean[n+1];
        int nodesCount = 0;
        pq.add(new DynimicInfo(k,0));
        int time=0;


        while(!pq.isEmpty()){
            DynimicInfo curr = pq.poll();
            int currstart=curr.startfromhere;
            int currcost=curr.costsinceSrc;

            if(vis[currstart])continue; //priority queue的话可以用visited[]来节省不必要的松弛步骤
            vis[currstart]= true;
            nodesCount++;
            time = Math.max(currcost,time);
            for(int next: adj.getOrDefault(currstart,new HashMap<>()).keySet()){
                if(vis[next]!= true){
                    int nextcost=adj.get(currstart).get(next);
                    pq.add(new DynimicInfo(next,time+nextcost));
                }
            }
        }
        return nodesCount == n ? time:-1;
    }





    public static int networkDelayTime3(int[][] times, int n, int k) {
        //floyd-warshall
        //Runtime: 47 ms, faster than 44.89% of Java online submissions for Network Delay Time.
        //Memory Usage: 61.6 MB, less than 77.01% of Java online submissions for Network Delay Time.
        float[][] dp=new float[n+1][n+1];
        for(int i=0; i<=n;i++){
            Arrays.fill(dp[i],Integer.MAX_VALUE);
            dp[i][i]=0;
        }

        for(int[] edge:times){
            dp[edge[0]][edge[1]]=edge[2];
        }

        for(int m=0;m<=n;m++){
            for(int i=0;i<=n;i++){
                for(int j=0;j<=n;j++){
                    dp[i][j]=Math.min(dp[i][j],dp[i][m]+dp[m][j]);
                }
            }
        }

        for(int i=0;i<=n;i++){
            for(int j=0;j<=n;j++){
                System.out.print(dp[i][j]+",");
            }
            System.out.println();
        }


        int res=0;
        for(int j=1;j<=n;j++){
            if(dp[k][j]==Integer.MAX_VALUE) return -1;
            if(dp[k][j]>res){
                res=(int)dp[k][j];
            }
        }
        return res;
    }
}
