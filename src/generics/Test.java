package generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yaodh on 2018/1/23.
 */
public class Test {
    public static void main(String[] args) {
        // http://mp.weixin.qq.com/s/-b2VtPqdFQyY4AIQp9h1AA
        List<Integer> ints = new ArrayList<>();
        ints.add(2);
        ints.add(3);
        ints.add(5);
        List<? extends Number> numbs = ints;
        Integer a = 200;

//        numbs.add(a);
//        numbs.add((Number) a);
//        numbs.add((Object) a);

//        List<? super Integer> is = ints;
//        is.add(a);

        Test test = new Test();
        test.print(ints);

        List<String> sl = new ArrayList<>();
        sl.add("A");
        sl.add("B");
        sl.add("C");

        test.print(sl);
        test.print2(sl);

        test.cls(Integer.class);
        test.cls(Integer.class);
    }

    private void print(List<?> list) {
//        list.add(new Object());
        System.out.println(Arrays.toString(list.toArray()));
    }

    private void print2(List list) {
//        list.add(new Object());
    }

    private void cls(Class clz) {
        clz.asSubclass(Object.class);
    }

    private void cls2(Class<?> clz) {
        clz.asSubclass(Object.class);
    }
}
