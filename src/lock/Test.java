package lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by yaodh on 2018/2/5.
 */
public class Test {
    ReentrantLock lock = new ReentrantLock();

    private void test() {
        new Thread() {
            @Override
            public void run() {
                lock.lock();
                try {
                    System.out.println("Thread 1");
                    sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                System.out.println("AA");
                lock.lock();
                System.out.println("lock");
                try {
                    System.out.println("Thread 2");
                } finally {
                    lock.unlock();
                }
            }
        }.start();
    }

    public static void main(String[] args) {
        new Test().test();
    }
}
