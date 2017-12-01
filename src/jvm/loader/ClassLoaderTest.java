package jvm.loader;

import sun.misc.Launcher;

import java.net.URL;

/**
 * Created by yaodh on 2017/4/11.
 */
public class ClassLoaderTest {


    public static void main(String[] args) {
        URL[] urLs = Launcher.getBootstrapClassPath().getURLs();
        for (URL url : urLs) {
            System.out.println(url.toExternalForm());
        }
        System.out.println(System.getProperty("sun.boot.class.path"));
        System.out.println(System.getProperty("java.ext.dirs"));
        System.out.println(System.getProperty("java.class.path"));

//        System.out.println(ClassLoader.getSystemClassLoader());

        try {
            Class<?> cla = ClassLoaderTest.class.getClassLoader().loadClass("jvm.loader.InitStaticOrder");
            System.out.println(cla.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        while (classLoader != null) {
            System.out.println(classLoader);
            classLoader = classLoader.getParent();
        }

        new Thread(() -> {
            ClassLoader classLoader1 = Thread.currentThread().getContextClassLoader();
            while (classLoader1 != null) {
                System.out.println(classLoader1);
                classLoader1 = classLoader1.getParent();
            }
        }).run();

        MyClassLoader myClassLoader = new MyClassLoader("E:/JavaProject/SayHello/out/production/SayHello/java/lang/");
        MySubClassLoader mySubClassLoader = new MySubClassLoader(null);
        try {
            Class clazz1 = ClassLoaderTest.class.getClassLoader().loadClass("java.lang.String");
            System.out.println("java.lang.String " + clazz1 + clazz1.hashCode());
//            System.out.println("clazz " + clazz);
            Class clazz2 = mySubClassLoader.loadClass("java.lang.String");
            System.out.println("java.lang.String " + clazz2 + clazz1.hashCode());
//            ClassLoader classLoader2 = mySubClassLoader;
//            while (classLoader2 != null) {
//                System.out.println("parent = " + classLoader2);
//                classLoader2 = classLoader2.getParent();
//            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        InitStaticOrder order = InitStaticOrder.instance;
        System.out.println(order);

//        Class<?> caller = Reflection.getCallerClass();
//        System.out.println(caller.getName());
        System.out.println(-5%10);

        SecurityManager sm = System.getSecurityManager();
        System.out.println(sm);

        try {
            ClassLoaderTest.class.getClassLoader().loadClass("jvm.loader.ClassLoaderTest");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String s = "hello";
        System.out.println(s);
    }
}
