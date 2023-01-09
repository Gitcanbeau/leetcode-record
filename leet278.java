public class leet278 {
    public static void main(String[] args) {
        //You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest version of your product fails the quality check. Since each version is developed based on the previous version, all the versions after a bad version are also bad.
        //Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.
        //You are given an API bool isBadVersion(version) which returns whether version is bad. Implement a function to find the first bad version. You should minimize the number of calls to the API.
        //Example 1:
        //Input: n = 5, bad = 4
        //Output: 4
        //Explanation:
        //call isBadVersion(3) -> false
        //call isBadVersion(5) -> true
        //call isBadVersion(4) -> true
        //Then 4 is the first bad version.
        //Example 2:
        //Input: n = 1, bad = 1
        //Output: 1
        //Constraints:
        //1 <= bad <= n <= 231 - 1
        int n=5;
        System.out.println(firstBadVersion(n));
        //Runtime: 21 ms, faster than 62.69% of Java online submissions for First Bad Version.
        //Memory Usage: 40.6 MB, less than 65.76% of Java online submissions for First Bad Version.
    }
    public static int firstBadVersion(int n) {
        int left=0;
        int right=n;
        while(left<right){
            int mid=left+(right-left)/2;
            if(isBadVersion(mid)==true){
                right=mid;
            }else{
                left=mid+1;
            }
        }
        return left;
    }

    public static boolean isBadVersion(int target){
        boolean[] version=isBadVersion2(5,3);
        return version[target];
    }
    public static boolean[] isBadVersion2(int n, int bad){
        boolean[] version=new boolean[n];
        for(int i=0; i<bad;i++){
            version[i]=false;
        }
        for(int i=bad; i<n-1;i++){
            version[i]=true;
        }

        return version;
    }
}
