public class leet69 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/sqrtx/

//        int x=4;
        int x=17;
        System.out.println(mySqrt(x));
    }
    public static int mySqrt(int x) {
        return (int) getSqrt(x);
    }
    public static long getSqrt(int x){
        //Runtime: 3 ms, faster than 41.19% of Java online submissions for Sqrt(x).
        //Memory Usage: 41.6 MB, less than 34.97% of Java online submissions for Sqrt(x).
        long left=1; //虽说是1->x 但是相当于0->x.length-1，本质上就是左闭右闭。或者你想1和x都能取到，但是0可是取不到的，怎么除以2都取不到
        long right=x;
        long ans=0;
        while(left<=right){ //既然是左闭右闭，那就left<=right
            long mid=left+(right-left)/2;
            if(mid*mid==x){
                return mid;
            }else if(mid*mid>x){
                right=mid-1; //既然是左闭右闭，那就不包含mid
            }else if(mid*mid<x){
                ans=mid;
                //这里是唯一需要想清楚的，1。就是我这里是从mid平方>x缩小区间来的，虽然有可能有好几个mid平方<x，但是left不断右移动就会一直更新这个ans，
                // 咱就说最大的一个保障mid平方<x 的mid，这个mid+1的值的平方一定比x大，
                // 也就是说我left+1以后就再也不会到达ans所在的区间，剩下的事全都发生在第二个条件中，不断地减小right直到right<left就能出循环了
                left=mid+1; //既然是左闭右闭，那就不包含mid
            }
        }
        return ans; //普通写法从0->len-1或者len都是返回left。但是这里返回的实际上是left-1
    }

//    public static int getSqrt(int x){
//        //2147395599 edge case 通不过 //虽然我这个x在int类型数字的取值范围内，但是中间的某个数的平方会比2^31-1大，所以要用long
          //2147483647=2^31-1
          //1000000000=10^9
//        int left=1;
//        int right=x;
//        int ans=0;
//        while(left<=right){
//            int mid=left+(right-left)/2;
//            if(mid*mid==x){
//                return mid;
//            }else if(mid*mid>x){
//                right=mid-1;
//            }else if(mid*mid<x){
//                ans=mid;
//
//                left=mid+1;
//            }
//        }
//        return ans;
//    }
}
