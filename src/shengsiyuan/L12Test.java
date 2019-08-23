package shengsiyuan;

public class L12Test {
    public static void main(String[] args) {
        System.out.println(Child12.x);
        System.out.println(Parent12.x);
    }
}

class Parent12 {
    static int x = 5;
    static {
        System.out.println("Parent12 static block");
    }
}

class Child12 extends Parent12 {
    private static final Object obj;
//    static int x = 7;
    static {
        x = 6;
        System.out.println("Child12 static block");
    }

    static {
        obj = null;
    }

    Child12(Object obj) {
//        this.obj = obj;
    }
}
class D extends Child12 {
    D(Object obj) {
        super(obj);
    }
}