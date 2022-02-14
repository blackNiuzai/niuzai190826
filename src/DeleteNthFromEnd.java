



public class DeleteNthFromEnd {


    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) return null;

        ListNode dummyHead = new ListNode();
        dummyHead.next = head;
        ListNode curr = dummyHead;
        ListNode prev = dummyHead;
        for(int i =0; i<n; i++){
            curr = curr.next;
        }

        while(curr!=null && curr.next !=null){
            curr =curr.next;
            prev = prev.next;
        }

        prev.next = prev.next.next;

        return dummyHead.next;

    }







}
