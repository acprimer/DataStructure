package hash;

/**
 * Created by yaodh on 2017/8/20.
 */
public class Marsaglis_XORShift {
    long random(long x) {
        long y = 842502087, z = 0x8767, w = 273326509;
        long t;
        t = (x ^ (x << 11));
        x = y;
        y = z;
        z = w;
        return (w = (w ^ (w >> 19)) ^ (t ^ (t >> 8)));
    }
}
