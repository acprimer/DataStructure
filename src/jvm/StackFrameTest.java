package jvm;

import sun.misc.Unsafe;
import utils.UnsafeUtils;

public class StackFrameTest {
    public static void main(String[] args) {
        int a = 10;
        int b = 11;
        int c = a + b;
    }

    private void init() {
        int x = 123;
        System.out.println(x);
        modify();
        System.out.println(x);
    }

    private void fun() {
        int a = 1;
    }

    private synchronized void modify() {
        Unsafe unsafe = UnsafeUtils.getUnsafe();
        long addr = unsafe.getLong(this, 0L);
        for (int i = 0; ; i++) {
            if (unsafe.getLong(addr + i * 8) == 123) {
                unsafe.putLong(addr + i * 8, 456);
                break;
            }
        }
    }
}
