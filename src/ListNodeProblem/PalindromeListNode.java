package ListNodeProblem;

import java.util.ArrayList;
import java.util.List;

/**
 * 回文链表
 */
public class PalindromeListNode {

    public boolean isPalindrome(ListNode head){
        if(head == null || head.next==null) return true;

        List<Integer> allValue = new ArrayList<>();

        while (head!=null){
            allValue.add(head.val);
            head = head.next;
        }

        int start = 0, end = allValue.size()-1;

        while(start<=end){
            if (allValue.get(start).equals(allValue.get(end))){
                start++;
                end--;
            }else {
                return false;
            }
        }

        return true;

    }

}
