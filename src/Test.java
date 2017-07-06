import sun.misc.Unsafe;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

import static sun.misc.Unsafe.getUnsafe;

/**
 * Created by yaodh on 2016/10/10.
 */

//import java.util.collections.map.HashMap;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        collections.map.HashMap<String, Object> map = new collections.map.HashMap<>(4);
        map.put("a", 0);
        map.put("sf", "sfda");
        map.put("b", "c");
        map.put("c", 3.0);
        map.put("d", map);
        map.put("n", "sl");
        map.put("va", "sfd");
        map.put(null, "sl");
        System.out.println(map);

        LinkedList list = new LinkedList();
        list.add(1);

        int a = (int) (100.0 * 30311636 / 30311636);
        System.out.println(a);

        ArrayList<String> alist = new ArrayList<>(3);
        alist.add("A");
        alist.add("B");
        alist.add("C");
        System.out.println(alist.get(0));
        Object[] objs = alist.toArray();
        System.out.println(Arrays.toString(objs));
        String[] strs = alist.toArray(new String[0]);
        System.out.println(Arrays.toString(strs));

        ArrayList<String> blist = new ArrayList<>(2);
        blist.add("D");
        blist.add("A");
        blist.add("B");
        System.out.println(alist.contains("hello"));
        System.out.println(alist.contains("A"));
        System.out.println(alist.containsAll(blist));
//        alist.removeAll(blist);
//        System.out.println(alist);
        alist.retainAll(blist);
        System.out.println(alist);

        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        String sw = "weak ref";
        switch (sw) {
            case "soft ref":
                Object objSoft = new Object();
                SoftReference<Object> softReference = new SoftReference<Object>(objSoft, referenceQueue);
                System.out.println("before GC: " + softReference.get());
                objSoft = null;
                System.gc();
                Thread.sleep(1000);
                System.out.println("after GC: " + softReference.get());
                System.out.println("reference queue: " + referenceQueue.poll());
                break;
            case "weak ref":
                Object objWeak = new Object();
                WeakReference<Object> weakReference = new WeakReference<Object>(objWeak, referenceQueue);
                System.out.println("before GC: " + weakReference.get());
                objWeak = null;
                System.gc();
                Thread.sleep(1000);
                System.out.println("after GC: " + weakReference.get());
                System.out.println("reference queue: " + referenceQueue.poll());
                break;
        }

    }
}
