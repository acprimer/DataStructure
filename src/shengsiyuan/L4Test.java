package shengsiyuan;

public class L4Test {
    static {
        System.out.println("L4Test static block");
    }
    public static void main(String[] args) {
        System.out.println(MyChild.str2);
    }
}

class MyParent {
    public static String str = "hello";
    static {
        System.out.println("MyParent static block");
    }
}

class MyChild extends MyParent {
    public static String str2 = "welcome";
    static {
        System.out.println("MyChild static block");
    }
}