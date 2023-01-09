public class TreeNodeNext {
    int val;
    TreeNodeNext left;
    TreeNodeNext right;
    TreeNodeNext next;

    public TreeNodeNext() {
    }

    public TreeNodeNext(int val) {
        this.val = val;
    }

    public TreeNodeNext(int val, TreeNodeNext left, TreeNodeNext right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "val=" + val +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}
