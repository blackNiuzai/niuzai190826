import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class WeakTest {

    public static void main(String[] args) throws InterruptedException {

        Map<WeakReference<Integer>, WeakReference<Integer>> map = new HashMap<>(8);
        // 注意这里~
        WeakReference<Integer> key = new WeakReference<>(666);
        WeakReference<Integer> value = new WeakReference<>(777);
        map.put(key,value);
        System.out.println("put success");
        Thread.sleep(1000);
        System.gc();
        System.out.println("get " + map.get(key).get());





//        Map<WeakReference<Integer>, WeakReference<Integer>> map = new HashMap<>(8);
//        WeakReference<Integer> key = new WeakReference<>(666);
//        WeakReference<Integer> value = new WeakReference<>(777);
//        map.put(key,value);
//        System.out.println("put success");
//        Thread.sleep(1000);
//        System.gc();
//        System.out.println("get " + map.get(key).get());

    }
}
