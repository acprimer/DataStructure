package patterns.singleton.eagerly_load;

public class TestLoadTime {
    public static void main(String[] args) {
        System.out.println("Sleep 1s: before");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Sleep 1s: after");
        new TestLoadTime().test();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            // 用这一行代码申请超大的内存空间，模拟OOM进行GC
            Object[] ignored = new Object[5000000];
        } catch (OutOfMemoryError e) {
            // Ignore
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void test() {
        SingletonHungry_1 singleton = SingletonHungry_1.INSTANCE;
        singleton.print();
    }
}

// VM参数 跟踪类的加载和卸载
// -XX:+TraceClassLoading