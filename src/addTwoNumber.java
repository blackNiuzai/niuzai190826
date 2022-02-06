public class addTwoNumber {



    public ListNode addTwoNumbers(ListNode l1, ListNode l2){
        ListNode head = null, tail =null;
        int carry = 0;
        while(l1 !=null || l2 !=null){
            int n1 = l1 != null? l1.val: 0;
            int n2 = l2 != null? l2.val: 0;
            int sum = carry + n1 + n2;
            if (head == null){
                head = tail =new ListNode(sum% 10);
            }else {
                tail.next = new ListNode(sum % 10);
                tail = tail.next;
            }
            // 如果超过10就进1,否则要归零
            if (sum >=10){
                carry = 1;
            }else {
                carry = 0;
            }

            if (l1!=null){
                l1 =l1.next;
            }

            if(l2!=null){
                l2 = l2.next;
            }
        }

        if (carry>0){
            tail.next = new ListNode(carry);
        }
        return head;
    }



}
