import java.util.*;

public class leet882 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/reachable-nodes-in-subdivided-graph/
//        int[][] edges={{0,1,10},{0,2,1},{1,2,2}};
//        int maxMoves=6;
//        int n=3;
//        int[][] edges={{2,4,2},{3,4,5},{2,3,1},{0,2,1},{0,3,5}};
//        int maxMoves=14;
//        int n=5;
//        int[][] edges={{0,3,4},{3,4,0},{1,3,0},{2,4,4},{2,3,0}};
//        int maxMoves=5;
//        int n=5;
        int[][] edges={{3,4,8},{0,1,3},{1,4,0},{1,2,3},{0,3,2},{0,4,10},{1,3,3},{2,4,3},{2,3,3},{0,2,10}};
        int maxMoves=7;
        int n=5;
        System.out.println(reachableNodes(edges,maxMoves,n));
//        System.out.println(build_ori_adj(edges,n));
    }

    private static class DynamicInfo{
        int startfromthere;
        int distanceSinceSrc;
        public DynamicInfo(int node,int distance){
            startfromthere=node;
            distanceSinceSrc=distance;
        }
    }
    public static int reachableNodes(int[][] edges, int maxMoves, int n) {
        //time limit exceeded
        Map<Integer, Set<Integer>> new_adj=build_ori_adj(edges,n);
        if(new_adj.keySet().contains(0)==false) return 1; //很重要，可能有孤立点
        System.out.println(new_adj);

        int totalnodes=n;
        for(int[] edge:edges){
            totalnodes+=edge[2];
        } //很重要，可能有孤立点
        System.out.println(totalnodes);
        if(maxMoves>=totalnodes) return totalnodes;

        int[] dist=new int[totalnodes];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[0]=0;


        Queue<DynamicInfo> bfsque=new LinkedList<>();
        bfsque.offer(new DynamicInfo(0,0));
        while(!bfsque.isEmpty()){
            DynamicInfo curr=bfsque.poll();
            int currstart=curr.startfromthere;
            int currdistance=curr.distanceSinceSrc;
            for(Integer end: new_adj.get(currstart)){
                int nextstart=end;
                int nextdistance=1;
                if(currdistance+nextdistance<dist[nextstart]){
                    dist[nextstart]=currdistance+nextdistance;
                    bfsque.offer(new DynamicInfo(nextstart,currdistance+nextdistance));
                }
            }
        }

        int reachablenodes=0;
        for(int i=0;i<dist.length;i++){
            if(dist[i]<=maxMoves){
                reachablenodes++;
            }
        }

        return reachablenodes==0?-1:reachablenodes;
    }

    private static Map<Integer, Set<Integer>> build_ori_adj(int[][] edges,int n){
        Map<Integer, Set<Integer>> adj=new HashMap<>();
        int idx=n;
        for(int[] edge:edges){

            int end1=edge[0];
            int end2=edge[1];
            int insertednodes=edge[2];

            if (insertednodes==0) {
                adj.putIfAbsent(end1,new HashSet<>());
                adj.putIfAbsent(end2,new HashSet<>());
                adj.get(end1).add(end2);
                adj.get(end2).add(end1);
                continue; //不插入任何点这种情况很重要
            }
            adj.putIfAbsent(end1,new HashSet<>());
            adj.putIfAbsent(idx,new HashSet<>());
            adj.putIfAbsent(end2,new HashSet<>());
            adj.putIfAbsent(idx+insertednodes-1,new HashSet<>());
            adj.get(end1).add(idx);
            adj.get(idx).add(end1);
            adj.get(end2).add(idx+insertednodes-1);
            adj.get(idx+insertednodes-1).add(end2);

            for(int i=idx+1;i<=idx+insertednodes-1;i++){
                adj.putIfAbsent(i-1,new HashSet<>());
                adj.putIfAbsent(i,new HashSet<>());
                adj.get(i-1).add(i);
                adj.get(i).add(i-1);
            }

            idx+=insertednodes;

        }
        return adj;
    }




    static class DynamicInfo2 {
        int nodeId;
        int remainingMoves;

        public DynamicInfo2(int nodeId, int remainingMoves) {
            this.nodeId = nodeId;
            this.remainingMoves = remainingMoves;
        }
    }

    public static int reachableNodes2(int[][] edges, int maxMoves, int n) {
        //So first we construct the adjacency matrix. After that we modify dijkstra's algorithm in order to find the result.
        // Also we maintain a vis boolean array in order to keep track of the elements already visited in order to avoid loop conditions.
        // We add the first index and current maximum available moves to our heap and our heap is max heap on the basis of moves available.
        // So if our current node is visited we just continue otherwise we look at its adjacent nodes.

        // While going through adjacent nodes we see how many nodes can we visit.
        // Number of nodes we can visit is minimum(remaining moves available, available number of partitions of that edge).
        // Now after finding that we update the number of partitions left that we can make further between the two nodes and also we update our counter with the number of moves we took.

        // Also we need to add something to our heap also right? How do we decide when to add to the heap? Simple.
        // Whenever the moves remaining is greater than the number of partition between the nodes + 1(for that node itself) we know we can reach this node and we add this node to our heap with moves available
        // Here graph[nearestNodeId][i] gives us the number of intermediary nodes and 1 because we have visited the current node.

        // For eg. we have current node 1 and its adjacent node as 2 and number of partition between them is 1 and maxMoves as 3.
        // So movesRemaining - graph[current node][adj node] would be 3 - 1 that is 2 but we have gone from 1 to the intermediary node and then from that node to 2.
        // So we essentially made 2 moves that's why -1.
        // Also in the if condition(maxMovesRemaining>=graph[nearestNodeId][i]+1) I have +1 because if we want to reach the node 2 we have to make 2 moves even if there is only 1 intermediary node.

        //create adjencylist matrix
        HashMap<Integer, HashMap<Integer, Integer>> graph = new HashMap();
        for (int nodeId = 0; nodeId < n; nodeId++) {
            graph.putIfAbsent(nodeId, new HashMap());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).put(edge[1], edge[2]);
            graph.get(edge[1]).put(edge[0], edge[2]);
        }

        //Max Heap: get the node with maximum number of remaining moves
        PriorityQueue<DynamicInfo2> pq = new PriorityQueue<DynamicInfo2>((a, b) -> (b.remainingMoves - a.remainingMoves)); //降序排列，最大的remainingmoves在最上面
        pq.add(new DynamicInfo2(0, maxMoves));

        int nodeReachableCount = 0;
        boolean[] visited = new boolean[n];

        while (!pq.isEmpty()) {
            DynamicInfo2 curr = pq.remove();
            if (visited[curr.nodeId]) continue;

            //mark as visited
            visited[curr.nodeId] = true;
            nodeReachableCount++;

            //check all adjacent node
            for (int adjNodeId : graph.get(curr.nodeId).keySet()) {
                int insertedNodeCount = graph.get(curr.nodeId).get(adjNodeId);
                if (!visited[adjNodeId] && curr.remainingMoves - (insertedNodeCount + 1) >= 0) {
                    pq.add(new DynamicInfo2(adjNodeId, curr.remainingMoves - (insertedNodeCount + 1)));
                }

                //Adjust the new node count,b/w this current node and adjNode
                int actualReachNodeCount = Math.min(curr.remainingMoves, insertedNodeCount);
                graph.get(curr.nodeId).put(adjNodeId, insertedNodeCount - actualReachNodeCount);
                graph.get(adjNodeId).put(curr.nodeId, insertedNodeCount - actualReachNodeCount);


                //append actual reach node count
                nodeReachableCount += actualReachNodeCount;
            }

        }

        return nodeReachableCount;
    }




}
