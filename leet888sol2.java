import java.util.Arrays;

public class leet888sol2 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/fair-candy-swap/
        int[] aliceSizes={2};
        int[] bobSizes={1,3};
        int[] swap=fairCandySwap(aliceSizes,bobSizes);
        System.out.println(swap[0]+","+swap[1]);
        //Runtime: 27 ms, faster than 44.91% of Java online submissions for Fair Candy Swap.
        //Memory Usage: 61.7 MB, less than 84.94% of Java online submissions for Fair Candy Swap.
    }
    public static int[] fairCandySwap(int[] aliceSizes, int[] bobSizes) {
        int[] res = new int [2];
        int alice = 0, bob = 0, start, end, mid;

        Arrays.sort(bobSizes);

        for (int i = 0; i < aliceSizes.length; ++i)
            alice += aliceSizes[i];
        for (int i = 0; i < bobSizes.length; ++i)
            bob += bobSizes[i];

        for (int i = 0; i < aliceSizes.length; ++i)
        {
            alice -= aliceSizes[i];
            bob += aliceSizes[i];
            start = 0;
            end = bobSizes.length - 1;

            while (start <= end)
            {
                mid = start + (end - start) / 2;

                alice += bobSizes[mid];
                bob -= bobSizes[mid];

                if (alice == bob)
                {
                    res[0] = aliceSizes[i];
                    res[1] = bobSizes[mid];

                    return res;
                }
                else if (alice > bob)
                    end = mid - 1;
                else
                    start = mid + 1;

                alice -= bobSizes[mid];
                bob += bobSizes[mid];
            }

            alice += aliceSizes[i];
            bob -= aliceSizes[i];
        }

        return res;
    }
}
