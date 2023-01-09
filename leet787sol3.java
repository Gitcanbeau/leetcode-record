import java.util.*;

public class leet787sol3 {
    public static void main(String[] args) {
        //
    }
    static class Pair{
        int numberOfStopsSinceSrc;
        int startfrom;
        int dist;
        public Pair(int numberofstops,int start,int dis){
            numberOfStopsSinceSrc=numberofstops;
            startfrom=start;
            dist=dis;
        }
    }
    public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        //Runtime: 22 ms, faster than 26.17% of Java online submissions for Cheapest Flights Within K Stops.
        //Memory Usage: 48.3 MB, less than 23.06% of Java online submissions for Cheapest Flights Within K Stops.
        //hashmap 就是慢而已，生成邻接表也不用非得是map，就像sol2里面自己做一个pairs也不错啊，
        //step1
        Map<Integer,Map<Integer,Integer>> adj=new HashMap<>();
        for(int[] edge: flights){
            int start=edge[0];
            int end=edge[1];
            int weight=edge[2];
            adj.putIfAbsent(start, new HashMap<>());
            adj.get(start).put(end,weight);
        }

        //step2
        int[] dist=new int[n];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[src]=0;
        Queue<Pair> bfsque=new LinkedList<>();
        bfsque.offer(new Pair(0,src,0));

        //step3
        while(!bfsque.isEmpty()){
            Pair curr=bfsque.poll();
            int numberofstopstohere=curr.numberOfStopsSinceSrc;
            int currstart=curr.startfrom;
            int distance=curr.dist;
            for(int end: adj.getOrDefault(currstart,new HashMap<>()).keySet()){
                int nextstart=end;
                int nextdistance=adj.get(currstart).get(end);
                if(numberofstopstohere<=k && distance+nextdistance<dist[nextstart]){
                    dist[nextstart]=distance+nextdistance;
                    bfsque.add(new Pair(numberofstopstohere+1,nextstart,distance+nextdistance));
                }
            }
        }
        //step4
        int ans=dist[dst];
        return (ans==Integer.MAX_VALUE) ? -1 : ans;
    }
}
