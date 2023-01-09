public class leet19 {
    public static void main(String[] args) {
//Given the head of a linked list, remove the nth node from the end of the list and return its head.
//Example 1:
//Input: head = [1,2,3,4,5], n = 2
//Output: [1,2,3,5]
//Example 2:
//Input: head = [1], n = 1
//Output: []
//Example 3:
//Input: head = [1,2], n = 1
//Output: [1]
        ListNode node1 = new ListNode(1, null);
        ListNode node2 = new ListNode(2, null);
        ListNode node3 = new ListNode(3, null);
        ListNode node4 = new ListNode(4, null);
        ListNode node5 = new ListNode(5, null);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
//        ListNode listNode = removeNthFromEnd(node1, 2);
//        System.out.println(listNode.toString());
        System.out.println(removeNthFromEnd2(node1,2));
        //Runtime: 1 ms, faster than 72.10% of Java online submissions for Remove Nth Node From End of List.
        //Memory Usage: 42.7 MB, less than 16.54% of Java online submissions for Remove Nth Node From End of List.
    }
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if(head==null){
            return null;
        }
        if(head.next==null&n==1){
            return null;
        }//安全检查不知道写？

        int count=1;
        ListNode reversedhead=reverse(head);
        ListNode dummy1 = new ListNode(0);
        dummy1.next = reversedhead;
        ListNode temp=dummy1;
        while(temp!=null&&temp.next!=null){
            if(count<n){
                temp=temp.next;
                count++;
            }else{
                temp.next=temp.next.next;
                break;//干完活了就出来，别在循环里面没完没了，break不会写是不是
            }
        }
        if(count<n) return null;
        ListNode newhead= reverse(dummy1.next);
        return newhead;
    }

    public static ListNode reverse(ListNode head) {
        ListNode dummy1 = new ListNode(0);
        dummy1.next = head;
        while (head.next != null) {
            ListNode dummynext = dummy1.next;
            ListNode headnext = head.next;
            dummy1.next = headnext;
            head.next = headnext.next;
            headnext.next = dummynext;
        }
        return dummy1.next;
    }


    public static ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode slow = dummy;
        ListNode fast = dummy;
        while (n > 0) {
            fast = fast.next;
            n--;
        }
        // 记住 待删除节点slow 的上一节点
        ListNode prev = null;
        while (fast != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next;
        }
        // 上一节点的next指针绕过 待删除节点slow 直接指向slow的下一节点
        prev.next = slow.next;
        // 释放 待删除节点slow 的next指针, 这句删掉也能AC
        slow.next = null;

        return dummy.next;
    }
}
