import java.util.*;

public class leet1203 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/sort-items-by-groups-respecting-dependencies/
//        int n=8;
//        int m=2;
//        int[] group={-1,-1,1,0,0,1,0,-1};
//        List<List<Integer>> beforeItems=new ArrayList<>();
//        for(int i=0;i<n;i++){
//            beforeItems.add(i,new ArrayList<>());
//        }
//
//        beforeItems.get(1).add(6);
//        beforeItems.get(2).add(5);
//        beforeItems.get(3).add(6);
//        beforeItems.get(4).add(3);
//        beforeItems.get(4).add(6);
//        int[] res=sortItems(n,m,group,beforeItems);
//        for(int i=0; i<res.length;i++){
//            System.out.print(res[i]+",");
//        }

        int n=5;
        int m=5;
        int[] group={2,0,-1,3,0};
        List<List<Integer>> beforeItems=new ArrayList<>();
        for(int i=0;i<n;i++){
            beforeItems.add(i,new ArrayList<>());
        }
        beforeItems.get(0).add(2);
        beforeItems.get(0).add(1);
        beforeItems.get(0).add(3);
        beforeItems.get(1).add(2);
        beforeItems.get(1).add(4);

        int[] res=sortItems(n,m,group,beforeItems);
        for(int i=0; i<res.length;i++){
            System.out.print(res[i]+",");
        }

    }
    public static int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
        //读懂题首先是比较难的
        //两层拓扑排序，group之间有顺序，然后节点之间也有顺序
        //如果两个节点在不同的group，那么前节点的group一定要在后节点的group前面，然后我这个group内部在对同一组的节点进行排序
        int[] sorted=new int[n];
        Map<Integer,List<Integer>> res=new HashMap<>();
        for(int i=-1;i<m;i++){
            res.putIfAbsent(i,new ArrayList<>());
        }

        int[] indegree=new int[n];
        Map<Integer, Map<Integer,List<Integer>>> adj=new HashMap<>();
        //key:from, value-key:the group of to,value-value: to
        for(int i=0; i<n;i++){
            adj.putIfAbsent(i,new HashMap<>());
            for(int j=-1;j<m;j++){
                adj.get(i).putIfAbsent(j,new ArrayList<>());
            }
        }

        for(int i=0; i<n;i++){
            if(beforeItems.get(i).size()!=0) {
                for (int j = 0; j < beforeItems.get(i).size(); j++) {
                    int from=beforeItems.get(i).get(j);
                    int groupofto=group[i];
                    int to=i;
                    if(!adj.get(from).get(groupofto).contains(to)) {
                        adj.get(from).get(groupofto).add(to);
                        indegree[i]++;
                    }
                }
            }
        }

        System.out.println(adj);
        for(int i=0; i<indegree.length;i++){
            System.out.print(i+"--"+indegree[i]+",");
        }
        System.out.println();

        Queue<Integer> bfsque=new LinkedList<>();
        for(int i=0;i<n;i++){
            if(indegree[i]==0){
                bfsque.offer(i);
            }
        }

        while(!bfsque.isEmpty()){
            int from=bfsque.poll();
            System.out.println(from);
            res.get(group[from]).add(from);
            System.out.println(res.get(group[from]));

            for(int groupnumber: adj.get(from).keySet()){
                for(int to:adj.get(from).get(groupnumber)){
                    indegree[to]--;
                    if(indegree[to]==0){
                        bfsque.offer(to);
                    }
                }
            }
        }

        int[] notexist=new int[0];
        if(res.size()!=n){
            return notexist;
        }

        int c=0;
        for(Integer groupnumber: res.keySet()){
            if(res.get(groupnumber).size()!=0) {
                for (int i = 0; i < res.get(groupnumber).size(); i++) {
                    sorted[c] = res.get(groupnumber).get(i);
                    c++;
                }
            }
        }
        return sorted;
    }




    // topological sort group first, then node within the group
    private static List<Integer>[] groups;
    private static List<Integer>[] graph;
    private static int[] indegrees;// indegrees of node
    private static int[] indegreeGroups;// indegrees of group
    public static int[] sortItems1(int n, int m, int[] group, List<List<Integer>> beforeItems) {
        buildGroups(n, group);
        buildGraph(n, beforeItems, group);
        int[] result = new int[n];
        int top = -1;
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (indegreeGroups[i] == 0) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            Integer groupId = queue.poll();
            List<Integer> groupItems = groups[groupId];
            if (groupItems == null) continue;
            Queue<Integer> itemQueue = new LinkedList<>();
            for (var item: groupItems) {
                if (indegrees[item] == 0) {
                    itemQueue.offer(item);
                }
            }
            while (!itemQueue.isEmpty()) {
                Integer item = itemQueue.poll();
                result[++top] = item;
                if (graph[item] == null) continue;
                for (var neighbor: graph[item]) {
                    indegrees[neighbor]--;
                    if (group[neighbor] != groupId) {
                        if (--indegreeGroups[group[neighbor]] == 0) {
                            queue.offer(group[neighbor]);
                        }
                    } else if (indegrees[neighbor] == 0) {
                        itemQueue.offer(neighbor);
                    }
                }
            }
        }
        if (top < n - 1) return new int[] {};
        return result;
    }
    private static void buildGroups(int n, int[] group) {
        // build groups;
        groups = new List[n];
        int index = n - 1;
        for (int i = 0; i < n; i++) {
            if (group[i] == -1) {
                // virtual group
                group[i] = index--;
            }
            if (groups[group[i]] == null) {
                groups[group[i]] = new ArrayList<>();
            }
            groups[group[i]].add(i);
        }
    }
    private static void buildGraph(int n, List<List<Integer>> beforeItems, int[] group) {
        graph = new List[n];
        indegrees = new int[n];
        indegreeGroups = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j: beforeItems.get(i)) {
                if (graph[j] == null) {
                    graph[j] = new ArrayList<>();
                }
                graph[j].add(i);
                indegrees[i]++;
                if (group[i] != group[j]) {
                    indegreeGroups[group[i]]++;
                }
            }
        }
    }
}
