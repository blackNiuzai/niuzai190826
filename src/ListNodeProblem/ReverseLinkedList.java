package ListNodeProblem;


public class ReverseLinkedList {

    public static void main(String[] args) {
        System.out.println("hello world");
    }


    public ListNode reverse(ListNode head){
       ListNode prev = null;
       ListNode curr =head;
       while (curr != null){
           ListNode next = curr.next;
           curr.next = prev;
           prev= curr;
           curr = next;
       }
       return prev;
    }


    public ListNode reverse2(ListNode head){
        //  递归法
        if (head==null || head.next ==null){
            return head;
        }
        ListNode newHead = reverse2(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }









}
