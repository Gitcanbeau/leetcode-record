public class leet2331 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/evaluate-boolean-binary-tree/
        TreeNode node1=new TreeNode(2,null,null);
        TreeNode node2=new TreeNode(1,null,null);
        TreeNode node3=new TreeNode(3,null,null);
        TreeNode node4=new TreeNode(0,null,null);
        TreeNode node5=new TreeNode(1,null,null);
        node1.left=node2;
        node1.right=node3;
        node3.left=node4;
        node3.right=node5;
//        TreeNode node1=new TreeNode(0,null,null);
        System.out.println(evaluateTree(node1));
        //Runtime: 1 ms, faster than 55.17% of Java online submissions for Evaluate Boolean Binary Tree.
        //Memory Usage: 47 MB, less than 15.08% of Java online submissions for Evaluate Boolean Binary Tree.

    }
    public static boolean evaluateTree(TreeNode root) {
        if(root.left==null){
            if(root.val==0){
             return false;
            }
            if(root.val==1){
                return true;
            }
        }

        return calculate(root.left,root.right,root);

    }

    public static boolean calculate(TreeNode left,TreeNode right, TreeNode root){

        boolean leftb=false;
        boolean rightb=false;
        if(left.left==null){
            if(left.val==0){
                leftb=false;
            }
            if(left.val==1){
                leftb=true;
            }
        }

        if(right.left==null){
            if(right.val==0){
                rightb=false;
            }
            if(right.val==1){
                rightb=true;
            }
        }

        if(left.left!=null){
            leftb=calculate(left.left,left.right,left);
        }

        if(right.left!=null){
            rightb=calculate(right.left,right.right,right);
        }



        boolean rootb=false;
        if(root.val==2){
            rootb= leftb|rightb;
        }
        if(root.val==3){
            rootb= leftb&rightb;
        }

        return rootb;
    }

}
