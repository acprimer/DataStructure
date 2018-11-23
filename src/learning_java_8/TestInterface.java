package learning_java_8;

import collections.map.HashMap;

/**
 * Created by yaodh on 2018/8/6.
 */
public interface TestInterface {
    void test();
    default void g() {
        System.out.println("g");
    }
    static void f(){

    }
    default void print() {
        System.out.println("Default print.");
    }

    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("1", 1);
        if (map.get("1") == 1) {

        }
        new TestInterface(){

            @Override
            public void test() {

            }
        }.print();
    }

    interface A extends TestInterface {
        @Override
        default void g() {
            TestInterface.super.g();
        }
    }
}
