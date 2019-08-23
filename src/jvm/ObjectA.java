package jvm;

/**
 * Created by yaodh on 2017/3/22.
 */
public class ObjectA {
    public int i1 = 10;
    String str = "Hello";
    byte b1 = 5;
    byte b2 = 9;
    private int i2 = 20;
    Object obj;
    byte b3 = 7;
    final int myFinalInt = 99;

    public int getI2() {
        return i2;
    }

    public int demo(int x, Object o) {
        int y = x;
        long z = Long.MAX_VALUE - 1;
        long p = Long.MAX_VALUE - 2;
        long q = Long.MAX_VALUE - 3;
        while (y -- > 0) {
            int k = x * 3;
        }
        return y;
    }

    public int getMyFinalInt() {
        return myFinalInt;
    }
}
