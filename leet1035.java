public class leet1035 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/uncrossed-lines/
    }
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        //Runtime: 14 ms, faster than 43.10% of Java online submissions for Uncrossed Lines.
        //Memory Usage: 44.5 MB, less than 26.94% of Java online submissions for Uncrossed Lines.
        int len1 = nums1.length;
        int len2 = nums2.length;
        int[][] dp = new int[len1 + 1][len2 + 1];

        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[len1][len2];
    }
}
