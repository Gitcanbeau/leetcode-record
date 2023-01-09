import java.util.*;
import java.util.stream.Collectors;

public class leet501 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/find-mode-in-binary-search-tree/
    }
    //两种处理map的方式
    public static int[] findMode1(TreeNode root) {
        //Runtime: 13 ms, faster than 27.11% of Java online submissions for Find Mode in Binary Search Tree.
        //Memory Usage: 47.2 MB, less than 36.33% of Java online submissions for Find Mode in Binary Search Tree.
        HashMap<Integer,Integer> map1=new HashMap<>();
        ArrayList<Integer> arrlst1=new ArrayList<>();
        inorder(root,map1);
        int maxcount=0;
        Set<Integer> keyset1 = map1.keySet();
        for(Integer key1:keyset1){
            int countatthiskey=map1.get(key1);
            maxcount=Math.max(maxcount,countatthiskey);
        }
        for(Integer key1:keyset1){
            int countatthiskey=map1.get(key1);
            if(maxcount==countatthiskey){
                arrlst1.add(key1);
            }
        }
        int[] res=new int[arrlst1.size()];
        for(int i=0; i<arrlst1.size();i++){
            res[i]=arrlst1.get(i);
        }
        return res;
    }

    public static int[] findMode2(TreeNode root) {
        //Runtime: 19 ms, faster than 10.73% of Java online submissions for Find Mode in Binary Search Tree.
        //Memory Usage: 47.6 MB, less than 17.21% of Java online submissions for Find Mode in Binary Search Tree.
        HashMap<Integer, Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        if (root == null) return list.stream().mapToInt(Integer::intValue).toArray();
        // 获得频率 Map
        inorder(root, map);
        List<Map.Entry<Integer, Integer>> mapList = map.entrySet().stream()
                .sorted((c1, c2) -> c2.getValue().compareTo(c1.getValue()))
                .collect(Collectors.toList());
        list.add(mapList.get(0).getKey());
        // 把频率最高的加入 list
        for (int i = 1; i < mapList.size(); i++) {
            if (mapList.get(i).getValue() == mapList.get(i - 1).getValue()) {
                list.add(mapList.get(i).getKey());
            } else {
                break;
            }
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void inorder(TreeNode root, HashMap<Integer,Integer> map1){
        //dfs-recursion
        if(root==null) return;
        if(root.left!=null) inorder(root.left,map1);
        map1.put(root.val, map1.getOrDefault(root.val,0)+1);
        if(root.right!=null) inorder(root.right,map1);
        return;
    }
    public static void inorder2(TreeNode root, HashMap<Integer,Integer> map1){
        //dfs-iteration
        Stack<TreeNode> stack1=new Stack<>();
        TreeNode curr=root;
        while(curr!=null || !stack1.isEmpty()){
            if(curr!=null){
                stack1.push(curr);
                curr=curr.left;
            }else{
                curr=stack1.pop();
                map1.put(root.val, map1.getOrDefault(root.val,0)+1);
                curr=curr.right;
            }
        }
    }

    ///////////////以上是普通写法

    //前后指针的写法

    private static ArrayList<Integer> resList =new ArrayList<>();
    private static int maxCount=0;
    private static int count=0;
    private static TreeNode pre=null;

    public static int[] findMode3(TreeNode root) {
        //Runtime: 1 ms, faster than 95.57% of Java online submissions for Find Mode in Binary Search Tree.
        //Memory Usage: 45.6 MB, less than 74.26% of Java online submissions for Find Mode in Binary Search Tree.
//        resList = new ArrayList<>();
//        maxCount = 0;
//        count = 0;
//        pre = null; //在外面初始化或者内部初始化都可以的
        findMode_helper(root);
        int[] res = new int[resList.size()];
        for (int i = 0; i < resList.size(); i++) {
            res[i] = resList.get(i);
        }
        return res;
    }
    private static void findMode_helper(TreeNode root) {
        if (root == null) {
            return;
        }

        findMode_helper(root.left); //左
        int rootValue = root.val; //中
        // 计数
        if (pre == null || rootValue != pre.val) {
            count = 1;
        } else {
            count++;
        }
        // 更新结果以及maxCount
        //在递归遍历二叉搜索树的过程中，我还介绍了一个统计最高出现频率元素集合的技巧，
        // 要不然就要遍历两次二叉搜索树才能把这个最高出现频率元素的集合求出来。
        //为什么没有这个技巧一定要遍历两次呢？
        // 因为要求的是集合，会有多个众数，如果规定只有一个众数，那么就遍历一次稳稳的了。m
        if (count > maxCount) {
            resList.clear(); //好方法啊！！！
            resList.add(rootValue);
            maxCount = count;
        } else if (count == maxCount) {
            resList.add(rootValue);
        }

        pre = root; //更新前指针

        findMode_helper(root.right); //右
    }

    //////////////
    public static int[] findMode4(TreeNode root) {
        //Runtime: 16 ms, faster than 17.39% of Java online submissions for Find Mode in Binary Search Tree.
        //Memory Usage: 46.2 MB, less than 54.72% of Java online submissions for Find Mode in Binary Search Tree.
        TreeNode pre = null;
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> result = new ArrayList<>();
        int maxCount = 0;
        int count = 0;
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur =cur.left;
            }else {
                cur = stack.pop();
                // 计数
                if (pre == null || cur.val != pre.val) {
                    count = 1;
                }else {
                    count++;
                }
                // 更新结果
                if (count > maxCount) {
                    maxCount = count;
                    result.clear();
                    result.add(cur.val);
                }else if (count == maxCount) {
                    result.add(cur.val);
                }
                pre = cur;
                cur = cur.right;
            }
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

}
