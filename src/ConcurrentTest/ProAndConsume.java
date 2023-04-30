package ConcurrentTest;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProAndConsume {
    public static final Queue<Msg> queue = new LinkedList<>();
    public static final Lock lock = new ReentrantLock();

    private static final Condition un_empty = lock.newCondition();
    private static final Condition un_full = lock.newCondition();

    public static void main(String[] args) {

        for (int i = 0; i < 3; i++) {
            new Thread(new Provider(queue, un_empty, un_full, lock),i +"号生产者").start();
            new Thread(new Consumer(queue, un_empty, un_full, lock),i +"号消费者").start();
        }

    }
}


class Msg{}


class Consumer implements Runnable{
    private Queue<Msg> queue;
    private Condition un_empty;
    private Condition un_full;
    private Lock lock;

    public Consumer(Queue<Msg> queue, Condition un_empty, Condition un_full, Lock lock){
        this.queue = queue;
        this.un_empty = un_empty;
        this.un_full = un_full;
        this.lock = lock;
    }


    @Override
    public void run() {
        while(true){
            doConsume();
        }
    }

    private void doConsume(){
        try {
            lock.lock();
            while (queue.size() == 0){
                un_empty.await();
            }
            queue.poll();
            System.out.println(Thread.currentThread().getName() + "执行消费");

            un_full.signal();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}


class Provider implements Runnable{
    private Queue<Msg> queue;
    private Condition un_empty;
    private Condition un_full;
    private Lock lock;

    public Provider(Queue<Msg> queue, Condition un_empty, Condition un_full, Lock lock){
        this.queue = queue;
        this.un_empty = un_empty;
        this.un_full = un_full;
        this.lock = lock;
    }


    @Override
    public void run() {
        while(true){
            doProvide();
        }
    }

    private void doProvide(){
        try {
            lock.lock();
            while (queue.size() == 2){
                System.out.println("队列满无法添加任务");
                un_full.await();
            }
            queue.offer(new Msg());
            System.out.println(Thread.currentThread().getName() + "执行添加");

            un_empty.signal();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}





