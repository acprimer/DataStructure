package reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;

/**
 * Created by yaodh on 2018/10/23.
 */
public class DynamicProxy {
    public static void main(String[] args) {
        ApiImpl impl = new ApiImpl();
        Class<?>[] interfaces = Api.class.getInterfaces();
        for (Class clz : interfaces) {
            System.out.println("interface: " + clz.getName());
        }
        InvocationHandler handler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                        System.out.println("This is in proxy");
//                        System.out.println("proxy " + proxy + " impl " + impl);
                System.out.println("this " + this);
                return method.invoke(impl, args);
//                        return new HttpServiceMethod().parseMethod(method).invoke();
            }
        };
        System.out.println("handler " + handler);
        Api api = (Api) Proxy.newProxyInstance(Api.class.getClassLoader(), new Class[]{Api.class},
                handler);
        api.fun();
        api.go();
    }

    interface Api {
        String fun();
        String go();
    }

    static class ApiImpl implements Api {
        public String fun() {
            System.out.println("This is in fun()");
            return "";
        }
        public String go() {
            System.out.println("This is in go()");
            return "";
        }
    }

    static abstract class ServiceMethod {
        abstract Object invoke();
    }

    static class HttpServiceMethod extends ServiceMethod {

        public HttpServiceMethod parseMethod(Method method) {
            System.out.println("这里开始解析Method " + method.getName());
            return this;
        }

        @Override
        Object invoke() {
            System.out.println("真正的执行函数在这里");
            return "";
        }
    }
}
