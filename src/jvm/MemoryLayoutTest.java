package jvm;

import sun.misc.Unsafe;
import utils.UnsafeUtils;

public class MemoryLayoutTest {
    private long x = 0x786;
    private long y = 0x3345;
    String str = "Hello";

    public static void main(String[] args) {
        MemoryLayoutTest obj = new MemoryLayoutTest();
        obj.print();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        obj.printSync();
//        Unsafe unsafe = UnsafeUtils.getUnsafe();
//        MemoryLayoutTest obj = new MemoryLayoutTest();
//        int hash = obj.hashCode();
//        System.out.println(Integer.toHexString(hash));
//        obj.print();
        int x = 5;
        int y = 6;
        int z = x + y;
//        // 计算Hash值
//        int hash = obj.hashCode();
//        System.out.println(Integer.toHexString(hash));
//        System.out.printf("0x%016x\n", unsafe.getLong(obj, 0L));
//        System.out.printf("0x%016x\n", unsafe.getLong(obj, 8L));
//        System.out.printf("0x%016x\n", unsafe.getLong(obj, 16L));
//        System.out.printf("0x%016x\n", unsafe.getLong(obj, 24L));
//        System.out.printf("0x%016x\n", unsafe.getLong(obj, 32L));

//        obj.fun();

    }

    private void fun() {
        x++;
        print();
        int hash = hashCode();
        System.out.println(Integer.toHexString(hash));
        print();
        hash = hashCode();
        System.out.println(Integer.toHexString(hash));
//        for (int i = 0; i < 2; i++) {
//            new Thread() {
//                @Override
//                public void run() {
//                    print();
//                }
//            }.start();
//        }

//        int hash = hashCode();
//        System.out.println(Integer.toHexString(hash));
//        System.out.println(Long.toHexString(unsafe.getLong(obj, 0)));
    }

    private void print() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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

    private synchronized void printSync() {
        print();
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