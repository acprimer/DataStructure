package patterns.singleton.eagerly_load;

/**
 * Created by yaodh on 2017/4/7.
 */

/**
 * singleton with public final field.
 * 优点：简洁
 */
public class SingletonHungry_1 {

    // !!! public !!!
    // 无需getInstance()方法，其他类可以直接拿到instance实例。
    // final关键字保证了instance实例不会被修改。
    public static final SingletonHungry_1 instance = new SingletonHungry_1();

    // private constructor
    private SingletonHungry_1() { }
}
