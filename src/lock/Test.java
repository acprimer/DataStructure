package lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by yaodh on 2018/2/5.
 */
public class Test {
    private Object obj = new Object();
    ReentrantLock lock = new ReentrantLock();

    private void test() {
        System.out.println(Thread.holdsLock(obj));
        synchronized (obj) {
            System.out.println(Thread.holdsLock(obj));
        }
        new Thread() {
            @Override
            public void run() {
                lock.lock();
                try {
                    System.out.println("Thread 1");
                    sleep(10000);
                    System.out.println(Thread.holdsLock(lock));
                    System.out.println(lock.isHeldByCurrentThread());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }.start();
//        new Thread() {
//            @Override
//            public void run() {
//                System.out.println("AA");
//                lock.lock();
//                System.out.println("lock");
//                try {
//                    System.out.println("Thread 2");
//                } finally {
//                    lock.unlock();
//                }
//            }
//        }.start();
        Thread t2 = new Thread() {
            @Override
            public void run() {
                System.out.println("AA");
                lock.lock();
                System.out.println("lock");
                try {
                    System.out.println("Thread 2");
                } finally {
                    lock.unlock();
                }
            }
        };
        t2.start();
    }

    public static void main(String[] args) {
//        new Test().test();
//        List<A> list = new ArrayList<>();
//        list.add(new A());
//        list.add(new A());
//        list.add(new A());
//        list.add(new A());
//        f(list);
//
//        List<B> list2 = new ArrayList<>();
//        list2.add(new B());
//        list2.add(new B());
//        list2.add(new B());
//        list2.add(new B());
//        list2.add(new B());
//        f(list2);
        int[] a = {2,6,7,1,0,8};
        partition(a, 0, a.length-1);
        for (int n : a) {
            System.out.println(n);
        }
    }

    static class A {
        String get() {return "A";}
    }

    static class B extends A {}
    static class C extends A {}
    static void f(List<A> list) {
        for (A a : list) {
            System.out.println(a.get());
        }
    }


    static void partition(int[] arr, int left, int right) {
        int i = left, j = right;
        int key = arr[left];
        while (i < j) {
            while (i< j && key <= arr[j]) j--;
            arr[i] = arr[j];
            while (i< j && key >= arr[i]) i++;
            arr[j] = arr[i];
            arr[i] = key;
        }
    }
}
