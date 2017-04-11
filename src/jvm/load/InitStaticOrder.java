package jvm.load;

/**
 * Created by yaodh on 2017/4/11.
 */
public class InitStaticOrder {
    public static InitStaticOrder instance = new InitStaticOrder();
    private static int n1;
    private static int n2 = 0;

    private InitStaticOrder() {
        n1 = 10;
        n2 = 5;
        System.out.println(this);
    }

    @Override
    public String toString() {
        return super.toString() + " n1= " + n1 + " n2= " + n2;
    }
}
