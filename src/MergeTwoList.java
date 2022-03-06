public class MergeTwoList {

    // 88 题
    public void merge(int[] nums1, int m, int[] nums2, int n){
        int lastIndex =m + n - 1;

        int nums1LastIndex = m - 1;
        int nums2LastIndex = n - 1;

        for (int j = lastIndex; j >=0  ; j--) {
            if (nums1LastIndex < 0){
                // nums2 肯定没走完
                nums1[j] = nums2[nums2LastIndex];
                nums2LastIndex--;
            } else if (nums2LastIndex < 0){
                // nums1 肯定没走完
                nums1[j] = nums1[nums1LastIndex];
                nums1LastIndex--;
            }else {
                // 两个数组都还有数字
                if (nums1[nums1LastIndex] >= nums2[nums2LastIndex]){
                    nums1[j] = nums1[nums1LastIndex];
                    nums1LastIndex --;
                }else {
                    nums1[j] = nums2[nums2LastIndex];
                    nums2LastIndex--;
                }
            }
        }

    }

    // 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
    public ListNode mergeTwoList(ListNode list1, ListNode list2){
        ListNode dummyHead = new ListNode();
        ListNode tail = dummyHead;
        while (list1 != null || list2 != null){
            if (list1 == null){
                tail.next = list2;
                break;
            }
            if (list2 == null){
                tail.next = list1;
                break;
            }
            if (list1.val <= list2.val){
                tail.next = new ListNode(list1.val);
                tail = tail.next;
                list1 = list1.next;
            }else {
                tail.next = new ListNode(list2.val);
                tail = tail.next;
                list2 = list2.next;
            }
        }

        return dummyHead.next;
    }







}
