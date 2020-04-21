package jvm.loader;

public class LoadOrder {
//    public static LoadOrder order1 = new LoadOrder();
//    public static LoadOrder order2 = new LoadOrder();

    public LoadOrder() {
        System.out.println("构造器");
    }

    {
        System.out.println("构造代码块");
    }

    static {
        System.out.println("静态代码块");
    }

    public static void main(String[] args) {
//        new LoadOrder();
        MyClass c = null;
    }

    public String minWindow(String s, String t) {
        return "";
    }
}
class MyClass {
    static {
        System.out.println("啥啥啥");
    }
}