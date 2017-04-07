package patterns.singleton;

/**
 * Created by yaodh on 2017/4/7.
 */

/**
 * singleton with static factory.
 * 优点：简洁
 */
public class SingletonHungry_3 {

    private static final SingletonHungry_3 instance;

    // 利用静态块初始化
    static {
        instance = new SingletonHungry_3();
    }

    // private constructor
    private SingletonHungry_3() { }

    public static SingletonHungry_3 getInstance() {
        return instance;
    }
}
