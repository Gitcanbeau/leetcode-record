public class leet669 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/trim-a-binary-search-tree/
        TreeNode t1=new TreeNode(3,null,null);
        TreeNode t2=new TreeNode(0,null,null);
        TreeNode t3=new TreeNode(4,null,null);
        TreeNode t5=new TreeNode(2,null,null);
        TreeNode t6=new TreeNode(1,null,null);
        t1.left=t2;
        t1.right=t3;
        t2.right=t5;
        t5.left=t6;
        System.out.println(trimBST(t1,1,3));
        System.out.println(trimBST2(t1,1,3));
    }

    public static TreeNode trimBST(TreeNode root, int low, int high) {
        //Runtime: 1 ms, faster than 20.34% of Java online submissions for Trim a Binary Search Tree.
        //Memory Usage: 44.5 MB, less than 83.95% of Java online submissions for Trim a Binary Search Tree.
        if(root==null) return null;

        if(root.val<low){
//            return root.right; 这么写不对，因为root.right 也有可能比low小，所以这里还要一直递归
            return trimBST(root.right,low,high);
        }
        if(root.val>high){
//            return root.left;
            return trimBST(root.left,low,high);
        }
        if(root.val<=high && root.val>=low){
            root.left=trimBST(root.left,low,high);
            root.right=trimBST(root.right,low,high);
        }

        return root;
    }
    public static TreeNode trimBST1_a(TreeNode root, int low, int high) {

        if(root==null) return null;

        if(root.val<low){
//            return root.right; 这么写不对，因为root.right 也有可能比low小，所以这里还要一直递归
            root=trimBST1_a(root.right,low,high);
        }else if(root.val>high){
//            return root.left;
            root=trimBST1_a(root.left,low,high);
        }else if(root.val<=high && root.val>=low){
            root.left=trimBST1_a(root.left,low,high);
            root.right=trimBST1_a(root.right,low,high);
        }

        return root;
    }
    public static TreeNode trimBST2(TreeNode root, int low, int high) {
        if(root==null) return null;
        // 处理头结点，让root移动到[L, R] 范围内，注意是左闭右闭
        while(root!=null && root.val<low || root.val>high){
            if(root.val<low){
                root=root.right;
            }else{
                root=root.left;
            }
        }
        TreeNode curr=root;
        // 此时root已经在[L, R] 范围内，处理左孩子元素小于L的情况
        while(curr!=null){
            while(curr.left!=null && curr.left.val<low){
                curr.left=curr.left.right;
            }
            curr=curr.left;
        }

        curr=root;
        while(curr!=null) {
            while (curr.right != null && curr.right.val > high) {
                curr.right = curr.right.left;
            }
            curr=curr.right;
        }
        return root;

    }

}
