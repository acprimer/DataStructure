package patterns.singleton.lazy_load;

/**
 * Created by yaodh on 2017/4/12.
 */
public enum  SingletonEnum {
    INSTANCE;

    SingletonEnum() {
        System.out.println("constructor");
    }

    public void doSomething() {
        System.out.println("do some other things...");
    }
}
