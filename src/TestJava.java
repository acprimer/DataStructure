/**
 * Created by yaodh on 2017/3/8.
 */
public class TestJava {
    private static final int SHAKE_DURATION = 2400;
    private static final int WAIT_DURATION = 4000;
    private static final float SLICE = SHAKE_DURATION / 28.f;
    public static final int DURATION = SHAKE_DURATION + WAIT_DURATION;
    static float S1 = 2 * SLICE;  // 阶段一，向左摆动一度，占有两个时间片
    static float s1 = 12 * SLICE; // 阶段二，第一个循环 左右摆动一个来回，占有12个时间片
    static float s2 = s1;              // 阶段二，第二个循环 左右摆动一个来回，占有12个时间片
    static float S2 = s1 + s2;         // 阶段二，包含上面两个小阶段
    static float S3 = S1;              //阶段三，向右摆动一度，占有两个时间片

    private static float fun1(float input) {
        input = input * DURATION;
        if (input <= SHAKE_DURATION) {
            if (input <= S1) {
                double x = (input / S1 * Math.PI);//归一化处理
                return (float) Math.sin(Math.PI + x);
            } else if (input <= S1 + S2) {
                double x = (input - S1) / s1 * 2 * Math.PI;
                return (float) (7 * Math.sin(x));
            } else {
                double x = (input - S1 - S2) / S3 * Math.PI;
                return (float) Math.sin(x);
            }
        } else {
            return 0f;
        }
    }

    public static final float PI = SHAKE_DURATION / 14;
    private static int getFraction(float input) {
        if (input < PI || input > 5 * PI) {
            return 1;
        } else {
            return 7;
        }
    }

    private static int getPI(float input) {
        if (input < PI || input > 5 * PI) {
            return 1;
        } else {
            return 2;
        }
    }

    private static float fun2(float input) {
        input = input * DURATION;
        if (input <= SHAKE_DURATION) {
            return -getFraction(input) * (float) Math.sin(input * Math.PI / PI / getPI(input));
        } else {
            return 0f;
        }
    }

    public static void main(String[] args) {
        for (float i = 0; i < 1.f; i += 0.01) {
            System.out.printf("%.3f\n", fun2(i));
        }
//        System.out.println("Hello world");
//        fun(0, 0f);
//        long x = fib(30);
//        System.out.println(x);
//        System.out.println(circumference(1.6f));
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