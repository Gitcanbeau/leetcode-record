public class leet474 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/ones-and-zeroes/
//        String[] strs={"10","0001","111001","1","0"};
//        int m=5;
//        int n=3;
        String[] strs={"10","0001","111001","1","0"};
        int m=4;
        int n=3;
        System.out.println(findMaxForm(strs,m,n));
    }
    public static int findMaxForm(String[] strs, int m, int n) {
        int[][] res=zero_one_s(strs);
        int[][] dp=new int[m+1][n+1];

        for(int i=0; i<res.length;i++){
            if(res[i][0]==0){
                dp[0][res[i][1]]+=1;
            }
            if(res[i][1]==0){
                dp[res[i][0]][0]+=1;
            }
        }

        for(int j=1;j<n+1;j++){
            if(dp[0][j-1]>dp[0][j]) {
                dp[0][j] = dp[0][j-1];
            }
        }

        for(int k=1;k<m+1;k++){
            if(dp[k-1][0]>dp[k][0]) {
                dp[k][0] = dp[k-1][0];
            }
        }

//        for(int k=0;k<m+1;k++){
//            for(int j=0;j<n+1;j++){
//                System.out.print(dp[k][j]+",");
//            }
//            System.out.println();
//        }
//        System.out.println("----------");

        for(int i=0; i<res.length;i++) {
            for (int k = m; k >=1; k--) {
                for (int j = n; j >=1; j--) { //为什么倒叙遍历，自己已经看出来了是两个维度的01背包
                    if (k >= res[i][0] && j >= res[i][1]) {
                        dp[k][j] = Math.max(dp[k - res[i][0]][j - res[i][1]] + 1, dp[k][j]);
                    }
                }
            }
        }


//        for(int k=0;k<m+1;k++){
//            for(int j=0;j<n+1;j++){
//                System.out.print(dp[k][j]+",");
//            }
//            System.out.println();
//        }

        return dp[m][n];
    }
    public static int[][] zero_one_s(String[] strs){
        int[][] res=new int[strs.length][2];

        for(int i=0;i< strs.length;i++){
            int count_zero=0;
            int count_one=0;
            int sizeofthisstr=strs[i].length();
            for(int j=0;j<sizeofthisstr;j++){
                if(strs[i].charAt(j)=='0') count_zero++;
                if(strs[i].charAt(j)=='1') count_one++;
            }
            res[i][0]=count_zero;
            res[i][1]=count_one;
        }
        return res;
    }


    public static int findMaxForm2(String[] strs, int m, int n) {
        //Runtime: 18 ms, faster than 100.00% of Java online submissions for Ones and Zeroes.
        //Memory Usage: 42 MB, less than 90.71% of Java online submissions for Ones and Zeroes.

        //两个dp一位滚动数组，所以两次到序遍历
        //dp[i][j]表示i个0和j个1时的最大子集
        int[][] dp = new int[m + 1][n + 1];
        int oneNum, zeroNum;
        for (String str : strs) {
            oneNum = 0;
            zeroNum = 0;
            for (char ch : str.toCharArray()) {
                if (ch == '0') {
                    zeroNum++;
                } else {
                    oneNum++;
                }
            }
            //倒序遍历
            for (int i = m; i >= zeroNum; i--) {// 遍历背包容量且从后向前遍历！
                for (int j = n; j >= oneNum; j--) {// 遍历背包容量且从后向前遍历！
                    //对比一下就会发现，字符串的zeroNum和oneNum相当于物品的重量（weight[i]），字符串本身的个数相当于物品的价值（value[i]）。
                    dp[i][j] = Math.max(dp[i][j], dp[i - zeroNum][j - oneNum] + 1);
                }
            }
        }



        return dp[m][n];
    }
}
