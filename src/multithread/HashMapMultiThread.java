package multithread;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yaodh on 2018/4/10.
 */
public class HashMapMultiThread {
    // 来源：《实战Java高并发程序设计》 P63
    static Map<String, String> map = new HashMap<>();

    public static class AddThread implements Runnable {
        int start = 0;

        public AddThread(int start) {
            this.start = start;
        }

        @Override
        public void run() {
            for (int i = start; i < 100000; i+=2) {
//                System.out.println(i);
                map.put(Integer.toString(i), Integer.toBinaryString(i));
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new AddThread(0));
        Thread t2 = new Thread(new AddThread(1));

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("map: " + map.size());

        for (int i = 0; i < 100000; i++) {
            String key = Integer.toString(i);
            if (map.containsKey(key) && map.get(key) == null) {
                System.out.println(map.get(key));
            } else if (!map.containsKey(key)){
                System.out.println(map.get(key));
            }
//            System.out.println(map.get(Integer.toString(i)));
        }
    }
}
