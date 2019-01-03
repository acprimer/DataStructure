package jvm;

import sun.misc.Unsafe;
import utils.UnsafeUtils;

import java.lang.reflect.Field;

public class StackFrameModifyTest {
    public static void main(String[] args) {
        StackFrameModifyTest test = new StackFrameModifyTest();
        test.init();

        test.testSwap();
    }

    private void testSwap() {
        Integer a = 1;
        Integer b = 2;
        System.out.printf("a = %s, b = %s\n", a, b);
        swap(a, b);
        System.out.printf("a = %s, b = %s\n", a, b);
        Integer c = 1;
        System.out.println(c);
    }

    private void init() {
        int x = 123;
        System.out.println(x);
        modify();
        System.out.println(x);
    }

    // 必须加synchronized：在this上加了synchronized才能使用轻量级锁
    // 轻量级锁的对象头mark_word中保存的是当前栈帧的地址
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

    public void swap(Integer a, Integer b) {
        long magic = 0x12345678;
        long addr;
        Unsafe unsafe = UnsafeUtils.getUnsafe();
        Object obj = new ObjectA();
        synchronized (obj) {
            addr = unsafe.getLong(obj, 0L);
        }
        long addrA, addrB;
        int index = 0;
        while (true) {
            if (unsafe.getLong(addr + index * 8) == magic
                    && unsafe.getLong(addr + (index + 1) * 8) == 0) {
                addrA = unsafe.getLong(addr + (index + 2) * 8); // a的地址
                addrB = unsafe.getLong(addr + (index + 3) * 8); // b的地址
                index+=4;
                break;
            }
            index++;
        }
        while (true) {
            if (unsafe.getLong(addr + index * 8) == addrA) {
                unsafe.putLong(addr + index * 8, addrB);
                index++;
                unsafe.putLong(addr + index * 8, addrA);
                break;
            }
            index++;
        }
    }

    // http://www.imooc.com/article/71970%20?mc_marking=40850a169d09e7ed8caffa45482c6266&mc_channel=weibo#
    public void swap2(Integer a, Integer b) {
        int temp = a.intValue();
        try {
            Field value = Integer.class.getDeclaredField("value");
            value.setAccessible(true);
            value.set(a, b);
            value.set(b, new Integer(temp));

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
