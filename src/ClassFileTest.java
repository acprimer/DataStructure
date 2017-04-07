import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yaodh on 2017/3/1.
 */
public class ClassFileTest {
//    public static final boolean FLAG = true;
//    public static final byte BYTE = 123;
//    public static final char X = 'X';
//    public static final short SHORT = 12345;
//    public static final int INT = 123456789;
//    public static final long LONG = 12345678901L;
//    public static final float PI = 3.14f;
//    public static final double E = 2.71828;
//    public static final String TAG = "TAG";

//    public static int staticVar;
//    public int instanceVar;

//    private static void bubbleSort(int[] arr) {
//        boolean swapped = true;
//        int j = 0;
//        int tmp;
//        while (swapped) {
//            swapped = false;
//            j++;
//            for (int i = 0; i < arr.length - j; i++) {
//                if (arr[i] > arr[i+1]) {
//                    tmp = arr[i];
//                    arr[i] = arr[i+1];
//                    arr[i+1] = tmp;
//                    swapped = true;
//                }
//            }
//        }
//    }
//    private static void printArray(int[] arr) {
//        for (int n : arr) {
//            System.out.println(n);
//        }
//    }
    public static void main(String[] args) {
//        System.out.println(System.getProperty("sun.boot.class.path").replace(';', '\n'));
//        System.out.println();
//        System.out.println(System.getProperty("java.ext.dirs").replace(';', '\n'));
//        System.out.println();
//        System.out.println(System.getProperty("java.class.path").replace(';', '\n'));
//        System.out.println();
//        System.out.println("ClassLoader is: " + ClassFileTest.class.getClassLoader());
//        System.out.println("ClassLoader is: " + int.class.getClassLoader());
//        System.out.println("ClassLoader is: " + String.class.getClassLoader());

//        System.out.println("Hello world");
//        List<Integer> list = new ArrayList<>();
//        list.add(1);
//        list.add(2);
//        list.add(3);
//        System.out.println(list.toString());
//        for (int x : list) {
//            System.out.println(x);
//        }
//        System.out.println(void.class.getName()); // void
//        System.out.println(boolean.class.getName()); // boolean
//        System.out.println(byte.class.getName()); // byte
//        System.out.println(char.class.getName()); // char
//        System.out.println(short.class.getName()); // short
//        System.out.println(int.class.getName()); // int
//        System.out.println(long.class.getName()); // long
//        System.out.println(float.class.getName()); // float
//        System.out.println(double.class.getName()); // double
//        System.out.println(Object.class.getName()); // java.lang.Object
//        System.out.println(int[].class.getName()); // [I
//        System.out.println(int[][].class.getName()); // [[I
//        System.out.println(Object[].class.getName()); // [Ljava.lang.Object;
//        System.out.println(Object[][].class.getName()); // [[Ljava.lang.Object;
//        System.out.println(Runnable.class.getName()); // java.lang.Runnable
//        System.out.println("abc".getClass().getName()); // java.lang.String
//        System.out.println(new double[0].getClass().getName()); // [D
//        System.out.println(new String[0].getClass().getName()); //[Ljava.lang.String;
//        int[] arr = new int[]{22, 84, 77, 11, 95, 9, 78, 56, 36, 97, 65, 36, 10, 24 ,92, 48};
//        bubbleSort(arr);
//        printArray(arr);
//        System.out.println("Hello world");
        new ClassFileTest().circumference(1.6f);
//        int x = fib(20);
//        System.out.println(x);

//        int sum = 0;
//        for (int i = 0; i <= 100; i++) {
//            sum += i;
//        }
//        System.out.println(sum);


//        int x = 32768;
//        ClassFileTest obj = new ClassFileTest();
//        obj.staticVar = x;
//        x = obj.staticVar;
//        obj.instanceVar = x;
//        x = obj.instanceVar;
//        Object o = obj;
//        if (o instanceof ClassFileTest) {
//            obj = (ClassFileTest) o;
//            System.out.println(obj.instanceVar);
//        }
    }

    public float circumference(float r) {
        float pi = 3.14f;
        float area = 2 * pi * r;
        return area;
    }

//    public static int fib(int n) {
//        return n <= 1 ? n : fib(n-1) + fib(n-2);
//    }
}