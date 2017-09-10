package multithread;

/**
 * Created by yaodh on 2017/9/10.
 */
public class DeadLock {
    public static void main(String[] args) {
        Object lockA = new Object();
        Object lockB = new Object();

        new Thread(new Runnable() {
            @Override
            public void run() {
                String name = Thread.currentThread().getName();
                synchronized (lockA) {
                    System.out.println(name + " got lock A, want lock B");
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (lockB) {
                        System.out.println(name + " got lock B");
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                String name = Thread.currentThread().getName();
                synchronized (lockB) {
                    System.out.println(name + " got lock B, want lock A");
                    synchronized (lockA) {
                        System.out.println(name + " got lock A");
                    }
                }
            }
        }).start();
    }
}
