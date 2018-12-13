package jvm.loader;

public class LoadOrder {
    public static LoadOrder order1 = new LoadOrder();
    public static LoadOrder order2 = new LoadOrder();

    {
        System.out.println("构造代码块");
    }

    static {
        System.out.println("静态代码块");
    }

    public static void main(String[] args) {
        new LoadOrder();
    }

    public String minWindow(String s, String t) {
        return "";
    }
}
