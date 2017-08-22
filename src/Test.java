import collections.map.*;
import com.javamex.classmexer.MemoryUtil;
import sun.misc.Unsafe;

import java.io.File;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.*;
import java.util.HashMap;
import java.util.Map;

import static sun.misc.Unsafe.getUnsafe;

/**
 * Created by yaodh on 2016/10/10.
 */

//import java.util.collections.map.HashMap;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(System.identityHashCode(new Object()));
        System.out.println(System.identityHashCode(new Object()));
        System.out.println(System.identityHashCode(new Object()));
//        collections.map.HashMap<String, Object> map = new collections.map.HashMap<>(4);
//        map.put("a", 0);
//        map.put("sf", "sfda");
//        map.put("b", "c");
//        map.put("c", 3.0);
//        map.put("d", map);
//        map.put("n", "sl");
//        map.put("va", "sfd");
//        map.put(null, "sl");
//        System.out.println(map);
//
//        LinkedList list = new LinkedList();
//        list.add(1);
//
//        int a = (int) (100.0 * 30311636 / 30311636);
//        System.out.println(a);
//
//        ArrayList<String> alist = new ArrayList<>(3);
//        alist.add("A");
//        alist.add("B");
//        alist.add("C");
//        System.out.println(alist.get(0));
//        Object[] objs = alist.toArray();
//        System.out.println(Arrays.toString(objs));
//        String[] strs = alist.toArray(new String[0]);
//        System.out.println(Arrays.toString(strs));
//
//        ArrayList<String> blist = new ArrayList<>(2);
//        blist.add("D");
//        blist.add("A");
//        blist.add("B");
//        System.out.println(alist.contains("hello"));
//        System.out.println(alist.contains("A"));
//        System.out.println(alist.containsAll(blist));
////        alist.removeAll(blist);
////        System.out.println(alist);
//        alist.retainAll(blist);
//        System.out.println(alist);
//
//        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
//        String sw = "weak ref";
//        switch (sw) {
//            case "soft ref":
//                Object objSoft = new Object();
//                SoftReference<Object> softReference = new SoftReference<Object>(objSoft, referenceQueue);
//                System.out.println("before GC: " + softReference.get());
//                objSoft = null;
//                System.gc();
//                Thread.sleep(1000);
//                System.out.println("after GC: " + softReference.get());
//                System.out.println("reference queue: " + referenceQueue.poll());
//                break;
//            case "weak ref":
//                Object objWeak = new Object();
//                WeakReference<Object> weakReference = new WeakReference<Object>(objWeak, referenceQueue);
//                System.out.println("before GC: " + weakReference.get());
//                objWeak = null;
//                System.gc();
//                Thread.sleep(1000);
//                System.out.println("after GC: " + weakReference.get());
//                System.out.println("reference queue: " + referenceQueue.poll());
//                break;
//        }
//
//        for (char ch = 'a'; ch <= 'z'; ch++) {
//            System.out.printf("%s", ch);
//        }
//
//        for (char ch = '\u0250'; ch <= '\u02AF'; ch++) {
//            System.out.printf("%s", ch);
//        }
//
//        // ˈː-abcdefghijklmnopqrstuvwxyzɐɑɒɓɔɕɖɗɘəɚɛɜɝɞɟɠɡɢɣɤɥɦɧɨɩɪɫɬɭɮɯɰɱɲɳɴɵɶɷɸɹɺɻɼɽɾɿʀʁʂʃʄʅʆʇʈʉʊʋʌʍʎʏʐʑʒʓʔʕʖʗʘʙʚʛʜʝʞʟʠʡʢʣʤʥʦʧʨʩʪʫʬʭʮʯ
//
//
//        System.out.println("SparseArray");
//        SparseArray<String> stringSparseArray = new SparseArray<>();
//        stringSparseArray.put(100, "a");
//        stringSparseArray.put(-190, "b");
//        stringSparseArray.put(389, "c");
//        stringSparseArray.put(27, "d");
//        for (int i = 0; i < stringSparseArray.size(); i++) {
//            int key = stringSparseArray.keyAt(i);
//            String val = stringSparseArray.valueAt(i);
//            System.out.printf("%d %d %s%n", i, key, val);
//        }
//        System.out.println(stringSparseArray);
//        System.out.println("mem size: " + MemoryUtil.memoryUsageOf(stringSparseArray));
//
//        collections.map.HashMap<Integer, String> map1 = new collections.map.HashMap<>();
//        map1.put(100, "a");
//        map1.put(-190, "b");
//        map1.put(389, "c");
//        map1.put(27, "d");
//        System.out.println(map1);
//        System.out.println("mem size: " + MemoryUtil.memoryUsageOf(map1));
//
//        String s = "hello";
//        System.out.println(MemoryUtil.memoryUsageOf(s));
//        System.out.println(MemoryUtil.deepMemoryUsageOf(s));
//        char[] chs = new char[]{'h','e','l','l','o'};
//        System.out.println(MemoryUtil.memoryUsageOf(chs));
        System.out.println(MemoryUtil.memoryUsageOf(new Object()));
        System.out.println(MemoryUtil.memoryUsageOf(new A()));
        System.out.println(MemoryUtil.deepMemoryUsageOf(new A()));
        System.out.println(MemoryUtil.memoryUsageOf(new B()));
        System.out.println(MemoryUtil.deepMemoryUsageOf(new B()));
        System.out.println(MemoryUtil.memoryUsageOf(new Integer[0]));
        System.out.println(MemoryUtil.memoryUsageOf(new Integer[1]));
        System.out.println(MemoryUtil.memoryUsageOf(new Integer[2]));
        System.out.println(MemoryUtil.memoryUsageOf(new Integer[3]));
        System.out.println(MemoryUtil.memoryUsageOf(new Integer[4]));
        System.out.println(MemoryUtil.deepMemoryUsageOf(new Integer[4]));

        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < 1; i++) {
            map.put(i, "1");
        }
        System.out.println(MemoryUtil.memoryUsageOf(map));
        System.out.println(MemoryUtil.deepMemoryUsageOf(map));

        SparseArray<String> arr2 = new SparseArray<>(1);
        for (int i = 0; i < 1; i++) {
            arr2.put(i, "i");
        }
        System.out.println(MemoryUtil.memoryUsageOf(arr2));
        System.out.println(MemoryUtil.deepMemoryUsageOf(arr2));

    }

    static class A {
        Integer[] a = new Integer[4];
    }
    static class B {
        int[] a = new int[4];
    }
}
