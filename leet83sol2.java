public class leet83sol2 {
    public static void main(String[] args) {
//        Given the head of a sorted linked list,
//        delete all duplicates such that each element appears only once.
//        Return the linked list sorted as well.
//        Example 1:
//        Input: head = [1,1,2]
//        Output: [1,2]
//        Example 2:
//        Input: head = [1,1,2,3,3]
//        Output: [1,2,3]
        ListNode node1 = new ListNode(1, null);
        ListNode node2 = new ListNode(1, null);
        ListNode node3 = new ListNode(2, null);
        ListNode node4 = new ListNode(3, null);
        ListNode node5 = new ListNode(4, null);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        ListNode listNode = deleteDuplicates(node1);
        System.out.println(listNode.toString());
        //写的不对
    }

    public static ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(0);
        ListNode temp = head;
        dummy.next = head;

        while (temp != null) {
            if (temp.val == temp.next.val) {
                temp = temp.next;
            }
            if (temp.val != temp.next.val) {
                ListNode node = new ListNode(temp.next.val);
                ListNode pre =new ListNode();
                pre= node;
                temp = temp.next;

            }
        }
        return dummy.next;
    }
}
