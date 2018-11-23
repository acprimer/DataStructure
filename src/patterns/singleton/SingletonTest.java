package patterns.singleton;

import patterns.singleton.eagerly_load.SingletonHungry_2;

/**
 * Created by yaodh on 2017/4/7.
 */
public class SingletonTest {
    public static void main(String[] args) {
//        System.out.println("hello");
        SingletonHungry_2 singletonHungry_2 = SingletonHungry_2.getInstance();
        System.out.println(singletonHungry_2.toString());
        System.out.println(SingletonHungry_2.c1);
        System.out.println(new SingletonHungry_2().toString());
//
//        SingletonHungry_3 singletonHungry_3 = SingletonHungry_3.getInstance();
//        System.out.println(singletonHungry_3);
//
//        SingletonEnum.INSTANCE.doSomething();
//
//        Class<?> clazz = SingletonHungry_1.class;
    }
}
