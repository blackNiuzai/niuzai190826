package NsumProblem;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {


    public static void main(String[] args) {
        TwoSum sum = new TwoSum();

        int[] testNums =new int[]{1,2,3};
        int target = 4;
        int[] res = sum.twoSum(testNums, target);

        for (int re : res) {
            System.out.println(re);
        }

    }



    public int[] twoSum(int[] nums, int target){
        Map<Integer, Integer> addMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (addMap.containsKey(target-nums[i])){
                return new int[]{addMap.get(target-nums[i]), i};
            }
            addMap.put(nums[i], i);
        }
        return new int[0];
    }




    public int[] twoSum2(int []nums, int target){
        Map<Integer, Integer> addMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if(addMap.containsKey(target - nums[i])){
                return new int[]{addMap.get((target-nums[i])), i};
            }
            addMap.put(nums[i], i);
        }

        return new int[0];

    }


}
