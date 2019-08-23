package multithread;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentMultiThread {
    private Map<Integer, Content> cache = new ConcurrentHashMap<>();

    private Content get(int key) {
        Content c = cache.get(key);
        if (c != null) return c;
        synchronized (cache) {
            c = cache.get(key);
            if (c == null) {
                c = new Content();
                cache.put(key, c);
            }
        }
        return c;
    }

    public static void main(String[] args) {
        ConcurrentMultiThread test = new ConcurrentMultiThread();
        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    test.get(i);
                }
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    test.get(i);
                }
            }
        }.start();
    }
}
