package multithread;

/**
 * Created by yaodh on 2017/8/15.
 */
public class Chap_01_05 {
    public static void main(String[] args) {
        Thread t;
        CountingTask ct = new CountingTask();

        final int nProcessors = Runtime.getRuntime().availableProcessors();

        for (int i = 0; i < 2 * nProcessors; i++) {
            t = new Thread(ct);
            t.start();
        }

        for (int i = 0; i < 2 * nProcessors; i++) {
            t = new CountingThread();
            t.start();
        }
    }

    static class Counter {
        private int count = 0;
        public synchronized void increment() {
            count++;
        }
        public synchronized int value() {
            return count;
        }
    }

    static class CountingTask implements Runnable {
        private Counter counter = new Counter();
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                doSomething();
                counter.increment();
            }
            System.out.println("---CountingTask: " + counter.value());
        }

        private void doSomething() {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class CountingThread extends Thread {
        private Counter counter = new Counter();
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                doSomething();
                counter.increment();
            }
            System.out.println("+++CountingThread: " + counter.value());
        }

        private void doSomething() {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
