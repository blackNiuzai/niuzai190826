import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;


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

    ThreadLocal<Integer> threadLocal = new ThreadLocal<>();


    public static void main(String[] args) {
//        String s = "()";
//        int a  = s.toCharArray().length;
//        System.out.println(a);
//
//        char c = s.toCharArray()[0];
//        boolean valid = isValid(s);

        PriorityQueue<Integer> pq = new PriorityQueue<>((x, y) -> {
            return x-y;
        });

        pq.add(2);
        pq.add(2);
        pq.add(3);
        pq.add(1);

        pq.add(0);
        System.out.println(pq.peek());
        while(pq.size() > 0){
            System.out.println(pq.poll());
        }

        int sqrt = (int) Math.sqrt(3);

        boolean digit = Character.isDigit('2');
        new ArrayList<Integer>();

        String s = "()";
        char[] chars = s.toCharArray();
        new ArrayList<Character>();

        Set<Integer> res = new HashSet<>();
        ReentrantLock lock = new ReentrantLock();
        AtomicInteger inte = new AtomicInteger(1);
        inte.incrementAndGet();
        Map<Integer, Integer> ma=new HashMap<>();
        ma.put(1, 1);
        for(Map.Entry<Integer, Integer> entry: ma.entrySet()){
            entry.getKey();
            entry.getValue();
        }

        int[] ints = Arrays.copyOfRange(new int[]{0, 1, 2}, 0, 1);

    }


    public static boolean isValid(String s) {

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



    public void testThreadLocal() throws IOException {
        threadLocal.set(1);
        Integer integer = threadLocal.get();
        System.out.println();
        threadLocal.remove();

        ServerSocket serverSocket = new ServerSocket(9000);
        Socket accept = serverSocket.accept();
        accept.getInputStream().read(new byte[1024]);



    }






}
