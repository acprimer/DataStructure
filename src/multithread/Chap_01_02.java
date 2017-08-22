package multithread;

/**
 * Created by yaodh on 2017/8/15.
 */
public class Chap_01_02 {

    public static void main(String[] args) {
        Thread welcomeThread = new WelcomeThread();
        welcomeThread.start();

        Thread welcomeTask = new Thread(new WelcomeTask());
        Thread welcomeTask2 = new Thread(new WelcomeTask());
        welcomeTask.start();
        welcomeTask2.start();

        System.out.printf("1. Welcome! I'm in %s.%n", Thread.currentThread().getName());
    }

    static class WelcomeThread extends Thread {
        @Override
        public void run() {
            System.out.printf("2. Welcome! I'm in %s.%n", Thread.currentThread().getName());
        }
    }

    static class WelcomeTask implements Runnable {
        @Override
        public void run() {
            System.out.printf("3. Welcome! I'm in %s.%n", Thread.currentThread().getName());
        }
    }
}
