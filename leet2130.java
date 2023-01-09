import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class leet2130 {
    public static void main(String[] args) {
        //In a linked list of size n, where n is even, the ith node (0-indexed) of the linked list is known as the twin of the (n-1-i)th node, if 0 <= i <= (n / 2) - 1.
        //For example, if n = 4, then node 0 is the twin of node 3, and node 1 is the twin of node 2. These are the only nodes with twins for n = 4.
        //The twin sum is defined as the sum of a node and its twin.
        //Given the head of a linked list with even length, return the maximum twin sum of the linked list.
        //Example 1:
        //Input: head = [5,4,2,1]
        //Output: 6
        //Explanation:
        //Nodes 0 and 1 are the twins of nodes 3 and 2, respectively. All have twin sum = 6.
        //There are no other nodes with twins in the linked list.
        //Thus, the maximum twin sum of the linked list is 6.
        //Example 2:
        //Input: head = [4,2,2,3]
        //Output: 7
        //Explanation:
        //The nodes with twins present in this linked list are:
        //- Node 0 is the twin of node 3 having a twin sum of 4 + 3 = 7.
        //- Node 1 is the twin of node 2 having a twin sum of 2 + 2 = 4.
        //Thus, the maximum twin sum of the linked list is max(7, 4) = 7.
        //Example 3:
        //Input: head = [1,100000]
        //Output: 100001
        //Explanation:
        //There is only one node with a twin in the linked list having twin sum of 1 + 100000 = 100001.
        //Constraints:
        //The number of nodes in the list is an even integer in the range [2, 105].
        //1 <= Node.val <= 105
        ListNode node0 = new ListNode(4, null);
        ListNode node1 = new ListNode(2, null);
        ListNode node2 = new ListNode(2, null);
        ListNode node3 = new ListNode(3, null);
        node0.next = node1;
        node1.next = node2;
        node2.next = node3;
//        ListNode reverse = reverse(node0);
//        System.out.println(reverse);
        int i = pairSum(node0);
        System.out.println(i);
        //Runtime: 13 ms, faster than 49.83% of Java online submissions for Maximum Twin Sum of a Linked List.
        //Memory Usage: 114.5 MB, less than 55.92% of Java online submissions for Maximum Twin Sum of a Linked List.
    }

    public static int pairSum(ListNode head) {
        int maxsumoftwin = Integer.MIN_VALUE;
        int tempsum;
        ListNode dummy = new ListNode();
        dummy.next = head;
        //找中点
        ListNode fast = dummy;
        ListNode slow = dummy;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        //翻转
        ListNode tail = reverse(slow.next);
        //删除中后没用的东西，省点时间
        slow.next = null;
        //动态求最大
        ListNode curr1 = head;
        ListNode curr2 = tail;
        while (curr1 != null) {
            maxsumoftwin = Math.max(curr1.val + curr2.val, maxsumoftwin);
            curr1 = curr1.next;
            curr2 = curr2.next;
        }
        return maxsumoftwin;
    }

    public static ListNode reverse(ListNode head) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        while (head.next != null) {
            ListNode dummynext = dummy.next;
            ListNode headnext = head.next;
            dummy.next = headnext;
            head.next = headnext.next;
            headnext.next = dummynext;
        }
        return dummy.next;
    }

}
