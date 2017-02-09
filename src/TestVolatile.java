/**
 * Created by yaodh on 2017/2/9.
 */
public class TestVolatile {

    public static void main(String[] args) {
        MyVolatile myVolatile = new MyVolatile();

        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    myVolatile.increase();
                    System.out.println("Thread 1: counter = " + myVolatile.getCounter());
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println("Thread 2: counter = " + myVolatile.getCounter());
                }
            }
        }.start();
    }

    private static class MyVolatile {
        private int counter = 0;

        public void increase() {
            counter++;
        }

        public int getCounter() {
            return counter;
        }

        public void setCounter(int counter) {
            this.counter = counter;
        }
    }
}
