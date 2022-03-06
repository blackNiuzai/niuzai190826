package BinarySearchTest;

/**
 * 整数数组 nums 按升序排列，数组中的值 互不相同 。
 * <p>
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
 * <p>
 * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回-1。
 */
public class BinarySearch {

    public static void main(String[] args) {
        BinarySearch instance = new BinarySearch();
        int res = instance.search(new int[]{3,1}, 1);
        System.out.println(res);
    }


    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int midNum = nums[mid];
            if (midNum == target) return mid;

            if (midNum >= nums[left]){
                //左边有序
                if (target >= nums[left] && target <= midNum){
                    right = mid;
                }else {
                    //左边有序但是并不在左边
                    left = mid + 1;
                }
            }else {
                // 右边有序
                if(target >= midNum && target <= nums[right]){
                    left = mid;
                }else {
                    right = mid - 1;
                }
            }
        }
        return -1;

    }
}
