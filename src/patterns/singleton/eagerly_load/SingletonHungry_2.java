package patterns.singleton.eagerly_load;

/**
 * Created by yaodh on 2017/4/7.
 */

/**
 * singleton with static factory.
 * 优点：简洁
 */
public class SingletonHungry_2 {

    private static final SingletonHungry_2 instance = new SingletonHungry_2();

    public static int c2 = 0;
    static {
        c1 = 4;
        System.out.println("static block");
    }
    public static int c1;

    // private constructor
    private SingletonHungry_2() {
        c1++;
        c2++;
        System.out.println("new SingletonHungry_2");
        System.out.println(this.toString());
    }

    // 静态工厂方法
    public static SingletonHungry_2 getInstance() {
        return instance;
    }

    @Override
    public String toString() {
        return "c1 = " + c1 + " c2 = " + c2;
    }
}
