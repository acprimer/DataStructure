package patterns.singleton.lazy_load;

/**
 * Created by yaodh on 2017/4/12.
 */
public class SingletonWithSynchronized {
    private static SingletonWithSynchronized instance;
    private SingletonWithSynchronized() { }

    public synchronized static SingletonWithSynchronized getInstance() {
        if (instance == null) {
            instance = new SingletonWithSynchronized();
        }
        return instance;
    }
}
