package hash;

/**
 * Created by yaodh on 2017/8/20.
 */
public class TestHash {
    public static void main(String[] args) {
        String str = "hello";
        System.out.println(str.hashCode());
        System.out.println(System.identityHashCode(str));

        RandomHash randomHash = new RandomHash();
        System.out.println(randomHash.hashCode());
        System.out.println(randomHash.hashCode());
        System.out.println(System.identityHashCode(randomHash));
        System.out.println(System.identityHashCode(randomHash));
    }
}
