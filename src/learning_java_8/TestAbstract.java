package learning_java_8;

/**
 * Created by yaodh on 2018/8/6.
 */
public abstract class TestAbstract {
    abstract void test();
//    static void test() {}

    public static void main(String[] args) {
//        TestAbstract.test();
        test(new TestAbstract() {
            @Override
            void test() {

            }
        });
    }

    public static void test(TestAbstract f) {
        f.test();
    }
}
