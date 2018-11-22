package effective_java.generics;

import java.util.List;

/**
 * Created by yaodh on 2017/11/4.
 */
public class Test {
    public static void main(String[] args) {
        CollectionGeneric cg = new CollectionGeneric();
        cg.getClassName(cg.getClass());
        List<?>[] test = new List<?>[2];
    }
}
