package patterns.singleton.lazy_load;

/**
 * Created by yaodh on 2017/4/12.
 */
public class SingletonDCL {
    private static volatile SingletonDCL instance;
    private SingletonDCL() { }

    public static SingletonDCL getInstance() {
        if (instance == null) {
            synchronized (SingletonDCL.class) {
                if (instance == null) {
                    instance = new SingletonDCL();
                }
            }
        }
        return instance;
    }
}
