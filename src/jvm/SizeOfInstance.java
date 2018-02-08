package jvm;

import sun.misc.Unsafe;
import utils.UnsafeUtils;

import java.lang.reflect.Field;

/**
 * Created by yaodh on 2017/3/22.
 */
public class SizeOfInstance {
    public static void main(String[] args) {
        Field[] fields = ObjectA.class.getDeclaredFields();
        Unsafe unsafe = UnsafeUtils.getUnsafe();
        for (Field f : fields) {
            System.out.println(f.getName() + " offset: " + unsafe.objectFieldOffset(f));
        }
    }
}
