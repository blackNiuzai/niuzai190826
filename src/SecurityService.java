
import java.time.Instant;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

/**
 * 你的系统登录接口正在被刷，需要建立一个防刷系统。
 *
 * 根据客户端ip，30分钟内，只能请求30次，某个ip超过限制，将锁定30分钟
 *
 *
 *  可以用intern()方法，强制相同的ip指向同一个对象，便可以起到锁的作用
 *
 */

public class SecurityService {

    static final long halfHourSeconds = 30 * 60;

    static final int timeLimit = 30;

    // 记录最近请求的时间， 最多存储30次
    static Map<String, LinkedList<Long>> requestTimeMap = new HashMap<>();

    static Map<String, Long> ipLockTime = new HashMap<>();

    public static void main(String[] args) {

        IntStream.range(1, 50).forEach(value -> {
            boolean res = checkAttackV2("192.168.0.0.1");
            System.out.println(Boolean.toString(res) + value);
        });

        IntStream.range(1, 50).forEach(value -> {
            boolean res = checkAttackV2("192.168.0.0.2");
            System.out.println(Boolean.toString(res) + value);
        });
        System.out.println("ok");


    }

    public static boolean checkAttack(String clientIp){
        // 判断是否已经30次
        synchronized (clientIp.intern()){

        }
        return true;
    }


    public static boolean checkAttackV2(String clientIp){

        Boolean res = SyncProcessByKey.SyncProcess(clientIp, () -> {
            if (ipLockTime.containsKey(clientIp)) return false;
            LinkedList<Long> requestTimeList = requestTimeMap.computeIfAbsent(clientIp, k -> new LinkedList<>());
            Long nowStamp = Instant.now().getEpochSecond();
            if (requestTimeList.size() == timeLimit) {
                requestTimeList.poll();
                requestTimeList.addLast(nowStamp);
                Long firstTime = requestTimeList.peek();
                if (nowStamp - firstTime < halfHourSeconds) {
                    ipLockTime.put(clientIp, nowStamp);
                    requestTimeMap.remove(clientIp);
                    return false;
                }
            } else {
                requestTimeList.addLast(nowStamp);
                ipLockTime.remove(clientIp);
            }
            return true;
        });

        return res;
    }

}
