import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Created by yaodh on 2017/6/28.
 */
public class ObjectMemoryAddress {
    private static final Unsafe unsafe = getUnsafe();
    private static final boolean is64bit = true;

    public static void main(String[] args) {
        ObjectMemoryAddress obj = new ObjectMemoryAddress();
        System.out.println("hashCode(): " + obj.hashCode() + " hex: " + Integer.toHexString(obj.hashCode()));
        System.out.println("identityHashCode(): " + System.identityHashCode(obj));
        System.out.println("toString(): " + obj);
        printAddress("Address", obj);
        System.out.println();

        String str = new String("Hello");
        System.out.println("hashCode(): " + str.hashCode() + " hex: " + Integer.toHexString(str.hashCode()));
        System.out.println("identityHashCode(): " + System.identityHashCode(str));
        System.out.println("toString(): " + str);
        printAddress("Address", str);



        long z = 100L;
        printAddress("Address", z);

        System.out.println(unsafe.arrayIndexScale(boolean[].class));
        System.out.println(unsafe.arrayIndexScale(byte[].class));
        System.out.println(unsafe.arrayIndexScale(char[].class));
        System.out.println(unsafe.arrayIndexScale(int[].class));
        System.out.println(unsafe.arrayIndexScale(long[].class));
        System.out.println(unsafe.arrayIndexScale(Object[].class));
        System.out.println(unsafe.addressSize());
        System.out.println(System.getProperty("sun.arch.data.model"));

        System.out.print("total mem=");
        System.out.println(Runtime.getRuntime().totalMemory() / 1024.0 / 1024 + "M");

        System.out.print("Xmx=");
        System.out.println(Runtime.getRuntime().maxMemory() / 1024.0 / 1024 + "M");

        int x = 5, y = 10;
        printAddress("Address x y ", x, y);
        System.out.println(Long.toHexString(addressOf(str)));

        MemTest memTest = new MemTest();
        MemTest.Inner inner = memTest.new Inner();
//        memTest.next = new MemTest();
        System.out.println("hash code: 0x" + Integer.toHexString(new Object().hashCode()));
        System.out.println("hash code: 0x" + Integer.toHexString(memTest.hashCode()) + " 0b:" + Integer.toBinaryString(memTest.hashCode()));
        System.out.println("hash code: 0x" + Integer.toHexString(memTest.hashCode()) + " 0b:" + Integer.toBinaryString(memTest.hashCode()));
        printAddress("mem: ", memTest);
//        printAddress("mem next: ", memTest.next);
        printAddress("mem inner: ", inner);

        System.out.println();

        long memoryAddress = unsafe.allocateMemory(8);
        unsafe.putAddress(memoryAddress, 5);
        System.out.println(Long.toHexString(memoryAddress) + ": " + unsafe.getAddress(memoryAddress));

//        System.out.println(new ParkMillerRNG().random());
//        System.out.println(new ParkMillerRNG().random());
//        long code = 1;
//        while (code != memTest.hashCode()) {
//            code = new ParkMillerRNG().random();
//            System.out.println("0x" + Long.toHexString(code));
//        }
    }

    public static long addressOf(Object obj) {
        Object[] arr = new Object[]{obj};
        long baseOffset = unsafe.arrayBaseOffset(arr.getClass());
        int addressSize = unsafe.addressSize();
        long objAddress;
        switch (addressSize) {
            case 4:
                objAddress = unsafe.getInt(arr, baseOffset);
                break;
            case 8:
                objAddress = unsafe.getLong(arr, baseOffset);
                break;
            default:
                throw new AssertionError("unsupported address size: " + addressSize);
        }
//        System.out.println(" getAddress " + unsafe.getAddress(objAddress));
        return objAddress;
    }

    public static void printAddress(String label, Object... objects) {
        System.out.print(label + ": 0x");
        long last = 0;
        int offset = unsafe.arrayBaseOffset(objects.getClass());
        int scale = unsafe.arrayIndexScale(objects.getClass());
        switch (scale) {
            case 4:
                long factor = is64bit ? 8 : 1;
                final long i1 = (unsafe.getInt(objects, offset) & 0xFFFFFFFFL) * factor;
                System.out.println(Long.toHexString(i1));
//                unsafe.putAddress(i1, 100L);
                System.out.println(" getAddress " + unsafe.getInt(i1) + " 0x" + Integer.toHexString(unsafe.getInt(i1)) + " 0b: " + Integer.toBinaryString(unsafe.getInt(i1)));
                System.out.println(" getAddress " + unsafe.getLong(i1 + 4));
                System.out.println(" getAddress " + unsafe.getInt(i1 + 12));
                System.out.println(" getAddress " + unsafe.getInt(i1 + 16) + " 0x" + Long.toHexString(8*unsafe.getLong(i1 + 16)));
                System.out.println(" getAddress " + unsafe.getInt(i1 + 20));
                System.out.println(" getAddress " + unsafe.getLong(i1 + 24));
                System.out.println(" getAddress hex: " + Long.toHexString(8*unsafe.getLong(i1 + 24)));
//                System.out.println(" getAddress " + unsafe.getInt(i1 + 28));
                last = i1;
                for (int i = 1; i < objects.length; i++) {
                    final long i2 = (unsafe.getInt(objects, offset + i * 4) & 0xFFFFFFFFL) * factor;
                    if (i2 > last)
                        System.out.print(", +" + Long.toHexString(i2 - last));
                    else
                        System.out.print(", -" + Long.toHexString( last - i2));
                    last = i2;
                }
                break;
            case 8:
                throw new AssertionError("Not supported");
        }
        System.out.println();
    }

    private static Unsafe getUnsafe() {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            return (Unsafe) theUnsafe.get(null);
        } catch (Exception e) {
            throw new AssertionError(e);
        }
    }
}
