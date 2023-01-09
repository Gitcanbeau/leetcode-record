public class leet206 {
    public static void main(String[] args) {
//        Given the head of a singly linked list, reverse the list, and return the reversed list.
//        Example 1:
//        Input: head = [1,2,3,4,5]
//        Output: [5,4,3,2,1]
//        Example 2:
//        Input: head = [1,2]
//        Output: [2,1]
//        Example 3:
//        Input: head = []
//        Output: []
        ListNode node1 = new ListNode(1, null);
        ListNode node2 = new ListNode(2, null);
        ListNode node3 = new ListNode(3, null);
        ListNode node4 = new ListNode(4, null);
        ListNode node5 = new ListNode(5, null);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        ListNode listNode1 = reverseList(node1);
        System.out.println(listNode1.toString());
        //Runtime: 0 ms, faster than 100.00% of Java online submissions for Reverse Linked List.
        //Memory Usage: 42.6 MB, less than 73.16% of Java online submissions for Reverse Linked List.
    }

    public static ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode dummy = new ListNode();
        dummy.next = head;

        while (head.next != null) {
            ListNode dummynext=dummy.next;//存dummy后点
            ListNode headnext=head.next;//存头点后面的点
            //都给我用存的点操作
            dummy.next=headnext;
            head.next=headnext.next;
            headnext.next=dummynext;
            //这里最重要，我前面的步骤都写对了，这个2指向1的地方写错了，你好多东西都动态变化了，这里就用存储的node
        }
        return dummy.next;
    }
}
