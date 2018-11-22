package generics;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yaodh on 2018/9/4.
 */
public class GenericsMethod {
    private <T> T fun(T x) {
        System.out.println(x.getClass().getName());
        List<Integer> list = new ArrayList<>();
        list.getClass();
        return x;
    }

    private <T> void g(T x) {
        System.out.println(x.getClass().getName() + " in g()");
    }

    private void g(int x) {

    }

    private void p(Integer[] a) {

    }

    private <T> void x(T... a) {

    }

    private void fff(List<? extends String> list) {
//        list.add("a");
    }

    private <U,V> void test(U u, V v) {

    }

    private <T> void test(List<T> list) {
        T o = list.get(0);
    }

    public static void main(String[] args) {
        GenericsMethod m = new GenericsMethod();
        m.<String>fun("123");
        m.fun(123);
        m.g(m.fun(123));
    }
}
