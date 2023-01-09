import java.util.*;

public class leet802 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/find-eventual-safe-states/
//        int[][] graph={{1,2},{2,3},{5},{0},{5},{},{}};
        int[][] graph={{1,2,3,4},{1,2},{3,4},{0,4},{}};
        System.out.println(getOutDegrees(5,graph));
        System.out.println(eventualSafeNodes1(graph));
        System.out.println(eventualSafeNodes2(graph));
        System.out.println(eventualSafeNodes3(graph));
    }
    public static List<Integer> eventualSafeNodes3(int[][] graph) {
        //time limit exceeded
        int n=graph.length;
        Map<Integer,Integer> outdegrees = getOutDegrees(n, graph);
        List<Integer> res=new ArrayList<>();
        Queue<Integer> queue1=new LinkedList<>();
        for(int i=0;i<n;i++){
            if(outdegrees.get(i)==0){
                queue1.offer(i);
            }
        }

        while(!queue1.isEmpty()){
            int currnode=queue1.poll();
            res.add(currnode);
            for(int i=0; i<n;i++){
                for(int j=0; j<graph[i].length;j++){
                    if(graph[i][j]==currnode){
                        outdegrees.put(i,outdegrees.get(i)-1);
                        if(outdegrees.get(i)==0){
                            queue1.offer(i);
                        }
                    }
                }
            }
        }
        Collections.sort(res);
        return res;
    }
    public static Map<Integer,Integer> getOutDegrees(int n, int[][] graph){
        //time limit exceeded
        Map<Integer,Integer> outdegrees=new HashMap();
        for(int i=0;i<n;i++){
            outdegrees.put(i,graph[i].length);
        }
        return outdegrees;
    }
    /////////////////////
    public static List<Integer> eventualSafeNodes4(int[][] graph) {
        List<Integer> res = new ArrayList<Integer>();
        Queue<Integer> queue = new LinkedList<Integer>();//outgoing == 0;
        int[] outdegree = new int[graph.length];
        int[] visited = new int[graph.length];
        for(int i = 0; i < graph.length;i++){
            outdegree[i] = graph[i].length;
            if(graph[i].length == 0){
                queue.offer(i);
                visited[i] = 1;
            }
        }
        while(!queue.isEmpty()){
            int tmp = queue.poll();
            res.add(tmp);
            for(int i = 0; i < graph.length;i++){
                for(int j = 0; j < graph[i].length; j++){
                    if(graph[i][j] == tmp)
                        outdegree[i]--;
                    if(outdegree[i] == 0 && visited[i] == 0){
                        queue.offer(i);
                        visited[i] = 1;
                    }
                }
            }
        }
        Collections.sort(res);
        return res;
    }

    /////////////////////
    public static List<Integer> eventualSafeNodes2(int[][] graph) {
        //Runtime: 59 ms, faster than 26.19% of Java online submissions for Find Eventual Safe States.
        //Memory Usage: 85.5 MB, less than 16.53% of Java online submissions for Find Eventual Safe States.

        //本质就是不断检查父节点，如果我安全，我就检查我的父节点是否安全，如果我不安全，我的父节点一定不安全

        // Construct reverse graph
        ArrayList<Integer>[] revAdjList = new ArrayList[graph.length];
        //相当于int[] 只不过存的类型不是int而是ArrayList<Integer>，数组里面的内容是一个个arraylist
        for (int s = 0; s < graph.length; s++) {
            revAdjList[s] = new ArrayList<Integer>();
        }
        for (int s = 0; s < graph.length; s++) {
            for (int e = 0; e < graph[s].length; e++) {
                // Edge from s to graph[s][e], so insert opposite into rev graph
                revAdjList[graph[s][e]].add(s); //数组里面i位置存放的是能够到达i的节点
            }
        }


        boolean[] safe = new boolean[graph.length]; //到达i点是否安全，安全的话就把它的父亲节点也加到que里面

        Queue<Integer> dfsque = new LinkedList<>();

        // Enqueue everyone with no leaving edges as starters
        for (int i = 0; i < graph.length; i++) {
            if (graph[i].length == 0)
                dfsque.add(i);
        }

        while (!dfsque.isEmpty()) {
            int curNode = dfsque.poll();

            // Are we guaranteed to terminal?
            boolean allGtt = true;
            for (int dest = 0; dest < graph[curNode].length; dest++) {
                // Destination is graph[curNode][dest]
                if (!safe[graph[curNode][dest]]) { //到达dest是否安全，从curnode到dest都不安全的话，本curnode作为父节点也一定不安全，直接break
                    allGtt = false;
                    break;
                }
            }

            // Yep we are!
            if (allGtt) { //如果本curnode到达该curnode的各个dest都安全，那肯定不会改allGtt的值，就可以把curnode说成是安全点，如果有一个false就不用走这步
                safe[curNode] = true;

                // Also enqueue people pointing to us now too!
                for (Integer prev : revAdjList[curNode]) {
                    if (!safe[prev]) {
                        dfsque.add(prev);
                    }
                }
            }
        }

        List<Integer> results = new ArrayList<Integer>();
        for (int i = 0; i < safe.length; i++) {
            if (safe[i])
                results.add(i);
        }
        return results;

    }





    /////////////////////dfs就是快
    public static List<Integer> eventualSafeNodes1(int[][] graph) {
        //Runtime: 8 ms, faster than 76.44% of Java online submissions for Find Eventual Safe States.
        //Memory Usage: 71.5 MB, less than 37.07% of Java online submissions for Find Eventual Safe States.
        //本质和queue实现明显不一样，就是看进了递归能不能出来，能不能有机会修改你的访问状态，你要是出来了你就安全，我所有点都检查一遍
        //queue实现不是从头开始检查，他是从最安全的子节点往父节点这个方向检查
        //两种方法我都挺喜欢
        List<Integer> res = new ArrayList<>();
        if (graph == null || graph.length == 0) return res;
        int N = graph.length;
        boolean[] safe = new boolean[N];
        int[] visited = new int[N];
        for (int i = 0; i < N; i++) {
            if (visited[i] == 0) isSafe(graph, visited, i, safe);
            //visited没访问过的默认值是0，safe默认值是false
            //每一个点都放进去修改一遍safe数组
        }
        // convert isSafe to List
        for (int i = 0; i < N; i++) {
            if (safe[i]) res.add(i);
        }
        return res;
    }
    private static boolean isSafe(int[][] graph, int[] visited, int node, boolean[] safe) {
        if (visited[node] == 1) return false; // 一直是1说明出不来递归，说明有环，这个点就是不安全的
        if (visited[node] == 2) return true; //安全就返回true
        visited[node] = 1; //访问过了就是1
        for (int next: graph[node]) {
            if (!isSafe(graph, visited, next, safe)) return false;
            //有一个点不是安全的，它的父亲节点也必然不是安全的，所以一路往上返回false，直到返回到eventualSafeNodes4里面调用 isSafe的地方，
            //而且返回false的主要目的是为了不往下走，不想修改safe数组的值，其实返回false回到eventualSafeNodes4里面调用 isSafe的地方，也没做什么处理
        }
        visited[node] = 2; //访问过了并且安全就是2
        safe[node] = true; //不仅要修改safe数组里面的布尔值，还要返回true为了回到if条件句里面
        return true;
    }



    public List<Integer> eventualSafeNodes(int[][] graph) {
        //if (graph[i].length == 0) it is a terminal node;
        //all thread have to return true;
        if (graph == null || graph.length == 0) return new LinkedList();
        int[] outdegree = new int[graph.length];
        Map<Integer, Set<Integer>> revadj = new HashMap();
        Queue<Integer> queue = new LinkedList();
        List<Integer> res = new LinkedList();
        for (int i = 0; i < graph.length; i++) {
            int[] dests = graph[i];
            for (int to : dests) {
                if (!revadj.containsKey(to)) revadj.put(to, new HashSet());
                revadj.get(to).add(i);
            }
            outdegree[i] = dests.length;
            if (outdegree[i] == 0) queue.offer(i);
        }

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            if (revadj.containsKey(curr)) {
                Set<Integer> froms = revadj.get(curr);
                for (int from : froms) {
                    outdegree[from]--;
                    if (outdegree[from] == 0) queue.offer(from);
                }
            }
        }

        for (int i = 0; i < outdegree.length; i++) {
            if (outdegree[i] == 0) res.add(i);
        }

        return res;
    }

}
