public class leet24 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/swap-nodes-in-pairs/
        ListNode node1=new ListNode(1,null);
        ListNode node2=new ListNode(2,null);
        ListNode node3=new ListNode(3,null);
        ListNode node4=new ListNode(4,null);
        ListNode node5=new ListNode(5,null);
        ListNode node6=new ListNode(6,null);
        node1.next=node2;
        node2.next=node3;
        node3.next=node4;
        node4.next=node5;
//        node5.next=node6;
        System.out.println(swapPairs(node1));
    }
    public static ListNode swapPairs(ListNode head) {
        //Runtime: 0 ms, faster than 100.00% of Java online submissions for Swap Nodes in Pairs.
        //Memory Usage: 41.5 MB, less than 66.66% of Java online submissions for Swap Nodes in Pairs.
//        王胖虎真厉害
        if(head==null) return null;

        ListNode dummy=new ListNode();
        ListNode pre=dummy;
        ListNode node1=head;
        ListNode node2=head.next;
        pre.next=node1;

        while(node1.next!=null){ //node2是null的话说明最后剩单独一个而不是一对
            ListNode oldnode2next=node2.next;
            pre.next=node2;
            node1.next=oldnode2next;
            node2.next=node1;
            //最后一对交换.完.以后.     oldnode2next==null
            pre = node1;
            node1 = oldnode2next; //这时候才变成null,就可以出来了 或者你最上面写while循环的条件再加上一个 && node1.next.next!=null
            if(node1==null) break;
            node2 = node1.next;

        }
        return dummy.next;
    }

}
