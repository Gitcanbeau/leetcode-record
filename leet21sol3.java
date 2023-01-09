public class leet21sol3 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/merge-two-sorted-lists/
//        You are given the heads of two sorted linked lists list1 and list2.
//         Merge the two lists in a one sorted list.
//         The list should be made by splicing together the nodes of the first two lists.
//         Return the head of the merged linked list.
//        Example 1:
//        Input: list1 = [1,2,4], list2 = [1,3,4]
//        Output: [1,1,2,3,4,4]
//        Example 2:
//        Input: list1 = [], list2 = []
//        Output: []
//        Example 3:
//        Input: list1 = [], list2 = [0]
//        Output: [0]
        ListNode node1a = new ListNode(1, null);
        ListNode node2a = new ListNode(2, null);
        ListNode node3a = new ListNode(4, null);
        node1a.next = node2a;
        node2a.next = node3a;
        ListNode node1b = new ListNode(1, null);
        ListNode node2b = new ListNode(3, null);
        ListNode node3b = new ListNode(4, null);
        node1b.next = node2b;
        node2b.next = node3b;
        ListNode head = mergeTwoLists(node1a, node1b);
        System.out.println(head.toString());
        //time limit exceeded
    }

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null && list2 == null) {
            return null;
        }
        if (list1 == null & list2 != null) {
            return list2;
        }
        if (list1 != null & list2 == null) {
            return list1;
        }
        ListNode curr1 = list1;
        ListNode curr2 = list2;
        ListNode dummy = new ListNode(0);
        ListNode temp = dummy;//注意保存临时变量
        while (curr1.next!= null & curr2.next!= null) {
            if (curr1.val <= curr2.val) {
                ListNode node = new ListNode(curr1.val);
                //注意新建一个linkedlist否则你list1在某处断开以后就找不到.next了，就是说你合并完之前都别改变list1和list2
                temp.next = node;
                temp = temp.next;
                curr1 = curr1.next;
            } else {
                ListNode node = new ListNode(curr2.val);
                temp.next = node;
                temp = temp.next;
                curr2 = curr2.next;
            }
        }

        if (curr1.next == null) {
            while(curr2.next!=null){
            ListNode node = new ListNode(curr2.val);
            temp.next = node;
            }
        }
        if (curr2.next == null) {
            while(curr1.next!=null) {
                ListNode node = new ListNode(curr1.val);
                temp.next = node;
            }
        }
        return dummy.next;
    }
}
