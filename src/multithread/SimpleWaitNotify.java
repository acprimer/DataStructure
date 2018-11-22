package multithread;

/**
 * Created by yaodh on 2018/3/29.
 */
public class SimpleWaitNotify {
    static final Object lock = new Object();

    static class T1 extends Thread {
        @Override
        public void run() {
            synchronized (lock) {
                System.out.println("T1 start");
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("T1 end");
            }
        }
    }

    static class T2 extends Thread {
        @Override
        public void run() {
            synchronized (lock) {
                System.out.println("T2 start");
                lock.notify();
                System.out.println("T2 end");
            }
        }
    }

    public static void main(String[] args) {
        Thread t1 = new T1();
        Thread t2 = new T2();
        t1.start();
        t2.start();
    }
}
