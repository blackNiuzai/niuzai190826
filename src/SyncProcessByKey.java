import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class SyncProcessByKey {

    static ConcurrentHashMap<String, ReentrantLock> mutexKey = new  ConcurrentHashMap<>();


    public static <V> V SyncProcess(String key, Callable<V> statement){
        V processResult = null;
        // 循环直到取得正确的锁
        ReentrantLock keyObject = null;
        ReentrantLock keyObjectInMap;
        do{
            if(keyObject != null){ keyObject.unlock();}
            keyObject = mutexKey.computeIfAbsent(key, k -> new ReentrantLock());
            keyObject.lock();
            keyObjectInMap = mutexKey.get(key);
        }while ( keyObjectInMap == null ||  keyObjectInMap != keyObject);

        try{
            processResult =  statement.call();
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            if (!keyObject.hasQueuedThreads()){
                mutexKey.remove(key);
            }
            keyObject.unlock();
        }
        return processResult;
    }

}
