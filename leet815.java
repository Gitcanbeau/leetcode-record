import java.util.*;

public class leet815 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/bus-routes/

//        int[][] routes={{1,2,7},{3,6,7}};
//        int source=1;
//        int target=6;
        int[][] routes={{7,12},{4,5,15},{6},{15,19},{9,12,13}};
        int source=15;
        int target=12;
        System.out.println(numBusesToDestination(routes,source,target));
    }
    private static class DynimicInfo{
        int hereisendline;
        int numberofswitchessofar;
        public DynimicInfo(int line, int number){
            hereisendline=line;
            numberofswitchessofar=number;
        }
    }
    public static int numBusesToDestination(int[][] routes, int source, int target) {
        //西吧，终于他妈改对了，这个edge case好像弱智
        //Runtime: 1442 ms, faster than 5.04% of Java online submissions for Bus Routes.
        //Memory Usage: 75 MB, less than 72.55% of Java online submissions for Bus Routes.


        //我只要到达了target所在的线路即可
        //本质的图是线路与线路的交点，不是站与站的交点，难点在于理解题目和生成邻接表，线路的邻接表生成真的好他么费劲

        //build node_adj反向邻接表，就是Map<Integer,Set<Integer>> 键是终点，set是第几号公交车可以到达该终点
        //build line_adj就是Map<Integer,Set<Integer>> 键是某条线路，set是哪些线路与之相交
        //弄一个dynamic info存当前终点，当前的换乘次数，放到队列里面每次往外弹，新的dynamic info入队列的时候更新换乘次数
        //source可能也有多条线路，返回的就是使得dist[sourceline]最小的那个dist[line]

        if(source==target) return 0;
        Set<Integer> allstops=new HashSet<>();
        for(int i=0;i<routes.length;i++){
            for(int j=0;j<routes[i].length;j++){
                allstops.add(routes[i][j]);
            }
        }
        if( (!allstops.contains(source)) || (!allstops.contains(target))) return -1;

        Map<Integer,Set<Integer>> node_adj=build_node_adj(routes);
        Map<Integer,Set<Integer>> line_adj=build_line_adj(routes,node_adj);


        Set<Integer> targetline=node_adj.get(target);
        Set<Integer> sourceline=node_adj.get(source);
        int[] dist=new int[routes.length];
        Arrays.fill(dist,Integer.MAX_VALUE);
        for(Integer line:targetline){
            dist[line]=1;
        }
        System.out.println(node_adj);
        System.out.println(line_adj);
        for(int i=0;i<dist.length;i++){
            System.out.println(dist[i]);
        }


        Queue<DynimicInfo> bfsque=new LinkedList<>();
        for(Integer line:targetline) {
            bfsque.offer(new DynimicInfo(line, 1));
        }
        while(!bfsque.isEmpty()){
            DynimicInfo curr=bfsque.poll();
            int currline=curr.hereisendline;
            int currstops=curr.numberofswitchessofar;
            for(Integer preline: line_adj.get(currline)){
                if(currstops+1<dist[preline]){
                    dist[preline]=currstops+1;
                    bfsque.offer(new DynimicInfo(preline, currstops+1));
                }
            }
        }

        int res=Integer.MAX_VALUE;
        for(Integer line:sourceline){
            if(dist[line]<res){
                res=dist[line];
            }
        }
        return res==Integer.MAX_VALUE?-1:res;
    }

    private static Map<Integer, Set<Integer>> build_node_adj(int[][] routes) {
        Map<Integer, Set<Integer>> node_busline_adj = new HashMap<>();
        for (int i = 0; i < routes.length; i++) { //公交车的号码
            for (int j = 0; j < routes[i].length; j++) { //每一个站点
                node_busline_adj.putIfAbsent(routes[i][j], new HashSet<>());
                node_busline_adj.get(routes[i][j]).add(i);
            }
        }
        return node_busline_adj;
    }
    private static Map<Integer, Set<Integer>> build_line_adj(int[][] routes,Map<Integer, Set<Integer>> node_busline_adj) {
        Map<Integer,Set<Integer>> busstop_busstop_adj=new HashMap<>();
        for(Integer node:node_busline_adj.keySet()){
            for(Integer busline: node_busline_adj.get(node)){
                for(int i=0;i< routes.length;i++) {
                    busstop_busstop_adj.putIfAbsent(busline, new HashSet<>());
                    if(node_busline_adj.get(node).contains(i) && busline!=i){
                        busstop_busstop_adj.get(busline).add(i);
                    }
                }
            }
        }

        return busstop_busstop_adj;
    }
}
