package jvm;

import java.util.List;
import java.util.Vector;

public class BiasedLockTest {
    public List<Integer> numberList = new Vector<Integer>();

    public static void main(String[] args) {
        BiasedLockTest lockTest = new BiasedLockTest();
        // 打印 Vector 的 HashCode
//        System.out.println(System.identityHashCode(lockTest.numberList));
        lockTest.test();
    }

    private void test() {
        long begin = System.currentTimeMillis();
        int count = 0;
        int startnum = 0;
        while (count < 10000000) {
            numberList.add(startnum);
            startnum += 2;
            count++;
        }
        long end = System.currentTimeMillis();
        System.out.println("time-->" + (end - begin));
    }
}
// -XX:+UseBiasedLocking -XX:BiasedLockingStartupDelay=0 -Xmx512m -Xms512m
// -XX:-UseBiasedLocking -Xmx512m -Xms512m