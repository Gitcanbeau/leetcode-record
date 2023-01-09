import java.util.*;

public class leet787sol2 {
    public static void main(String[] args) {
        //
    }
    //Runtime: 8 ms, faster than 71.83% of Java online submissions for Cheapest Flights Within K Stops.
    //Memory Usage: 47.8 MB, less than 33.73% of Java online submissions for Cheapest Flights Within K Stops.
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
    static class Pairs{
        int stop;
        int distance;
        public Pairs(int s,int d){
            stop=s;
            distance=d;
        }
    }


    public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        //step1-build adjacent map //不用pair也可以，用map生成邻接表也不错
        List<List<Pairs>> adj=new ArrayList<>();
        for(int i=0;i<n;i++)  adj.add(new ArrayList<Pairs>());
        for(int i=0;i<flights.length;i++) {
            int[] curr=flights[i];
            List<Pairs> lst=adj.get(flights[i][0]);
            lst.add(new Pairs(flights[i][1],flights[i][2]));
        }
        //step2-initialize dist array which stores the minimum cost to move from src to this point
        Queue<Pair> bfsqueue=new LinkedList<>();
        int[] dist=new int[n];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[src]=0;
        Pair start=new Pair(0,src,0);
        bfsqueue.add(start);
        //step3
        while(!bfsqueue.isEmpty()) {
            Pair curr=bfsqueue.poll();
            int numberofstopstohere=curr.numberOfStopsSinceSrc;
            int currstart=curr.startfrom;
            int distance=curr.dist;
            for(Pairs end : adj.get(currstart)) {
                int nextstart=end.stop;
                int nextdistance=end.distance;
                if(numberofstopstohere<=k && distance+nextdistance<dist[nextstart]) {
                    dist[nextstart]=distance+nextdistance;
                    bfsqueue.add(new Pair(numberofstopstohere+1,nextstart,distance+nextdistance));
                    //int[]只能记录一对信息，pair可以记录经过几站，一共3个信息,加入到queue里面
                    //int[]不够用的时候就自己做一个数据结构
                }
            }
        }

        //step4
        int ans=dist[dst];
        return (ans==Integer.MAX_VALUE) ? -1 : ans;
    }

}
