package jvm;

import java.util.List;
import java.util.Vector;

public class BiasedLockTest {
    int x;
//    public List<Integer> numberList = new Vector<Integer>();

    public static void main(String[] args) {
        BiasedLockTest lockTest = new BiasedLockTest();
        System.out.println(lockTest.hashCode());
        // 打印 Vector 的 HashCode
//        System.out.println(System.identityHashCode(lockTest.numberList));
//        lockTest.test();
//        long begin = System.currentTimeMillis();
//        for (int i = 0; i < 100_000_000; i++) {
//            lockTest.add();
//        }
//        long end = System.currentTimeMillis();
//        System.out.println("time-->" + (end - begin));
        String property = System.getProperty("java.library.path");
        System.out.println(property);
    }

    private synchronized void add() {
        x++;
    }

    private void test() {
//        List<Integer> numberList = new Vector<Integer>();
        long begin = System.currentTimeMillis();
        int count = 0;
        int startnum = 0;
        while (count < 100000000) {
            StringBuffer buffer = new StringBuffer();
            buffer.append("abc");
            buffer.append("def");
//            numberList.add(startnum);
//            startnum += 2;
            count++;
        }
        long end = System.currentTimeMillis();
        System.out.println("time-->" + (end - begin));
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
// -XX:+UseBiasedLocking -XX:BiasedLockingStartupDelay=0 -Xmx512m -Xms512m
// -XX:-UseBiasedLocking -Xmx512m -Xms512m
// -XX:+DoEscapeAnalysis
//-XX:-EliminateLocks
//-XX:-UseBiasedLocking
//-XX:+UnlockDiagnosticVMOptions
//-XX:+PrintBiasedLockingStatistics