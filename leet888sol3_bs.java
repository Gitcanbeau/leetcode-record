import java.util.Arrays;

public class leet888sol3_bs {
    public static void main(String[] args) {
        //https://leetcode.com/problems/fair-candy-swap/
        int[] aliceSizes={2};
        int[] bobSizes={1,3};
        int[] swap=fairCandySwap(aliceSizes,bobSizes);
        System.out.println(swap[0]+","+swap[1]);
    }
    public static int[] fairCandySwap(int[] aliceSizes, int[] bobSizes) {
        //Runtime: 23 ms, faster than 58.97% of Java online submissions for Fair Candy Swap.
        //Memory Usage: 62.6 MB, less than 79.06% of Java online submissions for Fair Candy Swap.
        int sum1=0;
        int sum2=0;
        for(int i=0; i< aliceSizes.length;i++){
            sum1+=aliceSizes[i];
        }
        for(int i=0; i< bobSizes.length;i++){
            sum2+=bobSizes[i];
        }
        //alicesum-alice[i]+bob[j]==bobsum-bob[j]+alice[i]
        //bob[j]-alice[i]=(bobsum-alicesum)/2;
        //(bobsum-alicesum)/2 就是target
        //找到alice[i]在bob_arr中符合条件的bob[j]，如果此alice[i]没有，就换下一个alice[i]
        //所以要在bob_arr中使用二分，并且至少需要对bob_arr排序

//        Arrays.sort(aliceSizes);
        Arrays.sort(bobSizes);
        int[] res=new int[2];
        res[0]=-1;
        res[1]=-1;
        int target=(sum2-sum1)/2;
        for(int i=0; i<aliceSizes.length;i++){
            int currentalicecandy=aliceSizes[i];
            int left=0;
            int right=bobSizes.length-1;
            while(left<=right){
                int mid=left+(right-left)/2;
                if(bobSizes[mid]-currentalicecandy==target){
                    res[0]=currentalicecandy;
                    res[1]=bobSizes[mid];
                    return res;
                }else if(bobSizes[mid]-currentalicecandy<target){
                    left=mid+1;
                }else if(bobSizes[mid]-currentalicecandy>target){
                    right=mid-1;
                }
            }
        }
        return res;
    }

}
