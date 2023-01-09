import java.util.Arrays;

public class leet888 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/fair-candy-swap/
        int[] aliceSizes={2};
        int[] bobSizes={1,3};
        int[] swap=fairCandySwap(aliceSizes,bobSizes);
        System.out.println(swap[0]+","+swap[1]);
        //time limit exceeded
    }
    public static int[] fairCandySwap(int[] aliceSizes, int[] bobSizes) {
        int[] res=new int[2];
        int sum1=0;
        int sum2=0;
        for(int i=0; i< aliceSizes.length;i++){
            sum1+=aliceSizes[i];
        }
        for(int i=0; i< bobSizes.length;i++){
            sum2+=bobSizes[i];
        }

        int totalsum=sum1+sum2;

        Arrays.sort(aliceSizes);
        Arrays.sort(bobSizes);

        for(int i=0; i<aliceSizes.length;i++){
            for(int j=0;j< bobSizes.length;j++){
                if(bobSizes[j]-aliceSizes[i]==totalsum/2-sum1){
                    res[0]=aliceSizes[i];
                    res[1]=bobSizes[j];
                    break;
                }
            }

        }

        return res;
    }
}
