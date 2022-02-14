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


}
