import java.util.Arrays;

public class leet1385sol2 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/find-the-distance-value-between-two-arrays/
        int[] arr1={4,5,8};
        int[] arr2={10,9,1,8};
        int d=2;
        System.out.println(findTheDistanceValue(arr1,arr2,d));
        //Runtime: 16 ms, faster than 12.05% of Java online submissions for Find the Distance Value Between Two Arrays.
        //Memory Usage: 44.1 MB, less than 77.19% of Java online submissions for Find the Distance Value Between Two Arrays.
    }
    public static int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        //快速排除不符合要求的arr1中的元素
        //sort
        //只需要找到arr2中夹住arr1[i]的两个点，计算距离是否符合，其他的unnecessary计算都可以省略
        //如果arr1中的某个数比arr2最小的数还小，就不用找到夹着的两个点计算两对距离了，只需要计算一对距离就行了
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        int count=0;
        for(int i=0; i<arr1.length;i++){
            if(arr1[i]<=arr2[arr2.length-1] && arr1[i]>=arr2[0]){
                int[] index=findindex(arr1[i],arr2);
                int dis=Math.min((arr1[i]-arr2[index[0]]),arr2[index[1]]-arr1[i]);
                if(dis<=d){
                    count++;
                }
            }else{
                int index=findindex2(arr1[i],arr2);
                int dis=Math.abs(arr1[i]-arr2[index]);
                if(dis<=d){
                    count++;
                }
            }
        }
        return arr1.length-count;
    }
    public static int[] findindex(int x, int[] arr2){
        //已经确认x在arr2区间中
        int[] indexarr=new int[2];
        for(int i=0; i<arr2.length-1;i++){
            if(arr2[i]<x && arr2[i+1]>=x){
                indexarr[0]=i;
                indexarr[1]=i+1;
                break;
            }
        }
        return indexarr;
    }
    public static int findindex2(int x, int[] arr2){
        //已经确认x在arr区间外
        int index=0;

        if(x<arr2[0]){
            index=0;
        }
        if(x>arr2[arr2.length-1]){
            index=arr2.length-1;
        }

        return index;
    }
}
