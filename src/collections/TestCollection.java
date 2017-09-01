package collections;

import address.ObjectMemoryAddress;
import collections.map.HashMap;
import collections.map.SparseArray;
import com.javamex.classmexer.MemoryUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by yaodh on 2017/7/2.
 */
public class TestCollection {
    public static final String LINE_SEPERATOR = "---------------";

    public static void main(String[] args) {
        boolean result;
        List<String> aList = new ArrayList<>();
        List<String> bList = new ArrayList<>();

        // 并集 addAll
        generateSet(aList, bList);
        result = aList.addAll(bList);
        System.out.println("并集操作 addAll ：\n" + "返回值：" + result + "\n结果：" + aList);
        System.out.println(LINE_SEPERATOR);

        // 交集 retainAll
        generateSet(aList, bList);
        result = aList.retainAll(bList);
        System.out.println("交集操作 retainAll ：\n" + "返回值：" + result + "\n结果：" + aList);
        System.out.println(LINE_SEPERATOR);

        // 差集 removeAll
        generateSet(aList, bList);
        result = aList.removeAll(bList);
        System.out.println("差集操作 removeAll ：\n" + "返回值：" + result + "\n结果：" + aList);
        System.out.println(LINE_SEPERATOR);


        System.out.println();
        System.out.println("SparseArray");
        SparseArray<String> stringSparseArray = new SparseArray<>();
        stringSparseArray.put(100, "a");
        stringSparseArray.put(-190, "b");
        stringSparseArray.put(389, "c");
        stringSparseArray.put(27, "d");
        for (int i = 0; i < stringSparseArray.size(); i++) {
            int key = stringSparseArray.keyAt(i);
            String val = stringSparseArray.valueAt(i);
            System.out.printf("%d %d %s%n", i, key, val);
        }
        System.out.println(stringSparseArray);
        System.out.println("mem size: " + MemoryUtil.memoryUsageOf(stringSparseArray));

//        ObjectMemoryAddress.printAddress("address: " + stringSparseArray);

        HashMap<Integer, String> map = new HashMap<>();
        map.put(100, "a");
        map.put(-190, "b");
        map.put(389, "c");
        map.put(27, "d");
        System.out.println(map);
        System.out.println("mem size: " + MemoryUtil.memoryUsageOf(map));

//        ObjectMemoryAddress.printAddress("address: " + map);
    }

    private static void generateSet(Collection a, Collection b) {
        a.clear();
        b.clear();

        a.add("a");
        a.add("b");
        a.add("b");
        a.add("c");
        a.add("d");

        b.add("a");
        b.add("d");
        b.add("b");

        System.out.println("A集合: " + a);
        System.out.println("B集合: " + b);
    }
}
