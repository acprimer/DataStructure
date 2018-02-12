package multithread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by yaodh on 2018/2/12.
 */
public class AtomicIntegerDemo {
    static AtomicInteger atomInt = new AtomicInteger();
    static int n = 0;

    public static class AddThread implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
//                atomInt.incrementAndGet();
                add();
            }
        }
    }

    private static synchronized void add() {
        n++;
    }

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread[] ts = new Thread[10];
        for (int i = 0; i < 10; i++) {
            ts[i] = new Thread(new AddThread());
        }
        for (int i = 0; i < 10; i++) ts[i].start();
        for (int i = 0; i < 10; i++) ts[i].join();
        System.out.println(atomInt);
        System.out.println(n);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
