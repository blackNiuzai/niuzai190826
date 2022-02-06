import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestSubString {


    public int lengthOfLongestSubString(String s){
        if (s.length() == 0) return 0;
        // 窗口移动法 使用Map记录当前窗口中的字母
        int left = 0;
        Map<Character,Integer> indexMap = new HashMap<>();
        int currentMax = 0;
        for (int right = 0; right<s.length(); right++){
            if (indexMap.containsKey(s.charAt(right))){
                int  oldLeft = left;
                left = indexMap.get(s.charAt(right)) + 1;
                // 将left不断移动到当前值并且删除数值
                while(oldLeft < left){
                    indexMap.remove(s.charAt(oldLeft));
                    oldLeft ++;
                }
                indexMap.put(s.charAt(right), right);
            }else {
                indexMap.put(s.charAt(right), right);
            }
            currentMax = Math.max(currentMax, right - left + 1);
        }

        return currentMax;
    }


}
