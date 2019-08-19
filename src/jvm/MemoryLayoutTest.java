package jvm;

import sun.misc.Unsafe;
import utils.UnsafeUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MemoryLayoutTest implements Cloneable {
    private long x = 0x786;
    private long y = 0x3345;
    String str = "Hello";
    boolean b = true;
    byte aByte = 0x9;
    char ch = 0x22;
    int xz = 0x1222;
    int xy = 0x1333;

    @Override
    public int hashCode() {
        return 1;
    }

    // 0x00000000 00000005  [mark word]
    // 0x0901000a 80051aa0  [][_meta data]
    // 0x00000000 00000786
    // 0x00000000 00003345
    // 0x00000000 f5789478

    public static void main(String[] args) {
//        MemoryLayoutTest obj = new MemoryLayoutTest();
        HashMap<MemoryLayoutTest, String> map = new HashMap<>();
        List<MemoryLayoutTest> list = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            MemoryLayoutTest o = new MemoryLayoutTest();
            o.print();
            map.put(o, String.valueOf(i));
            list.add(o);
        }
        System.out.println("after");
        for (MemoryLayoutTest o : list) {
            System.out.println("identityHash: " + Integer.toHexString(System.identityHashCode(o)));
            o.print();
        }
//        obj.printSync();
//        int x = 0;
//        int y = 1;
//        int z = x + y;
//        obj.print();
//        obj.printHash();
//        obj.fun();
//        obj.print();
//        obj.printSync();
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        new Thread() {
//            @Override
//            public void run() {
//                System.out.println("Thread " + Thread.currentThread().getName());
//                obj.printSync();
//            }
//        }.start();
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("Thread " + Thread.currentThread().getName());
//        obj.printSync();
//        new Thread() {
//            @Override
//            public void run() {
//                System.out.println("Thread " + Thread.currentThread().getName());
//                obj.printSync();
//            }
//        }.start();
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        for (int i = 0; i < 4; i++) {
//            new Thread() {
//                @Override
//                public void run() {
//                    System.out.println("Thread " + Thread.currentThread().getName());
//                    obj.printSync();
//                }
//            }.start();
//        }
    }

    public void print() {
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        MemoryLayoutTest obj = this;
        System.out.println("开始打印");
        Unsafe unsafe = UnsafeUtils.getUnsafe();
        // 计算Hash值
//        int hash = obj.hashCode();
//        System.out.println(Integer.toHexString(hash));
        System.out.printf("0x%016x\n", unsafe.getLong(obj, 0L));
        System.out.printf("0x%016x\n", unsafe.getLong(obj, 8L));
        System.out.printf("0x%016x\n", unsafe.getLong(obj, 16L));
        System.out.printf("0x%016x\n", unsafe.getLong(obj, 24L));
    }

    public synchronized void printSync() {
//        print();
        test();
    }

    public synchronized void test() {
        System.out.println("in sync");
    }

    public void printHash() {
        int hash = hashCode();
        System.out.println("HashCode: \n0x" + Integer.toHexString(hash));
    }

    private void fun() {
        int a = 1;
        int b = 1;
    }
}

// HSDB
// sudo java -cp /Library/Java/JavaVirtualMachines/jdk1.8.0_152.jdk/Contents/Home/lib/sa-jdi.jar sun.jvm.hotspot.HSDB

// 偏向锁
// -XX:+UseBiasedLocking -XX:BiasedLockingStartupDelay=0 -Xmx512m -Xms512m
// -XX:-UseBiasedLocking -Xmx512m -Xms512m

// 指针压缩
// -XX:+UseCompressedOops

// 布局顺序
// -XX:FieldsAllocationStyle=0