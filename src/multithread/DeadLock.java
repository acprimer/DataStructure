package multithread;

/**
 * Created by yaodh on 2017/9/10.
 */
public class DeadLock {
    private Object lockA = new Object();
    private Object lockB = new Object();

    private void f() {
        synchronized (lockA) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("I got lock A");
            g();
        }
    }

    private void g() {
        synchronized (lockB) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("I got lock B");
            f();
        }
    }

    public static void main(String[] args) {
        DeadLock deadLock = new DeadLock();
        new Thread(deadLock::f).start();
        new Thread(deadLock::g).start();
//        final Object lockA = new Object();
//        final Object lockB = new Object();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                String name = Thread.currentThread().getName();
//                synchronized (lockA) {
//                    System.out.println(name + " got lock A, want lock B");
//                    try {
//                        Thread.sleep(10);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    synchronized (lockB) {
//                        System.out.println(name + " got lock B");
//                    }
//                }
//            }
//        }).start();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                String name = Thread.currentThread().getName();
//                synchronized (lockB) {
//                    System.out.println(name + " got lock B, want lock A");
//                    synchronized (lockA) {
//                        System.out.println(name + " got lock A");
//                    }
//                }
//            }
//        }).start();
    }
}
