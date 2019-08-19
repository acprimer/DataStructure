package patterns.proxy;

import collections.YCollection;
import sun.reflect.misc.ReflectUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JavaDynamicProxy {
    public static void main(String[] args) throws ClassNotFoundException {
        JavaDeveloper zack = new JavaDeveloper("Zack");

        Class<?>[] intfs = zack.getClass().getInterfaces();
        for (int i = 0; i < intfs.length; i++) {
            System.out.println("intfs " + i + " " + intfs[i].getName());
        }
        Class testClass = Class.forName("patterns.singleton.TestInt");
        Class testClass2 = Class.forName("patterns.TestIntLook");

        Object zackProxy = Proxy.newProxyInstance(
                zack.getClass().getClassLoader(),
                new Class<?>[]{Developer.class},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args)
                            throws Throwable {
                        System.out.println("before method");
//                        if (method.getDeclaringClass() == Object.class) {
//                            System.out.println("this : " + this.getClass());
//                            return method.invoke(this, args);
//                        }
//                        System.out.println(this);
                        System.out.println(proxy.getClass());
//                        Object result = method.invoke(zack, args);
                        System.out.println("after method");
                        return null;
                    }
                }
        );
        System.out.println(zackProxy);

//        ((Developer)zackProxy).code();
//        ((Developer)zackProxy).debug();
//        ((Researcher)zackProxy).research();
    }
}
