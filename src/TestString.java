/**
 * Created by yaodh on 2018/9/12.
 */
public class TestString {
    public static void main(String[] args) {
        String s = "abc";
        System.out.println(System.identityHashCode(s));
        change(s);
        System.out.println(s);
    }

    public static void change(String s) {
        System.out.println(System.identityHashCode(s));
    }
}
