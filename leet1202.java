import java.util.*;

public class leet1202 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/smallest-string-with-swaps/
        String s="dcab";
        List<List<Integer>> pairs=new ArrayList<>();
        List<Integer> arrylst1=new ArrayList<>();
        List<Integer> arrylst2=new ArrayList<>();
        List<Integer> arrylst3=new ArrayList<>();
        arrylst1.add(0);
        arrylst1.add(3);
        arrylst2.add(1);
        arrylst2.add(2);
        arrylst3.add(0);
        arrylst3.add(2);
        pairs.add(arrylst1);
        pairs.add(arrylst2);
        pairs.add(arrylst3);
        System.out.println(smallestStringWithSwaps2(s,pairs));
    }
    class UF {
        public UF(int n) {
            id = new int[n];
            for (int i = 0; i < n; ++i)
                id[i] = i;
        }

        public void union(int u, int v) {
            id[find(u)] = find(v);
        }

        public int find(int u) {
            return id[u] == u ? u : (id[u] = find(id[u]));
        }

        private int[] id;
    }

    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        //Runtime: 52 ms, faster than 89.67% of Java online submissions for Smallest String With Swaps.
        //Memory Usage: 90.8 MB, less than 94.38% of Java online submissions for Smallest String With Swaps.
        StringBuilder ans = new StringBuilder();
        UF uf = new UF(s.length());
        Map<Integer, Queue<Character>> map = new HashMap<>();

        for (List<Integer> pair : pairs)
            uf.union(pair.get(0), pair.get(1));

        for (int i = 0; i < s.length(); ++i)
            map.computeIfAbsent(uf.find(i), k -> new PriorityQueue<>()).offer(s.charAt(i));

        for (int i = 0; i < s.length(); ++i)
            ans.append(map.get(uf.find(i)).poll());

        return ans.toString();
    }









    private static void dfs(String s, List<List<Integer>> adj,boolean[] visit, List<Integer> ind, List<Character> ch,int curr) {

        ch.add(s.charAt(curr));
        ind.add(curr);
        visit[curr] = true;

        for (int next : adj.get(curr)) {
            if (!visit[next]) {
                dfs( s, adj,visit, ind, ch,next);
            }
        }
        return;
    }

    public static String smallestStringWithSwaps2(String s, List<List<Integer>> pairs) {
        //Runtime: 186 ms, faster than 15.24% of Java online submissions for Smallest String With Swaps.
        //Memory Usage: 108.8 MB, less than 14.63% of Java online submissions for Smallest String With Swaps.

        //build adj
        List<List<Integer>> adj=new ArrayList<>();
        for(int i=0;i<s.length();i++){
            adj.add(i,new ArrayList<>());
        }
        for (List<Integer> edge : pairs) {
            adj.get(edge.get(0)).add(edge.get(1));
            adj.get(edge.get(1)).add(edge.get(0));
        }

        char[] res = new char[s.length()];
        boolean[] visit = new boolean[s.length()];
        for (int i = 0; i < s.length(); i++) {
            if (!visit[i]) {
                List<Character> ch = new ArrayList<>();
                List<Integer> ind = new ArrayList<>();

                dfs(s, adj,visit, ind, ch,i);

                Collections.sort(ch);
                Collections.sort(ind);

                for (int j = 0; j < ch.size(); j++) {
                    res[ind.get(j)] = ch.get(j);
                }
            }
        }
        return new String(res);
    }
}
