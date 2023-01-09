import java.util.*;

public class leet1462 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/course-schedule-iv/
        int numCourses=3;
        int[][] prerequisites={{1,2},{1,0},{2,0}};
        int[][] queries={{1,2},{1,0}};
        System.out.println(checkIfPrerequisite(numCourses,prerequisites,queries));
    }
    public static List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        //Runtime: 190 ms, faster than 20.08% of Java online submissions for Course Schedule IV.
        //Memory Usage: 72.5 MB, less than 54.19% of Java online submissions for Course Schedule IV.

        List<Boolean> res=new ArrayList<>(queries.length);
        if(prerequisites.length==0) {
            for(int i=0;i< queries.length;i++){
                res.add(false);
            }
            return res;
        }





        int[] outdegree=new int[numCourses];
        Map<Integer, Set<Integer>> rev_adj=new HashMap<>();
        Map<Integer, Set<Integer>> adj=new HashMap<>();
        for(int i=0;i<numCourses;i++){
            rev_adj.put(i,new HashSet<>());
            adj.put(i,new HashSet<>());
        }
        for(int [] edge:prerequisites){
            int start=edge[0];
            int end=edge[1];
            rev_adj.get(end).add(start);
            adj.get(start).add(end);
            outdegree[start]++;
        }


        Queue<Integer> bfsque=new LinkedList<>();
        for(int i=0;i<numCourses;i++){
            if(outdegree[i]==0) bfsque.offer(i);
        }

        while(!bfsque.isEmpty()){
            Integer currend=bfsque.poll();
            for(int pre:rev_adj.get(currend)){
                for(int next: adj.get(currend)){
                    adj.get(pre).add(next);
                }
                outdegree[pre]--;
                if(outdegree[pre]==0) bfsque.offer(pre);
            }
        }


        for(int[] edge: queries){
            int first=edge[0];
            int after=edge[1];
            if(adj.get(first).contains(after)){
                res.add(true);
            }else{
                res.add(false);
            }
        }

        return res;

    }


    public List<Boolean> checkIfPrerequisite2(int n, int[][] prerequisites, int[][] queries) {
        //floyd-warshall
        //Runtime: 79 ms, faster than 60.43% of Java online submissions for Course Schedule IV.
        //Memory Usage: 73.8 MB, less than 32.55% of Java online submissions for Course Schedule IV.

        boolean adjMatrix[][] = new boolean[n][n];

        for (int[] i : prerequisites){
            adjMatrix[i[0]][i[1]] = true;
        }

        for (int k = 0; k < n; ++k){
            for (int i = 0; i < n ; ++i){
                for (int j = 0; j < n ; ++j){
                    adjMatrix[i][j] = adjMatrix[i][j] || (adjMatrix[i][k] && adjMatrix[k][j]);
                }
            }
        }

        List<Boolean> ans = new ArrayList<Boolean>();

        for (int i = 0; i < queries.length; ++i){
            ans.add(adjMatrix[queries[i][0]][queries[i][1]]);
        }

        return ans;


    }
}
