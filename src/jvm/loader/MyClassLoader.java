package jvm.loader;

import sun.misc.VM;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by yaodh on 2017/4/11.
 */
public class MyClassLoader extends ClassLoader {
    private String byteCodePath;

    static {
//        boolean result = registerAsParallelCapable();
//        System.out.println("MyClassLoader registerAsParallelCapable " + result);
    }

    public MyClassLoader(ClassLoader parent) {
        super(parent);
    }

    public MyClassLoader(String path) {
        byteCodePath = path;
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        return findClass(name);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] val = null;
        BufferedInputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(byteCodePath + name + ".class"));
            val = new byte[in.available()];
            in.read(val);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return defineClass(val, 0, val.length);
    }

    // true if the name is null or has the potential to be a valid binary name
    public boolean checkName(String name) {
        if ((name == null) || (name.length() == 0))
            return true;
        if ((name.indexOf('/') != -1)
                || (!VM.allowArraySyntax() && (name.charAt(0) == '[')))
            return false;
        return true;
    }
}
