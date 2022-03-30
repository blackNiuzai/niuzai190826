package ListNodeProblem;

public class CycleListNode {

    public ListNode detectCycle(ListNode head) {

        if (head == null || head.next == null){
            return null;
        }

        ListNode slow = head;
        ListNode fast = head;

        boolean cycleFlag = false;
        while(fast!=null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast){
                cycleFlag = true;
                break;
            }
        }
        if (!cycleFlag) return null;

        fast = head;
        while(fast != slow){
            fast = fast.next;
            slow = slow.next;
        }

        return slow;

    }
}
