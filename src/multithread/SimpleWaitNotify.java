package multithread;

import jvm.MemoryLayoutTest;

/**
 * Created by yaodh on 2018/3/29.
 */
public class SimpleWaitNotify {
    static final MemoryLayoutTest lock = new MemoryLayoutTest();

    static class T1 extends Thread {
        @Override
        public void run() {
//            lock.print();
            synchronized (lock) {
                System.out.println("T1 start");
                try {
//                    lock.print();
                    lock.wait(0);
//                    lock.print();
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
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock) {
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("T2 start");
                lock.notify();
                System.out.println("T2 end");
            }
        }
    }

    static class T3 extends Thread {
        @Override
        public void run() {
            while (!Thread.interrupted()) {
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("中断了");
                    interrupt();
//                e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread t1 = new T1();
        Thread t2 = new T2();
        t1.start();
//        t2.start();
//        Thread t3 = new T3();
//        t3.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t1.interrupt();
    }

    private void fun() {
        Object o = new Object();
        byte[] b = new byte[0];
    }
}
