import com.sun.jmx.remote.internal.ArrayQueue;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class TwinPrime {
    private static final int MAX = 10000;

    // 找到大于limit的孪生素数中较大的那个数
    private static int twinPrime(int limit) {
        boolean[] prime = new boolean[MAX];
        Arrays.fill(prime, true);
        prime[0] = prime[1] = false;
        int p = 0;
        for (int i = 2; i < MAX; i++) {
            if (!prime[i]) continue;
            if (i > limit) {
                if (p > 0 && i - p == 2) {
                    return i;
                } else {
                    p = i;
                }
            }
            for (int j = i * i; j < MAX; j += i) {
                prime[j] = false;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int large = twinPrime(500);
        int small = large - 2;
        // 521 523
        System.out.println(small + " " + large);
    }
}
