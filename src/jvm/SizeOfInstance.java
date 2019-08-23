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

        // 通过Unsafe类修改成员变量
        try {
            ObjectA a = new ObjectA();
            System.out.println(a.i1);
            Field fi1 = ObjectA.class.getDeclaredField("i1");
            unsafe.putInt(a, unsafe.objectFieldOffset(fi1), 5);
            System.out.println(a.i1);

            System.out.println(a.getI2());
            Field fi2 = ObjectA.class.getDeclaredField("i2");
            unsafe.putInt(a, unsafe.objectFieldOffset(fi2), 5);
            System.out.println(a.getI2());

            System.out.println(a.getMyFinalInt());
            Field fi3 = ObjectA.class.getDeclaredField("myFinalInt");
            unsafe.putInt(a, unsafe.objectFieldOffset(fi3), 0);
            System.out.println(a.getMyFinalInt());
            int x = (int) fi3.get(a);
            System.out.println(x);
//            int x = unsafe.getInt(a, unsafe.objectFieldOffset(fi3));
//            System.out.println(x);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();

        }

        System.out.println("=============");

        // 通过反射修改成员变量
        try {
            ObjectA a = new ObjectA();
            Field fi1 = ObjectA.class.getDeclaredField("i1");
            fi1.set(a, 5);
            System.out.println(a.i1);

            System.out.println(a.getI2());
            Field fi2 = ObjectA.class.getDeclaredField("i2");
            fi2.setAccessible(true);
            fi2.set(a, 5);
            System.out.println(a.getI2());

            System.out.println(a.getMyFinalInt());
            Field fi3 = ObjectA.class.getDeclaredField("myFinalInt");
            fi3.setAccessible(true);
            fi3.set(a, 0);
            System.out.println(a.getMyFinalInt());
            int x = (int) fi3.get(a);
            System.out.println(x);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


    }
}
