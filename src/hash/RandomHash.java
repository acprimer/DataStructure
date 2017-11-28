package hash;

import java.util.Random;

/**
 * Created by yaodh on 2017/10/25.
 */
public class RandomHash {
    @Override
    public int hashCode() {
        return new Random().nextInt();
    }
}
