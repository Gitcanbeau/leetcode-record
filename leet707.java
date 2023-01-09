public class leet707 {

    public ListNode dummy;
    public int size;
    public leet707() {
        size=0;
        dummy=new ListNode();
    }

    public int get(int index) {
        if(index<0 || index>size) return -1;

        ListNode curr=dummy;
        for(int i=0; i<=index;i++){
            curr=curr.next;
        }
        return curr.val;
    }

    public void addAtHead(int val) {
        addAtIndex(0,val);
    }

    public void addAtTail(int val) {
        addAtIndex(size,val);
    }

    public void addAtIndex(int index, int val) {
        if (index > size) {
            return;
        }
        if (index < 0) {
            index = 0;
        }
        size++;
        //找到要插入节点的前驱
        ListNode pred = dummy;
        for (int i = 0; i < index; i++) {
            pred = pred.next;
        }
        ListNode toAdd = new ListNode(val);
        toAdd.next = pred.next;
        pred.next = toAdd;
    }

    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) {
            return;
        }
        size--;
        if (index == 0) {
            dummy = dummy.next;
            return;
        }
        ListNode pred = dummy;
        for (int i = 0; i < index ; i++) {
            pred = pred.next;
        }
        pred.next = pred.next.next;
    }
    public static void main(String[] args) {
        //https://leetcode.com/problems/design-linked-list/
        leet707 mylinkedlist=new leet707();
        mylinkedlist.addAtHead(1);
        mylinkedlist.addAtTail(3);
        mylinkedlist.addAtIndex(0,2);
        System.out.println(mylinkedlist.get(1));
        mylinkedlist.deleteAtIndex(1);
        System.out.println(mylinkedlist.get(1));
    }
}
