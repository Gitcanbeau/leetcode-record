import java.util.ArrayList;

public class leet997 {
    public static void main(String[] args) {
        //In a town, there are n people labeled from 1 to n. There is a rumor that one of these people is secretly the town judge.
        //If the town judge exists, then:
        //The town judge trusts nobody.
        //Everybody (except for the town judge) trusts the town judge.
        //There is exactly one person that satisfies properties 1 and 2.
        //You are given an array trust where trust[i] = [ai, bi] representing that the person labeled ai trusts the person labeled bi.
        //Return the label of the town judge if the town judge exists and can be identified, or return -1 otherwise.
        //Example 1:
        //Input: n = 2, trust = [[1,2]]
        //Output: 2
        //Example 2:
        //Input: n = 3, trust = [[1,3],[2,3]]
        //Output: 3
        //Example 3:
        //Input: n = 3, trust = [[1,3],[2,3],[3,1]]
        //Output: -1
        //Constraints:
        //1 <= n <= 1000
        //0 <= trust.length <= 104
        //trust[i].length == 2
        //All the pairs of trust are unique.
        //ai != bi
        //1 <= ai, bi <= n
//        int[][] trust={{1,3},{2,3}};
//        int n=3;
//        int[][] trust={{1,2}};
//        int n=2;
//        int[][] trust={{1,3},{2,3},{3,1}};
//        int n=3;
        int[][] trust={{1,3},{1,4},{2,3},{2,4},{4,3}};
        int n=4;
        System.out.println(findJudge(n,trust));
        //Runtime: 43 ms, faster than 7.20% of Java online submissions for Find the Town Judge.
        //Memory Usage: 74.1 MB, less than 30.03% of Java online submissions for Find the Town Judge.
    }
    public static int findJudge(int n, int[][] trust) {

        ArrayList<Integer> countateachpoint=new ArrayList<>(n+1);//这里写n+1也不行，用set就是要人为初始化
        ArrayList<Integer> votecount=new ArrayList<>(n+1);
        for(int i=0; i<n+1;i++){
            countateachpoint.add(0);
            votecount.add(0);
        }

        for(int[] pair: trust){
            votecount.set(pair[0],countateachpoint.get(pair[0])+1);
            countateachpoint.set(pair[1],countateachpoint.get(pair[1])+1);//用set必须有初始化
        }

        //center以外在votecount中只有0票，center本人在countateachpoint中有n-1票

        ArrayList<Integer> couldbecenter=new ArrayList<>();
        for(int i=1; i<countateachpoint.size();i++){
            if(countateachpoint.get(i)==n-1 && votecount.get(i)==0) {
                couldbecenter.add(i);
                continue;
            }
        }

        if(couldbecenter.size()!=1){
            return -1;
        }else{
            return couldbecenter.get(0);
        }

    }
}
