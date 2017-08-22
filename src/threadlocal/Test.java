package threadlocal;

/**
 * Created by yaodh on 2017/8/13.
 */
public class Test {
    private static YThreadLocal[] sThreadLocal;
    public static void main(String[] args) {
//        MessageQ.put("world");
//        System.out.println(MessageQ.get(0));
//
//        new MessageThread().start();

        sThreadLocal = new YThreadLocal[4];
        for (int i = 0; i < 4; i++) {
            sThreadLocal[i] = new YThreadLocal<String>();
        }
        new TestThread().start();
    }

    static class MessageThread extends Thread {
        @Override
        public void run() {
            super.run();
            MessageQ.put("hello");
            System.out.println(MessageQ.get(0));
        }
    }

    static class TestThread extends YThread {
        @Override
        public void run() {
            super.run();
            sThreadLocal[0].set("A");
            sThreadLocal[1].set("B");
            System.out.println(sThreadLocal[1].get());
            sThreadLocal[2].set("C");

            sThreadLocal[1] = null;
            sThreadLocal[2] = null;
            System.gc();

            sThreadLocal[3].set("D");
//            sThreadLocal[1].set("E");
            System.out.println(sThreadLocal[0].get());
//            System.out.println(sThreadLocal[1].get());
            System.out.println(sThreadLocal[2].get());
            System.out.println(sThreadLocal[3].get());
        }
    }
}
