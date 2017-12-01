package jvm.loader;

/**
 * Created by yaodh on 2017/4/11.
 */
public class MySubClassLoader extends MyClassLoader {
    private String byteCodePath;

    static {
        boolean result = registerAsParallelCapable();
        System.out.println("MySubClassLoader registerAsParallelCapable " + result);
    }

    public MySubClassLoader(ClassLoader parent) {
        super(parent);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        throw new ClassNotFoundException();
    }
}
