package shengsiyuan;

import java.util.UUID;

public class L6Test {


    public static final String str1 = "hello";
    public static final String str2 = "hello" + " world";
    public static final String str3 = new String("hello");
    public static final String str4 = String.valueOf(1);
    public static final String str5 = UUID.randomUUID().toString();


    public static void main(String[] args) {
        System.out.println(L6Test.str1);
        System.out.println(L6Test.str2);
        System.out.println(L6Test.str3);
        System.out.println(L6Test.str4);
        System.out.println(L6Test.str5);
    }
}
class MyParent2 {



    public static final int a = 1;
    public static final int b = a + a;

    static {
        System.out.println("MyParent2 static block");
    }
}