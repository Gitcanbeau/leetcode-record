import java.util.HashMap;

public class leet1791 {
    public static void main(String[] args) {
        //There is an undirected star graph consisting of n nodes labeled from 1 to n. A star graph is a graph where there is one center node and exactly n - 1 edges that connect the center node with every other node.
        //You are given a 2D integer array edges where each edges[i] = [ui, vi] indicates that there is an edge between the nodes ui and vi. Return the center of the given star graph.
        //Example 1:
        //Input: edges = [[1,2],[2,3],[4,2]]
        //Output: 2
        //Explanation: As shown in the figure above, node 2 is connected to every other node, so 2 is the center.
        //Example 2:
        //Input: edges = [[1,2],[5,1],[1,3],[1,4]]
        //Output: 1
        //Constraints:
        //3 <= n <= 105
        //edges.length == n - 1
        //edges[i].length == 2
        //1 <= ui, vi <= n
        //ui != vi
        //The given edges represent a valid star graph.
        int[][] edges={{1,2},{5,1},{1,3},{1,4}};
        System.out.println(findCenter(edges));
        //Runtime: 92 ms, faster than 5.79% of Java online submissions for Find Center of Star Graph.
        //Memory Usage: 87.1 MB, less than 20.44% of Java online submissions for Find Center of Star Graph.
    }
    public static int findCenter(int[][] edges) {
        HashMap<Integer,Integer> hm1=new HashMap<>();
        for(int[] arr: edges){
            hm1.put(arr[0], hm1.getOrDefault(arr[0],0)+1);
            hm1.put(arr[1], hm1.getOrDefault(arr[1],0)+1);
        }
        int res=Integer.MIN_VALUE;
        int center=Integer.MIN_VALUE;
        for(Integer key: hm1.keySet()){
            if(res<hm1.get(key)){
                res=hm1.get(key);
                center=key;
            }
        }
        return center;
    }
}
