public class leet83 {
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
        //Runtime: 1 ms, faster than 79.52% of Java online submissions for Remove Duplicates from Sorted List.
        //Memory Usage: 44.1 MB, less than 61.41% of Java online submissions for Remove Duplicates from Sorted List.
    }
    public static ListNode deleteDuplicates(ListNode head) {
        ListNode temp = head;

        if(head == null) return null;
        while(temp.next != null && temp != null){
            if(temp.val == temp.next.val){
                temp.next = temp.next.next;
            }else{
                temp = temp.next;
            }
        }
        return head;
    }
}
