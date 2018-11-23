package multithread;

/**
 * Created by yaodh on 2018/4/17.
 */
public class SyncTest {

    private void f() {
        synchronized (SyncTest.class) {
            try {
                Thread.sleep(1000);
                System.out.println("f finished");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void g() {
        synchronized (SyncTest.class) {
            try {
                Thread.sleep(1000);
                System.out.println("g finished");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        int b = Integer.MIN_VALUE;
        System.out.println(Integer.toBinaryString(b));
//        SyncTest test1 = new SyncTest();
//        SyncTest test2 = new SyncTest();
//        new Thread() {
//            @Override
//            public void run() {
//                test1.f();
//            }
//        }.start();
//        new Thread() {
//            @Override
//            public void run() {
//                test2.g();
//            }
//        }.start();
    }
}
