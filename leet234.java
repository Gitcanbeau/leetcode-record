public class leet234 {
    public static void main(String[] args) {
//Given the head of a singly linked list, return true if it is a palindrome回文 or false otherwise.
//Example 1:
//Input: head = [1,2,2,1]
//Output: true
//Example 2:
//Input: head = [1,2]
//Output: false
        ListNode node1 = new ListNode(1, null);
        ListNode node2 = new ListNode(2, null);
        ListNode node3 = new ListNode(2, null);
        ListNode node4 = new ListNode(1, null);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        boolean palindrome = isPalindrome(node1);
        System.out.println(palindrome);
        //Runtime: 5 ms, faster than 90.27% of Java online submissions for Palindrome Linked List.
        //Memory Usage: 97.4 MB, less than 63.51% of Java online submissions for Palindrome Linked List.
    }
    public static boolean isPalindrome(ListNode head) {
        if(head==null){
            return true;
        }
        ListNode mid=getMid(head);
        ListNode midNext=reverse(mid.next);
        while(midNext!=null){ //不管奇数偶数个，我新翻转的就是<=
            if(head.val== midNext.val){
                head=head.next;
                midNext=midNext.next;
            }else{
                return false;
            }
        }
        return true;
    }

    public static ListNode getMid(ListNode head){
        ListNode dummy=new ListNode(0);
        dummy.next=head;
        ListNode fast=dummy;//找中点通常从dummy开始，偶数个找到的是中间偏前的，奇数个找的就是正中间的
        ListNode slow=dummy;
        while(fast!=null&&fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        return slow;
    }

    public static ListNode reverse(ListNode head){
        ListNode pre=null;
        while(head!=null){
            ListNode temp= head.next;
            head.next=pre;
            pre=head;
            head=temp;
        }
        return pre;
    }
}
