import java.util.*;

public class SlideWindow {

    public static void main(String[] args) {
        SlideWindow instance = new SlideWindow();
        String s = "abacbabc";
        String p = "abc";
        List<Integer> anagrams = instance.findAnagrams(s, p);
        Map<Character, Integer> window = new HashMap<>();

        for (Map.Entry<Character, Integer> entry : window.entrySet()) {
            entry.getKey();
        }
        System.out.println(anagrams);

    }

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();

        char[] chars = p.toCharArray();

        if (p.length() > s.length()){
            return res;
        }

        Map<Character, Integer> originWindow = new HashMap<>();
        for(char c: chars){
            originWindow.put(c, originWindow.getOrDefault(c, 0) + 1);
        }

        int left = 0;
        int right = p.length() - 1;

        while(right < s.length()){
            boolean flag = check(s, left, right, originWindow);
            if (flag) {
                res.add(left);
            }
            left ++;
            right ++;
        }
        return res;


    }


    private boolean check(String s, int left, int right, Map<Character, Integer> originWindow){
        Map<Character, Integer> window = new HashMap<>();
        char[] chars = s.toCharArray();
        for(int i = left; i <= right; i++){
            window.put(chars[i], window.getOrDefault(chars[i], 0) + 1);
        }
        boolean equalFlag = true;
        for (Map.Entry<Character, Integer> entry : originWindow.entrySet()) {
            Character key = entry.getKey();
            Integer value = entry.getValue();
            if (!window.containsKey(key)){
                equalFlag = false;
                break;
            }
            if(window.get(key).intValue() != value.intValue()){
                equalFlag = false;
                break;
            }
        }

        return equalFlag;

    }



}
