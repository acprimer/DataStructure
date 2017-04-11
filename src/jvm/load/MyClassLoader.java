package jvm.load;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by yaodh on 2017/4/11.
 */
public class MyClassLoader extends ClassLoader{
    private String byteCodePath;

    public MyClassLoader(String path) {
        byteCodePath = path;
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
}
