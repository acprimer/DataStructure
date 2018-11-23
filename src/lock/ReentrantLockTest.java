package lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by yaodh on 2018/3/27.
 */
public class ReentrantLockTest {
    private ReentrantLock lock = new ReentrantLock();

    /**
     * 使用tryLock()保证同时刻只执行一次
     */
    private void clean() {
        if (lock.tryLock()) {
            System.out.println("acquire lock");
            try {
                // tryLock()之后就不用再次lock()了
//                lock.lock();
                System.out.println("start clean");
                Thread.sleep(3000);
                System.out.println("hold count " + lock.getHoldCount());
                System.out.println("queue count " + lock.getQueueLength());
                System.out.println("clean done");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        } else {
            System.out.println("not acquire lock");
        }
    }

    private void queue() {
        try {
            System.out.println(Thread.currentThread().getId() + " come to lock");
            lock.lock();
            System.out.println(Thread.currentThread().getId() + " acquire lock");
        } finally {
            lock.unlock();
        }
    }

    class CleanTask extends Thread {
        @Override
        public void run() {
            clean();
//            queue();
        }
    }

    public static void main(String[] args) {
        ReentrantLockTest test = new ReentrantLockTest();
//        for (int i = 0; i < 10; i++) {
//            Thread t1 = test.new CleanTask();
//            System.out.println(t1.getId());
//            t1.start();
//        }
        Thread t1 = test.new CleanTask();
        System.out.println(t1.getId());
        t1.start();
        Thread t2 = test.new CleanTask();
        System.out.println(t2.getId());
        t2.start();
    }
}
