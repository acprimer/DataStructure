package address;

import sun.misc.Unsafe;
import utils.UnsafeUtils;

/**
 * Created by yaodh on 2018/2/8.
 */
public class MarkWordTest {
    public static final Unsafe unsafe = UnsafeUtils.getUnsafe();
    private static final boolean is64bit = false;
    private static final MemTest obj = new MemTest();

    private void test() {
//        System.out.println("hashCode(): " + this.hashCode() + " hex: " + Integer.toHexString(this.hashCode()));
        printAddress("Address", this);
        synchronized (this) {
            printAddress("Address", this);
//            System.out.println("hashCode(): " + obj.hashCode() + " hex: " + Integer.toHexString(obj.hashCode()));
//            printAddress("Address", obj);
        }
        printAddress("Address", this);
    }

    public static void main(String[] args) {
        String property = System.getProperty("sun.arch.data.model");
        System.out.println(property);
        new MarkWordTest().test();
    }

    public static void printAddress(String label, Object... objects) {
        long last = 0;
        int offset = unsafe.arrayBaseOffset(objects.getClass());
        int scale = unsafe.arrayIndexScale(objects.getClass());
        System.out.printf("offset %d scale %d\n", offset, scale);
        System.out.print(label + ": 0x");
        switch (scale) {
            case 4:
                long factor = is64bit ? 8 : 1;
                final long i1 = (unsafe.getInt(objects, offset) & 0xFFFFFFFFL) * factor;
                System.out.println(Long.toHexString(i1));
//                unsafe.putAddress(i1, 100L);

                for (int i = 0; i < 4; i++) {
                    System.out.println(" getAddress " + unsafe.getInt(i1 + i * 4) + " 0x"
                            + Integer.toHexString(unsafe.getInt(i1 + i * 4)) + " 0b: "
                            + Integer.toBinaryString(unsafe.getInt(i1 + i * 4)));
                }
                System.out.println();

                int x = unsafe.getInt(i1);
                System.out.println("25 bit hash code : " + Integer.toHexString(x >>> 7));

//                int y = unsafe.getInt(x >> 2);
//                System.out.println("pointer " + y);

//                System.out.println(" getAddress " + unsafe.getInt(i1) + " 0x" + Integer.toHexString(unsafe.getInt(i1)) + " 0b: " + Integer.toBinaryString(unsafe.getInt(i1)));
//                System.out.println(" getAddress " + unsafe.getInt(i1+4) + " 0x" + Integer.toHexString(unsafe.getInt(i1+4)) + " 0b: " + Integer.toBinaryString(unsafe.getInt(i1+4)));
//                System.out.println(" getAddress " + unsafe.getInt(i1+8) + " 0x" + Integer.toHexString(unsafe.getInt(i1+4)) + " 0b: " + Integer.toBinaryString(unsafe.getInt(i1+4)));
//                System.out.println(" getAddress " + unsafe.getLong(i1 + 4));
//                System.out.println(" getAddress " + unsafe.getInt(i1 + 12));
//                System.out.println(" getAddress " + unsafe.getInt(i1 + 16) + " 0x" + Long.toHexString(8*unsafe.getLong(i1 + 16)));
//                System.out.println(" getAddress " + unsafe.getInt(i1 + 20));
//                System.out.println(" getAddress " + unsafe.getLong(i1 + 24));
//                System.out.println(" getAddress hex: " + Long.toHexString(8*unsafe.getLong(i1 + 24)));
//                System.out.println(" getAddress " + unsafe.getInt(i1 + 28));
//                last = i1;
//                for (int i = 1; i < objects.length; i++) {
//                    final long i2 = (unsafe.getInt(objects, offset + i * 4) & 0xFFFFFFFFL) * factor;
//                    if (i2 > last)
//                        System.out.print(", +" + Long.toHexString(i2 - last));
//                    else
//                        System.out.print(", -" + Long.toHexString( last - i2));
//                    last = i2;
//                }
                break;
            case 8:
                throw new AssertionError("Not supported");
        }
        System.out.println();
    }
}
