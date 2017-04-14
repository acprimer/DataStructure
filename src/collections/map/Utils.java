package collections.map;

/**
 * Created by yaodh on 2016/10/10.
 */
public class Utils {

    public static int roundUpToPowerOfTwo(int i) {
        i--;

        i |= i >>> 1;
        i |= i >>> 2;
        i |= i >>> 4;
        i |= i >>> 8;
        i |= i >>> 16;

        return i + 1;
    }

    public static int secondaryHash(Object key) {
        return secondaryHash(key.hashCode());
    }

    public static int secondaryHash(int h) {
        h += (h <<  15) ^ 0xffffcd7d;
        h ^= (h >>> 10);
        h += (h <<   3);
        h ^= (h >>>  6);
        h += (h <<   2) + (h << 14);
        return h ^ (h >>> 16);
    }
}
