package multithread;

/**
 * Created by yaodh on 2018/2/26.
 */
public class ThreadMethod {
    class T1 extends Thread {
        int i;
        @Override
        public void run() {
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int j = 0; j < 100; j++) {
                i++;
            }
            System.out.println(i);
        }
    }
    class T2 extends Thread {
        int i;
        @Override
        public void run() {
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int j = 0; j < 100; j++) {
                i++;
            }
            System.out.println(i);
        }
    }
    class T3 extends Thread {
        int i;
        @Override
        public void run() {
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int j = 0; j < 100; j++) {
                i++;
            }
            System.out.println(i);
        }
    }

    private void test() throws InterruptedException {
        Thread T1 = new T1();
        Thread T2 = new T2();
        Thread T3 = new T3();
        T1.start();
        T1.join();
        T2.start();
        T2.join();
        T3.start();
        T3.join();
    }

    public static void main(String[] args) throws InterruptedException {
        new ThreadMethod().test();
    }
}
