public class leet450 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/delete-node-in-a-bst/
        TreeNode t1=new TreeNode(5,null,null);
        TreeNode t2=new TreeNode(3,null,null);
        TreeNode t3=new TreeNode(6,null,null);
        TreeNode t4=new TreeNode(2,null,null);
        TreeNode t5=new TreeNode(4,null,null);
        TreeNode t6=new TreeNode(7,null,null);
        t1.left=t2;
        t1.right=t3;
        t2.left=t4;
        t2.right=t5;
        t3.right=t6;
        System.out.println(deleteNode_wrong1(t1,3));
        System.out.println(deleteNode_wrong2(t1,3));
        TreeNode t0=new TreeNode(0,null,null);
//        System.out.println(deleteNode1(t0,0));
    }
    public static TreeNode deleteNode_wrong1(TreeNode root, int key) {
        //edge case【0】0 过不了
        if(root==null) return root; //不是return null
        if(root.val==key){
            TreeNode oldrootleft=root.left;
            TreeNode oldrootright=root.right;
            TreeNode cur = root.right;
            if(cur.left!=null){
                while (cur.left != null) {
                cur = cur.left;
                }
            }
            cur.left =  oldrootleft;
            root = oldrootright;
            return root;
        }

        if(root.val<key){
            root.right=deleteNode_wrong1(root.right,key);
        }
        if(root.val>key){
            root.left=deleteNode_wrong1(root.left,key);
        }
        return root;
    }

    public static TreeNode deleteNode_wrong2(TreeNode root, int key) {
        //edge case【0】0 过不了
        if(root==null) return root; //不是return null

        if(root.val<key){
            root.right=deleteNode_wrong2(root.right,key);
        }
        if(root.val>key){
            root.left=deleteNode_wrong2(root.left,key);
        }

        TreeNode pre=root;

        if(root.val==key){
            TreeNode oldkeyleft=root.left;
            TreeNode oldkeyright=root.right;
            TreeNode curr=oldkeyright;
            while(curr!=null){
                pre=curr;
                curr=curr.left;
            }
            pre.left=oldkeyleft;

        }

        return pre;
    }


    public static TreeNode deleteNode2(TreeNode root, int key) {
        //Runtime: 1 ms, faster than 14.33% of Java online submissions for Delete Node in a BST.
        //Memory Usage: 49.3 MB, less than 75.40% of Java online submissions for Delete Node in a BST.
        if (root == null) return null;

        if (root.val > key) {
            root.left = deleteNode2(root.left,key);
        } else if (root.val < key) {
            root.right = deleteNode2(root.right,key);
        } else {
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;
            TreeNode tmp = root.right;
//            TreeNode pre=root.right;
            while (tmp.left != null) {
//                pre=tmp;
                tmp = tmp.left;
            }
            root.val = tmp.val; //不删除，只是改值，真聪明
//            pre.left=null; //不对
            root.right = deleteNode2(root.right,tmp.val); //没看懂，但是很必要的一句
        }
        return root;
    }

    public static TreeNode deleteNode1(TreeNode root, int key) {
        //Runtime: 0 ms, faster than 100.00% of Java online submissions for Delete Node in a BST.
        //Memory Usage: 50.6 MB, less than 6.19% of Java online submissions for Delete Node in a BST.
        if (root == null) return root;
        if (root.val == key) {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            } else {
                TreeNode cur = root.right;
                while (cur.left != null) {
                    cur = cur.left;
                }
                cur.left = root.left; //把原节点的左树挂在右树的左下角，让原节点的右树替代原节点
                root = root.right;
                return root;
            }
        }
        if (root.val > key) root.left = deleteNode1(root.left, key);
        if (root.val < key) root.right = deleteNode1(root.right, key);
        return root;
    }
}
