package interview;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class ThreadPoolTest {
    public static void main(String[] args) {
        ExecutorService service = Executors
                .newSingleThreadExecutor(Executors.defaultThreadFactory());
    }
}
