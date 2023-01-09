import java.util.HashSet;
import java.util.Set;

public class leet753 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/cracking-the-safe/
    }

    //The key point for solving this problem is confidence :-) I'm serious.
    //
    //The whole solution is based on an assumption: the length of result string must be n + k^n-1, which means every following substring takes n-1 characters of previous string as its first n-1 characters. For example: n=2, k=2, result="00110": "00"+"01"+"11"+"10".
    //
    //However sometimes simply add a random character after first n-1 characters may not form the correct result. For example: still n=2, k=2, "0010": "00"+"01"+"10", then we found that we cannot add any characters after "0010" to form "11"
    //
    //So the problem becomes trying all possible results and find the one that has length of n+k^n-1, and that is the reason why I use DFS.
    //
    //(Note: In fact the assumption has been proved, it is called "De Bruijn sequence", but for people like me who does not know it, the only way is trying several combinations and believe in your intuition)
    public String crackSafe(int n, int k) {
        Set<String> visited = new HashSet<String>();
        //*start from string "00.."
        String res = "";
        for(int j = 0; j < n; j++){
            res+=0;
        }
        //*calculate target length, which is k^n+n-1
        int total = 1;
        for(int i = 0; i < n; i++){
            total *= k;
        }
        total += n-1;
        //*run DFS
        res=DFS(res, n, k, visited, total);
        return res;
    }
    private String DFS(String res, int n, int k, Set<String> visited, int total){
        int len = res.length();
        visited.add(res.substring(len-n, len));
        for(int i = 0; i < k; i++){
            if(!visited.contains(res.substring(len-n+1, len)+i)){
                String tmp = DFS(res+i, n, k, visited, total);
                //*if length of result is less than total length, remove substring from visited and continue loop, else we are done! break the loop!
                if(tmp.length() == total){
                    res = tmp;
                    break;
                }
                visited.remove(res.substring(len-n+1, len)+i);
            }
        }
        return res;
    }

}
