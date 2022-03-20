import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 有效括号
 *
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']'的字符串 s ，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class StackTest1 {


    public boolean isValid(String s) {
        int length = s.length();
        if (length % 2 == 1) return false;

        Map<Character, Character> matchMap = new HashMap<>();
        matchMap.put(')', '(');
        matchMap.put('}', '{');
        matchMap.put(']', '[');

        Deque<Character> stack = new LinkedList<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < length; i++) {
            Character ch = chars[i];
            if(!matchMap.containsKey(ch)){
                stack.addFirst(ch);
            }else {
                if(stack.size() > 0 && stack.getFirst() == matchMap.get(ch)){
                    stack.removeFirst();
                }else {
                    stack.addFirst(ch);
                }
            }
        }
        return stack.size() == 0;


    }










}