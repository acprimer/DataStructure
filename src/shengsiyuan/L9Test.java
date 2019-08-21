package shengsiyuan;

public class L9Test {
    public static void main(String[] args) {
//        System.out.println(MyInterface9.x);   // 1
//        System.out.println(MyInterface9.obj); // 2
        new MyChild9().fun();  // 3
    }
}

interface MyInterface9 {
    public static final int x = 9;
    public static final Object obj = new Object() {
        {
            System.out.println("MyInterface9 init");
        }
    };
    public void fun();
}

class MyChild9 implements MyInterface9 {

    @Override
    public void fun() {
        System.out.println("MyChild9 fun");
    }
}