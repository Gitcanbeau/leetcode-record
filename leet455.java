import java.util.Arrays;

public class leet455 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/assign-cookies/
        int[] g={1,2,3};
        int[] s={1,1,1,1,1,1,2};
        System.out.println(findContentChildren(g,s));
    }
    public static int findContentChildren(int[] g, int[] s) {
        //Runtime: 15 ms, faster than 37.08% of Java online submissions for Assign Cookies.
        //Memory Usage: 53.7 MB, less than 52.87% of Java online submissions for Assign Cookies.
        Arrays.sort(g);
        Arrays.sort(s);
        if(s==null || s.length==0 || g[0]>s[s.length-1]) return 0;

        int res=0;
        int childindex=0;
        int candyindex=0;
        while(childindex<g.length && candyindex<s.length){
            if(g[childindex]<=s[candyindex]){
                res++;
                childindex++;
                candyindex++;
            }else{
                candyindex++;
            }
        }
        return res;
    }
    //这里的局部最优就是大饼干喂给胃口大的，充分利用饼干尺寸喂饱一个，全局最优就是喂饱尽可能多的小孩。
    //也可以换一个思路，小饼干先喂饱小胃口
}
