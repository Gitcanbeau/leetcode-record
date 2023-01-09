public class leet203 {
    public static void main(String[] args) {
//        Given the head of a linked list and an integer val,
//        remove all the nodes of the linked list that has Node.val == val, and return the new head.

//        Example 1:
//        Input: head = [1,2,6,3,4,5,6], val = 6
//        Output: [1,2,3,4,5]
//        Example 2:
//        Input: head = [], val = 1
//        Output: []
//        Example 3:
//        Input: head = [7,7,7,7], val = 7
//        Output: []
        ListNode node1=new ListNode(1,null);
        ListNode node2=new ListNode(2,null);
        ListNode node3=new ListNode(6,null);
        ListNode node4=new ListNode(3,null);
        ListNode node5=new ListNode(4,null);
        ListNode node6=new ListNode(5,null);
        ListNode node7=new ListNode(6,null);
        node1.next=node2;
        node2.next=node3;
        node3.next=node4;
        node4.next=node5;
        node5.next=node6;
        node6.next=node7;
        int target_val=6;
        ListNode listNode1 = removeElements(node1, target_val);
        System.out.println(listNode1.toString());
//Runtime: 1 ms, faster than 99.03% of Java online submissions for Remove Linked List Elements.
//Memory Usage: 49.7 MB, less than 12.67% of Java online submissions for Remove Linked List Elements.
    }

    public static ListNode removeElements(ListNode head, int target) {
        //Runtime: 3 ms, faster than 16.30% of Java online submissions for Remove Linked List Elements.
        //Memory Usage: 48.7 MB, less than 72.21% of Java online submissions for Remove Linked List Elements.
        ListNode dummy=new ListNode(0);
        dummy.next=head;
        ListNode pre=dummy;
        while(head!=null){
            if(head.val==target){
                pre.next=head.next;
                head=head.next;
            }else{
                pre=head;
                head=head.next;
            }
        }
        return dummy.next;
    }
    public static ListNode removeElements2(ListNode head, int target) {
        if(head == null){
            return null;
        }
        while(head!=null && head.val == target ){
            head = head.next;
        }
        ListNode curr = head;
        ListNode prev = null;
        while(curr != null){
            if(curr.val == target){
                prev.next = curr.next;
                curr = curr.next;
            }else{
                prev = curr;
                curr = curr.next;
            }
        }
        return head;
    }
}
