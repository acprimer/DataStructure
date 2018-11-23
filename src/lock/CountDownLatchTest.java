package lock;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * Created by yaodh on 2018/3/28.
 */
public class CountDownLatchTest implements Runnable {
    static final CountDownLatch latch = new CountDownLatch(10);
    static final CountDownLatchTest test = new CountDownLatchTest();

    @Override
    public void run() {
        try {
            // 模拟执行任务
            int time = new Random().nextInt(10) * 1000;
            System.out.println(Thread.currentThread().getId() + " sleep " + time);
            Thread.sleep(time);
            System.out.println(Thread.currentThread().getId() + " check complete");

            // 任务完成，计数器-1
            latch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        joinTest();
//        CountDownLatch counter = new CountDownLatch(1);
//        new Thread() {
//            @Override
//            public void run() {
//                try {
//                    System.out.println(Thread.currentThread().getId() + " start");
//                    Thread.sleep(1000);
//                    System.out.println(Thread.currentThread().getId() + " end");
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                } finally {
//                    counter.countDown();
//                }
//            }
//        }.start();
//        try {
//            counter.await();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        new Thread() {
//            @Override
//            public void run() {
//                try {
//                    System.out.println(Thread.currentThread().getId() + " start");
//                    Thread.sleep(1000);
//                    System.out.println(Thread.currentThread().getId() + " end");
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }.start();

        // 使用 CountDownLatch
//        ExecutorService exec = Executors.newFixedThreadPool(10);
//        for (int i = 0; i < 10; i++) {
//            exec.submit(test);
//        }
//
//        // 计数器开始计数等待
//        // 与CountDownLatch第一次交互是主线程等待其它的线程，
//        // 主线程必须在启动其它线程后立即调用await方法，
//        // 这样主线程的操作就会在这个方法上阻塞，直到其他线程完成各自的任务。
//        try {
//            latch.await(2000, TimeUnit.MILLISECONDS);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println("Fire");
//        exec.shutdown();
    }

    private static void joinTest() throws InterruptedException {
        Thread work0 = new Thread(new CountDownLatchTest());
        Thread work1 = new Thread(new CountDownLatchTest());
        Thread work2 = new Thread(new CountDownLatchTest());
        work0.start();
        work1.start();
        work0.join();
        work1.join();

        System.out.println("work 2 start");
        work2.start();

//        new Thread() {
//            @Override
//            public void run() {
//                HashMap<Integer, String> map = new HashMap<>();
//                for (int i = 0; i < 1000; i++) {
//                    try {
//                        map.put(i, "i" + i);
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }.start();
    }
}
