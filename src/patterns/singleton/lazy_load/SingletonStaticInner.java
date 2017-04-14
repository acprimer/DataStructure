package patterns.singleton.lazy_load;

/**
 * Created by yaodh on 2017/4/12.
 */
public class SingletonStaticInner {
    private SingletonStaticInner() { }
    public static final SingletonStaticInner getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final SingletonStaticInner INSTANCE = new SingletonStaticInner();
    }
}
