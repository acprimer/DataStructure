package lock;

public class SleepExp {
    public static void main(String[] args) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                while (!isInterrupted()) {
                    System.out.println("sleep");
                    try {
                        sleep(100);
                    } catch (InterruptedException e) {
                        interrupt();
                        System.out.println("exception");
                        e.printStackTrace();
                    }
                }
            }
        };
        thread.start();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }
}
