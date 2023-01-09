import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class leet904 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/fruit-into-baskets/
//        int[] fruits={1,2,3,2,2};//4
//        int[] fruits={1,1,1,2,2,3,4,3,4,3,4,3,4,3,4,2,2,2,1,1,1}; //10
//        int[] fruits={1,2};//2
//        int[] fruits={1};//1
        int[] fruits={1,0,1,4,1,4,1,2,3};//5
        System.out.println(totalFruit(fruits));
        //Runtime: 183 ms, faster than 5.06% of Java online submissions for Fruit Into Baskets.
        //Memory Usage: 121.4 MB, less than 5.22% of Java online submissions for Fruit Into Baskets.
    }
    public static int totalFruit(int[] fruits) {
        if(fruits.length==1){
            return 1;
        }
        Map<Integer,Integer> setoftype=new HashMap();
        int left=0;
        setoftype.put(fruits[left],1);
        int count=1;
        int maxcount=0;
        for(int right=1;right<fruits.length;right++){
            if(setoftype.size()<2){
                if(setoftype.containsKey(fruits[right])){
                    setoftype.replace(fruits[right],setoftype.get(fruits[right])+1);
                }else{
                    setoftype.put(fruits[right],1);
                }
                count=right-left+1;
                maxcount=Math.max(maxcount,count);
            }else if(setoftype.size()==2 && setoftype.containsKey(fruits[right])){
                setoftype.replace(fruits[right],setoftype.get(fruits[right])+1);
                count=right-left+1;
                maxcount=Math.max(maxcount,count);
            }else if(setoftype.size()==2 && !setoftype.containsKey(fruits[right])){
                //hashset存该元素的个数，每左移动一次就减去该元素的个数，直到我这个值不为0的元素就2个，left就移动停止
                setoftype.put(fruits[right],1);
                while(setoftype.get(fruits[left])>0){
                    setoftype.replace(fruits[left],setoftype.get(fruits[left])-1);
                    if(setoftype.get(fruits[left])==0){
                        break;
                    }
                    left++; //我不知道右移动left到谁那里正好就剩2个元素，所以，你就边右移left边消耗元素个数，直到某个位置某元素剩0个了，我left就应该放在这个位置的下一个位置
                }
                setoftype.remove(fruits[left]);//终于消灭光了，那就从词典里面删除
                left++;//刚才的left停在被删除的元素，所以往后移动
                count=right-left+1;
                maxcount=Math.max(maxcount,count);//其实这里不用比较，因为怎么着这一块代码都不会给你更大的值了
            }
        }
        return maxcount;
    }


    public static int totalFruit2(int[] fruits) {
        HashMap<Integer,Integer> dict = new HashMap();
        int count = 0;
        int start = 0;
        int end = 0;
        int max = 0;
        while(end<fruits.length) {
            dict.put(fruits[end] , dict.getOrDefault(fruits[end],0)+1);
            if(dict.size() > 2) {
                while(dict.size()>2) {
                    dict.put(fruits[start], dict.get(fruits[start])-1);
                    if(dict.get(fruits[start]) == 0)
                        dict.remove(fruits[start]);
                    start++;
                }
            }
            else
                max = Math.max(max,end-start+1);
            end++;
        }
        return max;
    }
}
