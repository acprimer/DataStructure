package patterns.singleton.eagerly_load;

/**
 * Created by yaodh on 2018/5/28.
 */
public class InitOrder {
    public static int z = 6;
    private int y = fun2();
    private static int x = fun();
    {
        z = 7;
        System.out.println("init block");
    }

    static {
        z = 8;
        System.out.println("static init block");
    }

    private static int fun() {
        System.out.println("init x = 4");
        return 4;
    }

    private int fun2() {
        System.out.println("init y = 5");
        return 5;
    }

    public InitOrder() {
        System.out.println("init start");
        System.out.println("init end");
    }
}
