package hash;

/**
 * Created by yaodh on 2017/8/20.
 */
public class TestHash {
    public static void main(String[] args) {
        String str = "hello";
        System.out.println(str.hashCode());
        System.out.println(System.identityHashCode(str));
    }
}
