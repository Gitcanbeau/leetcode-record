import java.util.*;

public class leet943 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/find-the-shortest-superstring/
    }
    private static String[][] lookup;


    public static String shortestSuperstring(String[] words) {

        String[][] dp = new String[words.length][5000];
        lookup = new String[words.length][words.length];

        for(int i=0;i<words.length;i++){
            for(int j=0;j<words.length;j++){
                if(i == j) continue;
                int k = 1;
                int idx = 0;
                while(k <= words[j].length()){
                    if(words[i].endsWith(words[j].substring(0, k)))
                        idx = k;
                    k++;
                }
                lookup[i][j] = words[j].substring(idx, words[j].length());
            }
        }

        String ans = "";
        int minLength = Integer.MAX_VALUE/2;
        int bitmask = 0;

        for(int i=0;i<words.length;i++){
            String tmp = words[i]+superString(words, i, bitmask|(1<<i), dp);
            if(tmp.length() < minLength){
                minLength = tmp.length();
                ans = tmp;
            }
        }
        return ans;
    }


    private static String superString(String[] A, int last, int bitmask, String[][] dp){
        String res = "";
        int minLength = Integer.MAX_VALUE/2;
        if(dp[last][bitmask] != null)
            return dp[last][bitmask];

        for(int i=0;i<A.length;i++){
            if((bitmask&(1<<i)) != 0)
                continue;
            String tmp = lookup[last][i]+superString(A, i, bitmask|(1<<i), dp);
            if(tmp.length() < minLength){
                minLength = tmp.length();
                res = tmp;
            }
        }
        return dp[last][bitmask] = res;
    }












    class State{
        int node;  //current node index
        int state;  //distinguish the node come from diferent node
        StringBuilder sb; //the accumulated string in this state
        int cost;  //the accumulated cost

        public State(int node, int state, String str, int cost){
            this.node = node;
            this.state = state;
            this.sb = new StringBuilder(str);
            this.cost = cost;
        }

    }

    public String shortestSuperstring2(String[] A) {
        int n = A.length;
        int goal = (1<<n) - 1;

        List<String> waitlist = new ArrayList<>();  //store the min cost start from each single node

        Map< Integer, List<List<Integer>> > map = new HashMap<>();  //<s1 index, <<s2 index, cost>,<s3 index, cost>...> >

        for(int i=0; i<n; i++) map.put(i, new ArrayList<>());

        //build the graph
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(A[i].equals(A[j])) continue;
                List<Integer> temp = new ArrayList<>();
                temp.add(j);
                temp.add(calcost(A[i], A[j]));

                map.get(i).add( new ArrayList<>(temp) ) ;
            }
        }


        Comparator<State> mycomp = new Comparator<>(){
            public int compare(State s1, State s2){
                return s1.cost - s2.cost;
            }
        };

        // for each node, we do bfs
        for(int i=0; i<A.length; i++){
            Queue<State> pq =  new PriorityQueue<>(mycomp);
            boolean [][]visited = new boolean [n][1<<n];
            pq.add(new State(i, 1<<i, A[i], 0));
            visited[i][1<<i] = true;

            while(!pq.isEmpty()){
                State currstate = pq.poll();
                if(currstate.state == goal) waitlist.add(currstate.sb.toString());

                for(List<Integer> nextnodelist : map.get(currstate.node)){
                    int nextnode = nextnodelist.get(0);
                    int nextcost = nextnodelist.get(1);
                    int nextstate = currstate.state | (1<<nextnode);

                    if(visited[nextnode][nextstate]) continue;

                    visited[nextnode][nextstate] = true;

                    //do not modify currstate.sb
                    StringBuilder nextsb = new StringBuilder(currstate.sb.toString());
                    //"abcd" + "bcde", nextcost is 1, we only need to add "e" behind
                    nextsb.append( A[nextnode].substring(A[nextnode].length()-nextcost) );

                    pq.add(new State(nextnode, nextstate, nextsb.toString(), currstate.cost+nextcost));
                }
            }

        }

        int minlen = Integer.MAX_VALUE;
        String minstr = "";

        for(String str : waitlist){
            if(minlen > str.length()){
                minlen = str.length();
                minstr = str;
            }
        }

        return minstr;
    }

    //original "abcd" add an extra "bcde", result is "abcde", so the cost is 1
    //use KMP can spped up this process, here I use an intuitive method
    public int calcost(String s1, String s2){
        int len1 = s1.length(), len2 = s2.length();
        int minlen = Math.min(len1, len2);
        int commonlen = 0;
        int maxcommonlen = 0;

        // s1[i, len1) match s2[0, j)
        while(commonlen < minlen){
            int i = len1 - 1 - commonlen;
            int j = commonlen + 1;

            //use commonlen to scan s1 and s2
            if( s1.substring(i).equals(s2.substring(0,j)) ) {
                maxcommonlen = commonlen + 1;
            }

            commonlen++;
        }
        return len2 - maxcommonlen;
    }
}
