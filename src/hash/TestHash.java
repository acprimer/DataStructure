package hash;

/**
 * Created by yaodh on 2017/8/20.
 */
public class TestHash {
    public static void main(String[] args) {
//        String str = "hello";
        Object obj = new Object();
        System.out.println(obj.hashCode());
        System.out.println(System.identityHashCode(obj));
        System.out.println(Integer.toHexString(new Object().hashCode()));
        System.out.println(Integer.toHexString(new Object().hashCode()));
        System.out.println(Integer.toHexString(new Object().hashCode()));
        System.out.println(Integer.toHexString(new Object().hashCode()));
        System.out.println(Integer.toHexString(new Object().hashCode()));
        System.out.println(Integer.toHexString(new Object().hashCode()));
        System.out.println(Integer.toHexString(new Object().hashCode()));
        System.out.println(Integer.toHexString(new Object().hashCode()));
        System.out.println(Integer.toHexString(new Object().hashCode()));
        System.out.println(Integer.toHexString(new Object().hashCode()));
        System.out.println(Integer.toHexString(new Object().hashCode()));

//        RandomHash randomHash = new RandomHash();
//        System.out.println(randomHash.hashCode());
//        System.out.println(randomHash.hashCode());
//        System.out.println(System.identityHashCode(randomHash));
//        System.out.println(System.identityHashCode(randomHash));
    }
}
