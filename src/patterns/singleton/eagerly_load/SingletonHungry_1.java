package patterns.singleton.eagerly_load;

/**
 * Created by yaodh on 2017/4/7.
 */

/**
 * singleton with public final field.
 * 优点：简洁
 */
public class SingletonHungry_1 implements Cloneable {

    // !!! public !!!
    // 无需getInstance()方法，其他类可以直接拿到instance实例。
    // final关键字保证了instance实例不会被修改。
    public static final SingletonHungry_1 INSTANCE = new SingletonHungry_1();

    // private constructor
    private SingletonHungry_1() { }

    public void print() {
        System.out.println("I'm Singleton");
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        // 为了防止克隆，可以直接抛出异常
//        throw new CloneNotSupportedException();

        // 也可以返回自身，防止拷贝对象
        return this;
    }
}
