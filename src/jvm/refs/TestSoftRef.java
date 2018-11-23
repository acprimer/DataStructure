package jvm.refs;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.Scanner;

public class TestSoftRef {
    public static void main(String[] args) {
        printMemInfo();
        ReferenceQueue<String> queue = new ReferenceQueue<>();
        Reference<String>[] refs = new Reference[1000];
        for (int i = 0; i < refs.length; i++) {
            System.out.println(i);
            refs[i] = new WeakReference<>(String.valueOf(i), queue);
        }
//        refs[0].get();
        printMemInfo();
//        System.gc();
        printMemInfo();
//        for (int i = 0; i < 10; i++) {
//            System.out.println(refs[i].get());
//        }

//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        refs[0].get();
//        System.gc();
//        try {
//            // 用这一行代码申请超大的内存空间，模拟OOM进行GC
//            Object[] ignored = new Object[5000000];
//        } catch (OutOfMemoryError e) {
//            // Ignore
//        }
        printMemInfo();
        int index = 0;
        System.out.println("------");
        for (int i = 0; i < 1000; i++) {
            System.out.println(refs[i].get());
            if (refs[i].get() == null) {
                index = i;
//                break;
            }
        }
        System.out.println("index "+index);

//        System.gc();
//        try {
//            // 用这一行代码申请超大的内存空间，模拟OOM进行GC
//            Object[] ignored = new Object[(int) Runtime.getRuntime().maxMemory()];
//        } catch (OutOfMemoryError e) {
//            // Ignore
//        }


//        System.gc();
//        try {
//            // 用这一行代码申请超大的内存空间，模拟OOM进行GC
//            Object[] ignored = new Object[(int) Runtime.getRuntime().maxMemory()];
//        } catch (OutOfMemoryError e) {
//            // Ignore
//        }
//
//        System.out.println("触发了GC");
//        for (int i = 0; i < 10; i++) {
//            System.out.println(refs[i].get());
//        }
    }

    private static void printMemInfo() {
        // Get current size of heap in bytes
        long heapSize = Runtime.getRuntime().totalMemory();

        // Get maximum size of heap in bytes. The heap cannot grow beyond this size.// Any attempt will result in an OutOfMemoryException.
        long heapMaxSize = Runtime.getRuntime().maxMemory();

        // Get amount of free memory within the heap in bytes. This size will increase // after garbage collection and decrease as new objects are created.
        long heapFreeSize = Runtime.getRuntime().freeMemory();
        System.out.println(heapSize / 1024 / 1024 + " " + heapMaxSize / 1024 / 1024 + " " + heapFreeSize / 1024 / 1024);
    }
}
