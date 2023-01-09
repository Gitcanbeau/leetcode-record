public class leet701 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/insert-into-a-binary-search-tree/
    }
    public static TreeNode insertIntoBST1(TreeNode root, int val) {
        //Runtime: 0 ms, faster than 100.00% of Java online submissions for Insert into a Binary Search Tree.
        //Memory Usage: 42.6 MB, less than 99.01% of Java online submissions for Insert into a Binary Search Tree.
        if(root==null) return new TreeNode(val,null,null);
        if(root.val<val){
            root.right=insertIntoBST1(root.right, val); //这里相当于把新的节点返回给上一层，上一层就要用 root.left接住返回值
        }
        if(root.val>val){
            root.left=insertIntoBST1(root.left,val);
        }
        return root;
    }

    public static TreeNode insertIntoBST1_a(TreeNode root, int val) {
        if(root==null) return new TreeNode(val,null,null);
        if(root.val<val){
            if(root.right!=null){
                root.right=insertIntoBST1_a(root.right, val);
            }else{
                root.right=new TreeNode(val,null,null);
            }
        }

        if(root.val>val){
            if(root.left!=null){
                root.left=insertIntoBST1_a(root.left, val);
            }else{
                root.left=new TreeNode(val,null,null);
            }
        }

        return root;
    }

    public static TreeNode insertIntoBST2(TreeNode root, int val) {
        //Runtime: 0 ms, faster than 100.00% of Java online submissions for Insert into a Binary Search Tree.
        //Memory Usage: 54.9 MB, less than 11.51% of Java online submissions for Insert into a Binary Search Tree.
        if (root == null) return new TreeNode(val);
        TreeNode curr=root;//很必要，如果你不暂存root为curr的话，直接遍历root以后到底是root就是null，你再返回root就是返回null而不知最顶上的根节点
        TreeNode pre=curr; //我要存上最后一个不是null的节点，然后新建一个新节点挂在这个节点下面
        while (curr != null) {
            pre=curr;
            if (curr.val > val) {
                curr = curr.left;
            } else if (curr.val < val) {
                curr = curr.right;
            }
        }


        if (pre.val > val) {
            pre.left = new TreeNode(val);
        } else {
            pre.right = new TreeNode(val);
        }

        return root;
    }
}
