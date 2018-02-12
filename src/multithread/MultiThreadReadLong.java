package multithread;

/**
 * Created by yaodh on 2018/2/11.
 */
public class MultiThreadReadLong {
    public static long t = 0;

    public static class ChangeThread implements Runnable {
        private long to;

        public ChangeThread(long to) {
            this.to = to;
        }

        @Override
        public void run() {
            while (true) {
                t = to;
                Thread.yield();
            }
        }
    }

    public static class ReadThread implements Runnable {
        @Override
        public void run() {
            while (true) {
                long tmp = t;
                if (tmp != 111L && tmp != -999L && tmp != 333L && tmp != -444L) {
                    System.out.println(tmp);
                }
                Thread.yield();
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new ChangeThread(111L)).start();
        new Thread(new ChangeThread(-999L)).start();
        new Thread(new ChangeThread(333L)).start();
        new Thread(new ChangeThread(-444L)).start();
        new Thread(new ReadThread()).start();
    }

}
