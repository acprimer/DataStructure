package reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ConstructorTest {
    private String name;

    public ConstructorTest() {
    }

    public ConstructorTest(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        try {
            Constructor<?> cons = ConstructorTest.class.getConstructor();
            Object obj = cons.newInstance();
        } catch (NoSuchMethodException
                | IllegalAccessException
                | InstantiationException
                | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
