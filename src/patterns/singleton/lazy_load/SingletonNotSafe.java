package patterns.singleton.lazy_load;

/**
 * Created by yaodh on 2017/4/12.
 */
public class SingletonNotSafe {
    private static SingletonNotSafe instance;
    private SingletonNotSafe() { }

    public static SingletonNotSafe getInstance() {
        if (instance == null) {
            instance = new SingletonNotSafe();
        }
        return instance;
    }
}
