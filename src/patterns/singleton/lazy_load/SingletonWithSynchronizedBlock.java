package patterns.singleton.lazy_load;

/**
 * Created by yaodh on 2017/4/12.
 */
public class SingletonWithSynchronizedBlock {
    private static SingletonWithSynchronizedBlock instance;
    private SingletonWithSynchronizedBlock() { }

    public static SingletonWithSynchronizedBlock getInstance() {
        if (instance == null) {
            synchronized (SingletonWithSynchronizedBlock.class) {
                instance = new SingletonWithSynchronizedBlock();
            }
        }
        return instance;
    }
}
