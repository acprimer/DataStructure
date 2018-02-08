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
}
