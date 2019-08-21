package shengsiyuan;

public class L7Test {
    public static void main(String[] args) {
        MyParent3[] arr = new MyParent3[1];
        int[] nums = new int[1];
        System.out.println(MyParent3.x);
        System.out.println(N.x);
    }
}

class MyParent3 {
    static int y;
    static {
        x = 1;
    }
    static int x = 1;
    static String s = "a";
    static String t = "b";
    static String z = s + t;
    static {
        x = 2;
        System.out.println("MyParent3 static block");
    }
}
interface M {
    int x = 1;
}
interface N extends M {
    int y = 1;
}