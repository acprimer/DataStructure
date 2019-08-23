package shengsiyuan;

public class L10Test {
    public static void main(String[] args) {
//        System.out.println(MyInterfaceExt.obj2);
        MyExt myExt = new MyExt();
        myExt.fun();
        System.out.println(MyExt.x);
    }
}

class MyObject {
    static {
        System.out.println("MyObject static block");
    }
}

class MyObjectBase {
    static {
        System.out.println("MyObjectBase static block");
    }
}

interface MyInterface {
    Object obj = new MyObjectBase();
}

interface MyInterfaceExt extends MyInterface {
    Object obj2 = new Object() {
        {
            System.out.println("MyInterfaceExt init block");
        }
    };
    int x = 5;
    void fun();
}

class MyExt implements MyInterfaceExt {
    static {
        System.out.println("MyExt static block");
    }

    @Override
    public void fun() {
        System.out.println("fun in MyExt");
    }
}