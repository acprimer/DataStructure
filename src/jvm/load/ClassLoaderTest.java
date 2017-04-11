package jvm.load;

import sun.applet.AppletClassLoader;

/**
 * Created by yaodh on 2017/4/11.
 */
public class ClassLoaderTest {


    public static void main(String[] args) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        while (classLoader != null) {
            System.out.println(classLoader);
            classLoader = classLoader.getParent();
        }

        new Thread() {
            @Override
            public void run() {
                ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                while (classLoader != null) {
                    System.out.println(classLoader);
                    classLoader = classLoader.getParent();
                }
            }
        }.run();

        MyClassLoader myClassLoader = new MyClassLoader("E:/JavaProject/");
        try {
            Class clazz = myClassLoader.loadClass("TestClass");
            ClassLoader classLoader2 = myClassLoader;
            while (classLoader2 != null) {
                System.out.println(classLoader2);
                classLoader2 = classLoader2.getParent();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        InitStaticOrder order = InitStaticOrder.instance;
        System.out.println(order);
    }
}
