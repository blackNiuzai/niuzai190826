
import java.time.Instant;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

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

    static ConcurrentHashMap<String, ReentrantLock> mutexKey = new  ConcurrentHashMap<>();

    static Map<String, Long> ipLockTime = new HashMap<>();


    public static boolean checkAttack(String clientIp){
        // 判断是否已经30次
        synchronized (clientIp.intern()){

        }
        return true;
    }


    public static boolean checkAttackV2(String clientIp){
        // 循环直到取得正确的锁
        ReentrantLock keyObject = null;
        ReentrantLock keyObjectInMap;
        do{
            if(keyObject != null){ keyObject.unlock();}
            keyObject = mutexKey.computeIfAbsent(clientIp, k -> new ReentrantLock());
            keyObject.lock();
            keyObjectInMap = mutexKey.get(clientIp);
        }while ( keyObjectInMap == null ||  keyObjectInMap != keyObject);

        try{
            LinkedList<Long> requestTimeList = requestTimeMap.putIfAbsent(clientIp, new LinkedList<>());
            Long nowStamp = Instant.now().getEpochSecond();
            if (requestTimeList.size() == 30){
                requestTimeList.poll();
                requestTimeList.addLast(nowStamp);
                Long firstTime = requestTimeList.peek();
                if (nowStamp - firstTime < halfHourSeconds){
                    ipLockTime.put(clientIp, nowStamp);
                    requestTimeMap.remove(clientIp);
                    return false;
                }
            }else {
                requestTimeList.addLast(nowStamp);
                ipLockTime.remove(clientIp);
            }

        }finally {
            if (!keyObject.hasQueuedThreads()){
                mutexKey.remove(clientIp);
            }
            keyObject.unlock();
        }

        return true;
    }





}
