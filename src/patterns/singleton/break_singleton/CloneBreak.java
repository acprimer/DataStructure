package patterns.singleton.break_singleton;


import patterns.singleton.eagerly_load.SingletonHungry_1;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

// 使用clone()方法破坏单例
public class CloneBreak {
    public static void main(String[] args) {
        SingletonHungry_1 singleton = SingletonHungry_1.INSTANCE;
        System.out.println(singleton);
        try {
            Class dexClazz = Class.forName("patterns.singleton.eagerly_load.SingletonHungry_1");
            Method method = dexClazz.getDeclaredMethod("clone");
            method.setAccessible(true);
            SingletonHungry_1 o2 = (SingletonHungry_1) method.invoke(singleton);
            System.out.println(o2);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
