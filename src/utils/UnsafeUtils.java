package utils;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Created by yaodh on 2018/2/5.
 */
public class UnsafeUtils {
    public static Unsafe getUnsafe() {
        Unsafe unsafe = null;
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            unsafe = (Unsafe) f.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return unsafe;
    }

    private static class UnsafeTest {
        int n;
        float f;
        long ln;
        double d;

        @Override
        public String toString() {
            return String.format("n %d f %.3f long %d d %.3f", n,f,ln,d);
        }
    }

    public static void main(String[] args) {
        Unsafe unsafe = getUnsafe();
        UnsafeTest test = new UnsafeTest();
        System.out.println(test);
        long[] offset = new long[4];
        Field[] fields = UnsafeTest.class.getDeclaredFields();
        int i = 0;
        for (Field field : fields) {
            offset[i++] = unsafe.objectFieldOffset(field);
        }
        unsafe.putInt(test, offset[0], 1);
        unsafe.putFloat(test, offset[1], 2.f);
        unsafe.putLong(test, offset[2], 3L);
        unsafe.putDouble(test, offset[3], 4.0);
        System.out.println(test);
    }
}
