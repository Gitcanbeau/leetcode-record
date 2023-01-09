public class leet1721 {
    public static void main(String[] args) {
//        You are given the head of a linked list, and an integer k.
//        Return the head of the linked list after swapping the values of the kth node from the beginning
//        and the kth node from the end (the list is 1-indexed).
//        Example 1:
//        Input: head = [1,2,3,4,5], k = 2
//        Output: [1,4,3,2,5]
//        Example 2:
//        Input: head = [7,9,6,6,7,8,3,0,9,5], k = 5
//        Output: [7,9,6,6,8,7,3,0,9,5]
        ListNode node1 = new ListNode(1, null);
        ListNode node2 = new ListNode(2, null);
        ListNode node3 = new ListNode(3, null);
        ListNode node4 = new ListNode(4, null);
        ListNode node5 = new ListNode(5, null);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
//        ListNode reverse = reverse(node1);
//        System.out.println(reverse.toString());
        ListNode listNode = swapNodes(node1,2);
        System.out.println(listNode.toString());
        //Runtime: 16 ms, faster than 10.65% of Java online submissions for Swapping Nodes in a Linked List.
        //Memory Usage: 182.4 MB, less than 40.28% of Java online submissions for Swapping Nodes in a Linked List.
    }
    public static ListNode swapNodes(ListNode head, int k) {
        if(head==null){
            return null;
        }
        if(head.next==null){
            return head;
        }
        int count1=1;
        int tempval1 = head.val;
        ListNode temp1=head;
        while(count1!=k){
            temp1=temp1.next;
            tempval1=temp1.val;
            count1++;
        }


        ListNode newhead=reverse(head);
        int count2=1;
        int tempval2=newhead.val;
        ListNode temp2=newhead;
        while(count2!=k){
            temp2=temp2.next;
            tempval2=temp2.val;
            count2++;
        }
        temp2.val=tempval1;


        ListNode updatedhead=reverse(newhead);
        int count3=1;
        ListNode temp3=updatedhead;
        while(count3!=k){
            temp3=temp3.next;
            count3++;
        }
        temp3.val=tempval2;
        return updatedhead;
    }
    public static ListNode reverse(ListNode head){
        ListNode dummy=new ListNode();
        dummy.next=head;
        ListNode temp=head;
        while(head.next!=null){
            ListNode dummynext=dummy.next;
            ListNode headnext=head.next;
            dummy.next=headnext;
            head.next=headnext.next;
            headnext.next=dummynext;
        }
        return dummy.next;
    }
}
