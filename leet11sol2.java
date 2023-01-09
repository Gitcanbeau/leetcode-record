public class leet11sol2 {
    public static void main(String[] args) {
//        You are given an integer array height of length n.
//        There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).
//        Find two lines that together with the x-axis form a container, such that the container contains the most water.
//        Return the maximum amount of water a container can store.
//        Notice that you may not slant the container.

//        Example 1:
//        Input: height = [1,8,6,2,5,4,8,3,7]
//        Output: 49
//        Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.
//        Example 2:
//        Input: height = [1,1]
//        Output: 1

        int[] nums={1,8,6,2,5,4,8,3,7};
//        int[] nums={1,1};
        int area = maxArea(nums);
        System.out.println(area);
        //Runtime: 5 ms, faster than 58.01% of Java online submissions for Container With Most Water.
        //Memory Usage: 73.6 MB, less than 65.19% of Java online submissions for Container With Most Water.
    }
    public static int maxArea(int[] height) {
        if(height==null || height.length<2){
            return 0;
        }
        int maxarea=Integer.MIN_VALUE;
        int temparea;
        int indexi=0;
        int indexj=height.length-1;
        while(indexi<indexj){
            temparea=(Math.min(height[indexi],height[indexj])*(indexj-indexi));
            maxarea=Math.max(maxarea,temparea);
            if(height[indexi]<height[indexj]){
                indexi++;
            }else{
                indexj--;
            }
        }
        return maxarea;
    }
}
