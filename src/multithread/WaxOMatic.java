package multithread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by yaodh on 2018/3/29.
 * https://my.oschina.net/itblog/blog/515193
 */
public class WaxOMatic {
    public static void main(String[] args) throws InterruptedException {
        Car car = new Car();
        ExecutorService exec = Executors.newCachedThreadPool();
        // 抛光
        exec.execute(new BuffTask(car, "Buff"));
        // 上蜡
        exec.execute(new WaxOnTask(car, "Wax"));
        TimeUnit.SECONDS.sleep(5);
        exec.shutdownNow();
    }
}

class WaxOnTask implements Runnable {
    private Car car;
    private String name;

    public WaxOnTask(Car car, String name) {
        this.car = car;
        this.name = name;
    }

    @Override
    public void run() {
        try {
//            while (!Thread.interrupted()) {
                System.out.printf("[%s] is Wax on!\n", name); // 正在上蜡
                TimeUnit.MILLISECONDS.sleep(500);
                car.waxed(); // 上蜡完成
                car.waitForBuffing(); // 等待抛光
//            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.printf("[%s] Exiting WaxOnTask via Interrupt.\n", name);
        }
    }
}

class BuffTask implements Runnable {
    private Car car;
    private String name;

    public BuffTask(Car car, String name) {
        this.car = car;
        this.name = name;
    }

    @Override
    public void run() {
        try {
//            while (!Thread.interrupted()) {
                car.waitForWaxing();
                System.out.printf("[%s] is Buffing...!\n", name); // 正在抛光
                TimeUnit.MILLISECONDS.sleep(500);
                car.buffed(); // 抛光完成
//            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.printf("[%s] Exiting BuffTask via Interrupt.\n", name);
        }
    }
}

class Car {
    private boolean waxOn = false; // 是否上蜡

    // 上蜡
    public synchronized void waxed() {
        waxOn = true;
        notify();
    }

    // 抛光
    public synchronized void buffed() {
        waxOn = false;
        notify();
    }

    // 等待上蜡
    public synchronized void waitForWaxing() throws InterruptedException {
        while (!waxOn) {
            wait();
        }
    }

    // 等待抛光
    public synchronized void waitForBuffing() throws InterruptedException {
        while (waxOn) {
            wait();
        }
    }
}