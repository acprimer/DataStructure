package collections.map;

import sun.misc.Unsafe;
import utils.UnsafeUtils;

/**
 * Created by yaodh on 2018/3/25.
 */
public class ConcurrentTest {
    public static void main(String[] args) {
        int initialCapacity = 40;
        int concurrencyLevel = 8;
        int sshift = 0;
        int ssize = 1;
        while (ssize < concurrencyLevel) {
            ++sshift;
            ssize <<= 1;
        }
        System.out.println(sshift);
        System.out.println(ssize);

        int c = initialCapacity / ssize;
        if (c * ssize < initialCapacity) {
            ++c;
        }
        System.out.println(c);

        int cap = 2;
        while (cap < c) {
            cap <<= 1;
        }
        System.out.println(cap);

        Unsafe unsafe = UnsafeUtils.getUnsafe();

    }


}
