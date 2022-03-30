package ListNodeProblem;

public class KGroupReverse {

    public static void main(String[] args) {
        ListNode head = new ListNode(1,
                new ListNode(2,
                        new ListNode(3,
                                new ListNode(4,
                                        new ListNode(5)))));

        KGroupReverse bean = new KGroupReverse();
        bean.reverseKGroup(head, 3);

    }

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummyHead =  new ListNode();
        dummyHead.next = head;
        int length = 0;
        while (head!=null){
            head = head.next;
            length ++;
        }

        head = dummyHead.next;

        ListNode prev = dummyHead, curr = head;

        for(int i = 1; i <= length / k; i++){
            for(int j = 1; j <= k - 1 ; j ++){
                ListNode next = curr.next;
                curr.next = next.next;
                next.next = prev.next;
                prev.next = next;
            }
            prev = curr;
            curr = prev.next;

        }
        return dummyHead.next;
    }


}
