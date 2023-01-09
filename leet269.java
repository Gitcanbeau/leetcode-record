import java.util.*;

public class leet269 {
    public static void main(String[] args) {
        //there is a new alien language which uses for the latin alphabet.however, the order among letters are unkonwn to you.
//        you receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules of this
//        new language. derive the order of the letters in this language.
        //example1:
        //intput:"wrt","wrf","er","ett","rftt"
        //output:"wertf"
        //example2:
        //intput:"z","x","z"
        //output:""
        //example3:
        //intput:"z","x"
        //output:"zx"
    }
    public static String alienorder(String[] words){
        int[] indegree=new int[26];
        Map<Character,Set<Character>> adj=new HashMap<>();
        get_adj(words,indegree,adj);
        return bfs(indegree,adj);
    }
    private static void get_adj(String[] words,int[] indegree,Map<Character,Set<Character>> adj) {
        for(String word: words){
            for(char c: word.toCharArray()){
                adj.putIfAbsent(c,new HashSet<>());
            }
        }

        for(int i=1;i<words.length;i++){
            String pre=words[i-1];
            String curr=words[i];
            int len=Math.min(pre.length(),curr.length());
            for(int j=0; j<len;j++){
                if(pre.charAt(j)!=curr.charAt(j)){
                    char from=pre.charAt(j);
                    char to=curr.charAt(j);
                    if(!adj.get(from).contains(to)){
                        adj.get(from).add(to);
                        indegree[to-'a']++;
                    }
                    break;
                    //难点在于理解题目，只要两个单词中的某个位置的字母不同，后面的东西都没必要继续比较了，
                    // 因为优先度高的字母所引领的单词优先度都比优先度第的字母所引领的单词优先度高
                }
            }
        }
    }
    private static String bfs(int[] indegree,Map<Character,Set<Character>> adj){
        StringBuilder sb=new StringBuilder();
        int totalChars=adj.size();
        Queue<Character> bfsque=new LinkedList<>();
        for(char c: adj.keySet()){
            if(indegree[c-'a']==0){
                sb.append(c);
                bfsque.offer(c);
            }
        }

        while(!bfsque.isEmpty()){
            char from= bfsque.poll();
            if(adj.get(from)==null || adj.get(from).size()==0){
                continue;
            }

            for(char to: adj.get(from)){
                indegree[to-'a']--;
                if(indegree[to-'a']==0){
                    sb.append(to);
                    bfsque.offer(to);
                }
            }
        }

        return sb.length()==totalChars ? sb.toString():"";
    }
}
