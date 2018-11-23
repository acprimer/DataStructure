package reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by yaodh on 2018/10/23.
 */
public class DynamicProxy {
    public static void main(String[] args) {
//        ApiImpl impl = new ApiImpl();
        Api api = (Api) Proxy.newProxyInstance(Api.class.getClassLoader(), new Class[]{Api.class},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("This is in proxy");
//                        return method.invoke(impl, args);
                        new HttpServiceMethod().parseMethod(method).invoke();
                        return null;
                    }
                });
        api.fun();
        api.go();
    }

    interface Api {
        void fun();
        void go();
    }

    static class ApiImpl implements Api {
        public void fun() {
            System.out.println("This is in fun()");
        }
        public void go() {
            System.out.println("This is in go()");
        }
    }

    static abstract class ServiceMethod {
        abstract void invoke();
    }

    static class HttpServiceMethod extends ServiceMethod {

        public HttpServiceMethod parseMethod(Method method) {
            System.out.println("这里开始解析Method " + method.getName());
            return this;
        }

        @Override
        void invoke() {
            System.out.println("真正的执行函数在这里");
        }
    }
}
