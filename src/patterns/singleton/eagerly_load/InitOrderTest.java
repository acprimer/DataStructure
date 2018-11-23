package patterns.singleton.eagerly_load;

/**
 * Created by yaodh on 2018/5/28.
 */
public class InitOrderTest {
    public static void main(String[] args) {
//        System.out.println(InitOrder.z);
//        InitOrder order = new InitOrder();
//        System.out.println(InitOrder.z);
//
//        byte a = (byte) -100;
//        byte b = (byte) -100;
//        byte c = (byte) (a + b);
//
//        System.out.printf("c=%s\n", Byte.toString(c));

        int c = 300;
        Integer a = new Integer(c);
        Integer b = c;
        Integer d = Integer.valueOf(c);
        System.out.println(a == b);
        System.out.println(a == c);
        System.out.println(b == d);
        System.out.println(b.equals(d));
    }

    int function(int n, int h, int z) {
        return 0;
    }
}
