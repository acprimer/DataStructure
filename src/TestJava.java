/**
 * Created by yaodh on 2017/3/8.
 */
public class TestJava {
    public static void main(String[] args) {
        System.out.println("Hello world");
        fun(0, 0f);
        long x = fib(30);
        System.out.println(x);
        System.out.println(circumference(1.6f));
    }

    public static float circumference(float r) {
        float pi = 3.14f;
        float area = 2 * pi * r;
        return area;
    }

    public static long fib(int n) {
        if (n <= 1) {
            return n;
        }
        return fib(n - 1) + fib(n - 2);
    }

    public static int fun(int a, float b) {
        int c = (int) (a + b);
        return c;
    }
}