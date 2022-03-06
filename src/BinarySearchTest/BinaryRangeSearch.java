package BinarySearchTest;

/**
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 *
 * 如果数组中不存在目标值 target，返回[-1, -1]。
 *
 */
public class BinaryRangeSearch {

    public static void main(String[] args) {
        BinaryRangeSearch instance = new BinaryRangeSearch();
        int res = instance.getLeftBound(new int[]{5,7,7,7,8,8,10}, 6);
        System.out.println(res);
    }


    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) return new int[]{-1,-1};

        int leftBound = getLeftBound(nums, target);
        int rightBound = getRightBound(nums, target);
        return new int[]{leftBound, rightBound};
    }

    private int getRightBound(int[] nums, int target) {
        int left = 0;
        int right =  nums.length - 1;
        boolean hit = false;
        while(left <= right){
            int mid = left + (right - left) / 2;
            if(target > nums[mid]){
                left = mid + 1;
            }else if (target < nums[mid]){
                right = mid - 1;
            }else {
                 hit = true;
                 left = mid + 1;
            }
        }
        return hit? left - 1: -1;

    }

    private int getLeftBound(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        boolean hit = false;
        while(left <= right){
            int mid = left + (right - left) / 2;
            if(target > nums[mid]){
                left = mid + 1;
            }else if (target < nums[mid]){
                right = mid - 1;
            }else {
                hit = true;
                right = mid - 1;
            }
        }
        return hit?right + 1: -1;

    }
}



