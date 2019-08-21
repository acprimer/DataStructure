package shengsiyuan;

public class L8Test {
    public static void main(String[] args) {
        Singleton singleton = Singleton.getInstance();
        System.out.println("counter1: " + Singleton.counter1);
        System.out.println("counter2: " + Singleton.counter2);
    }
}

class Singleton {
    public static int counter1;
    private static Singleton singleton = new Singleton();
    public static int counter2 = 0;

    private Singleton() {
        counter1++;
        counter2++;  // 准备阶段的重要意义
        System.out.println("init counter1: " + counter1);
        System.out.println("init counter2: " + counter2);
    }

    public static Singleton getInstance() {
        return singleton;
    }
}