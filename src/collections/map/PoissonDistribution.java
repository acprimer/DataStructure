package collections.map;

/**
 * Created by yaodh on 2017/9/7.
 */
public class PoissonDistribution {
    public static void main(String[] args) {
        double lambda = 0.5f;
        long fraction = 1;
        for (int i = 0; i < 10; i++) {
            double p = Math.exp(-lambda) * Math.pow(lambda, i) / fraction;
            fraction *= (i+1);
            System.out.printf("%2d: %.10f\n", i, p);
        }
    }
}
