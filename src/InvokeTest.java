import java.lang.ref.WeakReference;

/**
 * Created by yaodh on 2017/8/8.
 */
public class InvokeTest {
    public static void main(String[] args) {
        InvokeTest test = new InvokeTest();
        System.out.println(test);
        test.fun();
    }

    public void fun() {
        StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        System.out.println(elements[1].getClassName() + "." + elements[1].getMethodName());
        for (int i = 0; i < elements.length; i++) {
            System.out.println(elements[i]);
        }
        System.out.println(this);
    }
}
