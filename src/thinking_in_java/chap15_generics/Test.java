package thinking_in_java.chap15_generics;

/**
 * Created by yaodh on 2018/9/15.
 */
public class Test {
    public static void change(String s) {
        s = "abd";
    }
    public static void change(int x) {
        x = 10;
    }
    public static void main(String[] args) {
        String s = "abc";
        change(s);
        System.out.println(s);
        int x = 5;
        change(x);
        System.out.println(x);
    }
}
