package jvm;

import sun.misc.Unsafe;
import utils.UnsafeUtils;

import java.lang.reflect.Field;
import java.util.Scanner;

/**
 * Created by yaodh on 2017/3/22.
 */
public class SizeOfInstance {
    private final Object lock = new ObjectA();
    public static void main(String[] args) {
//        ObjectA obja = new ObjectA();
//        Field[] fields = ObjectA.class.getDeclaredFields();
        Unsafe unsafe = UnsafeUtils.getUnsafe();
//        for (Field f : fields) {
//            System.out.println(f.getName() + " offset: " + unsafe.objectFieldOffset(f));
//        }
//        System.out.println(Thread.currentThread().getId());
//        Scanner sc = new Scanner(System.in);
//        while (sc.hasNext()) {
//            String line  = sc.nextLine();
//        }

        SizeOfInstance sizeOfInstance = new SizeOfInstance();
        int hashCode = sizeOfInstance.hashCode();
        System.out.println(Integer.toHexString(hashCode));
        long l = unsafe.getLong(sizeOfInstance, 0);
        System.out.println(Long.toHexString(l));
        sizeOfInstance.fun();
    }

    private synchronized void fun() {
        System.out.println("In Lock");
    }
}
