package BackTrackTest;

import java.util.*;

public class AllLetters {


    public static void main(String[] args) {
        List<String> strings = digiMap.get(2);
        AllLetters instance = new AllLetters();
        instance.letterCombinations("23");


    }

    static Map<Character, List<String>> digiMap = new HashMap<>();
    List<String> res =  new ArrayList<>();
    static{
        digiMap.put('2', new ArrayList(Arrays.asList("a","b","c")));
        digiMap.put('3', new ArrayList(Arrays.asList("d","e","f")));
        digiMap.put('4', new ArrayList(Arrays.asList("g","h","i")));
        digiMap.put('5', new ArrayList(Arrays.asList("j","k","l")));
        digiMap.put('6', new ArrayList(Arrays.asList("m","n","o")));
        digiMap.put('7', new ArrayList(Arrays.asList("p","q","r","s")));
        digiMap.put('8', new ArrayList(Arrays.asList("t","u","v")));
        digiMap.put('9', new ArrayList(Arrays.asList("w","x","y", "z")));
    }



    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() ==0) return new ArrayList<>();
        backTrack(digits, "", 0);
        return res;
    }

    private void backTrack(String digits, String track, int index){
        if(track.length() == digits.length()) {
            res.add(track);
            return;
        }
        List<String> letters = digiMap.get(digits.charAt(index));
        System.out.println(letters);
        for (int i=0; i<letters.size(); i++){
            //如果当前位置
            backTrack(digits, track + letters.get(i), index+1);
        }
    }

    private int searchLeft(int[] nums, int target){
        int left = 0;
        int right = nums.length -1;

        while(left <= right){
            int mid = left + (right - left) / 2;
            int midNum = nums[mid];

            if(mid > target){
                left = mid + 1;
            }else if (mid < target){
                right = mid - 1;
            }else{
                // 命中的话， 因为我要求的是左边界， 所以收缩右边界
                right = mid - 1;
            }
        }

        return nums[left] == target? left: -1;
    }





}
