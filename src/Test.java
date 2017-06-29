import sun.misc.Unsafe;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
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

//        LinkedList list = new LinkedList();
//        list.add(1);
//
//        int a = (int) (100.0 * 30311636 / 30311636);
//        System.out.println(a);
//
//        ArrayList<String> alist = new ArrayList<>(2);
//        alist.add("hello");
//        System.out.println(alist.get(0));


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

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String s = sc.nextLine();

        }

    }

    static final Unsafe unsafe = getUnsafe();
    static final boolean is64bit = true; // auto detect if possible.
    public static void printAddresses(String label, Object... objects) {
        System.out.print(label + ": 0x");
        long last = 0;

        int offset = unsafe.arrayBaseOffset(objects.getClass());
        int scale = unsafe.arrayIndexScale(objects.getClass());
        switch (scale) {
            case 4:
                long factor = is64bit ? 8 : 1;
                final long i1 = (unsafe.getInt(objects, offset) & 0xFFFFFFFFL) * factor;
                System.out.print(Long.toHexString(i1));
                last = i1;
                for (int i = 1; i < objects.length; i++) {
                    final long i2 = (unsafe.getInt(objects, offset + i * 4) & 0xFFFFFFFFL) * factor;
                    if (i2 > last)
                        System.out.print(", +" + Long.toHexString(i2 - last));
                    else
                        System.out.print(", -" + Long.toHexString( last - i2));
                    last = i2;
                }
                break;
            case 8:
                throw new AssertionError("Not supported");
        }
        System.out.println();
    }
}
