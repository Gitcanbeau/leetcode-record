public class leet21sol2 {
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
    }
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if(list1 == null){
            return list2;
        }
        if(list2 == null) {
            return list1;
        }
        if(list1.val < list2.val){
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        } else{
            list2.next = mergeTwoLists(list1, list2.next);
        }
        return list2;
    }
}
