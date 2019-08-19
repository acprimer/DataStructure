package collections.map;


import java.util.HashMap;
import java.util.Map;

public class CmpSparseArray {
    private static final int COUNT = 1_000;
    private int[] keys = new int[COUNT];
    private String[] values = new String[COUNT];
    private HashMap<Integer, String> hashMap = new HashMap<>();
    private SparseArray<String> sparseArray = new SparseArray<>();

    {
        for (int i = 0; i < keys.length; i++) {
            keys[i] = keys.length - i - 1;
            values[i] = String.valueOf(i);

            hashMap.put(keys[i], values[i]);
            sparseArray.put(keys[i], values[i]);
        }
    }

    public static void main(String[] args) {
        CmpSparseArray cmp = new CmpSparseArray();
        cmp.testHashMap();
        cmp.testSparseArray();
    }

    private void testHashMap() {
        long start = System.nanoTime();
//        for (int i = 0; i < keys.length; i++) {
//            hashMap.put(keys[i], values[i]);
//        }
        for (int i = 0; i < keys.length; i++) {
            hashMap.get(i);
        }
        System.out.println(hashMap.size());
        long end = System.nanoTime();
        System.out.println("time: " + (end - start) / 1000);
    }

    private void testSparseArray() {
        long start = System.nanoTime();
//        for (int i = 0; i < keys.length; i++) {
//            sparseArray.put(keys[i], values[i]);
//        }
        for (int i = 0; i < keys.length; i++) {
            sparseArray.get(i);
        }
        System.out.println(sparseArray.size());
        long end = System.nanoTime();
        System.out.println("time: " + (end - start) / 1000);
    }
}
