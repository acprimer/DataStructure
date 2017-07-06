package collections;

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
