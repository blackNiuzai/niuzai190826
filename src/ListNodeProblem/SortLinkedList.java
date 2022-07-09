package ListNodeProblem;

/**
 * 链表排序
 *
 * 归并排序
 *
 */
public class SortLinkedList {

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode p1 = head;
        ListNode p2 = head.next;

        // 快慢指针法找到中间位置
        while(p2 != null && p2.next != null){
            p1 = p1.next;
            p2 = p2.next.next;
        }

        ListNode mid = p1.next;
        p1.next = null;
        ListNode first = head;
        ListNode part1 = sortList(first);
        ListNode part2 = sortList(mid);
        return mergeTwoList(part1, part2);
    }

    private ListNode mergeTwoList(ListNode l1, ListNode l2){
        if (l1==null) return l2;
        if (l2==null) return l1;

        ListNode resHead = new ListNode();
        ListNode tail = resHead;

        while(l1 != null && l2 != null){
            int l1First = l1.val;
            int l2First = l2.val;
            if (l1First <= l2First) {
                tail.next = new ListNode(l1First);
                tail = tail.next;
                l1 =l1.next;
            }else {
                tail.next = new ListNode(l2First);
                tail = tail.next;
                l2 = l2.next;
            }
        }

        tail.next = l1!=null? l1:l2;


        return resHead.next;
    }




}
