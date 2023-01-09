public class leet852 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/peak-index-in-a-mountain-array/
        int[] arr={0,10,9,8,2};
        System.out.println(peakIndexInMountainArray(arr));
        //Runtime: 0 ms, faster than 100.00% of Java online submissions for Peak Index in a Mountain Array.
        //Memory Usage: 70.5 MB, less than 84.85% of Java online submissions for Peak Index in a Mountain Array.
    }
    public static int peakIndexInMountainArray(int[] arr) {
        int left=0;
        int right=arr.length-1; //说了模版不可全信，我可以再说一次，理解本质因地制宜具体问题具体分析
        while(left<right){
            int mid=left+(right-left)/2;
            if(arr[mid]>arr[left] && arr[mid]<=arr[right]){//在左半边
                left=mid+1;
            }else if(arr[mid]>arr[right] && arr[mid]<=arr[left]){//在右半边
                right=mid;
            }else if(arr[mid]>arr[right] && arr[mid]>arr[left] && arr[mid]<arr[mid+1]){//在中间但是peak在mid右侧
                left=mid+1;
            }else if(arr[mid]>arr[right] && arr[mid]>arr[left] && arr[mid]<arr[mid-1]){//在中间但是peak在mid左侧
                right=mid;
            }else if(arr[mid]>arr[right] && arr[mid]>arr[left] && arr[mid]>arr[mid+1] && arr[mid]>arr[mid-1]){
                //这不完美了，peak就是比相邻的左右都大
                return mid;
            }
        }
        return left;
    }
}
