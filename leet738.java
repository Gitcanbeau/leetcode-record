public class leet738 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/monotone-increasing-digits/
    }

        //局部最优：遇到strNum[i - 1] > strNum[i]的情况，让strNum[i - 1]--，然后strNum[i]给为9，可以保证这两位变成最大单调递增整数。
        //
        //全局最优：得到小于等于N的最大单调递增的整数。
        //
        //但这里局部最优推出全局最优，还需要其他条件，即遍历顺序，和标记从哪一位开始统一改成9。
        //此时是从前向后遍历还是从后向前遍历呢？
        //
        //从前向后遍历的话，遇到strNum[i - 1] > strNum[i]的情况，让strNum[i - 1]减一，但此时如果strNum[i - 1]减一了，可能又小于strNum[i - 2]。
        //
        //这么说有点抽象，举个例子，数字：332，从前向后遍历的话，那么就把变成了329，此时2又小于了第一位的3了，真正的结果应该是299。
        //
        //所以从前后向遍历会改变已经遍历过的结果！
        //
        //那么从后向前遍历，就可以重复利用上次比较得出的结果了，从后向前遍历332的数值变化为：332 -> 329 -> 299
    public static int monotoneIncreasingDigits1(int N) {
        String[] strings = (N + "").split("");
        int start = strings.length;
        for (int i = strings.length - 1; i > 0; i--) {
            if (Integer.parseInt(strings[i]) < Integer.parseInt(strings[i - 1])) {
                strings[i - 1] = (Integer.parseInt(strings[i - 1]) - 1) + "";
                start = i;
            }
        }
        for (int i = start; i < strings.length; i++) {
            strings[i] = "9";
        }
        return Integer.parseInt(String.join("",strings));
    }
    public static int monotoneIncreasingDigits2(int n) {
        //Runtime: 2 ms, faster than 72.70% of Java online submissions for Monotone Increasing Digits.
        //Memory Usage: 41 MB, less than 60.74% of Java online submissions for Monotone Increasing Digits.
        String s = String.valueOf(n);
        char[] chars = s.toCharArray();
        int start = s.length();
        for (int i = s.length() - 2; i >= 0; i--) {
            if (chars[i] > chars[i + 1]) {
                chars[i]--;
                start = i+1;
            }
        }
        for (int i = start; i < s.length(); i++) {
            chars[i] = '9';
        }
        return Integer.parseInt(String.valueOf(chars));
    }
}
