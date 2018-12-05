package jvm;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class StackShareTest {
    static MemoryLayoutTest t1 = new MemoryLayoutTest();
    MemoryLayoutTest t2 = new MemoryLayoutTest();

    public void fn() {
        MemoryLayoutTest t3 = new MemoryLayoutTest();
        int x = 9;
        long y = 0x2;
        long z = x + y;
    }

    public static void main(String[] args) {
        StackShareTest test = new StackShareTest();
        test.fn();
    }
}
