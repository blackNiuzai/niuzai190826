package ConcurrentTest;

import java.util.concurrent.locks.ReentrantLock;

public class PrintABC {

    static int i = 1;


    public static void main(String[] args) {

        Object o = new Object();

        Thread threadA = new Thread(()->{
            while(i <= 100){
                System.out.println(Thread.currentThread().getName() + "判断为" + i);
                synchronized (o){
                    try{
                        while(i % 3 != 0){
                            o.wait();
                            if (i > 100) break;
                        }

                        if (i <= 100){
                            System.out.println("线程a print " + i);
                            i++;
                            o.notifyAll();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        threadA.setName("线程a");
        threadA.start();

        Thread threadB = new Thread(()->{
            while(i <= 100){
                System.out.println(Thread.currentThread().getName() + "判断为" + i);
                synchronized (o){
                    try{
                        while(i % 7 != 0){
                            o.wait();
                            if (i > 100) break;
                        }

                        if (i <= 100){
                            System.out.println("线程b print " + i);
                            i++;
                            o.notifyAll();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        threadA.setName("线程b");
        threadB.start();

        Thread threadC = new Thread(()->{
            while(i<=100){
                System.out.println(Thread.currentThread().getName() + "判断为" + i);
                synchronized (o){
                    try{
                        while(i % 7 == 0 || i % 3 == 0){
                            o.wait();
                            if (i > 100) break;
                        }

                        if (i <= 100){
                            System.out.println("线程c print " + i);
                            i++;
                            o.notifyAll();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        threadA.setName("线程c");
        threadC.start();










    }


}
