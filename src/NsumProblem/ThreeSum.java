package NsumProblem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class ThreeSum {

    public static void main(String[] args) {

        ThreeSum threeSum = new ThreeSum();
        List<List<Integer>> lists = threeSum.threeSum(new int[]{0,0,0}, 0);
        System.out.println(lists);
    }

    public List<List<Integer>> threeSum(int[] nums, int target) {
        // 先排序
        List<List<Integer>> threeSumResults = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            int twoSumTarget = target - nums[i];
            List<List<Integer>> twoSumResults = twoSum(nums, i + 1, nums.length - 1, twoSumTarget);
            if (twoSumResults.size() == 0) continue;
            for (List<Integer> twoSumResult : twoSumResults) {
                threeSumResults.add(Arrays.asList(twoSumResult.get(0), twoSumResult.get(1), nums[i]));
            }
            while(i < nums.length -2 && nums[i+1] == nums[i]){
                i++;
            }
        }
        return threeSumResults;
    }



    public static List<List<Integer>> twoSum(int[] nums,int start, int end,  int target){
        // 双指针
        List<List<Integer>> twoSumResults = new ArrayList<>();
        while (start < end){
            int sum = nums[start] + nums[end];
            if (sum > target){
                end --;
            }else if (sum < target){
                start++;
            }else {
                twoSumResults.add(Arrays.asList(nums[start], nums[end]));
                while(start + 1 <= end && nums[start] == nums[start + 1]){
                    start ++;
                }
                start++;
                while(end - 1 >= start && nums[end] == nums[end -1]){
                    end --;
                }
                end --;
            }

        }
        return twoSumResults;
    }


}
