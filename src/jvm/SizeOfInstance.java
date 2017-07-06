package jvm;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Created by yaodh on 2017/3/22.
 */
public class SizeOfInstance {
    public static void main(String[] args) {
        Field[] fields = ObjectA.class.getDeclaredFields();
        Unsafe unsafe = null;
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            unsafe = (Unsafe) f.get(null);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        for (Field f : fields) {
            System.out.println(f.getName() + " offset: " + unsafe.objectFieldOffset(f));
        }
    }
}
