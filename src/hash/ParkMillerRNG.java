package hash;

/**
 * Created by yaodh on 2017/6/28.
 */
public class ParkMillerRNG {
    static long _rand_seed = 1;
    long random() {
          /* standard, well-known linear congruential random generator with
           * next_rand = (16807*seed) mod (2**31-1)
           * see
           * (1) "Random Number Generators: Good Ones Are Hard to Find",
           *      S.K. Park and K.W. Miller, Communications of the ACM 31:10 (Oct 1988),
           * (2) "Two Fast Implementations of the 'Minimal Standard' Random
           *     Number Generator", David G. Carta, Comm. ACM 33, 1 (Jan 1990), pp. 87-88.
          */
        final long a = 16807;
        final long m = 2147483647;
        final long q = m / a;
//        assert (q == 127773,"weird math");
        final long r = m % a;
//        assert (r == 2836,"weird math");

        // compute az=2^31p+q
        long lo = a * (_rand_seed & 0xFFFF);
        long hi = a * (_rand_seed >> 16);
        lo += (hi & 0x7FFF) << 16;

        // if q overflowed, ignore the overflow and increment q
        if (lo > m) {
            lo &= m;
            ++lo;
        }
        lo += hi >> 15;

        // if (p+q) overflowed, ignore the overflow and increment (p+q)
        if (lo > m) {
            lo &= m;
            ++lo;
        }
        return (_rand_seed = lo);
    }
}
