package threadlocal;

/**
 * Created by yaodh on 2017/8/13.
 */
public class HashTest {
    public static void main(String[] args) {
        System.out.println(0x61c88647);

        System.out.println((1L<<32) * 0.618);

        System.out.println((1L<<31)-1);

        System.out.println(2654435769L & (~(1L<<31)));
        System.out.println(2654435769L & ((1L<<31) - 1));
        System.out.println((int)2654435769L);
        System.out.println(506952121L+1640531527L);


        System.out.println();
        System.out.println(Long.toHexString(2654435769L));
        System.out.println(Long.toHexString(-1640531527L));
        System.out.println(Long.toHexString(1640531527L));
        System.out.println(Long.toHexString(506952121L));

        System.out.println((Math.sqrt(5.0) - 1) / 2);

        // http://www.javaspecialists.eu/archive/Issue164.html
        long l1 = (long) ((1L << 31) * (Math.sqrt(5) - 1));
        System.out.println("as 32 bit unsigned: " + l1);
        int i1 = (int) l1;
        System.out.println("as 32 bit signed:   " + i1);
        System.out.println("MAGIC = " + 0x61c88647);


        int len = 16;
        YThreadLocal threadLocal = new YThreadLocal();
        System.out.println(threadLocal.getHashCode() & (len-1));

        YThreadLocal threadLocal1 = new YThreadLocal();
        System.out.println(threadLocal1.getHashCode() % len);

        System.out.println(8>>1);
        System.out.println(-8>>1);
        System.out.println(-8>>>1);
    }
}
