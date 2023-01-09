import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class leet406 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/queue-reconstruction-by-height/
    }
    public static int[][] reconstructQueue(int[][] people) {
        //Runtime: 13 ms, faster than 57.33% of Java online submissions for Queue Reconstruction by Height.
        //Memory Usage: 54.7 MB, less than 43.56% of Java online submissions for Queue Reconstruction by Height.
    //Runtime: 18 ms, faster than 43.65% of Java online submissions for Queue Reconstruction by Height.
        //Memory Usage: 54.3 MB, less than 75.78% of Java online submissions for Queue Reconstruction by Height.

        //那么按照身高h来排序呢，身高一定是从大到小排（身高相同的话则k小的站前面），让高个子在前面。
        //
        //此时我们可以确定一个维度了，就是身高，前面的节点一定都比本节点高！
        //按照身高排序之后，优先按身高高的people的k来插入，后序插入节点也不会影响前面已经插入的节点，最终按照k的规则完成了队列。
        //
        //所以在按照身高从大到小排序后：
        //
        //局部最优：优先按身高高的people的k来插入。插入操作过后的people满足队列属性
        //
        //全局最优：最后都做完插入操作，整个队列满足题目队列属性
        // 身高从大到小排（身高相同k小的站前面）

        Arrays.sort(people, (a, b) -> {
            // 身高从大到小排（身高相同k小的站前面）
            if (a[0] == b[0]) return a[1] - b[1]; //前-后，k升序排列
            return b[0] - a[0]; //后-前，h降序排列
        });

        List<int[]> que = new ArrayList<>();

        for (int[] p : people) {
            que.add(p[1],p);
        }

        return que.toArray(new int[people.length][]);
    }
}
