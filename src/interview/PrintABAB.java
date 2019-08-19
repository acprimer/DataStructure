package interview;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class PrintABAB {
    static class PrintTask implements Runnable {
        private String name;

        PrintTask(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println(name);
        }
    }

    public static void main(String[] args) {

        System.out.println((-1)>>1);
        System.out.println((-1)>>>1);
        System.out.println((Integer.MAX_VALUE + Integer.MAX_VALUE) );
        System.out.println((Integer.MAX_VALUE + Integer.MAX_VALUE) >>> 1);
        System.out.println((Integer.MIN_VALUE + Integer.MIN_VALUE) >>> 1);
//        PrintABAB abab = new PrintABAB();
//        abab.serialWithBlockingQueue();

//        ExecutorService service = Executors.newSingleThreadExecutor();
//        Runnable ra = new PrintTask("A");
//        Runnable rb = new PrintTask("B");
//        for (int i = 0; i < 10; i++) {
//            service.submit(ra);
//            service.submit(rb);
//        }
//        service.shutdown();

//        for (int i = 0; i < 10; i++) {
//            try {
//                Thread ta = new Thread(ra);
//                ta.start();
//                ta.join();
//                Thread tb = new Thread(rb);
//                tb.start();
//                tb.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
    }

    BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();
    List<Runnable> list = new ArrayList<>(20);
    Runnable ra = new PrintTaskWithQueue("A");
    Runnable rb = new PrintTaskWithQueue("B");
    int index;
    Thread mainThread;

    class PrintTaskWithQueue implements Runnable {
        private String name;

        PrintTaskWithQueue(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println(name);
            if (++index < list.size()) {
                queue.offer(list.get(index));
            } else {
                mainThread.interrupt();
            }
        }
    }

    void serialWithBlockingQueue() {
        for (int i = 0; i < 10; i++) {
            list.add(ra);
            list.add(rb);
        }
        index = 0;
        queue.offer(list.get(index));

        mainThread = Thread.currentThread();
        while (true) {
            try {
                Runnable r = queue.take();
                new Thread(r).start();
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
